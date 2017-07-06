package com.creditharmony.fortune.deduct.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.FileCopyUtils;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.deduct.entity.PlatformBankEntity;
import com.creditharmony.core.deduct.service.PlatformBankService;
import com.creditharmony.core.deduct.type.DeductFlagType;
import com.creditharmony.core.fortune.type.DeductPlat;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.fortune.common.ExportType;
import com.creditharmony.fortune.utils.FileZip;
import com.google.common.collect.Lists;

/**
 * 线下划扣拆分工具类
 * @Class Name DeductExportExcelUtil
 * @author 韩龙
 * @Create In 2016年3月2日
 */
public class DeductSplitExcelUtil {
	
	// 导出笔数
	private int currentSplitNum;
	// 当前平台
	private String currentDeductPlatId;
	// 文件序号
	private int fileIndex = 1; 
	// 文件名字
	private String flieName;
	// 输出流
	private OutputStream out;
	// 文件流集合
	private Map<String,InputStream> mapFile;
	
	private HttpServletResponse response;
	// 导出类型
	private String exportType;
	
	/**
	 * 获取platformRuleDao对象
	 */
	private static PlatformBankService platformBankService = SpringContextHolder.getBean(PlatformBankService.class);

	protected Logger logger = LoggerFactory.getLogger(DeductSplitExcelUtil.class);
	
	/**
	 * 构造方法
	 * @param response
	 * @param fileName
	 */
	public DeductSplitExcelUtil(HttpServletResponse response,String exportType,String fileName,String deductPlatId){
		this.flieName = fileName;
		this.currentDeductPlatId = deductPlatId;
		this.currentSplitNum = Constant.splitNum.get(deductPlatId);
		this.response = response;
		this.exportType = exportType;
		mapFile = new HashMap<String,InputStream>();
	}
	
	/**
	 * 文件名
	 * 2016年2月20日
	 * By 韩龙
	 * @return
	 */
	public String getFileName(){
		if(currentDeductPlatId.equals(DeductPlat.ZJPT.value)){
			logger.debug("线下划扣中金导出文件名：" + flieName + StringUtils.doNumFormat(fileIndex, "0000"));
			return flieName + StringUtils.doNumFormat(fileIndex, "0000");
		}else if(currentDeductPlatId.equals(DeductPlat.FYPT.value)){
			logger.debug("线下划扣富友导出文件名：" + flieName + StringUtils.doNumFormat(fileIndex, "_0000"));
			return flieName + StringUtils.doNumFormat(fileIndex, "_0000");
		}
		logger.debug("线下划扣导出文件名：" + flieName + StringUtils.doNumFormat(fileIndex, "_00000"));
		return flieName + StringUtils.doNumFormat(fileIndex, "_00000");
	}
	
	/**
	 * 数据拆分
	 * 2016年2月17日
	 * By 韩龙
	 * @param response
	 * @throws IOException 
	 */
	public void split(List<DeductExportExcel> dateList) throws IOException{
//		getFileName();
//		boolean b1 = true;
		// 是否有记录
		if (!ArrayHelper.isNotEmpty(dateList)) {
			return;
		}
		List<DeductExportExcel> newList = Lists.newArrayList();
		int index = 1;
		// 总金额
		BigDecimal sumMoney = new BigDecimal(0);
		for (DeductExportExcel deductExportExcel : dateList) {
			PlatformBankEntity pfr = new PlatformBankEntity(); 
			pfr.setBankId(deductExportExcel.getAccountBank());
			pfr.setPlatformId(currentDeductPlatId);
			pfr.setDeductFlag(DeductFlagType.COLLECTION.getCode());
			pfr.setSysId(String.valueOf(SystemFlag.FORTUNE.value));
			pfr = platformBankService.getPlatformBank(pfr);
//			PlatformRuleCache.getBatch(currentDeductPlatId,
//					deductExportExcel.getAccountBank());
			// 金額
			BigDecimal applyLendMoney = new BigDecimal(deductExportExcel.getDeductMoney());
			if(pfr.getSingleLimitMoney() == null){
				logger.debug("线下划扣导出失败出借编号为" + deductExportExcel.getLendCode() +
						";失败原因为限额配置表对应平台没有相应的银行信息。");
				continue;
			}
			// 单笔限额
			BigDecimal singleLimitMoney = new BigDecimal(pfr.getSingleLimitMoney()).divide(new BigDecimal(100));
			// 单日限额
			BigDecimal dayLimitMoney = new BigDecimal(pfr.getDayLimitMoney()).divide(new BigDecimal(100));
			if(singleLimitMoney.compareTo(new BigDecimal(0)) == 0){
				logger.debug("线下划扣导出失败出借编号为" + deductExportExcel.getLendCode() +
						";失败原因银行单笔限额为0。");
				continue;
			}
			if (applyLendMoney.compareTo(dayLimitMoney) == 1) {
				logger.debug("线下划扣导出失败出借编号为" + deductExportExcel.getLendCode() +
						";失败原因出借金额大于单日限额。");
				continue;
			} else {
				int reIndex = 0;
				BigDecimal [] big = applyLendMoney.divideAndRemainder(singleLimitMoney);
				if(big[1].intValue() == 0){
					reIndex = index + big[0].intValue();
				}else{
					reIndex = index + big[0].intValue()+1;
				}
				// 判断导出数据不能大于500条导出excel
				if(reIndex >= currentSplitNum && index != 1){
//					b1 = false;
					index = 1;
//					reNewList.add(newList);
					String reFileName = getFileName() + getExtension();
					DeductExportUtil deu = new DeductExportUtil();
					deu.outExcel(newList, currentDeductPlatId,exportType,reFileName, mapFile);
					fileIndex ++;
					newList = Lists.newArrayList();
					sumMoney = new BigDecimal(0);
				}
				// 导出总金额
				sumMoney = sumMoney.add(applyLendMoney);
				if (singleLimitMoney.compareTo(applyLendMoney) == -1) {
//					b1 = true;
					// 拆分金额
					do {
						// 判断导出数据不能大于500条导出excel
						if(index >= currentSplitNum){
//							b1 = false;
							index = 1;
//							reNewList.add(newList);
							String reFileName = getFileName() + getExtension();
							DeductExportUtil deu = new DeductExportUtil();
							deu.outExcel(newList, currentDeductPlatId,exportType, reFileName, mapFile);
							fileIndex ++;
							newList = Lists.newArrayList();
							sumMoney = new BigDecimal(0);
						}
						DeductExportExcel tem = new DeductExportExcel();
						BeanUtils.copyProperties(deductExportExcel, tem);
						tem.setIndex(index+"");
						// 如果金额小于单笔限额退出拆分
						if (applyLendMoney.compareTo(singleLimitMoney) == -1 ) {
							tem.setDeductMoney(applyLendMoney.toString());
							newList.add(tem);
							String reFileName = getFileName() + getExtension();
							DeductExportUtil deu = new DeductExportUtil();
							deu.outExcel(newList, currentDeductPlatId, exportType,reFileName, mapFile);
							break;
						}
						// 设置拆分金额
						tem.setDeductMoney(singleLimitMoney.toString());
						tem.setIndex(index+"");
						newList.add(tem);
						applyLendMoney = applyLendMoney.subtract(singleLimitMoney) ;
						index++;

					} while (true);
					continue;
				}
				newList.add(deductExportExcel);
				String reFileName = getFileName() + getExtension();
				DeductExportUtil deu = new DeductExportUtil();
				deu.outExcel(newList, currentDeductPlatId,exportType,reFileName, mapFile);
			}
		}
		// 推送到客户端
		download();
	}
	
	/**
	 * 下载
	 * 2016年3月29日
	 * By 韩龙
	 */
	public void download() {
		// 下载到客户端
		if (mapFile.size() > 1) {
			try {
				// 设置Header信息
				response.setHeader("Content-Disposition",
						"attachment; filename=" + flieName + ".zip"
								+ ";filename*=UTF-8''" + flieName + ".zip");
				out = response.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			FileZip.zipFiles(out, mapFile);
		} else {
			try {
				flieName = getFileName();
				// 设置Header信息
				response.setHeader("Content-Disposition",
						"attachment; filename=" + Encodes.urlEncode(flieName)
								+ getExtension() + ";filename*=UTF-8''"
								+ Encodes.urlEncode(flieName) + getExtension());
				out = response.getOutputStream();
				for (String key : mapFile.keySet()) {
					FileCopyUtils.copy(mapFile.get(key), out);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 根据类型判断导出类型
	 * 2016年3月4日
	 * By 韩龙
	 * @return
	 */
	private String getExtension(){
		String str = "";
		// 富有、通联只能导出excel
		if(currentDeductPlatId.equals(DeductPlat.FYPT.value) || currentDeductPlatId.equals(DeductPlat.TL.value)){
			str = ".xlsx";
		}else{
			// 好易联、中金可以导出excel与txt
			if(exportType.equals(ExportType.XLSX.getCode())){
				str = ".xlsx";
			}else{
				str = ".txt";
			}
		}
		return str;
	}
}

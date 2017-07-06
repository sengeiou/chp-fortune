package com.creditharmony.fortune.common.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.common.type.EffectiveFlag;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.utils.FileUtil;
import com.creditharmony.fortune.utils.ZipCompressor;

@Controller
@RequestMapping("${adminPath}/common/file/")
public class FileController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
	protected AttachmentManager attachmentService;
    
    
    
    /**
     * 2016年3月8日
     * By 刘雄武
     * 文件下载
     * @param response
     * @param attaId 出借编号等按照各自不同业务
     * @param type 预览:preview 下载:download
     * @throws IOException
     */
	@RequestMapping(value="/download/{attaId}/{type}", method = RequestMethod.GET)
	public void fileDownload(HttpServletResponse response, @PathVariable("attaId") String attaId, @PathVariable("type") String type) throws IOException {
		
		// 根据附件ID检索附件信息
		Attachment seletive = new Attachment();
		seletive.setAttaId(attaId.toString());
		seletive = attachmentService.get(seletive);
		if(type.equals("preview")){
			getImg(response,attaId);
		}else{
			String attaFilepath = seletive.getAttaFilepath();
			if (attaFilepath.startsWith("{")&&attaFilepath.endsWith("}")) {
				FileUtil.fileDownloadby(response, seletive, type);
			}else{
				FileUtil.downFileToNetdisk(response,attaFilepath,seletive.getAttaFilename());
			}
		}
	}
	
	/**
     * 
     */
	@RequestMapping(value="/downloaddjr", method = RequestMethod.GET)
	public void downloaddjr(HttpServletResponse response) throws IOException {
		ZipCompressor.compress(response, "授权委托书（充值授权信和财富）.pdf", "授权委托书（开户授权信和上融）新.pdf");
	}

	/**
	 * 
	 * 2016年3月8日
	 * By 韩龙
	 * @param response
	 * @param attaId
	 * @throws IOException
	 */
	public void getImg(HttpServletResponse response, String attaId)
			throws IOException {
		// 根据附件ID检索附件信息
		Attachment seletive = new Attachment();
		seletive.setAttaId(attaId.toString());
		seletive = attachmentService.get(seletive);
		if (seletive != null) {
			String fileName = seletive.getAttaFilename();
			if (StringUtils.isNotBlank(fileName)) {
				if (fileName.endsWith("txt")) {
					response.setHeader("Content-type",
							"text/html;charset=GB2312");
				}
			}
			try {
				OutputStream os = response.getOutputStream();
				String attaFilepath = seletive.getAttaFilepath();
				if (attaFilepath.startsWith("{") && attaFilepath.endsWith("}")) {
					DmService dmService = DmService.getInstance();
					dmService.download(os, seletive.getAttaFilepath());
				} else {
					InputStream in = new FileInputStream(attaFilepath);
					OutputStream out = response.getOutputStream();
					com.creditharmony.dm.file.util.FileUtil.writeFile(out, in);
				}
				os.flush();
				os.close();
				os = null;
				response.flushBuffer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Ajax方式附件保存
	 * 2016年1月31日
	 * By 朱杰
	 * @param file 上传的文件
	 * @param map
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value="/uploadAjax", method = RequestMethod.POST)
	@ResponseBody
	public String fileUploadAjax(MultipartFile file, String customerFile, Attachment atta) throws IllegalStateException, IOException {
		// 文件保存
		Attachment attachmen= FileUtil.saveFiles(file, customerFile);
		if(StringUtils.isNotEmpty(attachmen.getAttaFilepath())){
			if (atta != null) {
				attachmen.setAttaFileOwner(atta.getAttaFileOwner());
				attachmen.setLoanCode(atta.getLoanCode());
			}
			// 整形
			attachmen.setDictAttaFlag(FileConstants.FILE_TYPE_FJ);// 文件类型
			attachmen.setAttaId(IdGen.uuid());//ID
			attachmen.preInsert();
			logger.info("上传文件,id:" + attachmen.getAttaId());
			attachmentService.saveFile(attachmen);
		}		
		return JsonMapper.toJsonString(attachmen);
	}

	/**
	 * 获取文件
	 * 2016年2月1日
	 * By 朱杰
	 * @param code
	 * @param tableId
	 * @param tableName
	 * @return
	 */
	@RequestMapping(value="/getAttachmentAjax", method = RequestMethod.POST)
	@ResponseBody
	public String getAttachmentList( @RequestParam("code") String code, @RequestParam("tableId") String tableId, @RequestParam("tableName") String tableName){
		Attachment attachment = new Attachment();
		attachment.setLoanCode(code);
		attachment.setAttaTableId(tableId);
		attachment.setAttaFileOwner(tableName);
		attachment.setIsDiscard(EffectiveFlag.yx.value);
		List<Attachment> list = attachmentService.findList(attachment);
		
		return jsonMapper.toJson(list);
	}

	@RequestMapping(value = "/removeAjax", method = RequestMethod.POST)
	@ResponseBody
	public String removeAjax(Attachment attachment) {
		attachmentService.deleteById(attachment);
		return jsonMapper.toJson("success");
	}
	
	/**
	 * 用于审批时附件预览功能
	 * 2016年3月23日
	 * By 刘雄武
	 * @param model
	 * @param redirectAttributes
	 * @param response
	 * @param attachment
	 * @return
	 */
	@RequestMapping(value = { "preview"})
	public String preview(Model model,RedirectAttributes redirectAttributes, HttpServletResponse response,Attachment attachment) {

		Attachment seletive = attachmentService.get(attachment);
		Attachment seletiveReview = new Attachment();
		seletiveReview.setAttaFileOwner(seletive.getAttaFileOwner());
		seletiveReview.setAttaTableId(seletive.getAttaTableId());
		seletiveReview.setLoanCode(seletive.getLoanCode());
		List<Attachment> list = attachmentService.findList(seletiveReview);
		List<Attachment> listtwo = new ArrayList<Attachment>();
		for (Attachment a : list) {
			String str = a.getAttaFilename().substring(a.getAttaFilename().lastIndexOf(".") + 1);
			if(str.equals("jpg")||str.equals("jpeg")||str.equals("png")||str.equals("gif")||str.equals("pdf")||str.equals("txt")||str.equals("html")){
				if(!seletive.getAttaId().equals(a.getAttaId())){
					listtwo.add(a);
				}				
			}
		}
		model.addAttribute("list", listtwo).addAttribute("seletive", seletive);
		return "/include/preview";
	}
	
	/**
	 * 预览reportServer上的文件
	 * 2016年3月29日
	 * By 郭才林
	 * @param model
	 * @param type
	 * @param str
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping({ "reportServerfilePreview"})
	public void reportServerfilePreview(Model model,String type,String str, HttpServletResponse response) {
							
		String url=	com.creditharmony.fortune.deduct.common.Constant.getProperties.get("template_cpt_url");
		String typeStr=com.creditharmony.fortune.deduct.common.Constant.getProperties.get(type);
		url=url+typeStr+"&"+str.replaceAll("-","&");
		OutputStream os;
		HttpURLConnection httpConn = null;  
		try {
			os = response.getOutputStream();
			URL uri = new URL(url);
			httpConn = (HttpURLConnection) uri.openConnection();  
			httpConn.setConnectTimeout(15 * 1000);// 联通最长时间
			httpConn.setReadTimeout( 60 * 1000); // 获取数据最长时间
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("connection", "close");
			httpConn.setRequestProperty("Charsert", "UTF-8");
			InputStream in = httpConn.getInputStream();  
			com.creditharmony.dm.file.util.FileUtil.writeFile(os, in);
			os.flush();  
			os.close();  
			os = null;  
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
			if(httpConn!=null){
				httpConn.disconnect();
			}
			
		}
		
	}
}

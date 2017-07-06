package com.creditharmony.fortune.delivery.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.delivery.constant.Constant;
import com.creditharmony.fortune.delivery.dao.TripleCustomerDeliveryDao;
import com.creditharmony.fortune.delivery.service.TripleCustomerDeliveryManager;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;

/**
 * @Class Name TripleCustomerDeliveryFacade
 * @author 胡体勇
 * @Create In 2016年4月28日
 */
@Service
public class TripleCustomerDeliveryFacade extends CoreManager<TripleCustomerDeliveryDao,IntDeliveryEx> {
	
	// 日志
    protected Logger logger = LoggerFactory.getLogger(TripleCustomerDeliveryFacade.class);
	@Autowired
	private TripleCustomerDeliveryManager manager;
	
	/**
	 * 三网客户批量交割 2016年2月23日 By 胡体勇
	 * 
	 * @param code
	 * @param endCustomerCodeList
	 * @return
	 */
	public Map<String, Object> batchTripleCustomerDelivery(String code, String num, String userName, List<String> endCustomerCodeList) {
		int result = 0;
		// 解析客户信息
		String[] codes = code.split(";");
		for (int i = 0; i < codes.length; i++) {
			// 分离信息
			String[] str = codes[i].split(",", Constant.ARRAY_SIZE_TEN);
			// 手机号
			String phone = str[1];
			// 旧理财经理
			String empCode = str[2];
			// 新理财经理
			String newEmpCode = str[3];
			// 客户登录名
			String loginName = str[4];
			// 系统类型
			String osType = str[5];
			// 客户编号
			String custCode = str[8];
			try {
				// 修改手机号对应的理财经理
				int a = manager.upadateEmpCodeById(phone, newEmpCode, "", userName, endCustomerCodeList);
				if (a > 0) {
					result++;
				}
			} catch (Exception e) {
				logger.error("手机号为：" + phone + "的客户交割失败！", e);
				IntDeliveryEx ex = new IntDeliveryEx();
				ex.setRemark("交割异常，该客户请手动交割！");
				ex.setLoginName(loginName);
				ex.setEmpCode(empCode);
				ex.setCustomerCode(custCode);
				ex.setNewEmpCode(newEmpCode);
				ex.setBatchNumber(num);
				ex.setOsType(osType);
				List<IntDeliveryEx> empInfo = manager.findStopEmpInfoByCode(empCode);
				if (empInfo.size() > 0) {
					ex.setEmpName(empInfo.get(0).getNewEmpName());
					ex.setOrgName(empInfo.get(0).getNewOrgName());
				}
				List<IntDeliveryEx> newEmpInfo = manager.findEmpInfoByCode(newEmpCode);
				if (newEmpInfo.size() > 0) {
					ex.setNewEmpName(newEmpInfo.get(0).getNewEmpName());
				}
				manager.insertErrorInfo(ex);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}

	/**
	 * 三网理财经理客户交割
	 * 2016年2月25日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	public int tripleManagerCustomerDelivery(IntDeliveryEx intDeliveryEx){
		try {
			int n = manager.tripleManagerCustomerDelivery(intDeliveryEx);
			return n;
		} catch (Exception e) {
			this.logger.error("三网理财经理客户交割异常", e);
			return 0;
		}
	}

	/**
	 * 三网理财经理业绩交割 
	 * 2016年2月25日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	public int tripleManagerAchievementDelivery(IntDeliveryEx intDeliveryEx){
		try {
			int a = manager.tripleManagerAchievementDelivery(intDeliveryEx);
			return a;
		} catch (Exception e) {
			this.logger.error("三网理财经理业绩交割异常！", e);
			return 0;
		}
		
	}
	
	/**
	 * 检查导入excel里客户的信息
	 * 2016年3月1日
	 * By 胡体勇
	 * @param list
	 * @return
	 */
	public IntDeliveryEx checkExcelInfo(IntDeliveryEx ide,String num){
		IntDeliveryEx result = new IntDeliveryEx();
		  if(ide != null){
			  if(!StringUtils.isEmpty(ide.getLoginName())){
					// 判断客户编号是否存在
					if(!StringUtils.isEmpty(ide.getCustomerCode())){
						// 根据客户编号查询客户信息
						IntPhone intPhone = new IntPhone();
						intPhone.setCustomerCode(ide.getCustomerCode());
						IntPhone in = super.dao.findInfoByCustCode(intPhone);
						if(in != null){
							// 判断客户编号对应的客户姓名是否和表中一致
						if (ide.getLoginName().trim().equals(in.getLoginName().trim())) {
								if(!StringUtils.isEmpty(in.getEmpCode())){
									// 客户编号对应的客户姓名和需要导入的一致,验证客户对应的理财经理是否存在
									if(!StringUtils.isEmpty(ide.getEmpCode())){
				                        	// 判断理财经理是否一致
				                        	if(ide.getEmpCode().trim().equals(in.getEmpCode().trim())){
				                        		// 系统类型不为空
				                        		if(!StringUtils.isEmpty(ide.getOsType())){
				                        			// 判断系统类型是否一致
					                        		if(ide.getOsType().trim().equals(in.getOsType().trim())){
					                        			// 查询新理财经理对应信息
						                    			List<IntDeliveryEx> newEmpInfo = manager.findEmpInfoByCode(ide.getNewEmpCode());
						                    			// 验证新理财经理是否是信合内部理财经理
						                				if(newEmpInfo.size() == 0){
						            						IntDeliveryEx ex = ide;
						            						ex.setRemark("理财经理工号（新）错误！");
						            						ex.setBatchNumber(num);
						            						manager.insertErrorInfo(ex);
						            						result = ex;
						                				}else{
						                					if(StringUtils.isEmpty(ide.getNewEmpName())){
							            						IntDeliveryEx ex = ide;
							            						ex.setRemark("理财经理（新）错误！");
							            						ex.setBatchNumber(num);
							            						manager.insertErrorInfo(ex);
							            						result = ex;
						                					}else if(!ide.getNewEmpName().trim().equals(newEmpInfo.get(0).getNewEmpName().trim())){
							            						IntDeliveryEx ex = ide;
							            						ex.setRemark("理财经理（新）错误！");
							            						ex.setBatchNumber(num);
							            						manager.insertErrorInfo(ex);
							            						result = ex;
						                					}else{
							            						IntDeliveryEx ex = ide;
							            						ex.setPhone(in.getPhone());
							            						ex.setRemark(Constant.OK);
							            						ex.setBatchNumber(num);
							            						result = ex;
						                					}
						                				}
					                        		}else{
					            						IntDeliveryEx ex = ide;
					            						ex.setRemark("系统类型错误！");
					            						ex.setBatchNumber(num);
					            						manager.insertErrorInfo(ex);
					            						result = ex;
					                        		}
				                        		}else{
				                        			// 理财经理信息插入错误信息到交割表
				            						IntDeliveryEx ex = ide;
				            						ex.setRemark("系统类型错误！");
				            						ex.setBatchNumber(num);
				            						manager.insertErrorInfo(ex);
				            						result = ex;
				                        		}
				                        	}else{
				                        		// 理财经理信息不一致
				                        		IntDeliveryEx ex = ide;
				        						ex.setRemark("理财经理工号错误！");
				        						ex.setBatchNumber(num);
				        						manager.insertErrorInfo(ex);
				        						result = ex;
				                        	}
									}else{
										// 理财经理信息插入错误信息到交割表
										IntDeliveryEx ex = ide;
										ex.setRemark("理财经理工号错误！");
										ex.setBatchNumber(num);
										manager.insertErrorInfo(ex);
										result = ex;
									}
								}else{
									// 客户编号对应的客户姓名和需要导入的一致,验证客户对应的理财经理是否存在
									if(ide.getEmpCode() != null || !"".equals(ide.getEmpCode())){
		                        		// 系统类型不为空
		                        		if(!StringUtils.isEmpty(ide.getOsType())){
		                        			// 判断系统类型是否一致
			                        		if(ide.getOsType().trim().equals(in.getOsType().trim())){
			                        			// 查询新理财经理对应信息
				                    			List<IntDeliveryEx> newEmpInfo = manager.findEmpInfoByCode(ide.getNewEmpCode());
				                    			// 验证新理财经理是否是信合内部理财经理
				                				if(newEmpInfo.size() == 0){
				            						IntDeliveryEx ex = ide;
				            						ex.setRemark("理财经理工号（新）错误！");
				            						ex.setBatchNumber(num);
				            						manager.insertErrorInfo(ex);
				            						result = ex;
				                				}else{
				                					if(StringUtils.isEmpty(ide.getNewEmpName())){
					            						IntDeliveryEx ex = ide;
					            						ex.setRemark("理财经理（新）错误！");
					            						ex.setBatchNumber(num);
					            						manager.insertErrorInfo(ex);
					            						result = ex;
				                					}else if(!ide.getNewEmpName().trim().equals(newEmpInfo.get(0).getNewEmpName().trim())){
					            						IntDeliveryEx ex = ide;
					            						ex.setRemark("理财经理（新）错误！");
					            						ex.setBatchNumber(num);
					            						manager.insertErrorInfo(ex);
					            						result = ex;
				                					}else{
					            						IntDeliveryEx ex = ide;
					            						ex.setPhone(in.getPhone());
					            						ex.setRemark(Constant.OK);
					            						ex.setBatchNumber(num);
					            						result = ex;
				                					}
				                				}
			                        		}else{
			            						IntDeliveryEx ex = ide;
			            						ex.setRemark("系统类型错误！");
			            						ex.setBatchNumber(num);
			            						manager.insertErrorInfo(ex);
			            						result = ex;
			                        		}
		                        		}else{
		                        			// 理财经理信息插入错误信息到交割表
		            						IntDeliveryEx ex = ide;
		            						ex.setRemark("系统类型错误！");
		            						ex.setBatchNumber(num);
		            						manager.insertErrorInfo(ex);
		            						result = ex;
		                        		}
		                        	}else{
		                        		// 理财经理信息不一致
		                        		IntDeliveryEx ex = ide;
		        						ex.setRemark("理财经理工号错误！");
		        						ex.setBatchNumber(num);
		        						manager.insertErrorInfo(ex);
		        						result = ex;
		                        	}
								}
								
							}else{
								// 客户编号对应的客户姓名和需要导入的不一致，插入错误信息到交割表
								IntDeliveryEx ex = ide;
								ex.setRemark("客户姓名错误！");
								ex.setBatchNumber(num);
								manager.insertErrorInfo(ex);
								result = ex;
							}
						}else{
							// 客户编号对应的客户姓名和需要导入的不一致，插入错误信息到交割表
							IntDeliveryEx ex = ide;
							ex.setRemark("客户编号错误！");
							ex.setBatchNumber(num);
							manager.insertErrorInfo(ex);
							result = ex;
						}
						
					}else{
							if(StringUtils.isEmpty(ide.getOsType())){
								IntDeliveryEx ex = ide;
								ex.setRemark("系统类型错误！");
								ex.setBatchNumber(num);
								manager.insertErrorInfo(ex);
								result = ex;
							}else{
								// 根据客户姓名和系统类型查询客户信息
								IntPhone p = new IntPhone();
								p.setLoginName(ide.getLoginName());
								p.setOsType(ide.getOsType());
								p.setEmpCode(ide.getEmpCode());
			                    List<IntPhone> phoneList = super.dao.findPhoneList(p);
			                    if(phoneList.size() == 1){
			                    	// 当手机号有理财经理时去判断上传的理财经理是否为空
			                    	if(!StringUtils.isEmpty(phoneList.get(0).getEmpCode())){
			                    		if(!StringUtils.isEmpty(ide.getEmpCode())){
			                    			// 判断上传的理财经理是否和查询的手机号对应的理财经理是否一致
			                    			if(phoneList.get(0).getEmpCode().trim().equals(ide.getEmpCode().trim())){
					                    		// 查询新理财经理对应信息
					            				List<IntDeliveryEx> newEmpInfo = manager.findEmpInfoByCode(ide.getNewEmpCode());
					            				// 验证新理财经理是否是信合内部理财经理
					            				if(newEmpInfo.size() == 0){
					            					// 理财经理信息插入错误信息到交割表
					        						IntDeliveryEx ex = ide;
					        						ex.setRemark("理财经理工号（新）错误！");
					        						ex.setBatchNumber(num);
					        						manager.insertErrorInfo(ex);
					        						result = ex;
					            				}else{
						            					if(StringUtils.isEmpty(ide.getNewEmpName())){
						            						// 理财经理信息插入错误信息到交割表
						            						IntDeliveryEx ex = ide;
						            						ex.setRemark("理财经理（新）错误！");
						            						ex.setBatchNumber(num);
						            						manager.insertErrorInfo(ex);
						            						result = ex;
						            					}else if(!ide.getNewEmpName().trim().equals(newEmpInfo.get(0).getNewEmpName().trim())){
					                						// 理财经理信息插入错误信息到交割表
						            						IntDeliveryEx ex = ide;
						            						ex.setRemark("理财经理（新）错误！");
						            						ex.setBatchNumber(num);
						            						manager.insertErrorInfo(ex);
						            						result = ex;
					                					}else{
						            						// 理财经理信息插入错误信息到交割表
						            						IntDeliveryEx ex = ide;
						            						ex.setRemark(Constant.OK);
						            						ex.setPhone(phoneList.get(0).getPhone());
						            						ex.setBatchNumber(num);
						            						result = ex;
						            					}
					            				}
					                    	}else{
					                    		// 理财经理信息插入错误信息到交割表
					    						IntDeliveryEx ex = ide;
					    						ex.setRemark("理财经理工号错误！");
					    						ex.setBatchNumber(num);
					    						manager.insertErrorInfo(ex);
					    						result = ex;
					                    	}
			                    		}else{
			                    			// 理财经理信息插入错误信息到交割表
			    							IntDeliveryEx ex = ide;
			    							ex.setRemark("理财经理工号错误！");
			    							ex.setBatchNumber(num);
			    							manager.insertErrorInfo(ex);
			    							result = ex;
			                    		}
			                    	}else{
			                    		if(ide.getEmpCode() != null || !"".equals(ide.getEmpCode())){
					                    		// 查询新理财经理对应信息
					            				List<IntDeliveryEx> newEmpInfo = manager.findEmpInfoByCode(ide.getNewEmpCode());
					            				// 验证新理财经理是否是信合内部理财经理
					            				if(newEmpInfo.size() == 0){
					            					// 理财经理信息插入错误信息到交割表
					        						IntDeliveryEx ex = ide;
					        						ex.setRemark("理财经理工号（新）错误！");
					        						ex.setBatchNumber(num);
					        						manager.insertErrorInfo(ex);
					        						result = ex;
					            				}else{
						            					if(StringUtils.isEmpty(ide.getNewEmpName())){
						            						// 理财经理信息插入错误信息到交割表
						            						IntDeliveryEx ex = ide;
						            						ex.setRemark("理财经理（新）错误！");
						            						ex.setBatchNumber(num);
						            						manager.insertErrorInfo(ex);
						            						result = ex;
						            					}else if(!ide.getNewEmpName().trim().equals(newEmpInfo.get(0).getNewEmpName().trim())){
					                						// 理财经理信息插入错误信息到交割表
						            						IntDeliveryEx ex = ide;
						            						ex.setRemark("理财经理（新）错误！");
						            						ex.setBatchNumber(num);
						            						manager.insertErrorInfo(ex);
						            						result = ex;
					                					}else{
						            						// 理财经理信息插入错误信息到交割表
						            						IntDeliveryEx ex = ide;
						            						ex.setRemark(Constant.OK);
						            						ex.setPhone(phoneList.get(0).getPhone());
						            						ex.setBatchNumber(num);
						            						result = ex;
						            					}
					            				}
					                    	}else{
					                    		// 理财经理信息插入错误信息到交割表
					    						IntDeliveryEx ex = ide;
					    						ex.setRemark("理财经理工号错误！");
					    						ex.setBatchNumber(num);
					    						manager.insertErrorInfo(ex);
					    						result = ex;
					                    	}
			                    	}
			                    	
			                    }else if(phoneList.size() > 1){
			                    	// 理财经理信息插入错误信息到交割表
									IntDeliveryEx ex = ide;
									ex.setRemark("该客户请手动交割！");
									ex.setBatchNumber(num);
									manager.insertErrorInfo(ex);
									result = ex;
			                    }else if(phoneList.size() == 0){
			                    	// 理财经理信息插入错误信息到交割表
									IntDeliveryEx ex = ide;
									ex.setRemark("客户姓名/系统类型/理财经理工号错误");
									ex.setBatchNumber(num);
									manager.insertErrorInfo(ex);
									result = ex;
			                    }
							}
					}
			     }else{
			    	// 理财经理信息插入错误信息到交割表
					IntDeliveryEx ex = ide;
					ex.setRemark("客户姓名错误！");
					ex.setBatchNumber(num);
					manager.insertErrorInfo(ex);
					result = ex;
			     }
		  }
		return result;
	}
	
	/**
	 * 转赠调三网插入交割历史表
	 * 2016年6月28日
	 * By 胡体勇
	 * @param phone
	 * @param newEmpCode
	 * @param flag
	 */
	public void upadateEmpCodeById(String phone,String newEmpCode,String flag){
		manager.upadateEmpCodeById(phone, newEmpCode, flag, "", null);
	}
	
	/**
	 * 子页面客户批量交割
	 * 2016年3月1日
	 * By 胡体勇
	 * @param code
	 * @return
	 */
	/*
	public int childPageTripleCustomerDelivery(String code){
		String info = "";
		String infoCode = "";
		// 提取交割信息
		String[] codes = code.split(";");
		for(int i = 0;i<codes.length;i++){
			// 分离交割信息：批次号，客户编号，客户姓名，系统类型，理财经理工号，新理财经理工号
			String[] str = codes[i].split(",",Constant.ARRAY_SIZE_SIX);
			String phone = str[1]; // 手机号
			String custName = str[2]; // 客户姓名
			String osType = str[3]; // 系统类型
			String empCode = str[4]; // 理财经理工号
			String newEmpCode = str[5]; // 新理财经理工号
			String orderStatus = "";// 成单状态
			String cardId = "";
			String cardType = "";
			String verTime = "";
			String num = "";

		    info = orderStatus+","+phone+","+empCode+","+newEmpCode+","+custName+","+osType+","+cardType+","+cardId+","+verTime+","+num;
		    infoCode = infoCode + info +";";
		}
		Map<String,Object> map = this.batchTripleCustomerDelivery(infoCode);
		int result = (int) map.get("result");
		return result;
	}*/
}

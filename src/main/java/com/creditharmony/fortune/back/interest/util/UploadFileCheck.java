package com.creditharmony.fortune.back.interest.util;

import org.apache.commons.lang.StringUtils;

import com.creditharmony.fortune.template.entity.backInterest.ChinaFinanceUpload;
import com.creditharmony.fortune.template.entity.backInterest.CommunicationsUploadFirst;
import com.creditharmony.fortune.template.entity.backInterest.FuYouUpload;
import com.creditharmony.fortune.template.entity.backInterest.GoldAccountUpload;
import com.creditharmony.fortune.template.entity.backInterest.NetBankUpload;
import com.creditharmony.fortune.template.entity.backInterest.UploadIsInterest;
import com.creditharmony.fortune.template.entity.backInterest.UploadMoney;
import com.creditharmony.fortune.template.entity.backInterest.UploadReturnBackMoney;

/**
 * 回息上传文件校验
 * @Class Name UploadFileCheck 
 * @author 李志伟
 * @Create In 2016年5月19日
 */
public class UploadFileCheck {

	/**
	 * 上传回息金额文件校验
	 * 2016年5月19日
	 * by 李志伟
	 * @return
	 */
	public static boolean uploadMoneyFileCheck(UploadMoney um){
		if(StringUtils.isEmpty(um.getApplyPay())){
			if(null == um.getApplyLendMoney()){
				if(StringUtils.isEmpty(um.getAccountName())){
					if(StringUtils.isEmpty(um.getAccountNo())){
						if(StringUtils.isEmpty(um.getAccountBank())){
							if(StringUtils.isEmpty(um.getBankName())){
								if(StringUtils.isEmpty(um.getAccountBranch())){
									if(StringUtils.isEmpty(um.getAccountCardOrBooklet())){
										if(StringUtils.isEmpty(um.getAccountAddrprovince())){
											if(StringUtils.isEmpty(um.getAccountAddrdistrict())){
												if(StringUtils.isEmpty(um.getProductName())){
													if(StringUtils.isEmpty(um.getLendCode())){
														if(StringUtils.isEmpty(um.getMemo())){
															if(null == um.getApplyLendDay()){
																if(null == um.getBackRealMoney()){
																	if(StringUtils.isEmpty(um.getBackiId())){
																		return true;
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 中金上传文件数据校验(扫除空对象)
	 * 2016年5月19日
	 * by 李志伟
	 * @return
	 */
	public static boolean zjUploadFileDataCheck(ChinaFinanceUpload cu){
		
		if(StringUtils.isEmpty(cu.getNo())){
			if(StringUtils.isEmpty(cu.getTradingTime())){
				if(StringUtils.isEmpty(cu.getOrgName())){
					if(StringUtils.isEmpty(cu.getBatchNo())){
						if(StringUtils.isEmpty(cu.getDetailNo())){
							if(StringUtils.isEmpty(cu.getBankId())){
								if(StringUtils.isEmpty(cu.getAccountType())){
									if(StringUtils.isEmpty(cu.getAccountNo())){
										if(StringUtils.isEmpty(cu.getAccountName())){
											if(StringUtils.isEmpty(cu.getAccountBranch())){
												if(StringUtils.isEmpty(cu.getProvince())){
													if(StringUtils.isEmpty(cu.getCity())){
														if(StringUtils.isEmpty(cu.getBackiId())){
															if(StringUtils.isEmpty(cu.getBankPayTime())){
																if(StringUtils.isEmpty(cu.getTradingStatus())){
																	if(StringUtils.isEmpty(cu.getBankCode())){
																		if(StringUtils.isEmpty(cu.getBankMessage())){
																			if(StringUtils.isEmpty(cu.getBackMoney())){
																				if(StringUtils.isEmpty(cu.getBackRealMoney())){
																					return true;
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 富友上传文件校验(扫除空对象)
	 * 2016年5月20日
	 * by 李志伟
	 * @param fyo
	 * @return
	 */
	public static boolean fyUploadFileDataCheck(FuYouUpload fyo){
		
		if(StringUtils.isEmpty(fyo.getTradeTime())){
			if(StringUtils.isEmpty(fyo.getTradeFlow())){
				if(StringUtils.isEmpty(fyo.getFileDetailNo())){
					if(StringUtils.isEmpty(fyo.getBusinessType())){
						if(StringUtils.isEmpty(fyo.getFileName())){
							if(StringUtils.isEmpty(fyo.getBank())){
								if(StringUtils.isEmpty(fyo.getAccountNo())){
									if(StringUtils.isEmpty(fyo.getAccountName())){
										if(StringUtils.isEmpty(fyo.getRemark())){
											if(StringUtils.isEmpty(fyo.getTradeStatus())){
												if(StringUtils.isEmpty(fyo.getBackRemark())){
													if(StringUtils.isEmpty(fyo.getSendBack())){
														if(StringUtils.isEmpty(fyo.getAmount())){
															if(StringUtils.isEmpty(fyo.getBackiId())){
																return true;
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 通联上传文件数据校验，扫除上传的空对象
	 * 2016年5月20日
	 * by 李志伟
	 * @return
	 */
	public static boolean tlUploadFileDataCheck(CommunicationsUploadFirst cuf){
		
		if(StringUtils.isEmpty(cuf.getFileNo())){
			if(StringUtils.isEmpty(cuf.getFileName())){
				if(StringUtils.isEmpty(cuf.getRecordNo())){
					if(StringUtils.isEmpty(cuf.getTransactionType())){
						if(StringUtils.isEmpty(cuf.getProcessingState())){
							if(StringUtils.isEmpty(cuf.getMerchant())){
								if(StringUtils.isEmpty(cuf.getBusinessName())){
									if(StringUtils.isEmpty(cuf.getBankName())){
										if(StringUtils.isEmpty(cuf.getAccountNo())){
											if(StringUtils.isEmpty(cuf.getCustomerName())){
												if(null == cuf.getMerchantAudit()){
													if(StringUtils.isEmpty(cuf.getCounterFee())){
														if(StringUtils.isEmpty(cuf.getProvince())){
															if(StringUtils.isEmpty(cuf.getCity())){
																if(StringUtils.isEmpty(cuf.getPayNum())){
																	if(StringUtils.isEmpty(cuf.getTerminalNo())){
																		if(null == cuf.getCommitTime()){
																			if(StringUtils.isEmpty(cuf.getBackReason())){
																				if(StringUtils.isEmpty(cuf.getFinishTime())){
																					if(StringUtils.isEmpty(cuf.getBackRealMoney())){
																						if(StringUtils.isEmpty(cuf.getBackiId())){
																							return true;
																						}
																					}
																				}
																			}
																			
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 金账户上传文件数据校验，扫除空对象
	 * 2016年5月20日
	 * by 李志伟
	 * @param gau
	 * @return
	 */
	public static boolean jzhUploadFileDataCheck(GoldAccountUpload gau){
		
		if(StringUtils.isEmpty(gau.getNum())){
			if(StringUtils.isEmpty(gau.getPayLoginName())){
				if(StringUtils.isEmpty(gau.getPayChnName())){
					if(StringUtils.isEmpty(gau.getPayMoneyFromFrozen())){
						if(StringUtils.isEmpty(gau.getGetMoneyLoginName())){
							if(StringUtils.isEmpty(gau.getGetMoneyChnName())){
								if(StringUtils.isEmpty(gau.getGetMoneyFrozen())){
									if(StringUtils.isEmpty(gau.getContractNo())){
										if(StringUtils.isEmpty(gau.getReturnMesg())){
											if(StringUtils.isEmpty(gau.getReturnNo())){
												if(null == gau.getBackRealMoney()){
													if(StringUtils.isEmpty(gau.getBackiId())){
														return true;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 网银上传文件数据校验
	 * 2016年5月20日
	 * by 李志伟
	 * @param nbu
	 * @return
	 */
	public static boolean wyUploadFileDataCheck(NetBankUpload nbu){
		
		if(StringUtils.isEmpty(nbu.getLendCode())){
			if(StringUtils.isEmpty(nbu.getGetMoneyAccount())){
				if(StringUtils.isEmpty(nbu.getGetMoneyName())){
					if(null == nbu.getLoanDate()){
						if(StringUtils.isEmpty(nbu.getApplyBillday())){
							if(StringUtils.isEmpty(nbu.getGetMoneyBank())){
								if(StringUtils.isEmpty(nbu.getGetMoneyBankBranch())){
									if(StringUtils.isEmpty(nbu.getCardOrBooklet())){
										if(StringUtils.isEmpty(nbu.getAccountAddrprovince())){
											if(StringUtils.isEmpty(nbu.getAccountAddrcity())){
												if(StringUtils.isEmpty(nbu.getLoanAccount())){
													if(StringUtils.isEmpty(nbu.getAccountBank())){
														if(StringUtils.isEmpty(nbu.getAccountNo())){
															if(null == nbu.getBackRealMoney()){
																if(StringUtils.isEmpty(nbu.getBackiId())){
																	return true;
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	
	
	/**
	 * 上传回息是否回息
	 * gaoxu
	 * by 2017-3-24 13:30:41
	 * @return
	 */
	public static boolean uploadIsInterestFileCheck(UploadIsInterest um){
		if(StringUtils.isEmpty(um.getCustomerName())){
			if(null == um.getLendCode()){
				if(null == um.getApplyLendDay()){
					if(null == um.getApplyLendMoney()){
						if(StringUtils.isEmpty(um.getApplyProduct())){
							if(null == um.getApplyExpireDay()){
								if(StringUtils.isEmpty(um.getIsInterest())){
									if(StringUtils.isEmpty(um.getBackiId())){
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	
	
	/**
	 * 上传回息是否回息
	 * gaoxu
	 * by 2017-3-24 13:30:41
	 * @return
	 */
	public static boolean uploadMoneyFileCheckReturn(UploadReturnBackMoney um){
		if(StringUtils.isEmpty(um.getCustomerName())){
			if(null == um.getLendCode()){
				if(null == um.getApplyLendDay()){
					if(null == um.getApplyLendMoney()){
						if(StringUtils.isEmpty(um.getApplyProduct())){
							if(null == um.getApplyExpireDay()){
								if(null==um.getBackRealMoney()){
									if(StringUtils.isEmpty(um.getBackiId())){
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
}
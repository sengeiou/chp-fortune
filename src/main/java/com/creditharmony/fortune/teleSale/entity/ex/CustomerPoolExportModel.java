package com.creditharmony.fortune.teleSale.entity.ex;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.fortune.look.apply.util.ExcelField4BD;


/**
 * 导出公共池数据Excel Bean
 * @Class Name CustomerPoolExportModel
 * @author 肖长伟
 * @Create In 2016年2月16日
 */
public class CustomerPoolExportModel implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = -1437321853124591386L;

	@ExcelField4BD(title = "客户编号")
	private String customerCode;  //客户Code
	@ExcelField4BD(title = "客户姓名")	
	private String customerName; //客户姓名
	@ExcelField4BD(title = "省份")	
	private String provinceName;   		//省份id
	@ExcelField4BD(title = "城市")	
	private String cityName;					//城市id
	@ExcelField4BD(title = "营业部")	
	private String salesDeptName;				//营业部名称
	@ExcelField4BD(title = "联系电话")	
	private String customerTel;  		//联系电话
	@ExcelField4BD(title = "证件号码")	
	private String card;					//身份证号
	@ExcelField4BD(title = "进入公共池时间")	
	private Date createTime;
	@ExcelField4BD(title = "出借咨询录入时间")
	private Date lendInputDate;
	@ExcelField4BD(title = "最后编辑时间")	
	private Date modifyTime; 				//操作时间
	
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getSalesDeptName() {
		return salesDeptName;
	}
	public void setSalesDeptName(String salesDeptName) {
		this.salesDeptName = salesDeptName;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLendInputDate() {
		return lendInputDate;
	}
	public void setLendInputDate(Date lendInputDate) {
		this.lendInputDate = lendInputDate;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
//	private String managerId;  //理财经理Id
//	private String managerName; //理财经理姓名
//	private String oldManagerId; //原理财经理id
//    private String salesDept;			//营业部ID
//    private String dataType;			//数据的类型，1进入公共池、2分配给理财经理、3退回到电销
//    
//    private String teleManagerCode;	//电销专员编号
//    private String teleManagerName;		//电销专员姓名
//    private String teamManagerCode;	//团队经理code
//    private String teamManagerName; 	//团队经理姓名
//    private String storeManagerCode;	//部门经理code
//    private String storeManagerName;	//部门经理姓名
//    
//    private String userId;		//电销专员id
//    private String userName;  //电销专员姓名

	
}

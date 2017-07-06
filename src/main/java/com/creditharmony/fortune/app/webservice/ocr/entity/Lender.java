package com.creditharmony.fortune.app.webservice.ocr.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import com.creditharmony.core.persistence.DataEntity;

@SuppressWarnings("serial")
public class Lender extends DataEntity<Lender> {
	
	// 主键信息
	private String id;
	private String userid;
	private String addrid;
	private String euserid;
	private String faccountid;
	private String haccountid;
	private String lenderid;
	private String examineid;
	private String cjsqid;

	private String c_id;
	private String c_code;
	private String c_name;
	private String c_ename;
	private String c_marriage;
	private String c_cert_type;
	private String c_cert_num;
	private Date c_cert_issuedate;
	private Date c_cert_failuredate;
	private String c_cert_org;
	private String c_education;
	private String c_sex;
	private Date c_birthday;
	private Date c_first_contactdate;
	private String c_occupation;
	private String c_industry;
	private String c_unitname;
	private String c_work_experience;
	private String c_unit_scale;
	private String c_post;
	private String c_mobilephone;
	private String c_phone;
	private String c_email;
	private String c_fax;
	private String c_source;
	private String c_mother_name;
	private Date c_creat_date;
	private String c_creatid;
	private String c_usertype;
	private String c_cztype;

	private String c_provinceid;
	private String c_cityid;
	private String c_countyid;
	private String c_addr_province;
	private String c_addr_city;
	private String c_addr_district;
	private String c_addr;
	private String c_postal_code;
	private String c_addr_type;
	private String c_user_type;

	private String c_e_name;
	private String c_e_cert_type;
	private String c_e_cert_num;
	private Date c_e_birthday;
	private String c_e_ename;
	private String c_e_sex;
	private String c_e_mobilephone;
	private String c_e_phone;
	private String c_e_email;
	private String c_e_relationship;
	private String c_e_addr_province;
	private String c_e_addr_city;
	private String c_e_addr_district;
	private String c_e_addr;
	private String c_e_postal_code;

	private String c_financial_manager;
	private String c_fmanager_name;
	private String c_yybid;
	private String c_bill_collect;
	private String c_agreement;
	private Date c_agreement_date;
	private String c_agreement_type;
	private String c_agreement_edition;
	private String c_state;
	private String c_user_level;
	private String c_examine;
	private String c_examinetype;

	private String c_f_account_bank;
	private String c_f_addr_province;
	private String c_f_addr_city;
	private String c_f_card_or_booklet;
	private String c_f_branch;
	private String c_f_account_name;
	private String c_f_accountid;

	private String c_h_account_bank;
	private String c_h_addr_province;
	private String c_h_addr_city;
	private String c_h_card_or_booklet;
	private String c_h_branch;
	private String c_h_account_name;
	private String c_h_accountid;

	private String c_cjmoney_q;
	private String c_cjmoney_z;
	private String c_pay;
	private String c_products;
	private Date c_lend_date;

	private String rn;
	private String cjrcode;
	private String cjrname;
	private String cjcode;
	private String province;
	private String city;
	private String kftd;
	private String yyb;
	private String tdjlname;
	private String lcjlname;
	private String source;
	private String creat_date;
	private String state;
	private String statename;
	private String lend_money;
	private String productname;
	private String fkfs;
	private String sqstate;
	private String hkstate;
	private String wjstate;
	private Date expire_date;

	private Date contributive_date;
	private String contributive_money;
	private Date comdate;
	private String comtype;
	private Date combegin_date;
	private Date commend_date;
	private String intention_product;
	private String customer_intention;
	private String com_content;
	private Date next_contact_date;
	private String next_contact_type;
	private String creatid;
	private String urlsource;

	// 银行卡信息
	private String y_account_bank;
	private String y_branch;
	private String y_account_id;
	private String y_province;
	private String y_city;
	private String y_district;
	// 遗漏字段
	private String team_id;
	private String org_id;

	private String c_cert_issuedate2;
	private String c_cert_failuredate2;
	private String c_birthday2;
	private String contributive_date2;

	// ocr切图添加字段
	private String picPath;// 身份证切图路径
	private String bankPicPath;// 银行卡切图路径

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getBankPicPath() {
		return bankPicPath;
	}

	public void setBankPicPath(String bankPicPath) {
		this.bankPicPath = bankPicPath;
	}

	public String getY_province() {
		return y_province;
	}

	public void setY_province(String yProvince) {
		y_province = yProvince;
	}

	public String getY_city() {
		return y_city;
	}

	public void setY_city(String yCity) {
		y_city = yCity;
	}

	public String getY_district() {
		return y_district;
	}

	public void setY_district(String yDistrict) {
		y_district = yDistrict;
	}

	public String getTeam_id() {
		return team_id;
	}

	public String getC_cert_issuedate2() {
		return c_cert_issuedate2;
	}

	public void setC_cert_issuedate2(String cCertIssuedate2) {
		c_cert_issuedate2 = cCertIssuedate2;
	}

	public String getC_cert_failuredate2() {
		return c_cert_failuredate2;
	}

	public void setC_cert_failuredate2(String cCertFailuredate2) {
		c_cert_failuredate2 = cCertFailuredate2;
	}

	public String getC_birthday2() {
		return c_birthday2;
	}

	public void setC_birthday2(String cBirthday2) {
		c_birthday2 = cBirthday2;
	}

	public String getContributive_date2() {
		return contributive_date2;
	}

	public void setContributive_date2(String contributiveDate2) {
		contributive_date2 = contributiveDate2;
	}

	public void setTeam_id(String teamId) {
		team_id = teamId;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String orgId) {
		org_id = orgId;
	}

	public String getY_account_bank() {
		return y_account_bank;
	}

	public void setY_account_bank(String yAccountBank) {
		y_account_bank = yAccountBank;
	}

	public String getY_branch() {
		return y_branch;
	}

	public void setY_branch(String yBranch) {
		y_branch = yBranch;
	}

	public String getY_account_id() {
		return y_account_id;
	}

	public void setY_account_id(String yAccountId) {
		y_account_id = yAccountId;
	}

	public String getC_code() {
		return c_code;
	}

	public void setC_code(String c_code) {
		this.c_code = c_code;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_ename() {
		return c_ename;
	}

	public void setC_ename(String c_ename) {
		this.c_ename = c_ename;
	}

	public String getC_marriage() {
		return c_marriage;
	}

	public void setC_marriage(String c_marriage) {
		this.c_marriage = c_marriage;
	}

	public String getC_cert_type() {
		return c_cert_type;
	}

	public void setC_cert_type(String c_cert_type) {
		this.c_cert_type = c_cert_type;
	}

	public String getC_cert_num() {
		return c_cert_num;
	}

	public void setC_cert_num(String c_cert_num) {
		this.c_cert_num = c_cert_num;
	}

	public String getC_cert_org() {
		return c_cert_org;
	}

	public void setC_cert_org(String c_cert_org) {
		this.c_cert_org = c_cert_org;
	}

	public String getC_education() {
		return c_education;
	}

	public void setC_education(String c_education) {
		this.c_education = c_education;
	}

	public String getC_sex() {
		return c_sex;
	}

	public void setC_sex(String c_sex) {
		this.c_sex = c_sex;
	}

	public String getC_occupation() {
		return c_occupation;
	}

	public void setC_occupation(String c_occupation) {
		this.c_occupation = c_occupation;
	}

	public String getC_industry() {
		return c_industry;
	}

	public void setC_industry(String c_industry) {
		this.c_industry = c_industry;
	}

	public String getC_unitname() {
		return c_unitname;
	}

	public void setC_unitname(String c_unitname) {
		this.c_unitname = c_unitname;
	}

	public String getC_work_experience() {
		return c_work_experience;
	}

	public void setC_work_experience(String c_work_experience) {
		this.c_work_experience = c_work_experience;
	}

	public String getC_unit_scale() {
		return c_unit_scale;
	}

	public void setC_unit_scale(String c_unit_scale) {
		this.c_unit_scale = c_unit_scale;
	}

	public String getC_post() {
		return c_post;
	}

	public void setC_post(String c_post) {
		this.c_post = c_post;
	}

	public String getC_mobilephone() {
		return c_mobilephone;
	}

	public void setC_mobilephone(String c_mobilephone) {
		this.c_mobilephone = c_mobilephone;
	}

	public String getC_phone() {
		return c_phone;
	}

	public void setC_phone(String c_phone) {
		this.c_phone = c_phone;
	}

	public String getC_email() {
		return c_email;
	}

	public void setC_email(String c_email) {
		this.c_email = c_email;
	}

	public String getC_fax() {
		return c_fax;
	}

	public void setC_fax(String c_fax) {
		this.c_fax = c_fax;
	}

	public String getC_source() {
		return c_source;
	}

	public void setC_source(String c_source) {
		this.c_source = c_source;
	}

	public String getC_mother_name() {
		return c_mother_name;
	}

	public void setC_mother_name(String c_mother_name) {
		this.c_mother_name = c_mother_name;
	}

	public String getC_creatid() {
		return c_creatid;
	}

	public void setC_creatid(String c_creatid) {
		this.c_creatid = c_creatid;
	}

	public String getC_addr_province() {
		return c_addr_province;
	}

	public void setC_addr_province(String c_addr_province) {
		this.c_addr_province = c_addr_province;
	}

	public String getC_addr_city() {
		return c_addr_city;
	}

	public void setC_addr_city(String c_addr_city) {
		this.c_addr_city = c_addr_city;
	}

	public String getC_addr_district() {
		return c_addr_district;
	}

	public void setC_addr_district(String c_addr_district) {
		this.c_addr_district = c_addr_district;
	}

	public String getC_addr() {
		return c_addr;
	}

	public void setC_addr(String c_addr) {
		this.c_addr = c_addr;
	}

	public String getC_postal_code() {
		return c_postal_code;
	}

	public void setC_postal_code(String c_postal_code) {
		this.c_postal_code = c_postal_code;
	}

	public String getC_addr_type() {
		return c_addr_type;
	}

	public void setC_addr_type(String c_addr_type) {
		this.c_addr_type = c_addr_type;
	}

	public String getC_user_type() {
		return c_user_type;
	}

	public void setC_user_type(String c_user_type) {
		this.c_user_type = c_user_type;
	}

	public String getC_e_name() {
		return c_e_name;
	}

	public void setC_e_name(String c_e_name) {
		this.c_e_name = c_e_name;
	}

	public String getC_e_cert_type() {
		return c_e_cert_type;
	}

	public void setC_e_cert_type(String c_e_cert_type) {
		this.c_e_cert_type = c_e_cert_type;
	}

	public String getC_e_cert_num() {
		return c_e_cert_num;
	}

	public void setC_e_cert_num(String c_e_cert_num) {
		this.c_e_cert_num = c_e_cert_num;
	}

	public String getC_e_ename() {
		return c_e_ename;
	}

	public void setC_e_ename(String c_e_ename) {
		this.c_e_ename = c_e_ename;
	}

	public String getC_e_sex() {
		return c_e_sex;
	}

	public void setC_e_sex(String c_e_sex) {
		this.c_e_sex = c_e_sex;
	}

	public String getC_e_mobilephone() {
		return c_e_mobilephone;
	}

	public void setC_e_mobilephone(String c_e_mobilephone) {
		this.c_e_mobilephone = c_e_mobilephone;
	}

	public String getC_e_phone() {
		return c_e_phone;
	}

	public void setC_e_phone(String c_e_phone) {
		this.c_e_phone = c_e_phone;
	}

	public String getC_e_email() {
		return c_e_email;
	}

	public void setC_e_email(String c_e_email) {
		this.c_e_email = c_e_email;
	}

	public String getC_e_relationship() {
		return c_e_relationship;
	}

	public void setC_e_relationship(String c_e_relationship) {
		this.c_e_relationship = c_e_relationship;
	}

	public String getC_financial_manager() {
		return c_financial_manager;
	}

	public void setC_financial_manager(String c_financial_manager) {
		this.c_financial_manager = c_financial_manager;
	}

	public String getC_bill_collect() {
		return c_bill_collect;
	}

	public void setC_bill_collect(String c_bill_collect) {
		this.c_bill_collect = c_bill_collect;
	}

	public String getC_agreement() {
		return c_agreement;
	}

	public void setC_agreement(String c_agreement) {
		this.c_agreement = c_agreement;
	}

	public String getC_agreement_type() {
		return c_agreement_type;
	}

	public void setC_agreement_type(String c_agreement_type) {
		this.c_agreement_type = c_agreement_type;
	}

	public String getC_agreement_edition() {
		return c_agreement_edition;
	}

	public void setC_agreement_edition(String c_agreement_edition) {
		this.c_agreement_edition = c_agreement_edition;
	}

	public String getC_state() {
		return c_state;
	}

	public void setC_state(String c_state) {
		this.c_state = c_state;
	}

	public String getC_user_level() {
		return c_user_level;
	}

	public void setC_user_level(String c_user_level) {
		this.c_user_level = c_user_level;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getC_fmanager_name() {
		return c_fmanager_name;
	}

	public void setC_fmanager_name(String c_fmanager_name) {
		this.c_fmanager_name = c_fmanager_name;
	}

	public String getC_yybid() {
		return c_yybid;
	}

	public void setC_yybid(String c_yybid) {
		this.c_yybid = c_yybid;
	}

	public String getC_provinceid() {
		return c_provinceid;
	}

	public void setC_provinceid(String c_provinceid) {
		this.c_provinceid = c_provinceid;
	}

	public String getC_cityid() {
		return c_cityid;
	}

	public void setC_cityid(String c_cityid) {
		this.c_cityid = c_cityid;
	}

	public String getCjrcode() {
		return cjrcode;
	}

	public void setCjrcode(String cjrcode) {
		this.cjrcode = cjrcode;
	}

	public String getCjrname() {
		return cjrname;
	}

	public void setCjrname(String cjrname) {
		this.cjrname = cjrname;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRn() {
		return rn;
	}

	public void setRn(String rn) {
		this.rn = rn;
	}

	public String getKftd() {
		return kftd;
	}

	public void setKftd(String kftd) {
		this.kftd = kftd;
	}

	public String getYyb() {
		return yyb;
	}

	public void setYyb(String yyb) {
		this.yyb = yyb;
	}

	public String getTdjlname() {
		return tdjlname;
	}

	public void setTdjlname(String tdjlname) {
		this.tdjlname = tdjlname;
	}

	public String getLcjlname() {
		return lcjlname;
	}

	public void setLcjlname(String lcjlname) {
		this.lcjlname = lcjlname;
	}

	public String getCreat_date() {
		return creat_date;
	}

	public void setCreat_date(String creat_date) {
		this.creat_date = creat_date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public String getContributive_money() {
		return contributive_money;
	}

	public void setContributive_money(String contributive_money) {
		this.contributive_money = contributive_money;
	}

	public String getComtype() {
		return comtype;
	}

	public void setComtype(String comtype) {
		this.comtype = comtype;
	}

	public String getIntention_product() {
		return intention_product;
	}

	public void setIntention_product(String intention_product) {
		this.intention_product = intention_product;
	}

	public String getCustomer_intention() {
		return customer_intention;
	}

	public void setCustomer_intention(String customer_intention) {
		this.customer_intention = customer_intention;
	}

	public String getCom_content() {
		return com_content;
	}

	public void setCom_content(String com_content) {
		this.com_content = com_content;
	}

	public String getNext_contact_type() {
		return next_contact_type;
	}

	public void setNext_contact_type(String next_contact_type) {
		this.next_contact_type = next_contact_type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getC_usertype() {
		return c_usertype;
	}

	public void setC_usertype(String c_usertype) {
		this.c_usertype = c_usertype;
	}

	public String getC_countyid() {
		return c_countyid;
	}

	public void setC_countyid(String c_countyid) {
		this.c_countyid = c_countyid;
	}

	public String getC_cztype() {
		return c_cztype;
	}

	public void setC_cztype(String c_cztype) {
		this.c_cztype = c_cztype;
	}

	public String getCreatid() {
		return creatid;
	}

	public void setCreatid(String creatid) {
		this.creatid = creatid;
	}

	public Date getContributive_date() {

		return contributive_date;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setContributive_date(Date contributive_date) {
		this.contributive_date = contributive_date;
	}

	public Date getComdate() {
		return comdate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setComdate(Date comdate) {
		this.comdate = comdate;
	}

	public Date getCombegin_date() {
		return combegin_date;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setCombegin_date(Date combegin_date) {
		this.combegin_date = combegin_date;
	}

	public Date getCommend_date() {
		return commend_date;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setCommend_date(Date commend_date) {
		this.commend_date = commend_date;
	}

	public Date getNext_contact_date() {
		return next_contact_date;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setNext_contact_date(Date next_contact_date) {
		this.next_contact_date = next_contact_date;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAddrid() {
		return addrid;
	}

	public void setAddrid(String addrid) {
		this.addrid = addrid;
	}

	public String getEuserid() {
		return euserid;
	}

	public void setEuserid(String euserid) {
		this.euserid = euserid;
	}

	public String getFaccountid() {
		return faccountid;
	}

	public void setFaccountid(String faccountid) {
		this.faccountid = faccountid;
	}

	public String getHaccountid() {
		return haccountid;
	}

	public void setHaccountid(String haccountid) {
		this.haccountid = haccountid;
	}

	public String getLenderid() {
		return lenderid;
	}

	public void setLenderid(String lenderid) {
		this.lenderid = lenderid;
	}

	public Date getC_cert_issuedate() {
		return c_cert_issuedate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setC_cert_issuedate(Date c_cert_issuedate) {
		this.c_cert_issuedate = c_cert_issuedate;
	}

	public Date getC_cert_failuredate() {
		return c_cert_failuredate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setC_cert_failuredate(Date c_cert_failuredate) {
		this.c_cert_failuredate = c_cert_failuredate;
	}

	public Date getC_birthday() {
		return c_birthday;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setC_birthday(Date c_birthday) {
		this.c_birthday = c_birthday;
	}

	public Date getC_first_contactdate() {
		return c_first_contactdate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setC_first_contactdate(Date c_first_contactdate) {
		this.c_first_contactdate = c_first_contactdate;
	}

	public Date getC_creat_date() {
		return c_creat_date;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setC_creat_date(Date c_creat_date) {
		this.c_creat_date = c_creat_date;
	}

	public Date getC_e_birthday() {
		return c_e_birthday;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setC_e_birthday(Date c_e_birthday) {
		this.c_e_birthday = c_e_birthday;
	}

	public Date getC_agreement_date() {
		return c_agreement_date;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setC_agreement_date(Date c_agreement_date) {
		this.c_agreement_date = c_agreement_date;
	}

	public String getC_f_account_bank() {
		return c_f_account_bank;
	}

	public void setC_f_account_bank(String c_f_account_bank) {
		this.c_f_account_bank = c_f_account_bank;
	}

	public String getC_f_addr_province() {
		return c_f_addr_province;
	}

	public void setC_f_addr_province(String c_f_addr_province) {
		this.c_f_addr_province = c_f_addr_province;
	}

	public String getC_f_addr_city() {
		return c_f_addr_city;
	}

	public void setC_f_addr_city(String c_f_addr_city) {
		this.c_f_addr_city = c_f_addr_city;
	}

	public String getC_f_card_or_booklet() {
		return c_f_card_or_booklet;
	}

	public void setC_f_card_or_booklet(String c_f_card_or_booklet) {
		this.c_f_card_or_booklet = c_f_card_or_booklet;
	}

	public String getC_f_branch() {
		return c_f_branch;
	}

	public void setC_f_branch(String c_f_branch) {
		this.c_f_branch = c_f_branch;
	}

	public String getC_f_account_name() {
		return c_f_account_name;
	}

	public void setC_f_account_name(String c_f_account_name) {
		this.c_f_account_name = c_f_account_name;
	}

	public String getC_f_accountid() {
		return c_f_accountid;
	}

	public void setC_f_accountid(String c_f_accountid) {
		this.c_f_accountid = c_f_accountid;
	}

	public String getC_h_account_bank() {
		return c_h_account_bank;
	}

	public void setC_h_account_bank(String c_h_account_bank) {
		this.c_h_account_bank = c_h_account_bank;
	}

	public String getC_h_addr_province() {
		return c_h_addr_province;
	}

	public void setC_h_addr_province(String c_h_addr_province) {
		this.c_h_addr_province = c_h_addr_province;
	}

	public String getC_h_addr_city() {
		return c_h_addr_city;
	}

	public void setC_h_addr_city(String c_h_addr_city) {
		this.c_h_addr_city = c_h_addr_city;
	}

	public String getC_h_card_or_booklet() {
		return c_h_card_or_booklet;
	}

	public void setC_h_card_or_booklet(String c_h_card_or_booklet) {
		this.c_h_card_or_booklet = c_h_card_or_booklet;
	}

	public String getC_h_branch() {
		return c_h_branch;
	}

	public void setC_h_branch(String c_h_branch) {
		this.c_h_branch = c_h_branch;
	}

	public String getC_h_account_name() {
		return c_h_account_name;
	}

	public void setC_h_account_name(String c_h_account_name) {
		this.c_h_account_name = c_h_account_name;
	}

	public String getC_h_accountid() {
		return c_h_accountid;
	}

	public void setC_h_accountid(String c_h_accountid) {
		this.c_h_accountid = c_h_accountid;
	}

	public String getC_e_addr_province() {
		return c_e_addr_province;
	}

	public void setC_e_addr_province(String c_e_addr_province) {
		this.c_e_addr_province = c_e_addr_province;
	}

	public String getC_e_addr_city() {
		return c_e_addr_city;
	}

	public void setC_e_addr_city(String c_e_addr_city) {
		this.c_e_addr_city = c_e_addr_city;
	}

	public String getC_e_addr_district() {
		return c_e_addr_district;
	}

	public void setC_e_addr_district(String c_e_addr_district) {
		this.c_e_addr_district = c_e_addr_district;
	}

	public String getC_e_addr() {
		return c_e_addr;
	}

	public void setC_e_addr(String c_e_addr) {
		this.c_e_addr = c_e_addr;
	}

	public String getC_e_postal_code() {
		return c_e_postal_code;
	}

	public void setC_e_postal_code(String c_e_postal_code) {
		this.c_e_postal_code = c_e_postal_code;
	}

	public String getUrlsource() {
		return urlsource;
	}

	public void setUrlsource(String urlsource) {
		this.urlsource = urlsource;
	}

	public String getC_examine() {
		return c_examine;
	}

	public void setC_examine(String c_examine) {
		this.c_examine = c_examine;
	}

	public String getC_examinetype() {
		return c_examinetype;
	}

	public void setC_examinetype(String c_examinetype) {
		this.c_examinetype = c_examinetype;
	}

	public String getExamineid() {
		return examineid;
	}

	public void setExamineid(String examineid) {
		this.examineid = examineid;
	}

	public String getCjsqid() {
		return cjsqid;
	}

	public void setCjsqid(String cjsqid) {
		this.cjsqid = cjsqid;
	}

	public String getCjcode() {
		return cjcode;
	}

	public void setCjcode(String cjcode) {
		this.cjcode = cjcode;
	}

	public String getLend_money() {
		return lend_money;
	}

	public void setLend_money(String lend_money) {
		this.lend_money = lend_money;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getFkfs() {
		return fkfs;
	}

	public void setFkfs(String fkfs) {
		this.fkfs = fkfs;
	}

	public String getSqstate() {
		return sqstate;
	}

	public void setSqstate(String sqstate) {
		this.sqstate = sqstate;
	}

	public String getHkstate() {
		return hkstate;
	}

	public void setHkstate(String hkstate) {
		this.hkstate = hkstate;
	}

	public String getWjstate() {
		return wjstate;
	}

	public void setWjstate(String wjstate) {
		this.wjstate = wjstate;
	}

	public Date getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(Date expire_date) {
		this.expire_date = expire_date;
	}

	public String getC_cjmoney_q() {
		return c_cjmoney_q;
	}

	public void setC_cjmoney_q(String c_cjmoney_q) {
		this.c_cjmoney_q = c_cjmoney_q;
	}

	public String getC_cjmoney_z() {
		return c_cjmoney_z;
	}

	public void setC_cjmoney_z(String c_cjmoney_z) {
		this.c_cjmoney_z = c_cjmoney_z;
	}

	public String getC_pay() {
		return c_pay;
	}

	public void setC_pay(String c_pay) {
		this.c_pay = c_pay;
	}

	public String getC_products() {
		return c_products;
	}

	public void setC_products(String c_products) {
		this.c_products = c_products;
	}

	public Date getC_lend_date() {
		return c_lend_date;
	}

	public void setC_lend_date(Date c_lend_date) {
		this.c_lend_date = c_lend_date;
	}

}

package com.creditharmony.fortune.back.interest.view;

import java.io.Serializable;
import java.util.List;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.fortune.back.interest.entity.DetailsPage;
import com.creditharmony.fortune.back.interest.entity.PlatformMsg;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.customer.entity.Product;

/**
 * 返回页面对象bean
 * @Class Name BackInterestBeanView 
 * @author 李志伟
 * @Create In 2016年4月8日
 */
public class BackInterestBeanView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 检索条件对象
	private SearchObject so;
	
	// 分页对象
	private Page<?> page;
	
	// 列表总金额对象
	private String listTotalMoney;
	
	// 当前页面总金额对象
	private String pageTotalMoney;
	
	// 回息产品下拉框对象
	private List<Product> productList;
	
	// 详情页对象
	private DetailsPage detailsPage;
	
	// 第三方平台对象
	private List<PlatformMsg> platformMesglist;

	public SearchObject getSo() {
		return so;
	}

	public void setSo(SearchObject so) {
		this.so = so;
	}

	public Page<?> getPage() {
		return page;
	}

	public void setPage(Page<?> page) {
		this.page = page;
	}

	public String getListTotalMoney() {
		return listTotalMoney;
	}

	public void setListTotalMoney(String listTotalMoney) {
		this.listTotalMoney = listTotalMoney;
	}

	public String getPageTotalMoney() {
		return pageTotalMoney;
	}

	public void setPageTotalMoney(String pageTotalMoney) {
		this.pageTotalMoney = pageTotalMoney;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public DetailsPage getDetailsPage() {
		return detailsPage;
	}

	public void setDetailsPage(DetailsPage detailsPage) {
		this.detailsPage = detailsPage;
	}

	public List<PlatformMsg> getPlatformMesglist() {
		return platformMesglist;
	}

	public void setPlatformMesglist(List<PlatformMsg> platformMesglist) {
		this.platformMesglist = platformMesglist;
	}
}

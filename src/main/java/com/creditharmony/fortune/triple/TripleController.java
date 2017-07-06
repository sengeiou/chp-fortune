package com.creditharmony.fortune.triple;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.adapter.service.triple.bean.TripleCallBackInBean;
import com.creditharmony.adapter.service.triple.bean.TripleChangePhoneInBean;
import com.creditharmony.adapter.service.triple.bean.TripleFirstOrderInBean;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.CookieUtils;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.config.Global;
import com.creditharmony.core.excel.util.ImportExcel;
import com.creditharmony.core.fortune.type.FileType;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.ex.BorrowLookEx;
import com.creditharmony.fortune.borrow.facade.BorrowFacade;
import com.creditharmony.fortune.borrow.service.BorrowManager;
import com.creditharmony.fortune.borrow.service.BorrowMonthManager;
import com.creditharmony.fortune.borrow.utils.ExportExcelUtils;
import com.creditharmony.fortune.borrow.view.BorrowAllotView;
import com.creditharmony.fortune.borrow.view.BorrowView;
import com.creditharmony.fortune.constants.BorrowConstant;
import com.creditharmony.fortune.template.entity.BorrowImportExcel;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;
import com.creditharmony.fortune.triple.system.service.TripleCallBackService;
import com.creditharmony.fortune.triple.system.service.TripleChangePhoneServiceImp;
import com.creditharmony.fortune.triple.system.service.TripleFirstOrderServiceImp;
import com.creditharmony.fortune.triple.system.service.TripleNewCustomerServiceImp;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 三网接口测试
 * @Class Name BorrowController
 * @author 周俊
 * @Create In 2015年12月2日
 */
@Controller
@RequestMapping("${adminPath}/triple")
public class TripleController extends BaseController{

	@Autowired
	private TripleNewCustomerServiceImp tripleNewCustomerServiceImp;
	
	@Autowired
	private TripleChangePhoneServiceImp tripleChangePhoneServiceImp;
	@Autowired
	private TripleCallBackService tripleCallBackService;
	@Autowired
	private TripleFirstOrderServiceImp tripleFirstOrderServiceImp;
	
	/**
	 * 可用债权列表
	 * 2015年12月2日
	 * By 周俊
	 * @param model
	 * @param borrowView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/firstorder")
	@ResponseBody
	public String firstorder(Model model,BorrowView borrowView,HttpServletRequest request,HttpServletResponse response){
		/*{"serialNum":"201611230638284544244",
		"cardType":"1","phone":"17310832602",
		"empCode":"","firstOrderTime":"2016-11-22 10:16:07",
		"param":"cardType=1, cardId=null, phone=17310832602, "
				+ "empCode=, firstOrderTime=2016-11-22 10:16:07, operation=null"}
					*/
		
		try{
			TripleFirstOrderInBean paramBean = new TripleFirstOrderInBean();
			paramBean.setCardId(null);
			paramBean.setPhone("17310832602");
			paramBean.setFirstOrderTime("2016-11-22 10:16:07");
			paramBean.setSerialNum("201611230638284544244");
			paramBean.setEmpCode("");
			paramBean.setCardType("1");
			paramBean.setOperation("");
			tripleFirstOrderServiceImp.firstOrder(paramBean);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "error:"+e.getMessage();
		}
	}
	
	/**
	 * 可用债权列表
	 * 2015年12月2日
	 * By 周俊
	 * @param model
	 * @param borrowView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/auth")
	@ResponseBody
	public String list(Model model,BorrowView borrowView,HttpServletRequest request,HttpServletResponse response){
		/*
		{"serialNum":"201608291612003754355","operation":"M","empCode":"68056109","osType":"XH_DJR",
			"loginName":"djr069785931","phone":"13408500697","userName":"谢静","cardType":"1",
			"cardId":"51112219720513902X","createTime":"2016-08-29 16:03:52","mail":"","sex":"",
			"birthday":"","faileName":"djr0520160829000198","param":"Operation=M ,OsId=null ,EmpCode=68056109 "
					+ ",OsType=XH_DJR ,loginName=djr069785931 ,Phone=13408500697 ,UserName=谢静 ,CardType=1 ,"
					+ "CardId=51112219720513902X ,CreateTime=2016-08-29 16:03:52 ,Mail= ,Sex= ,Birthday= ,faileName=djr0520160829000198"}
		
					*/
		
		try{
			IntCustomerBean paramBean = new IntCustomerBean();
			paramBean.setOperation("M");
			paramBean.setEmpCode("");
			paramBean.setOsType(adminPath);
			paramBean.setNewPhone("13408500697");
			paramBean.setLogName("djr069785931");
			paramBean.setCardId("51112219720513902X");
			paramBean.setCardType("1");
			paramBean.setUserName("谢静");
			paramBean.setFaileName("djr0520160829000198");
			tripleNewCustomerServiceImp.authenticateCustomer(paramBean);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "error:"+e.getMessage();
		}
	}
	
	@RequestMapping("/changephone")
	@ResponseBody
	public String changePhone(Model model,BorrowView borrowView,HttpServletRequest request,HttpServletResponse response){
		/*
		{"serialNum":"201608291612003754355","operation":"M","empCode":"68056109","osType":"XH_DJR",
			"loginName":"djr069785931","phone":"13408500697","userName":"谢静","cardType":"1",
			"cardId":"51112219720513902X","createTime":"2016-08-29 16:03:52","mail":"","sex":"",
			"birthday":"","faileName":"djr0520160829000198","param":"Operation=M ,OsId=null ,EmpCode=68056109 "
					+ ",OsType=XH_DJR ,loginName=djr069785931 ,Phone=13408500697 ,UserName=谢静 ,CardType=1 ,"
					+ "CardId=51112219720513902X ,CreateTime=2016-08-29 16:03:52 ,Mail= ,Sex= ,Birthday= ,faileName=djr0520160829000198"}
					*/
		
		try{
			TripleChangePhoneInBean paramBean = new TripleChangePhoneInBean();
			paramBean.setEmpCode("686776");
			paramBean.setOsType("XH_DJR");
			paramBean.setNewPhone("13003239923");
			paramBean.setOldPhone("13003239922");
			paramBean.setCardId(null);
			paramBean.setSerialNum("510282198105077533");
			tripleChangePhoneServiceImp.changePhone(paramBean);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "error:"+e.getMessage();
		}
	}
	@RequestMapping("/callback")
	@ResponseBody
	public String callback(Model model,BorrowView borrowView,HttpServletRequest request,HttpServletResponse response){
		
		try{
			TripleCallBackInBean paramBean = new TripleCallBackInBean();
			paramBean.setMsgTypeId("A030");
			paramBean.setCallBackList(null);
			paramBean.setSerialNum("22222");
			paramBean.setUniqueNum("11111");
			paramBean.setFromSys("XH_DJR");
			tripleCallBackService.doExec(paramBean);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "error:"+e.getMessage();
		}
	}
}

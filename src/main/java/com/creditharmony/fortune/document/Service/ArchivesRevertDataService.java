package com.creditharmony.fortune.document.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.service.archives.ArchivesRevertDataBaseService;
import com.creditharmony.adapter.service.archives.bean.ArchivesRevertDataInBean;
import com.creditharmony.adapter.service.archives.bean.ArchivesRevertDataOutBean;
import com.creditharmony.fortune.document.dao.DocumentDao;
import com.creditharmony.fortune.document.entity.DocumentBean;

/**
 * @Class Name DocumentResultService
 * @author 胡体勇
 * @Create In 2016年3月16日
 */
@Service
@Transactional(value="fortuneTransactionManager",readOnly = true)
public class ArchivesRevertDataService extends ArchivesRevertDataBaseService {
    
	@Autowired
	private DocumentDao documentDao;
	/**日志对象*/
	protected Logger logger = LoggerFactory.getLogger(ArchivesRevertDataService.class);
	
	/* (non-Javadoc)
	 * @see com.creditharmony.adapter.service.archives.ArchivesRevertDataBaseService#doExec(com.creditharmony.adapter.service.archives.bean.ArchivesRevertDataInBean)
	 */
	@Override
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public ArchivesRevertDataOutBean doExec(ArchivesRevertDataInBean paramBean) {
		// 设置返回信息
		ArchivesRevertDataOutBean outBean = new ArchivesRevertDataOutBean();
		try {
			if(paramBean.getRetList().size() > 0){
		    	for(int i = 0;i<paramBean.getRetList().size();i++){
		    		// 根据档案系统返回处理结果更新业务表对应的状态为成功
		    		DocumentBean bean = new DocumentBean();
		    		bean.setBatchNo(paramBean.getRetList().get(i).getCustomerNo());
		    		bean.setBusinessType(paramBean.getRetList().get(i).getLoanNo());
		    		bean.setResult(paramBean.getRetList().get(i).getResult());
		    		logger.info("档案归档调用接口回传是否归档成功：id=="+paramBean.getRetList().get(i).getCustomerNo()+"，状态Result="+paramBean.getRetList().get(i).getResult());
		    		documentDao.updateDocument(bean);
		    	}
		    }
			outBean.setRetCode(ReturnConstant.SUCCESS);
		} catch (Exception e) {
			logger.error("档案系统返回结果处理失败！");
			outBean.setRetCode(ReturnConstant.ERROR);
		}
		return outBean;
	}

}

package com.creditharmony.fortune.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.customer.dao.AddressDao;
import com.creditharmony.fortune.customer.entity.Address;

/**
 * @Class Name AddressManager
 * @author kevin
 * @Create In 2015年12月1日
 */
@Service
@Transactional(readOnly = true)
public class AddressManager extends CoreManager<AddressDao, Address> {

	/**
	 * 2015年12月2日
	 * By 孙凯文
	 * @param address
	 * @return
	 */
	@Transactional(readOnly = false, value = "fortuneTransactionManager")
	public int update(Address address) {
		address.preUpdate();
		return super.dao.update(address);
	}
}

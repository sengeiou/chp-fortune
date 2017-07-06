package com.creditharmony.fortune.triple.system.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.entity.IntPhoneCard;

/**
 * @Class Name InfoGroupService
 * @author 胡体勇
 * @Create In 2016年5月6日
 */
@Service
public class InfoGroupService {

	@Autowired
	private TripleNewCustomerDao tripleNewCustomerDao;
	/** 日志对象 */
	protected Logger logger = LoggerFactory.getLogger(InfoGroupService.class);

	/**
	 * 根据手机号查询同组内的手机号 2016年5月9日 By 胡体勇
	 * 
	 * @param phone
	 * @return
	 */
	public List<IntPhoneCard> groupByPhone(String phone) {

		try {
			List<IntPhoneCard> tPhoneList = new ArrayList<IntPhoneCard>();
			IntPhoneCard t = new IntPhoneCard();
			t.setPhone(phone);
			tPhoneList.add(t);

			boolean flag = false;

			do {
				List<String> strList = new ArrayList<String>(); // 一组手机号码
				for (int j = 0; j < tPhoneList.size(); j++) {
					if (tPhoneList.get(j) != null) {
						if (StringUtils.isNotEmpty(tPhoneList.get(j).getPhone())) {
							strList.add(tPhoneList.get(j).getPhone());
						}
					}
				}
				IntPhoneCard ipc = new IntPhoneCard();
				ipc.setPhoneList(strList);
				// 根据手机号查询对应的证件号
				List<IntPhoneCard> cardList = new ArrayList<IntPhoneCard>();
				if (ipc.getPhoneList().size() > 0) {
					cardList = tripleNewCustomerDao.findCardForGroup(ipc); // cehkongfu
				}
				if (cardList.size() > 0) {
					List<String> cardStr = new ArrayList<String>();
					for (int i = 0; i < cardList.size(); i++) {
						if (cardList.get(i) != null) {
							if (StringUtils.isNotEmpty(cardList.get(i).getCardId())) {
								cardStr.add(cardList.get(i).getCardId());
							}
						}
					}
					IntPhoneCard card = new IntPhoneCard();
					card.setCardList(cardStr);
					// 根据证件号查询手机号
					List<IntPhoneCard> phoneList = new ArrayList<IntPhoneCard>();
					if (card.getCardList().size() > 0) {
						phoneList = tripleNewCustomerDao.findPhoneForGroup(card); // cehkongfu
					}

					if (tPhoneList.size() != phoneList.size()) {
						tPhoneList = phoneList;
					} else {
						flag = true;
					}
				} else {
					flag = true;
				}

			} while (flag == false);

			if (tPhoneList.size() == 0) {
				IntPhoneCard t2 = new IntPhoneCard();
				t2.setPhone(phone);
				tPhoneList.add(t2);
			}
			return tPhoneList;
		} catch (Exception e) {
			this.logger.error("根据手机号查询同组手机号异常！", e);
			List<IntPhoneCard> tPhoneList = new ArrayList<IntPhoneCard>();
			IntPhoneCard t = new IntPhoneCard();
			t.setPhone(phone);
			tPhoneList.add(t);
			return tPhoneList;
		}
	}

	/**
	 * 根据证件号查询同组内的手机号 2016年5月9日 By 胡体勇
	 * 
	 * @param cardId
	 * @return
	 */
	public List<IntPhoneCard> groupByCard(String cardId) {

		try {
			List<IntPhoneCard> tolPhoneList = new ArrayList<IntPhoneCard>();

			List<IntPhoneCard> tPhoneList = new ArrayList<IntPhoneCard>();
			IntPhoneCard t = new IntPhoneCard();
			t.setCardId(cardId);
			tPhoneList.add(t);

			boolean flag = false;

			do {
				List<String> cardStr = new ArrayList<String>();
				for (int i = 0; i < tPhoneList.size(); i++) {
					if (tPhoneList.get(i) != null) {
						if (StringUtils.isNotEmpty(tPhoneList.get(i).getCardId())) {
							cardStr.add(tPhoneList.get(i).getCardId());
						}
					}
				}
				IntPhoneCard card = new IntPhoneCard();
				card.setCardList(cardStr);
				// 根据证件号查询手机号
				List<IntPhoneCard> phoneList = new ArrayList<IntPhoneCard>();
				if (card.getCardList().size() > 0) {
					phoneList = tripleNewCustomerDao.findPhoneForGroup(card);
				}
				if (phoneList.size() > 0) {
					List<String> strList = new ArrayList<String>();
					for (int j = 0; j < phoneList.size(); j++) {
						if (phoneList.get(j) != null) {
							if (StringUtils.isNotEmpty(phoneList.get(j).getPhone())) {
								strList.add(phoneList.get(j).getPhone());
							}
						}
					}
					IntPhoneCard ipc = new IntPhoneCard();
					ipc.setPhoneList(strList);
					// 根据手机号查询对应的证件号
					List<IntPhoneCard> cardList = new ArrayList<IntPhoneCard>();
					if (ipc.getPhoneList().size() > 0) {
						cardList = tripleNewCustomerDao.findCardForGroup(ipc);
					}
					if (cardList.size() != tPhoneList.size()) {
						tPhoneList = cardList;
					} else {
						tolPhoneList = phoneList;
						flag = true;
					}
				} else {
					flag = true;
				}

			} while (flag == false);

			return tolPhoneList;
		} catch (Exception e) {
			this.logger.error("根据证件号查询同组手机号异常！", e);
			List<IntPhoneCard> tolPhoneList2 = new ArrayList<IntPhoneCard>();
			return tolPhoneList2;
		}
	}
}
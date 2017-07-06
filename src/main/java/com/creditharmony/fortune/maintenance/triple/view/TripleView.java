package com.creditharmony.fortune.maintenance.triple.view;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class TripleView extends DataEntity<TripleView>{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	private Date sendTimeFrom;
	
	private Date sendTimeTo;
	
	public Date getSendTimeFrom() {
		return sendTimeFrom;
	}

	public void setSendTimeFrom(Date sendTimeFrom) {
		this.sendTimeFrom = sendTimeFrom;
	}

	public Date getSendTimeTo() {
		return sendTimeTo;
	}

	public void setSendTimeTo(Date sendTimeTo) {
		this.sendTimeTo = sendTimeTo;
	}
}

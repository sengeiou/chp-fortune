package com.creditharmony.fortune.creditor.matching.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 债权工作量统计
 * @Class Name CreditorStatistics
 * @author 朱杰
 * @Create In 2016年3月20日
 */
public class CreditorStatistics extends DataEntity<CreditorStatistics> {
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 7614301289956709216L;

    private Date statisticsDate;

    private String userId;
    
    private Integer creditTaskCnt;
    //首期派发量
    private Integer creditSqDistributedCnt;
    //非首期派发量
    private Integer creditFsqDistributedCnt;
    //首期完成量
    private Integer creditSqDoneCnt;
    //非首期完成量
    private Integer creditFsqDoneCnt;

    private Integer creditDropCnt;

    private Integer creditChangeCnt;
    //最后更新出借编号
    private String lastestSqLendCode;
    //最后审批首期出借时间
    private Date lastestSqTime;

    public Date getStatisticsDate() {
        return statisticsDate;
    }

    public void setStatisticsDate(Date statisticsDate) {
        this.statisticsDate = statisticsDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getCreditDropCnt() {
        return creditDropCnt == null ? 0 : creditDropCnt;
    }

    public void setCreditDropCnt(Integer creditDropCnt) {
        this.creditDropCnt = creditDropCnt;
    }

    public Integer getCreditChangeCnt() {
        return creditChangeCnt == null ? 0 : creditChangeCnt;
    }

    public void setCreditChangeCnt(Integer creditChangeCnt) {
        this.creditChangeCnt = creditChangeCnt;
    }

	public Integer getCreditTaskCnt() {
		return creditTaskCnt == null ? 0 : creditTaskCnt;
	}

	public void setCreditTaskCnt(Integer creditTaskCnt) {
		this.creditTaskCnt = creditTaskCnt;
	}

	public Integer getCreditSqDistributedCnt() {
		return creditSqDistributedCnt == null ? 0 : creditSqDistributedCnt;
	}

	public void setCreditSqDistributedCnt(Integer creditSqDistributedCnt) {
		this.creditSqDistributedCnt = creditSqDistributedCnt;
	}

	public Integer getCreditFsqDistributedCnt() {
		return creditFsqDistributedCnt == null ? 0 : creditFsqDistributedCnt;
	}

	public void setCreditFsqDistributedCnt(Integer creditFsqDistributedCnt) {
		this.creditFsqDistributedCnt=creditFsqDistributedCnt;
	}

	public Integer getCreditSqDoneCnt() {
		return creditSqDoneCnt == null ? 0 : creditSqDoneCnt;
	}

	public void setCreditSqDoneCnt(Integer creditSqDoneCnt) {
		this.creditSqDoneCnt = creditSqDoneCnt;
	}

	public Integer getCreditFsqDoneCnt() {
		return creditFsqDoneCnt == null ? 0 : creditFsqDoneCnt;
	}

	public void setCreditFsqDoneCnt(Integer creditFsqDoneCnt) {
		this.creditFsqDoneCnt = creditFsqDoneCnt;
	}

	public String getLastestSqLendCode() {
		return lastestSqLendCode;
	}

	public void setLastestSqLendCode(String lastestSqLendCode) {
		this.lastestSqLendCode = lastestSqLendCode;
	}

	public Date getLastestSqTime() {
		return lastestSqTime;
	}

	public void setLastestSqTime(Date lastestSqTime) {
		this.lastestSqTime = lastestSqTime;
	}
}
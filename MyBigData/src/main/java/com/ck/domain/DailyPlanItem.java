package com.ck.domain;

import java.util.Date;

import com.ck.domain.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class DailyPlanItem extends BaseModel{
	
	private String planId;
	
	private String itemContent;
	
	private Integer priority;
	
	private Date planTime;
	
	private String planTimeStr;
	
	private Date finishTime;
	
	private String finishTimeStr;
	
	private Integer finishType;
	
	private String finishDescr;
	
	private Date startTime;
	
	private Integer remindOn;
	
}

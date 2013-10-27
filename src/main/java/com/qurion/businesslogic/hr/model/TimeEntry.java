/**
 *  Business Logic.
 */
package com.qurion.businesslogic.hr.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.hr.model.TimeSheet;
import com.qurion.businesslogic.workeffort.model.WorkEffort;

import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.hr.model.TimeEntryBilling;

/**
 * TimeEntry 
 * @author Edward Banfa
 */
@Entity
@Table(name="TIME_ENTRY"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TimeEntry  extends BaseEntity implements java.io.Serializable {
	private TimeSheet timeSheet;
	private WorkEffort workEffort;
	private Date fromDt;
	private Date toDt;
	private Integer hours;
	private String comment;
	private Set<TimeEntryBilling> timeEntryBillings;

    public TimeEntry() {
    }

    public TimeEntry(Date fromDt, Date toDt, Integer hours, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.hours = hours;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public TimeEntry(TimeSheet timeSheet, WorkEffort workEffort, Date fromDt, Date toDt, Integer hours, String comment, Set timeEntryBillings, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.timeSheet = timeSheet;
        this.workEffort = workEffort;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.hours = hours;
        this.comment = comment;
        this.timeEntryBillings = timeEntryBillings;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.versionNo = versionNo;
        this.rowTs = rowTs;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
        this.lastModifiedDt = lastModifiedDt;
        this.lastModifiedUsr = lastModifiedUsr;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TIME_SHEET_ID")
    @JsonIgnore
    public TimeSheet getTimeSheet() 
    {
        return this.timeSheet;
    }
    
    public void setTimeSheet(TimeSheet timeSheet)
    {
        this.timeSheet = timeSheet;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="WRK_EFFORT_ID")
    @JsonIgnore
    public WorkEffort getWorkEffort() 
    {
        return this.workEffort;
    }
    
    public void setWorkEffort(WorkEffort workEffort)
    {
        this.workEffort = workEffort;
    }
    
    @Column(name="FROM_DT", nullable=false, length=19)
    public Date getFromDt() 
    {
        return this.fromDt;
    }
    
    public void setFromDt(Date fromDt) 
    {
        this.fromDt = fromDt;
    }
	
    @Column(name="TO_DT", nullable=false, length=19)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
    }
	
    @Column(name="HOURS", nullable=false)
    public Integer getHours() 
    {
        return this.hours;
    }
    
    public void setHours(Integer hours) 
    {
        this.hours = hours;
    }
	
    @Column(name="COMMENT", length=150)
    public String getComment() 
    {
        return this.comment;
    }
    
    public void setComment(String comment) 
    {
        this.comment = comment;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="timeEntry")
    @JsonIgnore
    public Set<TimeEntryBilling> getTimeEntryBillings() 
    {
        return this.timeEntryBillings;
    }
    
    public void setTimeEntryBillings(Set<TimeEntryBilling> timeEntryBillings) 
    {
        this.timeEntryBillings = timeEntryBillings;
    }			
		
    

}

/**
 *  Business Logic.
 */
package com.qurion.businesslogic.workeffort.model;

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
import com.qurion.businesslogic.workeffort.model.WorkEffortStatusType;
import com.qurion.businesslogic.workeffort.model.WorkEffort;

import java.util.Date;

/**
 * WorkEffortStatus 
 * @author Edward Banfa
 */
@Entity
@Table(name="WORK_EFFORT_STATUS"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WorkEffortStatus  extends BaseEntity implements java.io.Serializable {
	private WorkEffortStatusType workEffortStatusType;
	private WorkEffort workEffort;
	private Date datetime;

    public WorkEffortStatus() {
    }

    public WorkEffortStatus(Date datetime, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.datetime = datetime;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public WorkEffortStatus(WorkEffortStatusType workEffortStatusType, WorkEffort workEffort, Date datetime, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.workEffortStatusType = workEffortStatusType;
        this.workEffort = workEffort;
        this.datetime = datetime;
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
    @JoinColumn(name="STATUS_TY_ID")
    @JsonIgnore
    public WorkEffortStatusType getWorkEffortStatusType() 
    {
        return this.workEffortStatusType;
    }
    
    public void setWorkEffortStatusType(WorkEffortStatusType workEffortStatusType)
    {
        this.workEffortStatusType = workEffortStatusType;
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
    
    @Column(name="DATETIME", nullable=false, length=19)
    public Date getDatetime() 
    {
        return this.datetime;
    }
    
    public void setDatetime(Date datetime) 
    {
        this.datetime = datetime;
    }
	
    

}

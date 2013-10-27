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
import com.qurion.businesslogic.hr.model.RateType;
import com.qurion.businesslogic.workeffort.model.WorkEffortPartyAssignement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;

/**
 * WorkEffortAssignmentRate 
 * @author Edward Banfa
 */
@Entity
@Table(name="WORK_EFFORT_ASSIGNMENT_RATE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WorkEffortAssignmentRate  extends BaseEntity implements java.io.Serializable {
	private RateType rateType;
	private WorkEffortPartyAssignement workEffortPartyAssignement;
	private BigDecimal rate;
	private Date fromDt;
	private Date toDt;

    public WorkEffortAssignmentRate() {
    }

    public WorkEffortAssignmentRate(Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public WorkEffortAssignmentRate(RateType rateType, WorkEffortPartyAssignement workEffortPartyAssignement, BigDecimal rate, Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.rateType = rateType;
        this.workEffortPartyAssignement = workEffortPartyAssignement;
        this.rate = rate;
        this.fromDt = fromDt;
        this.toDt = toDt;
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
    @JoinColumn(name="RATE_TY_ID")
    @JsonIgnore
    public RateType getRateType() 
    {
        return this.rateType;
    }
    
    public void setRateType(RateType rateType)
    {
        this.rateType = rateType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ASSIGN_ID")
    @JsonIgnore
    public WorkEffortPartyAssignement getWorkEffortPartyAssignement() 
    {
        return this.workEffortPartyAssignement;
    }
    
    public void setWorkEffortPartyAssignement(WorkEffortPartyAssignement workEffortPartyAssignement)
    {
        this.workEffortPartyAssignement = workEffortPartyAssignement;
    }
    
    @Column(name="RATE")
    public BigDecimal getRate() 
    {
        return this.rate;
    }
    
    public void setRate(BigDecimal rate) 
    {
        this.rate = rate;
    }
	
    @Column(name="FROM_DT", nullable=false, length=10)
    public Date getFromDt() 
    {
        return this.fromDt;
    }
    
    public void setFromDt(Date fromDt) 
    {
        this.fromDt = fromDt;
    }
	
    @Column(name="TO_DT", length=10)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
    }
	
    

}

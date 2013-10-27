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
import com.qurion.businesslogic.hr.model.SalaryStep;
import com.qurion.businesslogic.party.model.PartyRelationship;
import com.qurion.businesslogic.businessdata.model.PeriodType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.hr.model.PerformanceReview;

/**
 * PayHistory 
 * @author Edward Banfa
 */
@Entity
@Table(name="PAY_HISTORY"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PayHistory  extends BaseEntity implements java.io.Serializable {
	private SalaryStep salaryStep;
	private PartyRelationship partyRelationship;
	private PeriodType periodType;
	private String comment;
	private BigDecimal amount;
	private Date fromDt;
	private Date toDt;
	private Set<PerformanceReview> performanceReviews;

    public PayHistory() {
    }

    public PayHistory(BigDecimal amount, Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.amount = amount;
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PayHistory(SalaryStep salaryStep, PartyRelationship partyRelationship, PeriodType periodType, String comment, BigDecimal amount, Date fromDt, Date toDt, Set performanceReviews, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.salaryStep = salaryStep;
        this.partyRelationship = partyRelationship;
        this.periodType = periodType;
        this.comment = comment;
        this.amount = amount;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.performanceReviews = performanceReviews;
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
    @JoinColumn(name="SALARY_STEP_ID")
    @JsonIgnore
    public SalaryStep getSalaryStep() 
    {
        return this.salaryStep;
    }
    
    public void setSalaryStep(SalaryStep salaryStep)
    {
        this.salaryStep = salaryStep;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="EMPLOYMENT_ID")
    @JsonIgnore
    public PartyRelationship getPartyRelationship() 
    {
        return this.partyRelationship;
    }
    
    public void setPartyRelationship(PartyRelationship partyRelationship)
    {
        this.partyRelationship = partyRelationship;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PERIOD_TY_ID")
    @JsonIgnore
    public PeriodType getPeriodType() 
    {
        return this.periodType;
    }
    
    public void setPeriodType(PeriodType periodType)
    {
        this.periodType = periodType;
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
	
    @Column(name="AMOUNT", nullable=false)
    public BigDecimal getAmount() 
    {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="payHistory")
    @JsonIgnore
    public Set<PerformanceReview> getPerformanceReviews() 
    {
        return this.performanceReviews;
    }
    
    public void setPerformanceReviews(Set<PerformanceReview> performanceReviews) 
    {
        this.performanceReviews = performanceReviews;
    }			
		
    

}

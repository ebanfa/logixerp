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
import com.qurion.businesslogic.hr.model.PositionType;
import com.qurion.businesslogic.hr.model.SalaryStep;
import com.qurion.businesslogic.businessdata.model.PeriodType;
import com.qurion.businesslogic.hr.model.RateType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;

/**
 * PositionTypeRate 
 * @author Edward Banfa
 */
@Entity
@Table(name="POSITION_TYPE_RATE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PositionTypeRate  extends BaseEntity implements java.io.Serializable {
	private PositionType positionType;
	private SalaryStep salaryStep;
	private PeriodType periodType;
	private RateType rateType;
	private BigDecimal rate;
	private Date fromDt;
	private Date toDt;

    public PositionTypeRate() {
    }

    public PositionTypeRate(Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PositionTypeRate(PositionType positionType, SalaryStep salaryStep, PeriodType periodType, RateType rateType, BigDecimal rate, Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.positionType = positionType;
        this.salaryStep = salaryStep;
        this.periodType = periodType;
        this.rateType = rateType;
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
    @JoinColumn(name="POSITION_TY_ID")
    @JsonIgnore
    public PositionType getPositionType() 
    {
        return this.positionType;
    }
    
    public void setPositionType(PositionType positionType)
    {
        this.positionType = positionType;
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

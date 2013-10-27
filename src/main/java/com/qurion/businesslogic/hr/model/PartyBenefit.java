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
import com.qurion.businesslogic.party.model.PartyRelationship;
import com.qurion.businesslogic.hr.model.BenefitType;
import com.qurion.businesslogic.businessdata.model.PeriodType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;

/**
 * PartyBenefit 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_BENEFIT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartyBenefit  extends BaseEntity implements java.io.Serializable {
	private PartyRelationship partyRelationship;
	private BenefitType benefitType;
	private PeriodType periodType;
	private BigDecimal cost;
	private Integer actualEmplPaidPer;
	private Integer availTime;
	private Date fromDt;
	private Date toDt;

    public PartyBenefit() {
    }

    public PartyBenefit(Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PartyBenefit(PartyRelationship partyRelationship, BenefitType benefitType, PeriodType periodType, BigDecimal cost, Integer actualEmplPaidPer, Integer availTime, Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.partyRelationship = partyRelationship;
        this.benefitType = benefitType;
        this.periodType = periodType;
        this.cost = cost;
        this.actualEmplPaidPer = actualEmplPaidPer;
        this.availTime = availTime;
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
    @JoinColumn(name="BENEFIT_TY_ID")
    @JsonIgnore
    public BenefitType getBenefitType() 
    {
        return this.benefitType;
    }
    
    public void setBenefitType(BenefitType benefitType)
    {
        this.benefitType = benefitType;
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
    
    @Column(name="COST")
    public BigDecimal getCost() 
    {
        return this.cost;
    }
    
    public void setCost(BigDecimal cost) 
    {
        this.cost = cost;
    }
	
    @Column(name="ACTUAL_EMPL_PAID_PER")
    public Integer getActualEmplPaidPer() 
    {
        return this.actualEmplPaidPer;
    }
    
    public void setActualEmplPaidPer(Integer actualEmplPaidPer) 
    {
        this.actualEmplPaidPer = actualEmplPaidPer;
    }
	
    @Column(name="AVAIL_TIME")
    public Integer getAvailTime() 
    {
        return this.availTime;
    }
    
    public void setAvailTime(Integer availTime) 
    {
        this.availTime = availTime;
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

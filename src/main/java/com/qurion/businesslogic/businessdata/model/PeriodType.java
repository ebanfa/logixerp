/**
 *  Business Logic.
 */
package com.qurion.businesslogic.businessdata.model;

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

import java.util.Set;

import com.qurion.businesslogic.hr.model.PayHistory;

import java.util.Set;

import com.qurion.businesslogic.businessdata.model.StandardTimePeriod;

import java.util.Set;

import com.qurion.businesslogic.hr.model.PartyBenefit;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.AccountingPeriod;
import com.qurion.businesslogic.application.model.BaseEntity;

import java.util.Set;

import com.qurion.businesslogic.hr.model.PositionTypeRate;

/**
 * PeriodType 
 * @author Edward Banfa
 */
@Entity
@Table(name="PERIOD_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PeriodType  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Set<PayHistory> payHistories;
	private Set<StandardTimePeriod> standardTimePeriods;
	private Set<PartyBenefit> partyBenefits;
	private Set<AccountingPeriod> accountingPeriods;
	private Set<PositionTypeRate> positionTypeRates;

    public PeriodType() {
    }

    public PeriodType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PeriodType(String name, String description, Set payHistories, Set standardTimePeriods, Set partyBenefits, Set accountingPeriods, Set positionTypeRates, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.payHistories = payHistories;
        this.standardTimePeriods = standardTimePeriods;
        this.partyBenefits = partyBenefits;
        this.accountingPeriods = accountingPeriods;
        this.positionTypeRates = positionTypeRates;
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
    
    @Column(name="NAME", nullable=false, length=75)
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
	
    @Column(name="DESCRIPTION", length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="periodType")
    @JsonIgnore
    public Set<PayHistory> getPayHistories() 
    {
        return this.payHistories;
    }
    
    public void setPayHistories(Set<PayHistory> payHistories) 
    {
        this.payHistories = payHistories;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="periodType")
    @JsonIgnore
    public Set<StandardTimePeriod> getStandardTimePeriods() 
    {
        return this.standardTimePeriods;
    }
    
    public void setStandardTimePeriods(Set<StandardTimePeriod> standardTimePeriods) 
    {
        this.standardTimePeriods = standardTimePeriods;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="periodType")
    @JsonIgnore
    public Set<PartyBenefit> getPartyBenefits() 
    {
        return this.partyBenefits;
    }
    
    public void setPartyBenefits(Set<PartyBenefit> partyBenefits) 
    {
        this.partyBenefits = partyBenefits;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="periodType")
    @JsonIgnore
    public Set<AccountingPeriod> getAccountingPeriods() 
    {
        return this.accountingPeriods;
    }
    
    public void setAccountingPeriods(Set<AccountingPeriod> accountingPeriods) 
    {
        this.accountingPeriods = accountingPeriods;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="periodType")
    @JsonIgnore
    public Set<PositionTypeRate> getPositionTypeRates() 
    {
        return this.positionTypeRates;
    }
    
    public void setPositionTypeRates(Set<PositionTypeRate> positionTypeRates) 
    {
        this.positionTypeRates = positionTypeRates;
    }			
		
    

}

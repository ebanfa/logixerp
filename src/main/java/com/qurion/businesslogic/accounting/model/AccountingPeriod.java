/**
 *  Business Logic.
 */
package com.qurion.businesslogic.accounting.model;

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

import com.qurion.businesslogic.accounting.model.AccountingPeriod;
import com.qurion.businesslogic.businessdata.model.PeriodType;
import com.qurion.businesslogic.party.model.Party;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.OrganizationGlAccountBalance;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.AccountingPeriod;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * AccountingPeriod 
 * @author Edward Banfa
 */
@Entity
@Table(name="ACCOUNTING_PERIOD"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AccountingPeriod  extends BaseEntity implements java.io.Serializable {
	private AccountingPeriod accountingPeriod;
	private PeriodType periodType;
	private Party party;
	private String name;
	private Integer periodNum;
	private String description;
	private Set<OrganizationGlAccountBalance> organizationGlAccountBalances;
	private Set<AccountingPeriod> accountingPeriods;

    public AccountingPeriod() {
    }

    public AccountingPeriod(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public AccountingPeriod(AccountingPeriod accountingPeriod, PeriodType periodType, Party party, String name, Integer periodNum, String description, Set organizationGlAccountBalances, Set accountingPeriods, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.accountingPeriod = accountingPeriod;
        this.periodType = periodType;
        this.party = party;
        this.name = name;
        this.periodNum = periodNum;
        this.description = description;
        //this.organizationGlAccountBalances = organizationGlAccountBalances;
        //this.accountingPeriods = accountingPeriods;
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
    @JoinColumn(name="PARENT_PERIOD_TY_ID")
    @JsonIgnore
    public AccountingPeriod getAccountingPeriod() 
    {
        return this.accountingPeriod;
    }
    
    public void setAccountingPeriod(AccountingPeriod accountingPeriod)
    {
        this.accountingPeriod = accountingPeriod;
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
    @JoinColumn(name="ORGANIZATION_ID")
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
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
	
    @Column(name="PERIOD_NUM")
    public Integer getPeriodNum() 
    {
        return this.periodNum;
    }
    
    public void setPeriodNum(Integer periodNum) 
    {
        this.periodNum = periodNum;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="accountingPeriod")
    @JsonIgnore
    public Set<OrganizationGlAccountBalance> getOrganizationGlAccountBalances() 
    {
        return this.organizationGlAccountBalances;
    }
    
    public void setOrganizationGlAccountBalances(Set<OrganizationGlAccountBalance> organizationGlAccountBalances) 
    {
        this.organizationGlAccountBalances = organizationGlAccountBalances;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="accountingPeriod")
    @JsonIgnore
    public Set<AccountingPeriod> getAccountingPeriods() 
    {
        return this.accountingPeriods;
    }
    
    public void setAccountingPeriods(Set<AccountingPeriod> accountingPeriods) 
    {
        this.accountingPeriods = accountingPeriods;
    }			
		
    

}

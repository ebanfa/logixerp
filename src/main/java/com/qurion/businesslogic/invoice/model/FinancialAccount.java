/**
 *  Business Logic.
 */
package com.qurion.businesslogic.invoice.model;

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
import com.qurion.businesslogic.invoice.model.FinancialAccountType;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.FinancialAccountRole;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.FinancialAccountTransaction;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.Payment;

/**
 * FinancialAccount 
 * @author Edward Banfa
 */
@Entity
@Table(name="FINANCIAL_ACCOUNT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class FinancialAccount  extends BaseEntity implements java.io.Serializable {
	private FinancialAccountType financialAccountType;
	private String name;
	private String description;
	private Set<FinancialAccountRole> financialAccountRoles;
	private Set<FinancialAccountTransaction> financialAccountTransactions;
	private Set<Payment> payments;

    public FinancialAccount() {
    }

    public FinancialAccount(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public FinancialAccount(FinancialAccountType financialAccountType, String name, String description, Set financialAccountRoles, Set financialAccountTransactions, Set payments, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.financialAccountType = financialAccountType;
        this.name = name;
        this.description = description;
        this.financialAccountRoles = financialAccountRoles;
        this.financialAccountTransactions = financialAccountTransactions;
        this.payments = payments;
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
    @JoinColumn(name="FIN_ACCT_TY_ID")
    @JsonIgnore
    public FinancialAccountType getFinancialAccountType() 
    {
        return this.financialAccountType;
    }
    
    public void setFinancialAccountType(FinancialAccountType financialAccountType)
    {
        this.financialAccountType = financialAccountType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="financialAccount")
    @JsonIgnore
    public Set<FinancialAccountRole> getFinancialAccountRoles() 
    {
        return this.financialAccountRoles;
    }
    
    public void setFinancialAccountRoles(Set<FinancialAccountRole> financialAccountRoles) 
    {
        this.financialAccountRoles = financialAccountRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="financialAccount")
    @JsonIgnore
    public Set<FinancialAccountTransaction> getFinancialAccountTransactions() 
    {
        return this.financialAccountTransactions;
    }
    
    public void setFinancialAccountTransactions(Set<FinancialAccountTransaction> financialAccountTransactions) 
    {
        this.financialAccountTransactions = financialAccountTransactions;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="financialAccount")
    @JsonIgnore
    public Set<Payment> getPayments() 
    {
        return this.payments;
    }
    
    public void setPayments(Set<Payment> payments) 
    {
        this.payments = payments;
    }			
		
    

}

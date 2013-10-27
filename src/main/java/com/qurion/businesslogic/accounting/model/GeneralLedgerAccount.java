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

import com.qurion.businesslogic.accounting.model.GeneralLedgerAccountType;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.OrganizationGeneralLedgerAccount;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.GlBudgetXref;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * GeneralLedgerAccount 
 * @author Edward Banfa
 */
@Entity
@Table(name="GENERAL_LEDGER_ACCOUNT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GeneralLedgerAccount  extends BaseEntity implements java.io.Serializable {
	private GeneralLedgerAccountType generalLedgerAccountType;
	private String name;
	private String description;
	private Set<OrganizationGeneralLedgerAccount> organizationGeneralLedgerAccounts;
	private Set<GlBudgetXref> glBudgetXrefs;

    public GeneralLedgerAccount() {
    }

    public GeneralLedgerAccount(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public GeneralLedgerAccount(GeneralLedgerAccountType generalLedgerAccountType, String name, String description, Set organizationGeneralLedgerAccounts, Set glBudgetXrefs, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.generalLedgerAccountType = generalLedgerAccountType;
        this.name = name;
        this.description = description;
        this.organizationGeneralLedgerAccounts = organizationGeneralLedgerAccounts;
        this.glBudgetXrefs = glBudgetXrefs;
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
    @JoinColumn(name="GL_ACCT_TY_ID")
    @JsonIgnore
    public GeneralLedgerAccountType getGeneralLedgerAccountType() 
    {
        return this.generalLedgerAccountType;
    }
    
    public void setGeneralLedgerAccountType(GeneralLedgerAccountType generalLedgerAccountType)
    {
        this.generalLedgerAccountType = generalLedgerAccountType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="generalLedgerAccount")
    @JsonIgnore
    public Set<OrganizationGeneralLedgerAccount> getOrganizationGeneralLedgerAccounts() 
    {
        return this.organizationGeneralLedgerAccounts;
    }
    
    public void setOrganizationGeneralLedgerAccounts(Set<OrganizationGeneralLedgerAccount> organizationGeneralLedgerAccounts) 
    {
        this.organizationGeneralLedgerAccounts = organizationGeneralLedgerAccounts;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="generalLedgerAccount")
    @JsonIgnore
    public Set<GlBudgetXref> getGlBudgetXrefs() 
    {
        return this.glBudgetXrefs;
    }
    
    public void setGlBudgetXrefs(Set<GlBudgetXref> glBudgetXrefs) 
    {
        this.glBudgetXrefs = glBudgetXrefs;
    }			
		
    

}

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
import com.qurion.businesslogic.invoice.model.FinancialAccount;
import com.qurion.businesslogic.invoice.model.FinancialAccountRoleType;
import com.qurion.businesslogic.party.model.Party;

import java.util.Date;
import java.util.Date;

/**
 * FinancialAccountRole 
 * @author Edward Banfa
 */
@Entity
@Table(name="FINANCIAL_ACCOUNT_ROLE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class FinancialAccountRole  extends BaseEntity implements java.io.Serializable {
	private FinancialAccount financialAccount;
	private FinancialAccountRoleType financialAccountRoleType;
	private Party party;
	private Date fromDt;
	private Date toDt;

    public FinancialAccountRole() {
    }

    public FinancialAccountRole(Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public FinancialAccountRole(FinancialAccount financialAccount, FinancialAccountRoleType financialAccountRoleType, Party party, Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.financialAccount = financialAccount;
        this.financialAccountRoleType = financialAccountRoleType;
        this.party = party;
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
    @JoinColumn(name="FIN_ACCT_ID")
    @JsonIgnore
    public FinancialAccount getFinancialAccount() 
    {
        return this.financialAccount;
    }
    
    public void setFinancialAccount(FinancialAccount financialAccount)
    {
        this.financialAccount = financialAccount;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ROLE_TY_ID")
    @JsonIgnore
    public FinancialAccountRoleType getFinancialAccountRoleType() 
    {
        return this.financialAccountRoleType;
    }
    
    public void setFinancialAccountRoleType(FinancialAccountRoleType financialAccountRoleType)
    {
        this.financialAccountRoleType = financialAccountRoleType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_ID")
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
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

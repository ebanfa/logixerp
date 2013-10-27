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

import com.qurion.businesslogic.product.model.ProductCategory;
import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.product.model.Product;
import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.accounting.model.GeneralLedgerAccount;

import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.accounting.model.OrganizationGlAccountBalance;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * OrganizationGeneralLedgerAccount 
 * @author Edward Banfa
 */
@Entity
@Table(name="ORGANIZATION_GENERAL_LEDGER_ACCOUNT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OrganizationGeneralLedgerAccount  extends BaseEntity implements java.io.Serializable {
	private ProductCategory productCategory;
	private Party partyByRefPartyId;
	private Product product;
	private Party partyByOrganizationId;
	private GeneralLedgerAccount generalLedgerAccount;
	private String description;
	private Date fromDt;
	private Date toDt;
	private Set<OrganizationGlAccountBalance> organizationGlAccountBalances;

    public OrganizationGeneralLedgerAccount() {
    }

    public OrganizationGeneralLedgerAccount(Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public OrganizationGeneralLedgerAccount(ProductCategory productCategory, Party partyByRefPartyId, Product product, Party partyByOrganizationId, GeneralLedgerAccount generalLedgerAccount, String description, Date fromDt, Date toDt, Set organizationGlAccountBalances, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.productCategory = productCategory;
        this.partyByRefPartyId = partyByRefPartyId;
        this.product = product;
        this.partyByOrganizationId = partyByOrganizationId;
        this.generalLedgerAccount = generalLedgerAccount;
        this.description = description;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.organizationGlAccountBalances = organizationGlAccountBalances;
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
    @JoinColumn(name="PRODUCT_CAT_ID")
    @JsonIgnore
    public ProductCategory getProductCategory() 
    {
        return this.productCategory;
    }
    
    public void setProductCategory(ProductCategory productCategory)
    {
        this.productCategory = productCategory;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="REF_PARTY_ID")
    @JsonIgnore
    public Party getPartyByRefPartyId() 
    {
        return this.partyByRefPartyId;
    }
    
    public void setPartyByRefPartyId(Party partyByRefPartyId)
    {
        this.partyByRefPartyId = partyByRefPartyId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ID")
    @JsonIgnore
    public Product getProduct() 
    {
        return this.product;
    }
    
    public void setProduct(Product product)
    {
        this.product = product;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ORGANIZATION_ID")
    @JsonIgnore
    public Party getPartyByOrganizationId() 
    {
        return this.partyByOrganizationId;
    }
    
    public void setPartyByOrganizationId(Party partyByOrganizationId)
    {
        this.partyByOrganizationId = partyByOrganizationId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GL_ACCOUNT_ID")
    @JsonIgnore
    public GeneralLedgerAccount getGeneralLedgerAccount() 
    {
        return this.generalLedgerAccount;
    }
    
    public void setGeneralLedgerAccount(GeneralLedgerAccount generalLedgerAccount)
    {
        this.generalLedgerAccount = generalLedgerAccount;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="organizationGeneralLedgerAccount")
    @JsonIgnore
    public Set<OrganizationGlAccountBalance> getOrganizationGlAccountBalances() 
    {
        return this.organizationGlAccountBalances;
    }
    
    public void setOrganizationGlAccountBalances(Set<OrganizationGlAccountBalance> organizationGlAccountBalances) 
    {
        this.organizationGlAccountBalances = organizationGlAccountBalances;
    }			
		
    

}

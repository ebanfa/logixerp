/**
 *  Business Logic.
 */
package com.qurion.businesslogic.product.model;

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

import com.qurion.businesslogic.product.model.ProductCategoryType;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductPriceComponent;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.SalesTaxLookup;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductCategoryClassification;

import java.util.Set;

import com.qurion.businesslogic.party.model.MarketInterest;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.OrganizationGeneralLedgerAccount;
import com.qurion.businesslogic.application.model.BaseEntity;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductCategoryRollup;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductCategoryRollup;

/**
 * ProductCategory 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_CATEGORY"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductCategory  extends BaseEntity implements java.io.Serializable {
	private ProductCategoryType productCategoryType;
	private String name;
	private String description;
	private Set<ProductPriceComponent> productPriceComponents;
	private Set<SalesTaxLookup> salesTaxLookups;
	private Set<ProductCategoryClassification> productCategoryClassifications;
	private Set<MarketInterest> marketInterests;
	private Set<OrganizationGeneralLedgerAccount> organizationGeneralLedgerAccounts;
	private Set<ProductCategoryRollup> productCategoryRollupsForParentProdCatId;
	private Set<ProductCategoryRollup> productCategoryRollupsForChildProdCatId;

    public ProductCategory() {
    }

    public ProductCategory(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ProductCategory(ProductCategoryType productCategoryType, String name, String description, Set productPriceComponents, Set salesTaxLookups, Set productCategoryClassifications, Set marketInterests, Set organizationGeneralLedgerAccounts, Set productCategoryRollupsForParentProdCatId, Set productCategoryRollupsForChildProdCatId, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.productCategoryType = productCategoryType;
        this.name = name;
        this.description = description;
        this.productPriceComponents = productPriceComponents;
        this.salesTaxLookups = salesTaxLookups;
        this.productCategoryClassifications = productCategoryClassifications;
        this.marketInterests = marketInterests;
        this.organizationGeneralLedgerAccounts = organizationGeneralLedgerAccounts;
        this.productCategoryRollupsForParentProdCatId = productCategoryRollupsForParentProdCatId;
        this.productCategoryRollupsForChildProdCatId = productCategoryRollupsForChildProdCatId;
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
    @JoinColumn(name="PROD_CAT_TY_ID")
    @JsonIgnore
    public ProductCategoryType getProductCategoryType() 
    {
        return this.productCategoryType;
    }
    
    public void setProductCategoryType(ProductCategoryType productCategoryType)
    {
        this.productCategoryType = productCategoryType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productCategory")
    @JsonIgnore
    public Set<ProductPriceComponent> getProductPriceComponents() 
    {
        return this.productPriceComponents;
    }
    
    public void setProductPriceComponents(Set<ProductPriceComponent> productPriceComponents) 
    {
        this.productPriceComponents = productPriceComponents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productCategory")
    @JsonIgnore
    public Set<SalesTaxLookup> getSalesTaxLookups() 
    {
        return this.salesTaxLookups;
    }
    
    public void setSalesTaxLookups(Set<SalesTaxLookup> salesTaxLookups) 
    {
        this.salesTaxLookups = salesTaxLookups;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productCategory")
    @JsonIgnore
    public Set<ProductCategoryClassification> getProductCategoryClassifications() 
    {
        return this.productCategoryClassifications;
    }
    
    public void setProductCategoryClassifications(Set<ProductCategoryClassification> productCategoryClassifications) 
    {
        this.productCategoryClassifications = productCategoryClassifications;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productCategory")
    @JsonIgnore
    public Set<MarketInterest> getMarketInterests() 
    {
        return this.marketInterests;
    }
    
    public void setMarketInterests(Set<MarketInterest> marketInterests) 
    {
        this.marketInterests = marketInterests;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productCategory")
    @JsonIgnore
    public Set<OrganizationGeneralLedgerAccount> getOrganizationGeneralLedgerAccounts() 
    {
        return this.organizationGeneralLedgerAccounts;
    }
    
    public void setOrganizationGeneralLedgerAccounts(Set<OrganizationGeneralLedgerAccount> organizationGeneralLedgerAccounts) 
    {
        this.organizationGeneralLedgerAccounts = organizationGeneralLedgerAccounts;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productCategoryByParentProdCatId")
    @JsonIgnore
    public Set<ProductCategoryRollup> getProductCategoryRollupsForParentProdCatId() 
    {
        return this.productCategoryRollupsForParentProdCatId;
    }
    
    public void setProductCategoryRollupsForParentProdCatId(Set<ProductCategoryRollup> productCategoryRollupsForParentProdCatId) 
    {
        this.productCategoryRollupsForParentProdCatId = productCategoryRollupsForParentProdCatId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productCategoryByChildProdCatId")
    @JsonIgnore
    public Set<ProductCategoryRollup> getProductCategoryRollupsForChildProdCatId() 
    {
        return this.productCategoryRollupsForChildProdCatId;
    }
    
    public void setProductCategoryRollupsForChildProdCatId(Set<ProductCategoryRollup> productCategoryRollupsForChildProdCatId) 
    {
        this.productCategoryRollupsForChildProdCatId = productCategoryRollupsForChildProdCatId;
    }			
		
    

}

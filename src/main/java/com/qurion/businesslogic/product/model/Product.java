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

import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.businessdata.model.Uom;
import com.qurion.businesslogic.product.model.ProductType;

import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortProductStandard;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductCategoryClassification;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderItem;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.AgreementProductApplicability;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.InvoiceItem;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortType;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductIncompatibility;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductIncompatibility;

import java.util.Set;

import com.qurion.businesslogic.product.model.InventoryItem;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductComplement;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductComplement;

import java.util.Set;

import com.qurion.businesslogic.product.model.SupplierProduct;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductComponent;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductComponent;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductSubstitute;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductSubstitute;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.QuoteItem;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentItemFeature;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentItem;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentReceipt;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductObsolescence;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductObsolescence;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.OrganizationGeneralLedgerAccount;
import com.qurion.businesslogic.application.model.BaseEntity;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductCostComponent;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductFeatureApplicability;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.Requirement;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductPriceComponent;

/**
 * Product 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Product  extends BaseEntity implements java.io.Serializable {
	private Party party;
	private Uom uom;
	private ProductType productType;
	private String name;
	private Date introductionDt;
	private Date salesDiscDt;
	private Date supportDiscDt;
	private Integer remarks;
	private Set<WorkEffortProductStandard> workEffortProductStandards;
	private Set<ProductCategoryClassification> productCategoryClassifications;
	private Set<OrderItem> orderItems;
	private Set<AgreementProductApplicability> agreementProductApplicabilities;
	private Set<InvoiceItem> invoiceItems;
	private Set<WorkEffortType> workEffortTypes;
	private Set<ProductIncompatibility> productIncompatibilitiesForForProdId;
	private Set<ProductIncompatibility> productIncompatibilitiesForInProdId;
	private Set<InventoryItem> inventoryItems;
	private Set<ProductComplement> productComplementsForForProdId;
	private Set<ProductComplement> productComplementsForInProdId;
	private Set<SupplierProduct> supplierProducts;
	private Set<ProductComponent> productComponentsForForProdId;
	private Set<ProductComponent> productComponentsForInProdId;
	private Set<ProductSubstitute> productSubstitutesForForProdId;
	private Set<ProductSubstitute> productSubstitutesForInProdId;
	private Set<QuoteItem> quoteItems;
	private Set<ShipmentItemFeature> shipmentItemFeatures;
	private Set<ShipmentItem> shipmentItems;
	private Set<ShipmentReceipt> shipmentReceipts;
	private Set<ProductObsolescence> productObsolescencesForInProdId;
	private Set<ProductObsolescence> productObsolescencesForForProdId;
	private Set<OrganizationGeneralLedgerAccount> organizationGeneralLedgerAccounts;
	private Set<ProductCostComponent> productCostComponents;
	private Set<ProductFeatureApplicability> productFeatureApplicabilities;
	private Set<Requirement> requirements;
	private Set<ProductPriceComponent> productPriceComponents;

    public Product() {
    }

    public Product(String name, Date introductionDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.introductionDt = introductionDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Product(Party party, Uom uom, ProductType productType, String name, Date introductionDt, Date salesDiscDt, Date supportDiscDt, Integer remarks, Set workEffortProductStandards, Set productCategoryClassifications, Set orderItems, Set agreementProductApplicabilities, Set invoiceItems, Set workEffortTypes, Set productIncompatibilitiesForForProdId, Set productIncompatibilitiesForInProdId, Set inventoryItems, Set productComplementsForForProdId, Set productComplementsForInProdId, Set supplierProducts, Set productComponentsForForProdId, Set productComponentsForInProdId, Set productSubstitutesForForProdId, Set productSubstitutesForInProdId, Set quoteItems, Set shipmentItemFeatures, Set shipmentItems, Set shipmentReceipts, Set productObsolescencesForInProdId, Set productObsolescencesForForProdId, Set organizationGeneralLedgerAccounts, Set productCostComponents, Set productFeatureApplicabilities, Set requirements, Set productPriceComponents, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.party = party;
        this.uom = uom;
        this.productType = productType;
        this.name = name;
        this.introductionDt = introductionDt;
        this.salesDiscDt = salesDiscDt;
        this.supportDiscDt = supportDiscDt;
        this.remarks = remarks;
        this.workEffortProductStandards = workEffortProductStandards;
        this.productCategoryClassifications = productCategoryClassifications;
        this.orderItems = orderItems;
        this.agreementProductApplicabilities = agreementProductApplicabilities;
        this.invoiceItems = invoiceItems;
        this.workEffortTypes = workEffortTypes;
        this.productIncompatibilitiesForForProdId = productIncompatibilitiesForForProdId;
        this.productIncompatibilitiesForInProdId = productIncompatibilitiesForInProdId;
        this.inventoryItems = inventoryItems;
        this.productComplementsForForProdId = productComplementsForForProdId;
        this.productComplementsForInProdId = productComplementsForInProdId;
        this.supplierProducts = supplierProducts;
        this.productComponentsForForProdId = productComponentsForForProdId;
        this.productComponentsForInProdId = productComponentsForInProdId;
        this.productSubstitutesForForProdId = productSubstitutesForForProdId;
        this.productSubstitutesForInProdId = productSubstitutesForInProdId;
        this.quoteItems = quoteItems;
        this.shipmentItemFeatures = shipmentItemFeatures;
        this.shipmentItems = shipmentItems;
        this.shipmentReceipts = shipmentReceipts;
        this.productObsolescencesForInProdId = productObsolescencesForInProdId;
        this.productObsolescencesForForProdId = productObsolescencesForForProdId;
        this.organizationGeneralLedgerAccounts = organizationGeneralLedgerAccounts;
        this.productCostComponents = productCostComponents;
        this.productFeatureApplicabilities = productFeatureApplicabilities;
        this.requirements = requirements;
        this.productPriceComponents = productPriceComponents;
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
    @JoinColumn(name="MANUFACTURER_ID")
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="UOM_ID")
    @JsonIgnore
    public Uom getUom() 
    {
        return this.uom;
    }
    
    public void setUom(Uom uom)
    {
        this.uom = uom;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_TY_ID")
    @JsonIgnore
    public ProductType getProductType() 
    {
        return this.productType;
    }
    
    public void setProductType(ProductType productType)
    {
        this.productType = productType;
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
	
    @Column(name="INTRODUCTION_DT", nullable=false, length=10)
    public Date getIntroductionDt() 
    {
        return this.introductionDt;
    }
    
    public void setIntroductionDt(Date introductionDt) 
    {
        this.introductionDt = introductionDt;
    }
	
    @Column(name="SALES_DISC_DT", length=10)
    public Date getSalesDiscDt() 
    {
        return this.salesDiscDt;
    }
    
    public void setSalesDiscDt(Date salesDiscDt) 
    {
        this.salesDiscDt = salesDiscDt;
    }
	
    @Column(name="SUPPORT_DISC_DT", length=10)
    public Date getSupportDiscDt() 
    {
        return this.supportDiscDt;
    }
    
    public void setSupportDiscDt(Date supportDiscDt) 
    {
        this.supportDiscDt = supportDiscDt;
    }
	
    @Column(name="REMARKS")
    public Integer getRemarks() 
    {
        return this.remarks;
    }
    
    public void setRemarks(Integer remarks) 
    {
        this.remarks = remarks;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<WorkEffortProductStandard> getWorkEffortProductStandards() 
    {
        return this.workEffortProductStandards;
    }
    
    public void setWorkEffortProductStandards(Set<WorkEffortProductStandard> workEffortProductStandards) 
    {
        this.workEffortProductStandards = workEffortProductStandards;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<ProductCategoryClassification> getProductCategoryClassifications() 
    {
        return this.productCategoryClassifications;
    }
    
    public void setProductCategoryClassifications(Set<ProductCategoryClassification> productCategoryClassifications) 
    {
        this.productCategoryClassifications = productCategoryClassifications;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<OrderItem> getOrderItems() 
    {
        return this.orderItems;
    }
    
    public void setOrderItems(Set<OrderItem> orderItems) 
    {
        this.orderItems = orderItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<AgreementProductApplicability> getAgreementProductApplicabilities() 
    {
        return this.agreementProductApplicabilities;
    }
    
    public void setAgreementProductApplicabilities(Set<AgreementProductApplicability> agreementProductApplicabilities) 
    {
        this.agreementProductApplicabilities = agreementProductApplicabilities;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<InvoiceItem> getInvoiceItems() 
    {
        return this.invoiceItems;
    }
    
    public void setInvoiceItems(Set<InvoiceItem> invoiceItems) 
    {
        this.invoiceItems = invoiceItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<WorkEffortType> getWorkEffortTypes() 
    {
        return this.workEffortTypes;
    }
    
    public void setWorkEffortTypes(Set<WorkEffortType> workEffortTypes) 
    {
        this.workEffortTypes = workEffortTypes;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productByForProdId")
    @JsonIgnore
    public Set<ProductIncompatibility> getProductIncompatibilitiesForForProdId() 
    {
        return this.productIncompatibilitiesForForProdId;
    }
    
    public void setProductIncompatibilitiesForForProdId(Set<ProductIncompatibility> productIncompatibilitiesForForProdId) 
    {
        this.productIncompatibilitiesForForProdId = productIncompatibilitiesForForProdId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productByInProdId")
    @JsonIgnore
    public Set<ProductIncompatibility> getProductIncompatibilitiesForInProdId() 
    {
        return this.productIncompatibilitiesForInProdId;
    }
    
    public void setProductIncompatibilitiesForInProdId(Set<ProductIncompatibility> productIncompatibilitiesForInProdId) 
    {
        this.productIncompatibilitiesForInProdId = productIncompatibilitiesForInProdId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<InventoryItem> getInventoryItems() 
    {
        return this.inventoryItems;
    }
    
    public void setInventoryItems(Set<InventoryItem> inventoryItems) 
    {
        this.inventoryItems = inventoryItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productByForProdId")
    @JsonIgnore
    public Set<ProductComplement> getProductComplementsForForProdId() 
    {
        return this.productComplementsForForProdId;
    }
    
    public void setProductComplementsForForProdId(Set<ProductComplement> productComplementsForForProdId) 
    {
        this.productComplementsForForProdId = productComplementsForForProdId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productByInProdId")
    @JsonIgnore
    public Set<ProductComplement> getProductComplementsForInProdId() 
    {
        return this.productComplementsForInProdId;
    }
    
    public void setProductComplementsForInProdId(Set<ProductComplement> productComplementsForInProdId) 
    {
        this.productComplementsForInProdId = productComplementsForInProdId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<SupplierProduct> getSupplierProducts() 
    {
        return this.supplierProducts;
    }
    
    public void setSupplierProducts(Set<SupplierProduct> supplierProducts) 
    {
        this.supplierProducts = supplierProducts;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productByForProdId")
    @JsonIgnore
    public Set<ProductComponent> getProductComponentsForForProdId() 
    {
        return this.productComponentsForForProdId;
    }
    
    public void setProductComponentsForForProdId(Set<ProductComponent> productComponentsForForProdId) 
    {
        this.productComponentsForForProdId = productComponentsForForProdId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productByInProdId")
    @JsonIgnore
    public Set<ProductComponent> getProductComponentsForInProdId() 
    {
        return this.productComponentsForInProdId;
    }
    
    public void setProductComponentsForInProdId(Set<ProductComponent> productComponentsForInProdId) 
    {
        this.productComponentsForInProdId = productComponentsForInProdId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productByForProdId")
    @JsonIgnore
    public Set<ProductSubstitute> getProductSubstitutesForForProdId() 
    {
        return this.productSubstitutesForForProdId;
    }
    
    public void setProductSubstitutesForForProdId(Set<ProductSubstitute> productSubstitutesForForProdId) 
    {
        this.productSubstitutesForForProdId = productSubstitutesForForProdId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productByInProdId")
    @JsonIgnore
    public Set<ProductSubstitute> getProductSubstitutesForInProdId() 
    {
        return this.productSubstitutesForInProdId;
    }
    
    public void setProductSubstitutesForInProdId(Set<ProductSubstitute> productSubstitutesForInProdId) 
    {
        this.productSubstitutesForInProdId = productSubstitutesForInProdId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<QuoteItem> getQuoteItems() 
    {
        return this.quoteItems;
    }
    
    public void setQuoteItems(Set<QuoteItem> quoteItems) 
    {
        this.quoteItems = quoteItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<ShipmentItemFeature> getShipmentItemFeatures() 
    {
        return this.shipmentItemFeatures;
    }
    
    public void setShipmentItemFeatures(Set<ShipmentItemFeature> shipmentItemFeatures) 
    {
        this.shipmentItemFeatures = shipmentItemFeatures;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<ShipmentItem> getShipmentItems() 
    {
        return this.shipmentItems;
    }
    
    public void setShipmentItems(Set<ShipmentItem> shipmentItems) 
    {
        this.shipmentItems = shipmentItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<ShipmentReceipt> getShipmentReceipts() 
    {
        return this.shipmentReceipts;
    }
    
    public void setShipmentReceipts(Set<ShipmentReceipt> shipmentReceipts) 
    {
        this.shipmentReceipts = shipmentReceipts;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productByInProdId")
    @JsonIgnore
    public Set<ProductObsolescence> getProductObsolescencesForInProdId() 
    {
        return this.productObsolescencesForInProdId;
    }
    
    public void setProductObsolescencesForInProdId(Set<ProductObsolescence> productObsolescencesForInProdId) 
    {
        this.productObsolescencesForInProdId = productObsolescencesForInProdId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productByForProdId")
    @JsonIgnore
    public Set<ProductObsolescence> getProductObsolescencesForForProdId() 
    {
        return this.productObsolescencesForForProdId;
    }
    
    public void setProductObsolescencesForForProdId(Set<ProductObsolescence> productObsolescencesForForProdId) 
    {
        this.productObsolescencesForForProdId = productObsolescencesForForProdId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<OrganizationGeneralLedgerAccount> getOrganizationGeneralLedgerAccounts() 
    {
        return this.organizationGeneralLedgerAccounts;
    }
    
    public void setOrganizationGeneralLedgerAccounts(Set<OrganizationGeneralLedgerAccount> organizationGeneralLedgerAccounts) 
    {
        this.organizationGeneralLedgerAccounts = organizationGeneralLedgerAccounts;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<ProductCostComponent> getProductCostComponents() 
    {
        return this.productCostComponents;
    }
    
    public void setProductCostComponents(Set<ProductCostComponent> productCostComponents) 
    {
        this.productCostComponents = productCostComponents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<ProductFeatureApplicability> getProductFeatureApplicabilities() 
    {
        return this.productFeatureApplicabilities;
    }
    
    public void setProductFeatureApplicabilities(Set<ProductFeatureApplicability> productFeatureApplicabilities) 
    {
        this.productFeatureApplicabilities = productFeatureApplicabilities;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<Requirement> getRequirements() 
    {
        return this.requirements;
    }
    
    public void setRequirements(Set<Requirement> requirements) 
    {
        this.requirements = requirements;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<ProductPriceComponent> getProductPriceComponents() 
    {
        return this.productPriceComponents;
    }
    
    public void setProductPriceComponents(Set<ProductPriceComponent> productPriceComponents) 
    {
        this.productPriceComponents = productPriceComponents;
    }			
		
    

}

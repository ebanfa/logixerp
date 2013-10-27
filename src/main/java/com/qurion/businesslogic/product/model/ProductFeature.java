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

import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.product.model.ProductFeatureType;
import com.qurion.businesslogic.businessdata.model.Uom;
import com.qurion.businesslogic.product.model.ProductFeatureCategory;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderItem;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductCostComponent;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.DesiredFeature;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductPriceComponent;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.InvoiceItem;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductFeatureApplicability;

/**
 * ProductFeature 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_FEATURE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductFeature  extends BaseEntity implements java.io.Serializable {
	private ProductFeatureType productFeatureType;
	private Uom uom;
	private ProductFeatureCategory productFeatureCategory;
	private String name;
	private String description;
	private Set<OrderItem> orderItems;
	private Set<ProductCostComponent> productCostComponents;
	private Set<DesiredFeature> desiredFeatures;
	private Set<ProductPriceComponent> productPriceComponents;
	private Set<InvoiceItem> invoiceItems;
	private Set<ProductFeatureApplicability> productFeatureApplicabilities;

    public ProductFeature() {
    }

    public ProductFeature(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ProductFeature(ProductFeatureType productFeatureType, Uom uom, ProductFeatureCategory productFeatureCategory, String name, String description, Set orderItems, Set productCostComponents, Set desiredFeatures, Set productPriceComponents, Set invoiceItems, Set productFeatureApplicabilities, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.productFeatureType = productFeatureType;
        this.uom = uom;
        this.productFeatureCategory = productFeatureCategory;
        this.name = name;
        this.description = description;
        this.orderItems = orderItems;
        this.productCostComponents = productCostComponents;
        this.desiredFeatures = desiredFeatures;
        this.productPriceComponents = productPriceComponents;
        this.invoiceItems = invoiceItems;
        this.productFeatureApplicabilities = productFeatureApplicabilities;
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
    @JoinColumn(name="PROD_FEAT_TY_ID")
    @JsonIgnore
    public ProductFeatureType getProductFeatureType() 
    {
        return this.productFeatureType;
    }
    
    public void setProductFeatureType(ProductFeatureType productFeatureType)
    {
        this.productFeatureType = productFeatureType;
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
    @JoinColumn(name="PROD_FEAT_CAT_ID")
    @JsonIgnore
    public ProductFeatureCategory getProductFeatureCategory() 
    {
        return this.productFeatureCategory;
    }
    
    public void setProductFeatureCategory(ProductFeatureCategory productFeatureCategory)
    {
        this.productFeatureCategory = productFeatureCategory;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productFeature")
    @JsonIgnore
    public Set<OrderItem> getOrderItems() 
    {
        return this.orderItems;
    }
    
    public void setOrderItems(Set<OrderItem> orderItems) 
    {
        this.orderItems = orderItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productFeature")
    @JsonIgnore
    public Set<ProductCostComponent> getProductCostComponents() 
    {
        return this.productCostComponents;
    }
    
    public void setProductCostComponents(Set<ProductCostComponent> productCostComponents) 
    {
        this.productCostComponents = productCostComponents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productFeature")
    @JsonIgnore
    public Set<DesiredFeature> getDesiredFeatures() 
    {
        return this.desiredFeatures;
    }
    
    public void setDesiredFeatures(Set<DesiredFeature> desiredFeatures) 
    {
        this.desiredFeatures = desiredFeatures;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productFeature")
    @JsonIgnore
    public Set<ProductPriceComponent> getProductPriceComponents() 
    {
        return this.productPriceComponents;
    }
    
    public void setProductPriceComponents(Set<ProductPriceComponent> productPriceComponents) 
    {
        this.productPriceComponents = productPriceComponents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productFeature")
    @JsonIgnore
    public Set<InvoiceItem> getInvoiceItems() 
    {
        return this.invoiceItems;
    }
    
    public void setInvoiceItems(Set<InvoiceItem> invoiceItems) 
    {
        this.invoiceItems = invoiceItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productFeature")
    @JsonIgnore
    public Set<ProductFeatureApplicability> getProductFeatureApplicabilities() 
    {
        return this.productFeatureApplicabilities;
    }
    
    public void setProductFeatureApplicabilities(Set<ProductFeatureApplicability> productFeatureApplicabilities) 
    {
        this.productFeatureApplicabilities = productFeatureApplicabilities;
    }			
		
    

}

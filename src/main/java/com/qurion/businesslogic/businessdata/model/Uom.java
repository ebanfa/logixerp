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

import com.qurion.businesslogic.application.model.BaseEntity;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductFeature;

import java.util.Set;

import com.qurion.businesslogic.fixedasset.model.FixedAsset;

import java.util.Set;

import com.qurion.businesslogic.product.model.Product;

import java.util.Set;

import com.qurion.businesslogic.businessdata.model.UomConversion;

import java.util.Set;

import com.qurion.businesslogic.businessdata.model.UomConversion;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductPriceComponent;

/**
 * Uom 
 * @author Edward Banfa
 */
@Entity
@Table(name="UOM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Uom  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Set<ProductFeature> productFeatures;
	private Set<FixedAsset> fixedAssets;
	private Set<Product> products;
	private Set<UomConversion> uomConversionsForToUomId;
	private Set<UomConversion> uomConversionsForFromUomId;
	private Set<ProductPriceComponent> productPriceComponents;

    public Uom() {
    }

    public Uom(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Uom(String name, String description, Set productFeatures, Set fixedAssets, Set products, Set uomConversionsForToUomId, Set uomConversionsForFromUomId, Set productPriceComponents, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.productFeatures = productFeatures;
        this.fixedAssets = fixedAssets;
        this.products = products;
        this.uomConversionsForToUomId = uomConversionsForToUomId;
        this.uomConversionsForFromUomId = uomConversionsForFromUomId;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="uom")
    @JsonIgnore
    public Set<ProductFeature> getProductFeatures() 
    {
        return this.productFeatures;
    }
    
    public void setProductFeatures(Set<ProductFeature> productFeatures) 
    {
        this.productFeatures = productFeatures;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="uom")
    @JsonIgnore
    public Set<FixedAsset> getFixedAssets() 
    {
        return this.fixedAssets;
    }
    
    public void setFixedAssets(Set<FixedAsset> fixedAssets) 
    {
        this.fixedAssets = fixedAssets;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="uom")
    @JsonIgnore
    public Set<Product> getProducts() 
    {
        return this.products;
    }
    
    public void setProducts(Set<Product> products) 
    {
        this.products = products;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="uomByToUomId")
    @JsonIgnore
    public Set<UomConversion> getUomConversionsForToUomId() 
    {
        return this.uomConversionsForToUomId;
    }
    
    public void setUomConversionsForToUomId(Set<UomConversion> uomConversionsForToUomId) 
    {
        this.uomConversionsForToUomId = uomConversionsForToUomId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="uomByFromUomId")
    @JsonIgnore
    public Set<UomConversion> getUomConversionsForFromUomId() 
    {
        return this.uomConversionsForFromUomId;
    }
    
    public void setUomConversionsForFromUomId(Set<UomConversion> uomConversionsForFromUomId) 
    {
        this.uomConversionsForFromUomId = uomConversionsForFromUomId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="uom")
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

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
import com.qurion.businesslogic.product.model.ProductPriceComponentType;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductPriceComponent;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductPriceComponentType;

/**
 * ProductPriceComponentType 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_PRICE_COMPONENT_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductPriceComponentType  extends BaseEntity implements java.io.Serializable {
	private ProductPriceComponentType productPriceComponentType;
	private String name;
	private String description;
	private Set<ProductPriceComponent> productPriceComponents;
	private Set<ProductPriceComponentType> productPriceComponentTypes;

    public ProductPriceComponentType() {
    }

    public ProductPriceComponentType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ProductPriceComponentType(ProductPriceComponentType productPriceComponentType, String name, String description, Set productPriceComponents, Set productPriceComponentTypes, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.productPriceComponentType = productPriceComponentType;
        this.name = name;
        this.description = description;
        this.productPriceComponents = productPriceComponents;
        this.productPriceComponentTypes = productPriceComponentTypes;
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
    @JoinColumn(name="PARENT_COMP_ID")
    @JsonIgnore
    public ProductPriceComponentType getProductPriceComponentType() 
    {
        return this.productPriceComponentType;
    }
    
    public void setProductPriceComponentType(ProductPriceComponentType productPriceComponentType)
    {
        this.productPriceComponentType = productPriceComponentType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productPriceComponentType")
    @JsonIgnore
    public Set<ProductPriceComponent> getProductPriceComponents() 
    {
        return this.productPriceComponents;
    }
    
    public void setProductPriceComponents(Set<ProductPriceComponent> productPriceComponents) 
    {
        this.productPriceComponents = productPriceComponents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productPriceComponentType")
    @JsonIgnore
    public Set<ProductPriceComponentType> getProductPriceComponentTypes() 
    {
        return this.productPriceComponentTypes;
    }
    
    public void setProductPriceComponentTypes(Set<ProductPriceComponentType> productPriceComponentTypes) 
    {
        this.productPriceComponentTypes = productPriceComponentTypes;
    }			
		
    

}

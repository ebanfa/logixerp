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
import com.qurion.businesslogic.product.model.ProductCostComponentType;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductCostComponentType;

import java.util.Set;

import com.qurion.businesslogic.product.model.ProductCostComponent;

/**
 * ProductCostComponentType 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_COST_COMPONENT_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductCostComponentType  extends BaseEntity implements java.io.Serializable {
	private ProductCostComponentType productCostComponentType;
	private String name;
	private String description;
	private Set<ProductCostComponentType> productCostComponentTypes;
	private Set<ProductCostComponent> productCostComponents;

    public ProductCostComponentType() {
    }

    public ProductCostComponentType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ProductCostComponentType(ProductCostComponentType productCostComponentType, String name, String description, Set productCostComponentTypes, Set productCostComponents, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.productCostComponentType = productCostComponentType;
        this.name = name;
        this.description = description;
        this.productCostComponentTypes = productCostComponentTypes;
        this.productCostComponents = productCostComponents;
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
    public ProductCostComponentType getProductCostComponentType() 
    {
        return this.productCostComponentType;
    }
    
    public void setProductCostComponentType(ProductCostComponentType productCostComponentType)
    {
        this.productCostComponentType = productCostComponentType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productCostComponentType")
    @JsonIgnore
    public Set<ProductCostComponentType> getProductCostComponentTypes() 
    {
        return this.productCostComponentTypes;
    }
    
    public void setProductCostComponentTypes(Set<ProductCostComponentType> productCostComponentTypes) 
    {
        this.productCostComponentTypes = productCostComponentTypes;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productCostComponentType")
    @JsonIgnore
    public Set<ProductCostComponent> getProductCostComponents() 
    {
        return this.productCostComponents;
    }
    
    public void setProductCostComponents(Set<ProductCostComponent> productCostComponents) 
    {
        this.productCostComponents = productCostComponents;
    }			
		
    

}

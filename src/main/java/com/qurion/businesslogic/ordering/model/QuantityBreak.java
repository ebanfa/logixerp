/**
 *  Business Logic.
 */
package com.qurion.businesslogic.ordering.model;

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

import com.qurion.businesslogic.product.model.ProductPriceComponent;

/**
 * QuantityBreak 
 * @author Edward Banfa
 */
@Entity
@Table(name="QUANTITY_BREAK"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class QuantityBreak  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Integer fromQuantity;
	private Integer toQuantity;
	private Set<ProductPriceComponent> productPriceComponents;

    public QuantityBreak() {
    }

    public QuantityBreak(String name, Integer fromQuantity, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.fromQuantity = fromQuantity;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public QuantityBreak(String name, String description, Integer fromQuantity, Integer toQuantity, Set productPriceComponents, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.fromQuantity = fromQuantity;
        this.toQuantity = toQuantity;
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
	
    @Column(name="FROM_QUANTITY", nullable=false)
    public Integer getFromQuantity() 
    {
        return this.fromQuantity;
    }
    
    public void setFromQuantity(Integer fromQuantity) 
    {
        this.fromQuantity = fromQuantity;
    }
	
    @Column(name="TO_QUANTITY")
    public Integer getToQuantity() 
    {
        return this.toQuantity;
    }
    
    public void setToQuantity(Integer toQuantity) 
    {
        this.toQuantity = toQuantity;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="quantityBreak")
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

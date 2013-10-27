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

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Set;

import com.qurion.businesslogic.product.model.ProductPriceComponent;

/**
 * OrderValue 
 * @author Edward Banfa
 */
@Entity
@Table(name="ORDER_VALUE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OrderValue  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private BigDecimal fromAmt;
	private BigDecimal toAmt;
	private Set<ProductPriceComponent> productPriceComponents;

    public OrderValue() {
    }

    public OrderValue(String name, BigDecimal fromAmt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.fromAmt = fromAmt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public OrderValue(String name, String description, BigDecimal fromAmt, BigDecimal toAmt, Set productPriceComponents, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.fromAmt = fromAmt;
        this.toAmt = toAmt;
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
	
    @Column(name="FROM_AMT", nullable=false)
    public BigDecimal getFromAmt() 
    {
        return this.fromAmt;
    }
    
    public void setFromAmt(BigDecimal fromAmt) 
    {
        this.fromAmt = fromAmt;
    }
	
    @Column(name="TO_AMT")
    public BigDecimal getToAmt() 
    {
        return this.toAmt;
    }
    
    public void setToAmt(BigDecimal toAmt) 
    {
        this.toAmt = toAmt;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orderValue")
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

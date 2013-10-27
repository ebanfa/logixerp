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
import com.qurion.businesslogic.product.model.Product;
import com.qurion.businesslogic.product.model.ProductFeature;
import com.qurion.businesslogic.product.model.ProductCostComponentType;
import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.businessdata.model.GeoBoundry;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;

/**
 * ProductCostComponent 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_COST_COMPONENT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductCostComponent  extends BaseEntity implements java.io.Serializable {
	private Product product;
	private ProductFeature productFeature;
	private ProductCostComponentType productCostComponentType;
	private Party party;
	private GeoBoundry geoBoundry;
	private String name;
	private String description;
	private String comment;
	private BigDecimal cost;
	private Date fromDt;
	private Date toDt;

    public ProductCostComponent() {
    }

    public ProductCostComponent(String name, BigDecimal cost, Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.cost = cost;
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ProductCostComponent(Product product, ProductFeature productFeature, ProductCostComponentType productCostComponentType, Party party, GeoBoundry geoBoundry, String name, String description, String comment, BigDecimal cost, Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.product = product;
        this.productFeature = productFeature;
        this.productCostComponentType = productCostComponentType;
        this.party = party;
        this.geoBoundry = geoBoundry;
        this.name = name;
        this.description = description;
        this.comment = comment;
        this.cost = cost;
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
    @JoinColumn(name="PROD_ID")
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
    @JoinColumn(name="PROD_FEAT_ID")
    @JsonIgnore
    public ProductFeature getProductFeature() 
    {
        return this.productFeature;
    }
    
    public void setProductFeature(ProductFeature productFeature)
    {
        this.productFeature = productFeature;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COST_COMP_ID")
    @JsonIgnore
    public ProductCostComponentType getProductCostComponentType() 
    {
        return this.productCostComponentType;
    }
    
    public void setProductCostComponentType(ProductCostComponentType productCostComponentType)
    {
        this.productCostComponentType = productCostComponentType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ORGANIZATION_ID")
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
    @JoinColumn(name="GEO_BOUNDRY_ID")
    @JsonIgnore
    public GeoBoundry getGeoBoundry() 
    {
        return this.geoBoundry;
    }
    
    public void setGeoBoundry(GeoBoundry geoBoundry)
    {
        this.geoBoundry = geoBoundry;
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
	
    @Column(name="COMMENT", length=150)
    public String getComment() 
    {
        return this.comment;
    }
    
    public void setComment(String comment) 
    {
        this.comment = comment;
    }
	
    @Column(name="COST", nullable=false)
    public BigDecimal getCost() 
    {
        return this.cost;
    }
    
    public void setCost(BigDecimal cost) 
    {
        this.cost = cost;
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

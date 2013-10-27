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
import com.qurion.businesslogic.product.model.ProductCategory;
import com.qurion.businesslogic.businessdata.model.GeoBoundry;

/**
 * SalesTaxLookup 
 * @author Edward Banfa
 */
@Entity
@Table(name="SALES_TAX_LOOKUP"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SalesTaxLookup  extends BaseEntity implements java.io.Serializable {
	private ProductCategory productCategory;
	private GeoBoundry geoBoundry;
	private Integer saleTaxPercentage;
	private String description;

    public SalesTaxLookup() {
    }

    public SalesTaxLookup(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public SalesTaxLookup(ProductCategory productCategory, GeoBoundry geoBoundry, Integer saleTaxPercentage, String description, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.productCategory = productCategory;
        this.geoBoundry = geoBoundry;
        this.saleTaxPercentage = saleTaxPercentage;
        this.description = description;
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
    @JoinColumn(name="PROD_CAT_ID")
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
    
    @Column(name="SALE_TAX_PERCENTAGE")
    public Integer getSaleTaxPercentage() 
    {
        return this.saleTaxPercentage;
    }
    
    public void setSaleTaxPercentage(Integer saleTaxPercentage) 
    {
        this.saleTaxPercentage = saleTaxPercentage;
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
	
    

}

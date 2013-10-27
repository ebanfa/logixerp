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
import com.qurion.businesslogic.product.model.ProductCategory;
import com.qurion.businesslogic.product.model.Product;

import java.util.Date;
import java.util.Date;

/**
 * ProductCategoryClassification 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_CATEGORY_CLASSIFICATION"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductCategoryClassification  extends BaseEntity implements java.io.Serializable {
	private ProductCategory productCategory;
	private Product product;
	private Character primaryFg;
	private Date fromDt;
	private Date toDt;
	private String remarks;

    public ProductCategoryClassification() {
    }

    public ProductCategoryClassification(Character primaryFg, Date fromDt, String remarks, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.primaryFg = primaryFg;
        this.fromDt = fromDt;
        this.remarks = remarks;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ProductCategoryClassification(ProductCategory productCategory, Product product, Character primaryFg, Date fromDt, Date toDt, String remarks, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.productCategory = productCategory;
        this.product = product;
        this.primaryFg = primaryFg;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.remarks = remarks;
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
    
    @Column(name="PRIMARY_FG", nullable=false, length=1)
    public Character getPrimaryFg() 
    {
        return this.primaryFg;
    }
    
    public void setPrimaryFg(Character primaryFg) 
    {
        this.primaryFg = primaryFg;
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
	
    @Column(name="REMARKS", nullable=false, length=150)
    public String getRemarks() 
    {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }
	
    

}

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
import com.qurion.businesslogic.product.model.ProductCategory;

/**
 * ProductCategoryRollup 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_CATEGORY_ROLLUP"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductCategoryRollup  extends BaseEntity implements java.io.Serializable {
	private ProductCategory productCategoryByParentProdCatId;
	private ProductCategory productCategoryByChildProdCatId;

    public ProductCategoryRollup() {
    }

    public ProductCategoryRollup(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ProductCategoryRollup(ProductCategory productCategoryByParentProdCatId, ProductCategory productCategoryByChildProdCatId, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.productCategoryByParentProdCatId = productCategoryByParentProdCatId;
        this.productCategoryByChildProdCatId = productCategoryByChildProdCatId;
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
    @JoinColumn(name="PARENT_PROD_CAT_ID")
    @JsonIgnore
    public ProductCategory getProductCategoryByParentProdCatId() 
    {
        return this.productCategoryByParentProdCatId;
    }
    
    public void setProductCategoryByParentProdCatId(ProductCategory productCategoryByParentProdCatId)
    {
        this.productCategoryByParentProdCatId = productCategoryByParentProdCatId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CHILD_PROD_CAT_ID")
    @JsonIgnore
    public ProductCategory getProductCategoryByChildProdCatId() 
    {
        return this.productCategoryByChildProdCatId;
    }
    
    public void setProductCategoryByChildProdCatId(ProductCategory productCategoryByChildProdCatId)
    {
        this.productCategoryByChildProdCatId = productCategoryByChildProdCatId;
    }
    
    

}

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
import com.qurion.businesslogic.product.model.Product;

import java.util.Date;
import java.util.Date;

/**
 * ProductIncompatibility 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_INCOMPATIBILITY"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductIncompatibility  extends BaseEntity implements java.io.Serializable {
	private Product productByInProdId;
	private Product productByForProdId;
	private String name;
	private String description;
	private Date fromDt;
	private Date toDt;
	private String reason;

    public ProductIncompatibility() {
    }

    public ProductIncompatibility(String name, Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ProductIncompatibility(Product productByInProdId, Product productByForProdId, String name, String description, Date fromDt, Date toDt, String reason, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.productByInProdId = productByInProdId;
        this.productByForProdId = productByForProdId;
        this.name = name;
        this.description = description;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.reason = reason;
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
    @JoinColumn(name="IN_PROD_ID")
    @JsonIgnore
    public Product getProductByInProdId() 
    {
        return this.productByInProdId;
    }
    
    public void setProductByInProdId(Product productByInProdId)
    {
        this.productByInProdId = productByInProdId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FOR_PROD_ID")
    @JsonIgnore
    public Product getProductByForProdId() 
    {
        return this.productByForProdId;
    }
    
    public void setProductByForProdId(Product productByForProdId)
    {
        this.productByForProdId = productByForProdId;
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
	
    @Column(name="FROM_DT", nullable=false, length=10)
    public Date getFromDt() 
    {
        return this.fromDt;
    }
    
    public void setFromDt(Date fromDt) 
    {
        this.fromDt = fromDt;
    }
	
    @Column(name="TO_DT", nullable=false, length=10)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
    }
	
    @Column(name="REASON", length=150)
    public String getReason() 
    {
        return this.reason;
    }
    
    public void setReason(String reason) 
    {
        this.reason = reason;
    }
	
    

}

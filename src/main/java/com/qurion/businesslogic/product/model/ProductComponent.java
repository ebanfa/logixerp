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
 * ProductComponent 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_COMPONENT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductComponent  extends BaseEntity implements java.io.Serializable {
	private Product productByInProdId;
	private Product productByForProdId;
	private String name;
	private String description;
	private Date fromDt;
	private Date toDt;
	private Integer quantityUsed;
	private String comment;
	private String instruction;

    public ProductComponent() {
    }

    public ProductComponent(String name, Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
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

    public ProductComponent(Product productByInProdId, Product productByForProdId, String name, String description, Date fromDt, Date toDt, Integer quantityUsed, String comment, String instruction, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.productByInProdId = productByInProdId;
        this.productByForProdId = productByForProdId;
        this.name = name;
        this.description = description;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.quantityUsed = quantityUsed;
        this.comment = comment;
        this.instruction = instruction;
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
	
    @Column(name="QUANTITY_USED")
    public Integer getQuantityUsed() 
    {
        return this.quantityUsed;
    }
    
    public void setQuantityUsed(Integer quantityUsed) 
    {
        this.quantityUsed = quantityUsed;
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
	
    @Column(name="INSTRUCTION", length=150)
    public String getInstruction() 
    {
        return this.instruction;
    }
    
    public void setInstruction(String instruction) 
    {
        this.instruction = instruction;
    }
	
    

}

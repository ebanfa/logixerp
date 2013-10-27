/**
 *  Business Logic.
 */
package com.qurion.businesslogic.workeffort.model;

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
import com.qurion.businesslogic.workeffort.model.WorkEffortType;
import com.qurion.businesslogic.product.model.Product;

import java.math.BigDecimal;

/**
 * WorkEffortProductStandard 
 * @author Edward Banfa
 */
@Entity
@Table(name="WORK_EFFORT_PRODUCT_STANDARD"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WorkEffortProductStandard  extends BaseEntity implements java.io.Serializable {
	private WorkEffortType workEffortType;
	private Product product;
	private Integer estQuantity;
	private BigDecimal estCost;

    public WorkEffortProductStandard() {
    }

    public WorkEffortProductStandard(Integer estQuantity, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.estQuantity = estQuantity;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public WorkEffortProductStandard(WorkEffortType workEffortType, Product product, Integer estQuantity, BigDecimal estCost, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.workEffortType = workEffortType;
        this.product = product;
        this.estQuantity = estQuantity;
        this.estCost = estCost;
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
    @JoinColumn(name="WRK_EFFORT_TY_ID")
    @JsonIgnore
    public WorkEffortType getWorkEffortType() 
    {
        return this.workEffortType;
    }
    
    public void setWorkEffortType(WorkEffortType workEffortType)
    {
        this.workEffortType = workEffortType;
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
    
    @Column(name="EST_QUANTITY", nullable=false)
    public Integer getEstQuantity() 
    {
        return this.estQuantity;
    }
    
    public void setEstQuantity(Integer estQuantity) 
    {
        this.estQuantity = estQuantity;
    }
	
    @Column(name="EST_COST")
    public BigDecimal getEstCost() 
    {
        return this.estCost;
    }
    
    public void setEstCost(BigDecimal estCost) 
    {
        this.estCost = estCost;
    }
	
    

}

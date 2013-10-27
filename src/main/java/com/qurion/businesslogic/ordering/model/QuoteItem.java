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
import com.qurion.businesslogic.ordering.model.Quote;
import com.qurion.businesslogic.product.model.Product;
import com.qurion.businesslogic.ordering.model.RequestItem;
import com.qurion.businesslogic.workeffort.model.WorkEffort;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.ordering.model.QuoteTerm;

/**
 * QuoteItem 
 * @author Edward Banfa
 */
@Entity
@Table(name="QUOTE_ITEM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class QuoteItem  extends BaseEntity implements java.io.Serializable {
	private Quote quote;
	private Product product;
	private RequestItem requestItem;
	private WorkEffort workEffort;
	private Integer quantityValue;
	private BigDecimal quoteUnitPrice;
	private Date deleiveryDt;
	private String description;
	private Set<QuoteTerm> quoteTerms;

    public QuoteItem() {
    }

    public QuoteItem(BigDecimal quoteUnitPrice, Date deleiveryDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.quoteUnitPrice = quoteUnitPrice;
        this.deleiveryDt = deleiveryDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public QuoteItem(Quote quote, Product product, RequestItem requestItem, WorkEffort workEffort, Integer quantityValue, BigDecimal quoteUnitPrice, Date deleiveryDt, String description, Set quoteTerms, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.quote = quote;
        this.product = product;
        this.requestItem = requestItem;
        this.workEffort = workEffort;
        this.quantityValue = quantityValue;
        this.quoteUnitPrice = quoteUnitPrice;
        this.deleiveryDt = deleiveryDt;
        this.description = description;
        this.quoteTerms = quoteTerms;
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
    @JoinColumn(name="QUOTE_ID")
    @JsonIgnore
    public Quote getQuote() 
    {
        return this.quote;
    }
    
    public void setQuote(Quote quote)
    {
        this.quote = quote;
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
    @JoinColumn(name="REQ_ITEM_ID")
    @JsonIgnore
    public RequestItem getRequestItem() 
    {
        return this.requestItem;
    }
    
    public void setRequestItem(RequestItem requestItem)
    {
        this.requestItem = requestItem;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="WRK_EFFORT_ID")
    @JsonIgnore
    public WorkEffort getWorkEffort() 
    {
        return this.workEffort;
    }
    
    public void setWorkEffort(WorkEffort workEffort)
    {
        this.workEffort = workEffort;
    }
    
    @Column(name="QUANTITY_VALUE")
    public Integer getQuantityValue() 
    {
        return this.quantityValue;
    }
    
    public void setQuantityValue(Integer quantityValue) 
    {
        this.quantityValue = quantityValue;
    }
	
    @Column(name="QUOTE_UNIT_PRICE", nullable=false)
    public BigDecimal getQuoteUnitPrice() 
    {
        return this.quoteUnitPrice;
    }
    
    public void setQuoteUnitPrice(BigDecimal quoteUnitPrice) 
    {
        this.quoteUnitPrice = quoteUnitPrice;
    }
	
    @Column(name="DELEIVERY_DT", nullable=false, length=10)
    public Date getDeleiveryDt() 
    {
        return this.deleiveryDt;
    }
    
    public void setDeleiveryDt(Date deleiveryDt) 
    {
        this.deleiveryDt = deleiveryDt;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="quoteItem")
    @JsonIgnore
    public Set<QuoteTerm> getQuoteTerms() 
    {
        return this.quoteTerms;
    }
    
    public void setQuoteTerms(Set<QuoteTerm> quoteTerms) 
    {
        this.quoteTerms = quoteTerms;
    }			
		
    

}

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
import com.qurion.businesslogic.businessdata.model.TermType;
import com.qurion.businesslogic.ordering.model.Quote;
import com.qurion.businesslogic.ordering.model.QuoteItem;

/**
 * QuoteTerm 
 * @author Edward Banfa
 */
@Entity
@Table(name="QUOTE_TERM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class QuoteTerm  extends BaseEntity implements java.io.Serializable {
	private TermType termType;
	private Quote quote;
	private QuoteItem quoteItem;
	private Integer termValue;

    public QuoteTerm() {
    }

    public QuoteTerm(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public QuoteTerm(TermType termType, Quote quote, QuoteItem quoteItem, Integer termValue, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.termType = termType;
        this.quote = quote;
        this.quoteItem = quoteItem;
        this.termValue = termValue;
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
    @JoinColumn(name="TERM_TY_ID")
    @JsonIgnore
    public TermType getTermType() 
    {
        return this.termType;
    }
    
    public void setTermType(TermType termType)
    {
        this.termType = termType;
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
    @JoinColumn(name="QUOTE_ITEM_ID")
    @JsonIgnore
    public QuoteItem getQuoteItem() 
    {
        return this.quoteItem;
    }
    
    public void setQuoteItem(QuoteItem quoteItem)
    {
        this.quoteItem = quoteItem;
    }
    
    @Column(name="TERM_VALUE")
    public Integer getTermValue() 
    {
        return this.termValue;
    }
    
    public void setTermValue(Integer termValue) 
    {
        this.termValue = termValue;
    }
	
    

}

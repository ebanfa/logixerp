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
import com.qurion.businesslogic.ordering.model.QuoteType;

import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.ordering.model.QuoteRole;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.QuoteTerm;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.QuoteItem;

/**
 * Quote 
 * @author Edward Banfa
 */
@Entity
@Table(name="QUOTE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Quote  extends BaseEntity implements java.io.Serializable {
	private QuoteType quoteType;
	private String name;
	private String description;
	private Date issueDt;
	private Date validFromDt;
	private Date validToDt;
	private Set<QuoteRole> quoteRoles;
	private Set<QuoteTerm> quoteTerms;
	private Set<QuoteItem> quoteItems;

    public Quote() {
    }

    public Quote(String name, Date issueDt, Date validFromDt, Date validToDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.issueDt = issueDt;
        this.validFromDt = validFromDt;
        this.validToDt = validToDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Quote(QuoteType quoteType, String name, String description, Date issueDt, Date validFromDt, Date validToDt, Set quoteRoles, Set quoteTerms, Set quoteItems, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.quoteType = quoteType;
        this.name = name;
        this.description = description;
        this.issueDt = issueDt;
        this.validFromDt = validFromDt;
        this.validToDt = validToDt;
        this.quoteRoles = quoteRoles;
        this.quoteTerms = quoteTerms;
        this.quoteItems = quoteItems;
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
    @JoinColumn(name="QUOTE_TY_ID")
    @JsonIgnore
    public QuoteType getQuoteType() 
    {
        return this.quoteType;
    }
    
    public void setQuoteType(QuoteType quoteType)
    {
        this.quoteType = quoteType;
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
	
    @Column(name="ISSUE_DT", nullable=false, length=10)
    public Date getIssueDt() 
    {
        return this.issueDt;
    }
    
    public void setIssueDt(Date issueDt) 
    {
        this.issueDt = issueDt;
    }
	
    @Column(name="VALID_FROM_DT", nullable=false, length=10)
    public Date getValidFromDt() 
    {
        return this.validFromDt;
    }
    
    public void setValidFromDt(Date validFromDt) 
    {
        this.validFromDt = validFromDt;
    }
	
    @Column(name="VALID_TO_DT", nullable=false, length=10)
    public Date getValidToDt() 
    {
        return this.validToDt;
    }
    
    public void setValidToDt(Date validToDt) 
    {
        this.validToDt = validToDt;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="quote")
    @JsonIgnore
    public Set<QuoteRole> getQuoteRoles() 
    {
        return this.quoteRoles;
    }
    
    public void setQuoteRoles(Set<QuoteRole> quoteRoles) 
    {
        this.quoteRoles = quoteRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="quote")
    @JsonIgnore
    public Set<QuoteTerm> getQuoteTerms() 
    {
        return this.quoteTerms;
    }
    
    public void setQuoteTerms(Set<QuoteTerm> quoteTerms) 
    {
        this.quoteTerms = quoteTerms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="quote")
    @JsonIgnore
    public Set<QuoteItem> getQuoteItems() 
    {
        return this.quoteItems;
    }
    
    public void setQuoteItems(Set<QuoteItem> quoteItems) 
    {
        this.quoteItems = quoteItems;
    }			
		
    

}

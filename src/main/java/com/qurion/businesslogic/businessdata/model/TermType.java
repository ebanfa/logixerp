/**
 *  Business Logic.
 */
package com.qurion.businesslogic.businessdata.model;

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

import java.util.Set;

import com.qurion.businesslogic.ordering.model.AgreementTerm;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.QuoteTerm;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.InvoiceTerm;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderTerm;

/**
 * TermType 
 * @author Edward Banfa
 */
@Entity
@Table(name="TERM_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TermType  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Set<AgreementTerm> agreementTerms;
	private Set<QuoteTerm> quoteTerms;
	private Set<InvoiceTerm> invoiceTerms;
	private Set<OrderTerm> orderTerms;

    public TermType() {
    }

    public TermType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public TermType(String name, String description, Set agreementTerms, Set quoteTerms, Set invoiceTerms, Set orderTerms, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.agreementTerms = agreementTerms;
        this.quoteTerms = quoteTerms;
        this.invoiceTerms = invoiceTerms;
        this.orderTerms = orderTerms;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="termType")
    @JsonIgnore
    public Set<AgreementTerm> getAgreementTerms() 
    {
        return this.agreementTerms;
    }
    
    public void setAgreementTerms(Set<AgreementTerm> agreementTerms) 
    {
        this.agreementTerms = agreementTerms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="termType")
    @JsonIgnore
    public Set<QuoteTerm> getQuoteTerms() 
    {
        return this.quoteTerms;
    }
    
    public void setQuoteTerms(Set<QuoteTerm> quoteTerms) 
    {
        this.quoteTerms = quoteTerms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="termType")
    @JsonIgnore
    public Set<InvoiceTerm> getInvoiceTerms() 
    {
        return this.invoiceTerms;
    }
    
    public void setInvoiceTerms(Set<InvoiceTerm> invoiceTerms) 
    {
        this.invoiceTerms = invoiceTerms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="termType")
    @JsonIgnore
    public Set<OrderTerm> getOrderTerms() 
    {
        return this.orderTerms;
    }
    
    public void setOrderTerms(Set<OrderTerm> orderTerms) 
    {
        this.orderTerms = orderTerms;
    }			
		
    

}

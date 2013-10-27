/**
 *  Business Logic.
 */
package com.qurion.businesslogic.invoice.model;

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
import com.qurion.businesslogic.party.model.ContactMechanism;

import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.invoice.model.BillingAccountRole;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.Invoice;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.PaymentApplication;

/**
 * BillingAccount 
 * @author Edward Banfa
 */
@Entity
@Table(name="BILLING_ACCOUNT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BillingAccount  extends BaseEntity implements java.io.Serializable {
	private ContactMechanism contactMechanism;
	private String name;
	private String description;
	private Date fromDt;
	private Date toDt;
	private Set<BillingAccountRole> billingAccountRoles;
	private Set<Invoice> invoices;
	private Set<PaymentApplication> paymentApplications;

    public BillingAccount() {
    }

    public BillingAccount(String name, Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public BillingAccount(ContactMechanism contactMechanism, String name, String description, Date fromDt, Date toDt, Set billingAccountRoles, Set invoices, Set paymentApplications, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.contactMechanism = contactMechanism;
        this.name = name;
        this.description = description;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.billingAccountRoles = billingAccountRoles;
        this.invoices = invoices;
        this.paymentApplications = paymentApplications;
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
    @JoinColumn(name="TO_CONT_MECH_ID")
    @JsonIgnore
    public ContactMechanism getContactMechanism() 
    {
        return this.contactMechanism;
    }
    
    public void setContactMechanism(ContactMechanism contactMechanism)
    {
        this.contactMechanism = contactMechanism;
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
	
    @Column(name="TO_DT", length=10)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="billingAccount")
    @JsonIgnore
    public Set<BillingAccountRole> getBillingAccountRoles() 
    {
        return this.billingAccountRoles;
    }
    
    public void setBillingAccountRoles(Set<BillingAccountRole> billingAccountRoles) 
    {
        this.billingAccountRoles = billingAccountRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="billingAccount")
    @JsonIgnore
    public Set<Invoice> getInvoices() 
    {
        return this.invoices;
    }
    
    public void setInvoices(Set<Invoice> invoices) 
    {
        this.invoices = invoices;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="billingAccount")
    @JsonIgnore
    public Set<PaymentApplication> getPaymentApplications() 
    {
        return this.paymentApplications;
    }
    
    public void setPaymentApplications(Set<PaymentApplication> paymentApplications) 
    {
        this.paymentApplications = paymentApplications;
    }			
		
    

}

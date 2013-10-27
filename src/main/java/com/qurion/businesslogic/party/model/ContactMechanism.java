/**
 *  Business Logic.
 */
package com.qurion.businesslogic.party.model;

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
import com.qurion.businesslogic.party.model.ContactMechanismType;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderItemContactMechanism;

import java.util.Set;

import com.qurion.businesslogic.party.model.PostalAddress;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.BillingAccount;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderContactMechanism;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.Invoice;

import java.util.Set;

import com.qurion.businesslogic.party.model.TelecommunicationsNumber;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.Invoice;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.RespondingParty;

import java.util.Set;

import com.qurion.businesslogic.party.model.ElectronicAddress;

import java.util.Set;

import com.qurion.businesslogic.party.model.PartyContactMechanism;

/**
 * ContactMechanism 
 * @author Edward Banfa
 */
@Entity
@Table(name="CONTACT_MECHANISM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ContactMechanism  extends BaseEntity implements java.io.Serializable {
	private ContactMechanismType contactMechanismType;
	private Set<OrderItemContactMechanism> orderItemContactMechanisms;
	private Set<PostalAddress> postalAddresses;
	private Set<BillingAccount> billingAccounts;
	private Set<OrderContactMechanism> orderContactMechanisms;
	private Set<Invoice> invoicesForFromContMechId;
	private Set<TelecommunicationsNumber> telecommunicationsNumbers;
	private Set<Invoice> invoicesForToContMechId;
	private Set<RespondingParty> respondingParties;
	private Set<ElectronicAddress> electronicAddresses;
	private Set<PartyContactMechanism> partyContactMechanisms;

    public ContactMechanism() {
    }

    public ContactMechanism(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ContactMechanism(ContactMechanismType contactMechanismType, Set orderItemContactMechanisms, Set postalAddresses, Set billingAccounts, Set orderContactMechanisms, Set invoicesForFromContMechId, Set telecommunicationsNumbers, Set invoicesForToContMechId, Set respondingParties, Set electronicAddresses, Set partyContactMechanisms, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.contactMechanismType = contactMechanismType;
        this.orderItemContactMechanisms = orderItemContactMechanisms;
        this.postalAddresses = postalAddresses;
        this.billingAccounts = billingAccounts;
        this.orderContactMechanisms = orderContactMechanisms;
        this.invoicesForFromContMechId = invoicesForFromContMechId;
        this.telecommunicationsNumbers = telecommunicationsNumbers;
        this.invoicesForToContMechId = invoicesForToContMechId;
        this.respondingParties = respondingParties;
        this.electronicAddresses = electronicAddresses;
        this.partyContactMechanisms = partyContactMechanisms;
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
    @JoinColumn(name="CONT_MECH_TY_ID")
    @JsonIgnore
    public ContactMechanismType getContactMechanismType() 
    {
        return this.contactMechanismType;
    }
    
    public void setContactMechanismType(ContactMechanismType contactMechanismType)
    {
        this.contactMechanismType = contactMechanismType;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanism")
    @JsonIgnore
    public Set<OrderItemContactMechanism> getOrderItemContactMechanisms() 
    {
        return this.orderItemContactMechanisms;
    }
    
    public void setOrderItemContactMechanisms(Set<OrderItemContactMechanism> orderItemContactMechanisms) 
    {
        this.orderItemContactMechanisms = orderItemContactMechanisms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanism")
    @JsonIgnore
    public Set<PostalAddress> getPostalAddresses() 
    {
        return this.postalAddresses;
    }
    
    public void setPostalAddresses(Set<PostalAddress> postalAddresses) 
    {
        this.postalAddresses = postalAddresses;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanism")
    @JsonIgnore
    public Set<BillingAccount> getBillingAccounts() 
    {
        return this.billingAccounts;
    }
    
    public void setBillingAccounts(Set<BillingAccount> billingAccounts) 
    {
        this.billingAccounts = billingAccounts;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanism")
    @JsonIgnore
    public Set<OrderContactMechanism> getOrderContactMechanisms() 
    {
        return this.orderContactMechanisms;
    }
    
    public void setOrderContactMechanisms(Set<OrderContactMechanism> orderContactMechanisms) 
    {
        this.orderContactMechanisms = orderContactMechanisms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanismByFromContMechId")
    @JsonIgnore
    public Set<Invoice> getInvoicesForFromContMechId() 
    {
        return this.invoicesForFromContMechId;
    }
    
    public void setInvoicesForFromContMechId(Set<Invoice> invoicesForFromContMechId) 
    {
        this.invoicesForFromContMechId = invoicesForFromContMechId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanism")
    @JsonIgnore
    public Set<TelecommunicationsNumber> getTelecommunicationsNumbers() 
    {
        return this.telecommunicationsNumbers;
    }
    
    public void setTelecommunicationsNumbers(Set<TelecommunicationsNumber> telecommunicationsNumbers) 
    {
        this.telecommunicationsNumbers = telecommunicationsNumbers;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanismByToContMechId")
    @JsonIgnore
    public Set<Invoice> getInvoicesForToContMechId() 
    {
        return this.invoicesForToContMechId;
    }
    
    public void setInvoicesForToContMechId(Set<Invoice> invoicesForToContMechId) 
    {
        this.invoicesForToContMechId = invoicesForToContMechId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanism")
    @JsonIgnore
    public Set<RespondingParty> getRespondingParties() 
    {
        return this.respondingParties;
    }
    
    public void setRespondingParties(Set<RespondingParty> respondingParties) 
    {
        this.respondingParties = respondingParties;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanism")
    @JsonIgnore
    public Set<ElectronicAddress> getElectronicAddresses() 
    {
        return this.electronicAddresses;
    }
    
    public void setElectronicAddresses(Set<ElectronicAddress> electronicAddresses) 
    {
        this.electronicAddresses = electronicAddresses;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanism")
    @JsonIgnore
    public Set<PartyContactMechanism> getPartyContactMechanisms() 
    {
        return this.partyContactMechanisms;
    }
    
    public void setPartyContactMechanisms(Set<PartyContactMechanism> partyContactMechanisms) 
    {
        this.partyContactMechanisms = partyContactMechanisms;
    }			
		
    

}

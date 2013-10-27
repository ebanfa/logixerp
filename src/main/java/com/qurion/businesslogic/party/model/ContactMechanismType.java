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

import java.util.Set;

import com.qurion.businesslogic.party.model.CommunicationEvent;

import java.util.Set;

import com.qurion.businesslogic.party.model.ContactMechanism;

/**
 * ContactMechanismType 
 * @author Edward Banfa
 */
@Entity
@Table(name="CONTACT_MECHANISM_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ContactMechanismType  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Set<CommunicationEvent> communicationEvents;
	private Set<ContactMechanism> contactMechanisms;

    public ContactMechanismType() {
    }

    public ContactMechanismType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ContactMechanismType(String name, String description, Set communicationEvents, Set contactMechanisms, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.communicationEvents = communicationEvents;
        this.contactMechanisms = contactMechanisms;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanismType")
    @JsonIgnore
    public Set<CommunicationEvent> getCommunicationEvents() 
    {
        return this.communicationEvents;
    }
    
    public void setCommunicationEvents(Set<CommunicationEvent> communicationEvents) 
    {
        this.communicationEvents = communicationEvents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanismType")
    @JsonIgnore
    public Set<ContactMechanism> getContactMechanisms() 
    {
        return this.contactMechanisms;
    }
    
    public void setContactMechanisms(Set<ContactMechanism> contactMechanisms) 
    {
        this.contactMechanisms = contactMechanisms;
    }			
		
    

}

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
import com.qurion.businesslogic.party.model.CommunicationEventStatus;
import com.qurion.businesslogic.party.model.CommunicationEventPurpose;
import com.qurion.businesslogic.party.model.PartyRelationship;
import com.qurion.businesslogic.party.model.Cases;
import com.qurion.businesslogic.party.model.ContactMechanismType;

import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.party.model.CommunicationEventRole;

/**
 * CommunicationEvent 
 * @author Edward Banfa
 */
@Entity
@Table(name="COMMUNICATION_EVENT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CommunicationEvent  extends BaseEntity implements java.io.Serializable {
	private CommunicationEventStatus communicationEventStatus;
	private CommunicationEventPurpose communicationEventPurpose;
	private PartyRelationship partyRelationship;
	private Cases cases;
	private ContactMechanismType contactMechanismType;
	private String name;
	private String description;
	private Date startedDt;
	private Date endedDt;
	private String remarks;
	private Set<CommunicationEventRole> communicationEventRoles;

    public CommunicationEvent() {
    }

    public CommunicationEvent(String name, Date startedDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.startedDt = startedDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public CommunicationEvent(CommunicationEventStatus communicationEventStatus, CommunicationEventPurpose communicationEventPurpose, PartyRelationship partyRelationship, Cases cases, ContactMechanismType contactMechanismType, String name, String description, Date startedDt, Date endedDt, String remarks, Set communicationEventRoles, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.communicationEventStatus = communicationEventStatus;
        this.communicationEventPurpose = communicationEventPurpose;
        this.partyRelationship = partyRelationship;
        this.cases = cases;
        this.contactMechanismType = contactMechanismType;
        this.name = name;
        this.description = description;
        this.startedDt = startedDt;
        this.endedDt = endedDt;
        this.remarks = remarks;
        this.communicationEventRoles = communicationEventRoles;
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
    @JoinColumn(name="COMM_EVENT_STATUS_ID")
    @JsonIgnore
    public CommunicationEventStatus getCommunicationEventStatus() 
    {
        return this.communicationEventStatus;
    }
    
    public void setCommunicationEventStatus(CommunicationEventStatus communicationEventStatus)
    {
        this.communicationEventStatus = communicationEventStatus;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="EVENT_PURP_ID")
    @JsonIgnore
    public CommunicationEventPurpose getCommunicationEventPurpose() 
    {
        return this.communicationEventPurpose;
    }
    
    public void setCommunicationEventPurpose(CommunicationEventPurpose communicationEventPurpose)
    {
        this.communicationEventPurpose = communicationEventPurpose;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_REL_ID")
    @JsonIgnore
    public PartyRelationship getPartyRelationship() 
    {
        return this.partyRelationship;
    }
    
    public void setPartyRelationship(PartyRelationship partyRelationship)
    {
        this.partyRelationship = partyRelationship;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CASE_ID")
    @JsonIgnore
    public Cases getCases() 
    {
        return this.cases;
    }
    
    public void setCases(Cases cases)
    {
        this.cases = cases;
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
	
    @Column(name="STARTED_DT", nullable=false, length=19)
    public Date getStartedDt() 
    {
        return this.startedDt;
    }
    
    public void setStartedDt(Date startedDt) 
    {
        this.startedDt = startedDt;
    }
	
    @Column(name="ENDED_DT", length=19)
    public Date getEndedDt() 
    {
        return this.endedDt;
    }
    
    public void setEndedDt(Date endedDt) 
    {
        this.endedDt = endedDt;
    }
	
    @Column(name="REMARKS", length=150)
    public String getRemarks() 
    {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="communicationEvent")
    @JsonIgnore
    public Set<CommunicationEventRole> getCommunicationEventRoles() 
    {
        return this.communicationEventRoles;
    }
    
    public void setCommunicationEventRoles(Set<CommunicationEventRole> communicationEventRoles) 
    {
        this.communicationEventRoles = communicationEventRoles;
    }			
		
    

}

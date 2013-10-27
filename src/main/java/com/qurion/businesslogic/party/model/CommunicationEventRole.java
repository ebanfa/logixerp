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
import com.qurion.businesslogic.party.model.CommunicationEvent;
import com.qurion.businesslogic.party.model.CommunicationEventRoleType;
import com.qurion.businesslogic.party.model.Party;

/**
 * CommunicationEventRole 
 * @author Edward Banfa
 */
@Entity
@Table(name="COMMUNICATION_EVENT_ROLE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CommunicationEventRole  extends BaseEntity implements java.io.Serializable {
	private CommunicationEvent communicationEvent;
	private CommunicationEventRoleType communicationEventRoleType;
	private Party party;

    public CommunicationEventRole() {
    }

    public CommunicationEventRole(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public CommunicationEventRole(CommunicationEvent communicationEvent, CommunicationEventRoleType communicationEventRoleType, Party party, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.communicationEvent = communicationEvent;
        this.communicationEventRoleType = communicationEventRoleType;
        this.party = party;
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
    @JoinColumn(name="EVENT_ID")
    @JsonIgnore
    public CommunicationEvent getCommunicationEvent() 
    {
        return this.communicationEvent;
    }
    
    public void setCommunicationEvent(CommunicationEvent communicationEvent)
    {
        this.communicationEvent = communicationEvent;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COMM_EVENT_ROLE_TY_ID")
    @JsonIgnore
    public CommunicationEventRoleType getCommunicationEventRoleType() 
    {
        return this.communicationEventRoleType;
    }
    
    public void setCommunicationEventRoleType(CommunicationEventRoleType communicationEventRoleType)
    {
        this.communicationEventRoleType = communicationEventRoleType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_ID")
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
    }
    
    

}

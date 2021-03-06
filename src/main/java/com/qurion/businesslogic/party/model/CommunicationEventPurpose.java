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
import com.qurion.businesslogic.party.model.CommunicationEventPurposeType;

import java.util.Set;

import com.qurion.businesslogic.party.model.CommunicationEvent;

/**
 * CommunicationEventPurpose 
 * @author Edward Banfa
 */
@Entity
@Table(name="COMMUNICATION_EVENT_PURPOSE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CommunicationEventPurpose  extends BaseEntity implements java.io.Serializable {
	private CommunicationEventPurposeType communicationEventPurposeType;
	private String name;
	private String description;
	private Set<CommunicationEvent> communicationEvents;

    public CommunicationEventPurpose() {
    }

    public CommunicationEventPurpose(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public CommunicationEventPurpose(CommunicationEventPurposeType communicationEventPurposeType, String name, String description, Set communicationEvents, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.communicationEventPurposeType = communicationEventPurposeType;
        this.name = name;
        this.description = description;
        this.communicationEvents = communicationEvents;
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
    @JoinColumn(name="EVENT_PURP_TY_ID")
    @JsonIgnore
    public CommunicationEventPurposeType getCommunicationEventPurposeType() 
    {
        return this.communicationEventPurposeType;
    }
    
    public void setCommunicationEventPurposeType(CommunicationEventPurposeType communicationEventPurposeType)
    {
        this.communicationEventPurposeType = communicationEventPurposeType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="communicationEventPurpose")
    @JsonIgnore
    public Set<CommunicationEvent> getCommunicationEvents() 
    {
        return this.communicationEvents;
    }
    
    public void setCommunicationEvents(Set<CommunicationEvent> communicationEvents) 
    {
        this.communicationEvents = communicationEvents;
    }			
		
    

}

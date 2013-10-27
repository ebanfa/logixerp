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
import com.qurion.businesslogic.party.model.CaseStatus;

import java.util.Set;

import com.qurion.businesslogic.party.model.CommunicationEvent;

import java.util.Set;

import com.qurion.businesslogic.party.model.CaseRole;

/**
 * Cases 
 * @author Edward Banfa
 */
@Entity
@Table(name="CASES"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Cases  extends BaseEntity implements java.io.Serializable {
	private CaseStatus caseStatus;
	private String name;
	private String description;
	private Set<CommunicationEvent> communicationEvents;
	private Set<CaseRole> caseRoles;

    public Cases() {
    }

    public Cases(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Cases(CaseStatus caseStatus, String name, String description, Set communicationEvents, Set caseRoles, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.caseStatus = caseStatus;
        this.name = name;
        this.description = description;
        this.communicationEvents = communicationEvents;
        this.caseRoles = caseRoles;
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
    @JoinColumn(name="CASE_STATUS_ID")
    @JsonIgnore
    public CaseStatus getCaseStatus() 
    {
        return this.caseStatus;
    }
    
    public void setCaseStatus(CaseStatus caseStatus)
    {
        this.caseStatus = caseStatus;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="cases")
    @JsonIgnore
    public Set<CommunicationEvent> getCommunicationEvents() 
    {
        return this.communicationEvents;
    }
    
    public void setCommunicationEvents(Set<CommunicationEvent> communicationEvents) 
    {
        this.communicationEvents = communicationEvents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="cases")
    @JsonIgnore
    public Set<CaseRole> getCaseRoles() 
    {
        return this.caseRoles;
    }
    
    public void setCaseRoles(Set<CaseRole> caseRoles) 
    {
        this.caseRoles = caseRoles;
    }			
		
    

}

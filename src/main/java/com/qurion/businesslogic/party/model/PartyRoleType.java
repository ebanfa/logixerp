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
import com.qurion.businesslogic.party.model.PartyRoleType;

import java.util.Set;

import com.qurion.businesslogic.party.model.PartyRole;

import java.util.Set;

import com.qurion.businesslogic.party.model.PartyContactMechanism;

import java.util.Set;

import com.qurion.businesslogic.party.model.PartyRoleType;

/**
 * PartyRoleType 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_ROLE_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartyRoleType  extends BaseEntity implements java.io.Serializable {
	private PartyRoleType partyRoleType;
	private String name;
	private String description;
	private Set<PartyRole> partyRoles;
	private Set<PartyContactMechanism> partyContactMechanisms;
	private Set<PartyRoleType> partyRoleTypes;

    public PartyRoleType() {
    }

    public PartyRoleType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PartyRoleType(PartyRoleType partyRoleType, String name, String description, Set partyRoles, Set partyContactMechanisms, Set partyRoleTypes, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.partyRoleType = partyRoleType;
        this.name = name;
        this.description = description;
        this.partyRoles = partyRoles;
        this.partyContactMechanisms = partyContactMechanisms;
        this.partyRoleTypes = partyRoleTypes;
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
    @JoinColumn(name="PARENT_ROLE_TY_ID")
    @JsonIgnore
    public PartyRoleType getPartyRoleType() 
    {
        return this.partyRoleType;
    }
    
    public void setPartyRoleType(PartyRoleType partyRoleType)
    {
        this.partyRoleType = partyRoleType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRoleType")
    @JsonIgnore
    public Set<PartyRole> getPartyRoles() 
    {
        return this.partyRoles;
    }
    
    public void setPartyRoles(Set<PartyRole> partyRoles) 
    {
        this.partyRoles = partyRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRoleType")
    @JsonIgnore
    public Set<PartyContactMechanism> getPartyContactMechanisms() 
    {
        return this.partyContactMechanisms;
    }
    
    public void setPartyContactMechanisms(Set<PartyContactMechanism> partyContactMechanisms) 
    {
        this.partyContactMechanisms = partyContactMechanisms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRoleType")
    @JsonIgnore
    public Set<PartyRoleType> getPartyRoleTypes() 
    {
        return this.partyRoleTypes;
    }
    
    public void setPartyRoleTypes(Set<PartyRoleType> partyRoleTypes) 
    {
        this.partyRoleTypes = partyRoleTypes;
    }			
		
    

}

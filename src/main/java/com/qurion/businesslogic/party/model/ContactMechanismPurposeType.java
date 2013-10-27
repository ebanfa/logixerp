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

import com.qurion.businesslogic.ordering.model.OrderItemContactMechanism;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderContactMechanism;

import java.util.Set;

import com.qurion.businesslogic.party.model.PartyContactMechanismPurpose;

/**
 * ContactMechanismPurposeType 
 * @author Edward Banfa
 */
@Entity
@Table(name="CONTACT_MECHANISM_PURPOSE_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ContactMechanismPurposeType  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Set<OrderItemContactMechanism> orderItemContactMechanisms;
	private Set<OrderContactMechanism> orderContactMechanisms;
	private Set<PartyContactMechanismPurpose> partyContactMechanismPurposes;

    public ContactMechanismPurposeType() {
    }

    public ContactMechanismPurposeType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ContactMechanismPurposeType(String name, String description, Set orderItemContactMechanisms, Set orderContactMechanisms, Set partyContactMechanismPurposes, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.orderItemContactMechanisms = orderItemContactMechanisms;
        this.orderContactMechanisms = orderContactMechanisms;
        this.partyContactMechanismPurposes = partyContactMechanismPurposes;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanismPurposeType")
    @JsonIgnore
    public Set<OrderItemContactMechanism> getOrderItemContactMechanisms() 
    {
        return this.orderItemContactMechanisms;
    }
    
    public void setOrderItemContactMechanisms(Set<OrderItemContactMechanism> orderItemContactMechanisms) 
    {
        this.orderItemContactMechanisms = orderItemContactMechanisms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanismPurposeType")
    @JsonIgnore
    public Set<OrderContactMechanism> getOrderContactMechanisms() 
    {
        return this.orderContactMechanisms;
    }
    
    public void setOrderContactMechanisms(Set<OrderContactMechanism> orderContactMechanisms) 
    {
        this.orderContactMechanisms = orderContactMechanisms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanismPurposeType")
    @JsonIgnore
    public Set<PartyContactMechanismPurpose> getPartyContactMechanismPurposes() 
    {
        return this.partyContactMechanismPurposes;
    }
    
    public void setPartyContactMechanismPurposes(Set<PartyContactMechanismPurpose> partyContactMechanismPurposes) 
    {
        this.partyContactMechanismPurposes = partyContactMechanismPurposes;
    }			
		
    

}

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
import com.qurion.businesslogic.party.model.ContactMechanismPurposeType;
import com.qurion.businesslogic.party.model.PartyContactMechanism;

import java.util.Date;
import java.util.Date;

/**
 * PartyContactMechanismPurpose 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_CONTACT_MECHANISM_PURPOSE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartyContactMechanismPurpose  extends BaseEntity implements java.io.Serializable {
	private ContactMechanismPurposeType contactMechanismPurposeType;
	private PartyContactMechanism partyContactMechanism;
	private Date fromDt;
	private Date toDt;

    public PartyContactMechanismPurpose() {
    }

    public PartyContactMechanismPurpose(Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PartyContactMechanismPurpose(ContactMechanismPurposeType contactMechanismPurposeType, PartyContactMechanism partyContactMechanism, Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.contactMechanismPurposeType = contactMechanismPurposeType;
        this.partyContactMechanism = partyContactMechanism;
        this.fromDt = fromDt;
        this.toDt = toDt;
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
    @JoinColumn(name="CONT_MECH_PURP_TY_ID")
    @JsonIgnore
    public ContactMechanismPurposeType getContactMechanismPurposeType() 
    {
        return this.contactMechanismPurposeType;
    }
    
    public void setContactMechanismPurposeType(ContactMechanismPurposeType contactMechanismPurposeType)
    {
        this.contactMechanismPurposeType = contactMechanismPurposeType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_CONT_MECH_ID")
    @JsonIgnore
    public PartyContactMechanism getPartyContactMechanism() 
    {
        return this.partyContactMechanism;
    }
    
    public void setPartyContactMechanism(PartyContactMechanism partyContactMechanism)
    {
        this.partyContactMechanism = partyContactMechanism;
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
	
    

}

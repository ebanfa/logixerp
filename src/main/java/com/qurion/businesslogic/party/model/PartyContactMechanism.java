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
import com.qurion.businesslogic.party.model.ContactMechanism;
import com.qurion.businesslogic.party.model.PartyRoleType;
import com.qurion.businesslogic.party.model.Party;

import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.party.model.PartyContactMechanismPurpose;

/**
 * PartyContactMechanism 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_CONTACT_MECHANISM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartyContactMechanism  extends BaseEntity implements java.io.Serializable {
	private ContactMechanism contactMechanism;
	private PartyRoleType partyRoleType;
	private Party party;
	private String extention;
	private String remarks;
	private Date fromDt;
	private Date toDt;
	private Character noSolicitaionFg;
	private Set<PartyContactMechanismPurpose> partyContactMechanismPurposes;

    public PartyContactMechanism() {
    }

    public PartyContactMechanism(Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PartyContactMechanism(ContactMechanism contactMechanism, PartyRoleType partyRoleType, Party party, String extention, String remarks, Date fromDt, Date toDt, Character noSolicitaionFg, Set partyContactMechanismPurposes, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.contactMechanism = contactMechanism;
        this.partyRoleType = partyRoleType;
        this.party = party;
        this.extention = extention;
        this.remarks = remarks;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.noSolicitaionFg = noSolicitaionFg;
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
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CONT_MECH_ID")
    @JsonIgnore
    public ContactMechanism getContactMechanism() 
    {
        return this.contactMechanism;
    }
    
    public void setContactMechanism(ContactMechanism contactMechanism)
    {
        this.contactMechanism = contactMechanism;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_ROLE_TY_ID")
    @JsonIgnore
    public PartyRoleType getPartyRoleType() 
    {
        return this.partyRoleType;
    }
    
    public void setPartyRoleType(PartyRoleType partyRoleType)
    {
        this.partyRoleType = partyRoleType;
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
    
    @Column(name="EXTENTION", length=15)
    public String getExtention() 
    {
        return this.extention;
    }
    
    public void setExtention(String extention) 
    {
        this.extention = extention;
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
	
    @Column(name="NO_SOLICITAION_FG", length=1)
    public Character getNoSolicitaionFg() 
    {
        return this.noSolicitaionFg;
    }
    
    public void setNoSolicitaionFg(Character noSolicitaionFg) 
    {
        this.noSolicitaionFg = noSolicitaionFg;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyContactMechanism")
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

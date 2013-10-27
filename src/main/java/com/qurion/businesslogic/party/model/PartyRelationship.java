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
import com.qurion.businesslogic.hr.model.TerminationType;
import com.qurion.businesslogic.party.model.PartyRole;
import com.qurion.businesslogic.party.model.PartyRelationshipStatus;
import com.qurion.businesslogic.hr.model.TerminationReason;
import com.qurion.businesslogic.party.model.PartyRelationshipType;
import com.qurion.businesslogic.party.model.PartyRole;

import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.ordering.model.Agreement;

import java.util.Set;

import com.qurion.businesslogic.hr.model.UnemploymentClaim;

import java.util.Set;

import com.qurion.businesslogic.hr.model.PayHistory;

import java.util.Set;

import com.qurion.businesslogic.party.model.CommunicationEvent;

import java.util.Set;

import com.qurion.businesslogic.hr.model.PartyBenefit;

/**
 * PartyRelationship 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_RELATIONSHIP"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartyRelationship  extends BaseEntity implements java.io.Serializable {
	private TerminationType terminationType;
	private PartyRole partyRoleByFromPartyRoleId;
	private PartyRelationshipStatus partyRelationshipStatus;
	private TerminationReason terminationReason;
	private PartyRelationshipType partyRelationshipType;
	private PartyRole partyRoleByToPartyRoleId;
	private Date fromDt;
	private Date toDt;
	private Set<Agreement> agreements;
	private Set<UnemploymentClaim> unemploymentClaims;
	private Set<PayHistory> payHistories;
	private Set<CommunicationEvent> communicationEvents;
	private Set<PartyBenefit> partyBenefits;

    public PartyRelationship() {
    }

    public PartyRelationship(Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PartyRelationship(TerminationType terminationType, PartyRole partyRoleByFromPartyRoleId, PartyRelationshipStatus partyRelationshipStatus, TerminationReason terminationReason, PartyRelationshipType partyRelationshipType, PartyRole partyRoleByToPartyRoleId, Date fromDt, Date toDt, Set agreements, Set unemploymentClaims, Set payHistories, Set communicationEvents, Set partyBenefits, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.terminationType = terminationType;
        this.partyRoleByFromPartyRoleId = partyRoleByFromPartyRoleId;
        this.partyRelationshipStatus = partyRelationshipStatus;
        this.terminationReason = terminationReason;
        this.partyRelationshipType = partyRelationshipType;
        this.partyRoleByToPartyRoleId = partyRoleByToPartyRoleId;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.agreements = agreements;
        this.unemploymentClaims = unemploymentClaims;
        this.payHistories = payHistories;
        this.communicationEvents = communicationEvents;
        this.partyBenefits = partyBenefits;
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
    @JoinColumn(name="TERM_TY_ID")
    @JsonIgnore
    public TerminationType getTerminationType() 
    {
        return this.terminationType;
    }
    
    public void setTerminationType(TerminationType terminationType)
    {
        this.terminationType = terminationType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_PARTY_ROLE_ID")
    @JsonIgnore
    public PartyRole getPartyRoleByFromPartyRoleId() 
    {
        return this.partyRoleByFromPartyRoleId;
    }
    
    public void setPartyRoleByFromPartyRoleId(PartyRole partyRoleByFromPartyRoleId)
    {
        this.partyRoleByFromPartyRoleId = partyRoleByFromPartyRoleId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_REL_STAT_ID")
    @JsonIgnore
    public PartyRelationshipStatus getPartyRelationshipStatus() 
    {
        return this.partyRelationshipStatus;
    }
    
    public void setPartyRelationshipStatus(PartyRelationshipStatus partyRelationshipStatus)
    {
        this.partyRelationshipStatus = partyRelationshipStatus;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TERM_REASON_ID")
    @JsonIgnore
    public TerminationReason getTerminationReason() 
    {
        return this.terminationReason;
    }
    
    public void setTerminationReason(TerminationReason terminationReason)
    {
        this.terminationReason = terminationReason;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_REL_ID")
    @JsonIgnore
    public PartyRelationshipType getPartyRelationshipType() 
    {
        return this.partyRelationshipType;
    }
    
    public void setPartyRelationshipType(PartyRelationshipType partyRelationshipType)
    {
        this.partyRelationshipType = partyRelationshipType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TO_PARTY_ROLE_ID")
    @JsonIgnore
    public PartyRole getPartyRoleByToPartyRoleId() 
    {
        return this.partyRoleByToPartyRoleId;
    }
    
    public void setPartyRoleByToPartyRoleId(PartyRole partyRoleByToPartyRoleId)
    {
        this.partyRoleByToPartyRoleId = partyRoleByToPartyRoleId;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRelationship")
    @JsonIgnore
    public Set<Agreement> getAgreements() 
    {
        return this.agreements;
    }
    
    public void setAgreements(Set<Agreement> agreements) 
    {
        this.agreements = agreements;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRelationship")
    @JsonIgnore
    public Set<UnemploymentClaim> getUnemploymentClaims() 
    {
        return this.unemploymentClaims;
    }
    
    public void setUnemploymentClaims(Set<UnemploymentClaim> unemploymentClaims) 
    {
        this.unemploymentClaims = unemploymentClaims;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRelationship")
    @JsonIgnore
    public Set<PayHistory> getPayHistories() 
    {
        return this.payHistories;
    }
    
    public void setPayHistories(Set<PayHistory> payHistories) 
    {
        this.payHistories = payHistories;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRelationship")
    @JsonIgnore
    public Set<CommunicationEvent> getCommunicationEvents() 
    {
        return this.communicationEvents;
    }
    
    public void setCommunicationEvents(Set<CommunicationEvent> communicationEvents) 
    {
        this.communicationEvents = communicationEvents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRelationship")
    @JsonIgnore
    public Set<PartyBenefit> getPartyBenefits() 
    {
        return this.partyBenefits;
    }
    
    public void setPartyBenefits(Set<PartyBenefit> partyBenefits) 
    {
        this.partyBenefits = partyBenefits;
    }			
		
    

}

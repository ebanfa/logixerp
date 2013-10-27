/**
 *  Business Logic.
 */
package com.qurion.businesslogic.party.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qurion.businesslogic.accounting.model.AccountingTransaction;
import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.hr.model.PerformanceNote;
import com.qurion.businesslogic.hr.model.PerformanceReview;
import com.qurion.businesslogic.hr.model.TimeSheet;

/**
 * PartyRole 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_ROLE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartyRole  extends BaseEntity implements java.io.Serializable {
	private PartyRoleType partyRoleType;
	private Party party;
	private String name;
	private String description;
	private Date fromDt;
	private Date toDt;
	private Set<PerformanceNote> performanceNotesForFromRoleId;
	private Set<TimeSheet> timeSheets;
	private Set<PerformanceNote> performanceNotesForForRoleId;
	private Set<PerformanceReview> performanceReviewsForFromRoleId;
	private Set<PartyRelationship> partyRelationshipsForFromPartyRoleId;
	private Set<PerformanceReview> performanceReviewsForForRoleId;
	private Set<PartyRelationship> partyRelationshipsForToPartyRoleId;
	private Set<AccountingTransaction> accountingTransactions;

    public PartyRole() {
    }

    public PartyRole(String name, Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PartyRole(PartyRoleType partyRoleType, Party party, String name, String description, Date fromDt, Date toDt, Set performanceNotesForFromRoleId, Set timeSheets, Set performanceNotesForForRoleId, Set performanceReviewsForFromRoleId, Set partyRelationshipsForFromPartyRoleId, Set performanceReviewsForForRoleId, Set partyRelationshipsForToPartyRoleId, Set accountingTransactions, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.partyRoleType = partyRoleType;
        this.party = party;
        this.name = name;
        this.description = description;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.performanceNotesForFromRoleId = performanceNotesForFromRoleId;
        this.timeSheets = timeSheets;
        this.performanceNotesForForRoleId = performanceNotesForForRoleId;
        this.performanceReviewsForFromRoleId = performanceReviewsForFromRoleId;
        this.partyRelationshipsForFromPartyRoleId = partyRelationshipsForFromPartyRoleId;
        this.performanceReviewsForForRoleId = performanceReviewsForForRoleId;
        this.partyRelationshipsForToPartyRoleId = partyRelationshipsForToPartyRoleId;
        this.accountingTransactions = accountingTransactions;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRoleByFromRoleId")
    @JsonIgnore
    public Set<PerformanceNote> getPerformanceNotesForFromRoleId() 
    {
        return this.performanceNotesForFromRoleId;
    }
    
    public void setPerformanceNotesForFromRoleId(Set<PerformanceNote> performanceNotesForFromRoleId) 
    {
        this.performanceNotesForFromRoleId = performanceNotesForFromRoleId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRole")
    @JsonIgnore
    public Set<TimeSheet> getTimeSheets() 
    {
        return this.timeSheets;
    }
    
    public void setTimeSheets(Set<TimeSheet> timeSheets) 
    {
        this.timeSheets = timeSheets;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRoleByForRoleId")
    @JsonIgnore
    public Set<PerformanceNote> getPerformanceNotesForForRoleId() 
    {
        return this.performanceNotesForForRoleId;
    }
    
    public void setPerformanceNotesForForRoleId(Set<PerformanceNote> performanceNotesForForRoleId) 
    {
        this.performanceNotesForForRoleId = performanceNotesForForRoleId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRoleByFromRoleId")
    @JsonIgnore
    public Set<PerformanceReview> getPerformanceReviewsForFromRoleId() 
    {
        return this.performanceReviewsForFromRoleId;
    }
    
    public void setPerformanceReviewsForFromRoleId(Set<PerformanceReview> performanceReviewsForFromRoleId) 
    {
        this.performanceReviewsForFromRoleId = performanceReviewsForFromRoleId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRoleByFromPartyRoleId")
    @JsonIgnore
    public Set<PartyRelationship> getPartyRelationshipsForFromPartyRoleId() 
    {
        return this.partyRelationshipsForFromPartyRoleId;
    }
    
    public void setPartyRelationshipsForFromPartyRoleId(Set<PartyRelationship> partyRelationshipsForFromPartyRoleId) 
    {
        this.partyRelationshipsForFromPartyRoleId = partyRelationshipsForFromPartyRoleId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRoleByForRoleId")
    @JsonIgnore
    public Set<PerformanceReview> getPerformanceReviewsForForRoleId() 
    {
        return this.performanceReviewsForForRoleId;
    }
    
    public void setPerformanceReviewsForForRoleId(Set<PerformanceReview> performanceReviewsForForRoleId) 
    {
        this.performanceReviewsForForRoleId = performanceReviewsForForRoleId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRoleByToPartyRoleId")
    @JsonIgnore
    public Set<PartyRelationship> getPartyRelationshipsForToPartyRoleId() 
    {
        return this.partyRelationshipsForToPartyRoleId;
    }
    
    public void setPartyRelationshipsForToPartyRoleId(Set<PartyRelationship> partyRelationshipsForToPartyRoleId) 
    {
        this.partyRelationshipsForToPartyRoleId = partyRelationshipsForToPartyRoleId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRole")
    @JsonIgnore
    public Set<AccountingTransaction> getAccountingTransactions() 
    {
        return this.accountingTransactions;
    }
    
    public void setAccountingTransactions(Set<AccountingTransaction> accountingTransactions) 
    {
        this.accountingTransactions = accountingTransactions;
    }			
		
    

}

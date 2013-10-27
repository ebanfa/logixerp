/**
 *  Business Logic.
 */
package com.qurion.businesslogic.hr.model;

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
import com.qurion.businesslogic.party.model.PartyRole;

import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.hr.model.TimeEntry;

import java.util.Set;

import com.qurion.businesslogic.hr.model.TimeSheetRole;

/**
 * TimeSheet 
 * @author Edward Banfa
 */
@Entity
@Table(name="TIME_SHEET"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TimeSheet  extends BaseEntity implements java.io.Serializable {
	private PartyRole partyRole;
	private String comment;
	private Date fromDt;
	private Date toDt;
	private Set<TimeEntry> timeEntries;
	private Set<TimeSheetRole> timeSheetRoles;

    public TimeSheet() {
    }

    public TimeSheet(Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public TimeSheet(PartyRole partyRole, String comment, Date fromDt, Date toDt, Set timeEntries, Set timeSheetRoles, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.partyRole = partyRole;
        this.comment = comment;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.timeEntries = timeEntries;
        this.timeSheetRoles = timeSheetRoles;
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
    @JoinColumn(name="PARTY_ROLE_ID")
    @JsonIgnore
    public PartyRole getPartyRole() 
    {
        return this.partyRole;
    }
    
    public void setPartyRole(PartyRole partyRole)
    {
        this.partyRole = partyRole;
    }
    
    @Column(name="COMMENT", length=150)
    public String getComment() 
    {
        return this.comment;
    }
    
    public void setComment(String comment) 
    {
        this.comment = comment;
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
	
    @Column(name="TO_DT", nullable=false, length=10)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="timeSheet")
    @JsonIgnore
    public Set<TimeEntry> getTimeEntries() 
    {
        return this.timeEntries;
    }
    
    public void setTimeEntries(Set<TimeEntry> timeEntries) 
    {
        this.timeEntries = timeEntries;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="timeSheet")
    @JsonIgnore
    public Set<TimeSheetRole> getTimeSheetRoles() 
    {
        return this.timeSheetRoles;
    }
    
    public void setTimeSheetRoles(Set<TimeSheetRole> timeSheetRoles) 
    {
        this.timeSheetRoles = timeSheetRoles;
    }			
		
    

}

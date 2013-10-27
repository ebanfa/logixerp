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
import com.qurion.businesslogic.party.model.PartyRole;
import com.qurion.businesslogic.hr.model.PerformanceNoteType;

import java.util.Date;
import java.util.Date;
import java.util.Date;

/**
 * PerformanceNote 
 * @author Edward Banfa
 */
@Entity
@Table(name="PERFORMANCE_NOTE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PerformanceNote  extends BaseEntity implements java.io.Serializable {
	private PartyRole partyRoleByForRoleId;
	private PartyRole partyRoleByFromRoleId;
	private PerformanceNoteType performanceNoteType;
	private String comment;
	private Date fromDt;
	private Date toDt;
	private Date commencementDt;

    public PerformanceNote() {
    }

    public PerformanceNote(Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PerformanceNote(PartyRole partyRoleByForRoleId, PartyRole partyRoleByFromRoleId, PerformanceNoteType performanceNoteType, String comment, Date fromDt, Date toDt, Date commencementDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.partyRoleByForRoleId = partyRoleByForRoleId;
        this.partyRoleByFromRoleId = partyRoleByFromRoleId;
        this.performanceNoteType = performanceNoteType;
        this.comment = comment;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.commencementDt = commencementDt;
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
    @JoinColumn(name="FOR_ROLE_ID")
    @JsonIgnore
    public PartyRole getPartyRoleByForRoleId() 
    {
        return this.partyRoleByForRoleId;
    }
    
    public void setPartyRoleByForRoleId(PartyRole partyRoleByForRoleId)
    {
        this.partyRoleByForRoleId = partyRoleByForRoleId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_ROLE_ID")
    @JsonIgnore
    public PartyRole getPartyRoleByFromRoleId() 
    {
        return this.partyRoleByFromRoleId;
    }
    
    public void setPartyRoleByFromRoleId(PartyRole partyRoleByFromRoleId)
    {
        this.partyRoleByFromRoleId = partyRoleByFromRoleId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="NOTE_TY_ID")
    @JsonIgnore
    public PerformanceNoteType getPerformanceNoteType() 
    {
        return this.performanceNoteType;
    }
    
    public void setPerformanceNoteType(PerformanceNoteType performanceNoteType)
    {
        this.performanceNoteType = performanceNoteType;
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
	
    @Column(name="TO_DT", length=10)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
    }
	
    @Column(name="COMMENCEMENT_DT", length=10)
    public Date getCommencementDt() 
    {
        return this.commencementDt;
    }
    
    public void setCommencementDt(Date commencementDt) 
    {
        this.commencementDt = commencementDt;
    }
	
    

}

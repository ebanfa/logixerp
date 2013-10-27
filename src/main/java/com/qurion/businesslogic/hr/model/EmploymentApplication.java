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
import com.qurion.businesslogic.hr.model.Position;
import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.hr.model.EmploymentApplicationStatusType;
import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.hr.model.EmploymentApplicationSourceType;

import java.util.Date;

/**
 * EmploymentApplication 
 * @author Edward Banfa
 */
@Entity
@Table(name="EMPLOYMENT_APPLICATION"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class EmploymentApplication  extends BaseEntity implements java.io.Serializable {
	private Position position;
	private Party partyByReferrerId;
	private EmploymentApplicationStatusType employmentApplicationStatusType;
	private Party partyByApplicantId;
	private EmploymentApplicationSourceType employmentApplicationSourceType;
	private String name;
	private String description;
	private Date applicationDt;

    public EmploymentApplication() {
    }

    public EmploymentApplication(String name, String description, Date applicationDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.description = description;
        this.applicationDt = applicationDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public EmploymentApplication(Position position, Party partyByReferrerId, EmploymentApplicationStatusType employmentApplicationStatusType, Party partyByApplicantId, EmploymentApplicationSourceType employmentApplicationSourceType, String name, String description, Date applicationDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.position = position;
        this.partyByReferrerId = partyByReferrerId;
        this.employmentApplicationStatusType = employmentApplicationStatusType;
        this.partyByApplicantId = partyByApplicantId;
        this.employmentApplicationSourceType = employmentApplicationSourceType;
        this.name = name;
        this.description = description;
        this.applicationDt = applicationDt;
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
    @JoinColumn(name="POSITION_ID")
    @JsonIgnore
    public Position getPosition() 
    {
        return this.position;
    }
    
    public void setPosition(Position position)
    {
        this.position = position;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="REFERRER_ID")
    @JsonIgnore
    public Party getPartyByReferrerId() 
    {
        return this.partyByReferrerId;
    }
    
    public void setPartyByReferrerId(Party partyByReferrerId)
    {
        this.partyByReferrerId = partyByReferrerId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="APPL_STATUS_ID")
    @JsonIgnore
    public EmploymentApplicationStatusType getEmploymentApplicationStatusType() 
    {
        return this.employmentApplicationStatusType;
    }
    
    public void setEmploymentApplicationStatusType(EmploymentApplicationStatusType employmentApplicationStatusType)
    {
        this.employmentApplicationStatusType = employmentApplicationStatusType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="APPLICANT_ID")
    @JsonIgnore
    public Party getPartyByApplicantId() 
    {
        return this.partyByApplicantId;
    }
    
    public void setPartyByApplicantId(Party partyByApplicantId)
    {
        this.partyByApplicantId = partyByApplicantId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="APPL_SOURCE_ID")
    @JsonIgnore
    public EmploymentApplicationSourceType getEmploymentApplicationSourceType() 
    {
        return this.employmentApplicationSourceType;
    }
    
    public void setEmploymentApplicationSourceType(EmploymentApplicationSourceType employmentApplicationSourceType)
    {
        this.employmentApplicationSourceType = employmentApplicationSourceType;
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
	
    @Column(name="DESCRIPTION", nullable=false, length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    @Column(name="APPLICATION_DT", nullable=false, length=10)
    public Date getApplicationDt() 
    {
        return this.applicationDt;
    }
    
    public void setApplicationDt(Date applicationDt) 
    {
        this.applicationDt = applicationDt;
    }
	
    

}

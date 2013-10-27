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
import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.hr.model.SkillType;

import java.util.Date;

/**
 * PartySkill 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_SKILL"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartySkill  extends BaseEntity implements java.io.Serializable {
	private Party party;
	private SkillType skillType;
	private Integer yrsExperience;
	private Date startedUsingDt;
	private Integer rating;

    public PartySkill() {
    }

    public PartySkill(Integer yrsExperience, Integer rating, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.yrsExperience = yrsExperience;
        this.rating = rating;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PartySkill(Party party, SkillType skillType, Integer yrsExperience, Date startedUsingDt, Integer rating, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.party = party;
        this.skillType = skillType;
        this.yrsExperience = yrsExperience;
        this.startedUsingDt = startedUsingDt;
        this.rating = rating;
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
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SKILL_TY_ID")
    @JsonIgnore
    public SkillType getSkillType() 
    {
        return this.skillType;
    }
    
    public void setSkillType(SkillType skillType)
    {
        this.skillType = skillType;
    }
    
    @Column(name="YRS_EXPERIENCE", nullable=false)
    public Integer getYrsExperience() 
    {
        return this.yrsExperience;
    }
    
    public void setYrsExperience(Integer yrsExperience) 
    {
        this.yrsExperience = yrsExperience;
    }
	
    @Column(name="STARTED_USING_DT", length=10)
    public Date getStartedUsingDt() 
    {
        return this.startedUsingDt;
    }
    
    public void setStartedUsingDt(Date startedUsingDt) 
    {
        this.startedUsingDt = startedUsingDt;
    }
	
    @Column(name="RATING", nullable=false)
    public Integer getRating() 
    {
        return this.rating;
    }
    
    public void setRating(Integer rating) 
    {
        this.rating = rating;
    }
	
    

}

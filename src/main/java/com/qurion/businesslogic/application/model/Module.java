/**
 *  Business Logic.
 */
package com.qurion.businesslogic.application.model;

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

import java.util.Set;

import com.qurion.businesslogic.application.model.ActivityGroup;

import java.util.Set;

import com.qurion.businesslogic.application.model.EntityData;

import java.util.Set;

import com.qurion.businesslogic.application.model.Activity;

/**
 * Module 
 * @author Edward Banfa
 */
@Entity
@Table(name="MODULE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Module  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Integer sequenceNo;
	private String displayNm;
	private String displayImg;
	private Character displayFg;
	private Set<ActivityGroup> activityGroups;
	private Set<EntityData> entityDatas;
	private Set<Activity> activities;

    public Module() {
    }

    public Module(String name, Integer sequenceNo, String displayNm, Character displayFg) 
    {
        this.name = name;
        this.sequenceNo = sequenceNo;
        this.displayNm = displayNm;
        this.displayFg = displayFg;
    }

    public Module(String name, String description, Integer sequenceNo, String displayNm, String displayImg, Character displayFg, Set activityGroups, Set entityDatas, Set activities) 
    {
        this.name = name;
        this.description = description;
        this.sequenceNo = sequenceNo;
        this.displayNm = displayNm;
        this.displayImg = displayImg;
        this.displayFg = displayFg;
        this.activityGroups = activityGroups;
        this.entityDatas = entityDatas;
        this.activities = activities;
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
	
    @Column(name="DESCRIPTION", length=100)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    @Column(name="SEQUENCE_NO", nullable=false)
    public Integer getSequenceNo() 
    {
        return this.sequenceNo;
    }
    
    public void setSequenceNo(Integer sequenceNo) 
    {
        this.sequenceNo = sequenceNo;
    }
	
    @Column(name="DISPLAY_NM", nullable=false, length=50)
    public String getDisplayNm() 
    {
        return this.displayNm;
    }
    
    public void setDisplayNm(String displayNm) 
    {
        this.displayNm = displayNm;
    }
	
    @Column(name="DISPLAY_IMG", length=35)
    public String getDisplayImg() 
    {
        return this.displayImg;
    }
    
    public void setDisplayImg(String displayImg) 
    {
        this.displayImg = displayImg;
    }
	
    @Column(name="DISPLAY_FG", nullable=false, length=1)
    public Character getDisplayFg() 
    {
        return this.displayFg;
    }
    
    public void setDisplayFg(Character displayFg) 
    {
        this.displayFg = displayFg;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="module")
    @JsonIgnore
    public Set<ActivityGroup> getActivityGroups() 
    {
        return this.activityGroups;
    }
    
    public void setActivityGroups(Set<ActivityGroup> activityGroups) 
    {
        this.activityGroups = activityGroups;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="module")
    @JsonIgnore
    public Set<EntityData> getEntityDatas() 
    {
        return this.entityDatas;
    }
    
    public void setEntityDatas(Set<EntityData> entityDatas) 
    {
        this.entityDatas = entityDatas;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="module")
    @JsonIgnore
    public Set<Activity> getActivities() 
    {
        return this.activities;
    }
    
    public void setActivities(Set<Activity> activities) 
    {
        this.activities = activities;
    }			
		
    

}

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

import com.qurion.businesslogic.application.model.ActivityGroupType;
import com.qurion.businesslogic.application.model.Module;
import com.qurion.businesslogic.application.model.ActivityGroup;

import java.util.Set;

import com.qurion.businesslogic.application.model.ActivityGroup;

import java.util.Set;

import com.qurion.businesslogic.application.model.ActivityGrouping;

/**
 * ActivityGroup 
 * @author Edward Banfa
 */
@Entity
@Table(name="ACTIVITY_GROUP"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ActivityGroup  extends BaseEntity implements java.io.Serializable {
	private ActivityGroupType activityGroupType;
	private Module module;
	private ActivityGroup activityGroup;
	private String name;
	private String description;
	private String groupUrl;
	private Integer sequenceNo;
	private String displayNm;
	private String displayImg;
	private Character displayFg;
	private Set<ActivityGroup> activityGroups;
	private Set<ActivityGrouping> activityGroupings;

    public ActivityGroup() {
    }

    public ActivityGroup(String name, Integer sequenceNo, String displayNm, Character displayFg) 
    {
        this.name = name;
        this.sequenceNo = sequenceNo;
        this.displayNm = displayNm;
        this.displayFg = displayFg;
    }

    public ActivityGroup(ActivityGroupType activityGroupType, Module module, ActivityGroup activityGroup, String name, String description, String groupUrl, Integer sequenceNo, String displayNm, String displayImg, Character displayFg, Set activityGroups, Set activityGroupings) 
    {
        this.activityGroupType = activityGroupType;
        this.module = module;
        this.activityGroup = activityGroup;
        this.name = name;
        this.description = description;
        this.groupUrl = groupUrl;
        this.sequenceNo = sequenceNo;
        this.displayNm = displayNm;
        this.displayImg = displayImg;
        this.displayFg = displayFg;
        this.activityGroups = activityGroups;
        this.activityGroupings = activityGroupings;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GROUP_TY_ID")
    @JsonIgnore
    public ActivityGroupType getActivityGroupType() 
    {
        return this.activityGroupType;
    }
    
    public void setActivityGroupType(ActivityGroupType activityGroupType)
    {
        this.activityGroupType = activityGroupType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MODULE_ID")
    @JsonIgnore
    public Module getModule() 
    {
        return this.module;
    }
    
    public void setModule(Module module)
    {
        this.module = module;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_GROUP_ID")
    @JsonIgnore
    public ActivityGroup getActivityGroup() 
    {
        return this.activityGroup;
    }
    
    public void setActivityGroup(ActivityGroup activityGroup)
    {
        this.activityGroup = activityGroup;
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
	
    @Column(name="GROUP_URL")
    public String getGroupUrl() 
    {
        return this.groupUrl;
    }
    
    public void setGroupUrl(String groupUrl) 
    {
        this.groupUrl = groupUrl;
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
	
    @Column(name="DISPLAY_NM", nullable=false, length=75)
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="activityGroup")
    @JsonIgnore
    public Set<ActivityGroup> getActivityGroups() 
    {
        return this.activityGroups;
    }
    
    public void setActivityGroups(Set<ActivityGroup> activityGroups) 
    {
        this.activityGroups = activityGroups;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="activityGroup")
    @JsonIgnore
    public Set<ActivityGrouping> getActivityGroupings() 
    {
        return this.activityGroupings;
    }
    
    public void setActivityGroupings(Set<ActivityGrouping> activityGroupings) 
    {
        this.activityGroupings = activityGroupings;
    }			
		
    

}

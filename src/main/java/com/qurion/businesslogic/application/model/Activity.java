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

import com.qurion.businesslogic.application.model.Module;
import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.model.ActivityType;

import java.util.Set;

import com.qurion.businesslogic.application.model.UserActivity;

import java.util.Set;

import com.qurion.businesslogic.application.model.ActivityRelationship;

import java.util.Set;

import com.qurion.businesslogic.application.model.ActivityRelationship;

import java.util.Set;

import com.qurion.businesslogic.application.model.ActivityGrouping;

/**
 * Activity 
 * @author Edward Banfa
 */
@Entity
@Table(name="ACTIVITY"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Activity  extends BaseEntity implements java.io.Serializable {
	private Module module;
	private EntityData entityData;
	private ActivityType activityType;
	private String name;
	private String description;
	private String activityUrl;
	private Integer sequenceNo;
	private String displayNm;
	private String displayImg;
	private String operationCd;
	private Set<UserActivity> userActivities;
	private Set<ActivityRelationship> activityRelationshipsForFromActId;
	private Set<ActivityRelationship> activityRelationshipsForToActId;
	private Set<ActivityGrouping> activityGroupings;

    public Activity() {
    }

    public Activity(String name, Integer sequenceNo, String displayNm, String operationCd) 
    {
        this.name = name;
        this.sequenceNo = sequenceNo;
        this.displayNm = displayNm;
        this.operationCd = operationCd;
    }

    public Activity(Module module, EntityData entityData, ActivityType activityType, String name, String description, String activityUrl, Integer sequenceNo, String displayNm, String displayImg, String operationCd, Set userActivities, Set activityRelationshipsForFromActId, Set activityRelationshipsForToActId, Set activityGroupings) 
    {
        this.module = module;
        this.entityData = entityData;
        this.activityType = activityType;
        this.name = name;
        this.description = description;
        this.activityUrl = activityUrl;
        this.sequenceNo = sequenceNo;
        this.displayNm = displayNm;
        this.displayImg = displayImg;
        this.operationCd = operationCd;
        this.userActivities = userActivities;
        this.activityRelationshipsForFromActId = activityRelationshipsForFromActId;
        this.activityRelationshipsForToActId = activityRelationshipsForToActId;
        this.activityGroupings = activityGroupings;
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
    @JoinColumn(name="ENTITY_ID")
    @JsonIgnore
    public EntityData getEntityData() 
    {
        return this.entityData;
    }
    
    public void setEntityData(EntityData entityData)
    {
        this.entityData = entityData;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ACTIVITY_TY_ID")
    @JsonIgnore
    public ActivityType getActivityType() 
    {
        return this.activityType;
    }
    
    public void setActivityType(ActivityType activityType)
    {
        this.activityType = activityType;
    }
    
    @Column(name="NAME", nullable=false, length=150)
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
	
    @Column(name="ACTIVITY_URL")
    public String getActivityUrl() 
    {
        return this.activityUrl;
    }
    
    public void setActivityUrl(String activityUrl) 
    {
        this.activityUrl = activityUrl;
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
	
    @Column(name="OPERATION_CD", nullable=false, length=35)
    public String getOperationCd() 
    {
        return this.operationCd;
    }
    
    public void setOperationCd(String operationCd) 
    {
        this.operationCd = operationCd;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="activity")
    @JsonIgnore
    public Set<UserActivity> getUserActivities() 
    {
        return this.userActivities;
    }
    
    public void setUserActivities(Set<UserActivity> userActivities) 
    {
        this.userActivities = userActivities;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="activityByFromActId")
    @JsonIgnore
    public Set<ActivityRelationship> getActivityRelationshipsForFromActId() 
    {
        return this.activityRelationshipsForFromActId;
    }
    
    public void setActivityRelationshipsForFromActId(Set<ActivityRelationship> activityRelationshipsForFromActId) 
    {
        this.activityRelationshipsForFromActId = activityRelationshipsForFromActId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="activityByToActId")
    @JsonIgnore
    public Set<ActivityRelationship> getActivityRelationshipsForToActId() 
    {
        return this.activityRelationshipsForToActId;
    }
    
    public void setActivityRelationshipsForToActId(Set<ActivityRelationship> activityRelationshipsForToActId) 
    {
        this.activityRelationshipsForToActId = activityRelationshipsForToActId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="activity")
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

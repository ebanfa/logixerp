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

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.Activity;

/**
 * ActivityRelationship 
 * @author Edward Banfa
 */
@Entity
@Table(name="ACTIVITY_RELATIONSHIP"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ActivityRelationship  extends BaseEntity implements java.io.Serializable {
	private Activity activityByFromActId;
	private Activity activityByToActId;
	private Integer relationshipTyId;
	private String name;
	private String description;
	private Integer sequenceNo;

    public ActivityRelationship() {
    }

    public ActivityRelationship(Integer relationshipTyId, String name, String description, Integer sequenceNo) 
    {
        this.relationshipTyId = relationshipTyId;
        this.name = name;
        this.description = description;
        this.sequenceNo = sequenceNo;
    }

    public ActivityRelationship(Activity activityByFromActId, Activity activityByToActId, Integer relationshipTyId, String name, String description, Integer sequenceNo) 
    {
        this.activityByFromActId = activityByFromActId;
        this.activityByToActId = activityByToActId;
        this.relationshipTyId = relationshipTyId;
        this.name = name;
        this.description = description;
        this.sequenceNo = sequenceNo;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_ACT_ID")
    @JsonIgnore
    public Activity getActivityByFromActId() 
    {
        return this.activityByFromActId;
    }
    
    public void setActivityByFromActId(Activity activityByFromActId)
    {
        this.activityByFromActId = activityByFromActId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TO_ACT_ID")
    @JsonIgnore
    public Activity getActivityByToActId() 
    {
        return this.activityByToActId;
    }
    
    public void setActivityByToActId(Activity activityByToActId)
    {
        this.activityByToActId = activityByToActId;
    }
    
    @Column(name="RELATIONSHIP_TY_ID", nullable=false)
    public Integer getRelationshipTyId() 
    {
        return this.relationshipTyId;
    }
    
    public void setRelationshipTyId(Integer relationshipTyId) 
    {
        this.relationshipTyId = relationshipTyId;
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
	
    @Column(name="SEQUENCE_NO", nullable=false)
    public Integer getSequenceNo() 
    {
        return this.sequenceNo;
    }
    
    public void setSequenceNo(Integer sequenceNo) 
    {
        this.sequenceNo = sequenceNo;
    }
	
    

}

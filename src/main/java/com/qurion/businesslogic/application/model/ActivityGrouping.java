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

import com.qurion.businesslogic.application.model.ActivityGroup;
import com.qurion.businesslogic.application.model.Activity;

/**
 * ActivityGrouping 
 * @author Edward Banfa
 */
@Entity
@Table(name="ACTIVITY_GROUPING"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ActivityGrouping  extends BaseEntity implements java.io.Serializable {
	private ActivityGroup activityGroup;
	private Activity activity;
	private String name;
	private String description;

    public ActivityGrouping() {
    }

    public ActivityGrouping(String name) 
    {
        this.name = name;
    }

    public ActivityGrouping(ActivityGroup activityGroup, Activity activity, String name, String description) 
    {
        this.activityGroup = activityGroup;
        this.activity = activity;
        this.name = name;
        this.description = description;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GROUP_ID")
    @JsonIgnore
    public ActivityGroup getActivityGroup() 
    {
        return this.activityGroup;
    }
    
    public void setActivityGroup(ActivityGroup activityGroup)
    {
        this.activityGroup = activityGroup;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ACTIVITY_ID")
    @JsonIgnore
    public Activity getActivity() 
    {
        return this.activity;
    }
    
    public void setActivity(Activity activity)
    {
        this.activity = activity;
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
	
    

}

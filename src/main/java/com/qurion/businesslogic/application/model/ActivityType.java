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

import com.qurion.businesslogic.application.model.Activity;

/**
 * ActivityType 
 * @author Edward Banfa
 */
@Entity
@Table(name="ACTIVITY_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ActivityType  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Set<Activity> activities;

    public ActivityType() {
    }

    public ActivityType(String name) 
    {
        this.name = name;
    }

    public ActivityType(String name, String description, Set activities) 
    {
        this.name = name;
        this.description = description;
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
	
    @Column(name="DESCRIPTION", length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="activityType")
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

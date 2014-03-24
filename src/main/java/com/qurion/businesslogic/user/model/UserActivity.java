/**
 *  Business Logic.
 */
package com.qurion.businesslogic.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * UserActivity 
 * @author Edward Banfa
 */
@Entity
@Table(name="USER_ACTIVITY"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UserActivity  extends BaseEntity implements java.io.Serializable {
	private String description;
	private SystemUser systemUser;
	private Activity activity;

    public UserActivity() {
    }


    public UserActivity(SystemUser systemUser, Activity activity, String description) 
    {
        this.systemUser = systemUser;
        this.description = description;
        this.activity = activity;
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
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    @JsonIgnore
    public SystemUser getSystemUser() 
    {
        return this.systemUser;
    }
    
    public void setSystemUser(SystemUser systemUser)
    {
        this.systemUser = systemUser;
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
    
    

}

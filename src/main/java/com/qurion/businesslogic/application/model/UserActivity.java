/**
 *  Business Logic.
 */
package com.qurion.businesslogic.application.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qurion.businesslogic.party.model.Party;

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
	private Party party;
	private Activity activity;

    public UserActivity() {
    }


    public UserActivity(Party party, Activity activity) 
    {
        this.party = party;
        this.activity = activity;
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

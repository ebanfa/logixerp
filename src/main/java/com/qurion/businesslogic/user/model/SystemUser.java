/**
 *  Business Logic.
 */
package com.qurion.businesslogic.user.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 * SystemUser 
 * @author Edward Banfa
 */
@Entity
@Table(name="SYSTEM_USER"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SystemUser  extends BaseEntity implements java.io.Serializable {
	private Party party;
	private String userNm;
	private String password;
	private Date lastLoginDt;
	private String lockedFg;
	private Set<UserActivity> userActivities;

    public SystemUser() {
    }

    public SystemUser(String userNm, String password, String lockedFg) 
    {
        this.userNm = userNm;
        this.password = password;
        this.lockedFg = lockedFg;
    }

    public SystemUser(Party party, String userNm, String password, Date lastLoginDt, String lockedFg, Set userActivities) 
    {
        this.party = party;
        this.userNm = userNm;
        this.password = password;
        this.lastLoginDt = lastLoginDt;
        this.lockedFg = lockedFg;
        this.userActivities = userActivities;
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
    
    @Column(name="USER_NM", nullable=false, length=75)
    public String getUserNm() 
    {
        return this.userNm;
    }
    
    public void setUserNm(String userNm) 
    {
        this.userNm = userNm;
    }
	
    @Column(name="PASSWORD", nullable=false, length=15)
    public String getPassword() 
    {
        return this.password;
    }
    
    public void setPassword(String password) 
    {
        this.password = password;
    }
	
    @Column(name="LAST_LOGIN_DT", length=19)
    public Date getLastLoginDt() 
    {
        return this.lastLoginDt;
    }
    
    public void setLastLoginDt(Date lastLoginDt) 
    {
        this.lastLoginDt = lastLoginDt;
    }
	
    @Column(name="LOCKED_FG", nullable=false, length=1)
    public String getLockedFg() 
    {
        return this.lockedFg;
    }
    
    public void setLockedFg(String lockedFg) 
    {
        this.lockedFg = lockedFg;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="systemUser")
    @JsonIgnore
    public Set<UserActivity> getUserActivities() 
    {
        return this.userActivities;
    }
    
    public void setUserActivities(Set<UserActivity> userActivities) 
    {
        this.userActivities = userActivities;
    }			
		
    

}

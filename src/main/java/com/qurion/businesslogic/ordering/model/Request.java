/**
 *  Business Logic.
 */
package com.qurion.businesslogic.ordering.model;

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
import com.qurion.businesslogic.ordering.model.RequestType;

import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.ordering.model.RequestItem;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.RequirementRequest;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.RequestRole;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.RespondingParty;

/**
 * Request 
 * @author Edward Banfa
 */
@Entity
@Table(name="REQUEST"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Request  extends BaseEntity implements java.io.Serializable {
	private RequestType requestType;
	private String name;
	private String description;
	private Date requestDt;
	private Date reqReqDt;
	private Set<RequestItem> requestItems;
	private Set<RequirementRequest> requirementRequests;
	private Set<RequestRole> requestRoles;
	private Set<RespondingParty> respondingParties;

    public Request() {
    }

    public Request(String name, String description, Date requestDt, Date reqReqDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.description = description;
        this.requestDt = requestDt;
        this.reqReqDt = reqReqDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Request(RequestType requestType, String name, String description, Date requestDt, Date reqReqDt, Set requestItems, Set requirementRequests, Set requestRoles, Set respondingParties, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.requestType = requestType;
        this.name = name;
        this.description = description;
        this.requestDt = requestDt;
        this.reqReqDt = reqReqDt;
        this.requestItems = requestItems;
        this.requirementRequests = requirementRequests;
        this.requestRoles = requestRoles;
        this.respondingParties = respondingParties;
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
    @JoinColumn(name="REQ_TY_ID")
    @JsonIgnore
    public RequestType getRequestType() 
    {
        return this.requestType;
    }
    
    public void setRequestType(RequestType requestType)
    {
        this.requestType = requestType;
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
	
    @Column(name="REQUEST_DT", nullable=false, length=10)
    public Date getRequestDt() 
    {
        return this.requestDt;
    }
    
    public void setRequestDt(Date requestDt) 
    {
        this.requestDt = requestDt;
    }
	
    @Column(name="REQ_REQ_DT", nullable=false, length=10)
    public Date getReqReqDt() 
    {
        return this.reqReqDt;
    }
    
    public void setReqReqDt(Date reqReqDt) 
    {
        this.reqReqDt = reqReqDt;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="request")
    @JsonIgnore
    public Set<RequestItem> getRequestItems() 
    {
        return this.requestItems;
    }
    
    public void setRequestItems(Set<RequestItem> requestItems) 
    {
        this.requestItems = requestItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="request")
    @JsonIgnore
    public Set<RequirementRequest> getRequirementRequests() 
    {
        return this.requirementRequests;
    }
    
    public void setRequirementRequests(Set<RequirementRequest> requirementRequests) 
    {
        this.requirementRequests = requirementRequests;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="request")
    @JsonIgnore
    public Set<RequestRole> getRequestRoles() 
    {
        return this.requestRoles;
    }
    
    public void setRequestRoles(Set<RequestRole> requestRoles) 
    {
        this.requestRoles = requestRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="request")
    @JsonIgnore
    public Set<RespondingParty> getRespondingParties() 
    {
        return this.respondingParties;
    }
    
    public void setRespondingParties(Set<RespondingParty> respondingParties) 
    {
        this.respondingParties = respondingParties;
    }			
		
    

}

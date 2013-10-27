/**
 *  Business Logic.
 */
package com.qurion.businesslogic.workeffort.model;

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
import com.qurion.businesslogic.workeffort.model.DeliverableType;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortDeliverableProduced;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.Requirement;

/**
 * Deliverable 
 * @author Edward Banfa
 */
@Entity
@Table(name="DELIVERABLE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Deliverable  extends BaseEntity implements java.io.Serializable {
	private DeliverableType deliverableType;
	private String name;
	private String description;
	private Set<WorkEffortDeliverableProduced> workEffortDeliverableProduceds;
	private Set<Requirement> requirements;

    public Deliverable() {
    }

    public Deliverable(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Deliverable(DeliverableType deliverableType, String name, String description, Set workEffortDeliverableProduceds, Set requirements, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.deliverableType = deliverableType;
        this.name = name;
        this.description = description;
        this.workEffortDeliverableProduceds = workEffortDeliverableProduceds;
        this.requirements = requirements;
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
    @JoinColumn(name="DELIVERABLE_TY_ID")
    @JsonIgnore
    public DeliverableType getDeliverableType() 
    {
        return this.deliverableType;
    }
    
    public void setDeliverableType(DeliverableType deliverableType)
    {
        this.deliverableType = deliverableType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="deliverable")
    @JsonIgnore
    public Set<WorkEffortDeliverableProduced> getWorkEffortDeliverableProduceds() 
    {
        return this.workEffortDeliverableProduceds;
    }
    
    public void setWorkEffortDeliverableProduceds(Set<WorkEffortDeliverableProduced> workEffortDeliverableProduceds) 
    {
        this.workEffortDeliverableProduceds = workEffortDeliverableProduceds;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="deliverable")
    @JsonIgnore
    public Set<Requirement> getRequirements() 
    {
        return this.requirements;
    }
    
    public void setRequirements(Set<Requirement> requirements) 
    {
        this.requirements = requirements;
    }			
		
    

}

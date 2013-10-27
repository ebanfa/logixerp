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

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortType;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.Deliverable;

/**
 * DeliverableType 
 * @author Edward Banfa
 */
@Entity
@Table(name="DELIVERABLE_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DeliverableType  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Set<WorkEffortType> workEffortTypes;
	private Set<Deliverable> deliverables;

    public DeliverableType() {
    }

    public DeliverableType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public DeliverableType(String name, String description, Set workEffortTypes, Set deliverables, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.workEffortTypes = workEffortTypes;
        this.deliverables = deliverables;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="deliverableType")
    @JsonIgnore
    public Set<WorkEffortType> getWorkEffortTypes() 
    {
        return this.workEffortTypes;
    }
    
    public void setWorkEffortTypes(Set<WorkEffortType> workEffortTypes) 
    {
        this.workEffortTypes = workEffortTypes;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="deliverableType")
    @JsonIgnore
    public Set<Deliverable> getDeliverables() 
    {
        return this.deliverables;
    }
    
    public void setDeliverables(Set<Deliverable> deliverables) 
    {
        this.deliverables = deliverables;
    }			
		
    

}

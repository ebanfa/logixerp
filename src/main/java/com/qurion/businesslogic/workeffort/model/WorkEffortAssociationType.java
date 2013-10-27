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
import com.qurion.businesslogic.workeffort.model.WorkEffortAssociationType;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortAssociationType;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortAssociation;

/**
 * WorkEffortAssociationType 
 * @author Edward Banfa
 */
@Entity
@Table(name="WORK_EFFORT_ASSOCIATION_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WorkEffortAssociationType  extends BaseEntity implements java.io.Serializable {
	private WorkEffortAssociationType workEffortAssociationType;
	private String name;
	private String description;
	private Set<WorkEffortAssociationType> workEffortAssociationTypes;
	private Set<WorkEffortAssociation> workEffortAssociations;

    public WorkEffortAssociationType() {
    }

    public WorkEffortAssociationType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public WorkEffortAssociationType(WorkEffortAssociationType workEffortAssociationType, String name, String description, Set workEffortAssociationTypes, Set workEffortAssociations, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.workEffortAssociationType = workEffortAssociationType;
        this.name = name;
        this.description = description;
        this.workEffortAssociationTypes = workEffortAssociationTypes;
        this.workEffortAssociations = workEffortAssociations;
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
    @JoinColumn(name="PARENT_ASSOC_TY_ID")
    @JsonIgnore
    public WorkEffortAssociationType getWorkEffortAssociationType() 
    {
        return this.workEffortAssociationType;
    }
    
    public void setWorkEffortAssociationType(WorkEffortAssociationType workEffortAssociationType)
    {
        this.workEffortAssociationType = workEffortAssociationType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffortAssociationType")
    @JsonIgnore
    public Set<WorkEffortAssociationType> getWorkEffortAssociationTypes() 
    {
        return this.workEffortAssociationTypes;
    }
    
    public void setWorkEffortAssociationTypes(Set<WorkEffortAssociationType> workEffortAssociationTypes) 
    {
        this.workEffortAssociationTypes = workEffortAssociationTypes;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffortAssociationType")
    @JsonIgnore
    public Set<WorkEffortAssociation> getWorkEffortAssociations() 
    {
        return this.workEffortAssociations;
    }
    
    public void setWorkEffortAssociations(Set<WorkEffortAssociation> workEffortAssociations) 
    {
        this.workEffortAssociations = workEffortAssociations;
    }			
		
    

}

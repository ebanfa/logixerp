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

import com.qurion.businesslogic.application.model.Module;

import java.util.Set;

import com.qurion.businesslogic.application.model.EntityField;

import java.util.Set;

import com.qurion.businesslogic.application.model.EntityField;

import java.util.Set;

import com.qurion.businesslogic.application.model.Activity;

/**
 * EntityData 
 * @author Edward Banfa
 */
@Entity
@Table(name="ENTITY_DATA"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class EntityData  extends BaseEntity implements java.io.Serializable {
	private Module module;
	private String name;
	private String description;
	private String entityClassNm;
	private String displayNm;
	private String displayNmPlural;
	private Character hasTable;
	private String dbName;
	private Set<EntityField> entityFieldsForEntityId;
	private Set<EntityField> entityFieldsForRelatedEntityId;
	private Set<Activity> activities;

    public EntityData() {
    }

    public EntityData(String name, String entityClassNm, String displayNm, String displayNmPlural, Character hasTable, String dbName) 
    {
        this.name = name;
        this.entityClassNm = entityClassNm;
        this.displayNm = displayNm;
        this.displayNmPlural = displayNmPlural;
        this.hasTable = hasTable;
        this.dbName = dbName;
    }

    public EntityData(Module module, String name, String description, String entityClassNm, String displayNm, String displayNmPlural, Character hasTable, String dbName, Set entityFieldsForEntityId, Set entityFieldsForRelatedEntityId, Set activities) 
    {
        this.module = module;
        this.name = name;
        this.description = description;
        this.entityClassNm = entityClassNm;
        this.displayNm = displayNm;
        this.displayNmPlural = displayNmPlural;
        this.hasTable = hasTable;
        this.dbName = dbName;
        this.entityFieldsForEntityId = entityFieldsForEntityId;
        this.entityFieldsForRelatedEntityId = entityFieldsForRelatedEntityId;
        this.activities = activities;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MODULE_ID")
    @JsonIgnore
    public Module getModule() 
    {
        return this.module;
    }
    
    public void setModule(Module module)
    {
        this.module = module;
    }
    
    @Column(name="NAME", nullable=false, length=150)
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
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
	
    @Column(name="ENTITY_CLASS_NM", nullable=false)
    public String getEntityClassNm() 
    {
        return this.entityClassNm;
    }
    
    public void setEntityClassNm(String entityClassNm) 
    {
        this.entityClassNm = entityClassNm;
    }
	
    @Column(name="DISPLAY_NM", nullable=false, length=75)
    public String getDisplayNm() 
    {
        return this.displayNm;
    }
    
    public void setDisplayNm(String displayNm) 
    {
        this.displayNm = displayNm;
    }
	
    @Column(name="DISPLAY_NM_PLURAL", nullable=false, length=75)
    public String getDisplayNmPlural() 
    {
        return this.displayNmPlural;
    }
    
    public void setDisplayNmPlural(String displayNmPlural) 
    {
        this.displayNmPlural = displayNmPlural;
    }
	
    @Column(name="HAS_TABLE", nullable=false, length=1)
    public Character getHasTable() 
    {
        return this.hasTable;
    }
    
    public void setHasTable(Character hasTable) 
    {
        this.hasTable = hasTable;
    }
	
    @Column(name="DB_NAME", nullable=false, length=50)
    public String getDbName() 
    {
        return this.dbName;
    }
    
    public void setDbName(String dbName) 
    {
        this.dbName = dbName;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="entityDataByEntityId")
    @JsonIgnore
    public Set<EntityField> getEntityFieldsForEntityId() 
    {
        return this.entityFieldsForEntityId;
    }
    
    public void setEntityFieldsForEntityId(Set<EntityField> entityFieldsForEntityId) 
    {
        this.entityFieldsForEntityId = entityFieldsForEntityId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="entityDataByRelatedEntityId")
    @JsonIgnore
    public Set<EntityField> getEntityFieldsForRelatedEntityId() 
    {
        return this.entityFieldsForRelatedEntityId;
    }
    
    public void setEntityFieldsForRelatedEntityId(Set<EntityField> entityFieldsForRelatedEntityId) 
    {
        this.entityFieldsForRelatedEntityId = entityFieldsForRelatedEntityId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="entityData")
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

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

import com.qurion.businesslogic.application.model.EntityFieldType;
import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.model.EntityData;

/**
 * EntityField 
 * @author Edward Banfa
 */
@Entity
@Table(name="ENTITY_FIELD"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class EntityField  extends BaseEntity implements java.io.Serializable {
	private EntityFieldType entityFieldType;
	private EntityData entityDataByRelatedEntityId;
	private EntityData entityDataByEntityId;
	private String name;
	private String description;
	private String storage;
	private Character primarykeyFg;
	private Character requiredFg;
	private Character searchFieldFg;
    private Character listFieldFg;
    private Character viewFieldFg;
    private Character editFieldFg;
    private Character createFieldFg;
    private Character deleteFieldFg;
	private Character uniqueFg;
	private Character relatedFg;
	private Integer size;
	private Integer maxDigits;
	private Integer decimalPrecision;
	private Integer sequenceNo;

    public EntityField() {
    }

    public EntityField(String name, String storage, Character primarykeyFg, Character requiredFg, Character searchFieldFg, Integer sequenceNo) 
    {
        this.name = name;
        this.storage = storage;
        this.primarykeyFg = primarykeyFg;
        this.requiredFg = requiredFg;
        this.searchFieldFg = searchFieldFg;
        this.sequenceNo = sequenceNo;
    }

    public EntityField(EntityFieldType entityFieldType, EntityData entityDataByRelatedEntityId, EntityData entityDataByEntityId, String name, String description, String storage, Character primarykeyFg, Character requiredFg, Character searchFieldFg, Character uniqueFg, Character relatedFg, Integer size, Integer maxDigits, Integer decimalPrecision, Integer sequenceNo) 
    {
        this.entityFieldType = entityFieldType;
        this.entityDataByRelatedEntityId = entityDataByRelatedEntityId;
        this.entityDataByEntityId = entityDataByEntityId;
        this.name = name;
        this.description = description;
        this.storage = storage;
        this.primarykeyFg = primarykeyFg;
        this.requiredFg = requiredFg;
        this.searchFieldFg = searchFieldFg;
        this.uniqueFg = uniqueFg;
        this.relatedFg = relatedFg;
        this.size = size;
        this.maxDigits = maxDigits;
        this.decimalPrecision = decimalPrecision;
        this.sequenceNo = sequenceNo;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FIELD_TY_ID")
    @JsonIgnore
    public EntityFieldType getEntityFieldType() 
    {
        return this.entityFieldType;
    }
    
    public void setEntityFieldType(EntityFieldType entityFieldType)
    {
        this.entityFieldType = entityFieldType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RELATED_ENTITY_ID")
    @JsonIgnore
    public EntityData getEntityDataByRelatedEntityId() 
    {
        return this.entityDataByRelatedEntityId;
    }
    
    public void setEntityDataByRelatedEntityId(EntityData entityDataByRelatedEntityId)
    {
        this.entityDataByRelatedEntityId = entityDataByRelatedEntityId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ENTITY_ID")
    @JsonIgnore
    public EntityData getEntityDataByEntityId() 
    {
        return this.entityDataByEntityId;
    }
    
    public void setEntityDataByEntityId(EntityData entityDataByEntityId)
    {
        this.entityDataByEntityId = entityDataByEntityId;
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
	
    @Column(name="DESCRIPTION", length=100)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    @Column(name="STORAGE", nullable=false, length=35)
    public String getStorage() 
    {
        return this.storage;
    }
    
    public void setStorage(String storage) 
    {
        this.storage = storage;
    }
	
    @Column(name="PRIMARYKEY_FG", nullable=false, length=1)
    public Character getPrimarykeyFg() 
    {
        return this.primarykeyFg;
    }
    
    public void setPrimarykeyFg(Character primarykeyFg) 
    {
        this.primarykeyFg = primarykeyFg;
    }
	
    @Column(name="REQUIRED_FG", nullable=false, length=1)
    public Character getRequiredFg() 
    {
        return this.requiredFg;
    }
    
    public void setRequiredFg(Character requiredFg) 
    {
        this.requiredFg = requiredFg;
    }
	
    @Column(name="SEARCH_FIELD_FG", nullable=false, length=1)
    public Character getSearchFieldFg() 
    {
        return this.searchFieldFg;
    }
    
    public void setSearchFieldFg(Character searchFieldFg) 
    {
        this.searchFieldFg = searchFieldFg;
    }
    
    @Column(name="LIST_FIELD_FG", nullable=false, length=1)
    public Character getListFieldFg() {
        return this.listFieldFg;
    }
    
    public void setListFieldFg(Character listFieldFg) {
        this.listFieldFg = listFieldFg;
    }

    
    @Column(name="VIEW_FIELD_FG", nullable=false, length=1)
    public Character getViewFieldFg() {
        return this.viewFieldFg;
    }
    
    public void setViewFieldFg(Character viewFieldFg) {
        this.viewFieldFg = viewFieldFg;
    }

    
    @Column(name="EDIT_FIELD_FG", nullable=false, length=1)
    public Character getEditFieldFg() {
        return this.editFieldFg;
    }
    
    public void setEditFieldFg(Character editFieldFg) {
        this.editFieldFg = editFieldFg;
    }

    
    @Column(name="CREATE_FIELD_FG", nullable=false, length=1)
    public Character getCreateFieldFg() {
        return this.createFieldFg;
    }
    
    public void setCreateFieldFg(Character createFieldFg) {
        this.createFieldFg = createFieldFg;
    }

    
    @Column(name="DELETE_FIELD_FG", nullable=false, length=1)
    public Character getDeleteFieldFg() {
        return this.deleteFieldFg;
    }
    
    public void setDeleteFieldFg(Character deleteFieldFg) {
        this.deleteFieldFg = deleteFieldFg;
    }

	
    @Column(name="UNIQUE_FG", length=1)
    public Character getUniqueFg() 
    {
        return this.uniqueFg;
    }
    
    public void setUniqueFg(Character uniqueFg) 
    {
        this.uniqueFg = uniqueFg;
    }
	
    @Column(name="RELATED_FG", length=1)
    public Character getRelatedFg() 
    {
        return this.relatedFg;
    }
    
    public void setRelatedFg(Character relatedFg) 
    {
        this.relatedFg = relatedFg;
    }
	
    @Column(name="SIZE")
    public Integer getSize() 
    {
        return this.size;
    }
    
    public void setSize(Integer size) 
    {
        this.size = size;
    }
	
    @Column(name="MAX_DIGITS")
    public Integer getMaxDigits() 
    {
        return this.maxDigits;
    }
    
    public void setMaxDigits(Integer maxDigits) 
    {
        this.maxDigits = maxDigits;
    }
	
    @Column(name="DECIMAL_PRECISION")
    public Integer getDecimalPrecision() 
    {
        return this.decimalPrecision;
    }
    
    public void setDecimalPrecision(Integer decimalPrecision) 
    {
        this.decimalPrecision = decimalPrecision;
    }
	
    @Column(name="SEQUENCE_NO", nullable=false)
    public Integer getSequenceNo() 
    {
        return this.sequenceNo;
    }
    
    public void setSequenceNo(Integer sequenceNo) 
    {
        this.sequenceNo = sequenceNo;
    }
	
    

}

/**
 *  Business Logic.
 */
package com.qurion.businesslogic.hr.model;

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

import com.qurion.businesslogic.hr.model.Position;

import java.util.Set;

import com.qurion.businesslogic.hr.model.PositionTypeClass;

import java.util.Set;

import com.qurion.businesslogic.hr.model.ValidResponsibility;

import java.util.Set;

import com.qurion.businesslogic.hr.model.PositionTypeRate;

/**
 * PositionType 
 * @author Edward Banfa
 */
@Entity
@Table(name="POSITION_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PositionType  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Set<Position> positions;
	private Set<PositionTypeClass> positionTypeClasses;
	private Set<ValidResponsibility> validResponsibilities;
	private Set<PositionTypeRate> positionTypeRates;

    public PositionType() {
    }

    public PositionType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PositionType(String name, String description, Set positions, Set positionTypeClasses, Set validResponsibilities, Set positionTypeRates, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.positions = positions;
        this.positionTypeClasses = positionTypeClasses;
        this.validResponsibilities = validResponsibilities;
        this.positionTypeRates = positionTypeRates;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="positionType")
    @JsonIgnore
    public Set<Position> getPositions() 
    {
        return this.positions;
    }
    
    public void setPositions(Set<Position> positions) 
    {
        this.positions = positions;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="positionType")
    @JsonIgnore
    public Set<PositionTypeClass> getPositionTypeClasses() 
    {
        return this.positionTypeClasses;
    }
    
    public void setPositionTypeClasses(Set<PositionTypeClass> positionTypeClasses) 
    {
        this.positionTypeClasses = positionTypeClasses;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="positionType")
    @JsonIgnore
    public Set<ValidResponsibility> getValidResponsibilities() 
    {
        return this.validResponsibilities;
    }
    
    public void setValidResponsibilities(Set<ValidResponsibility> validResponsibilities) 
    {
        this.validResponsibilities = validResponsibilities;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="positionType")
    @JsonIgnore
    public Set<PositionTypeRate> getPositionTypeRates() 
    {
        return this.positionTypeRates;
    }
    
    public void setPositionTypeRates(Set<PositionTypeRate> positionTypeRates) 
    {
        this.positionTypeRates = positionTypeRates;
    }			
		
    

}

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
import com.qurion.businesslogic.hr.model.PositionType;
import com.qurion.businesslogic.hr.model.PositionClassificationType;

import java.util.Date;
import java.util.Date;

/**
 * PositionTypeClass 
 * @author Edward Banfa
 */
@Entity
@Table(name="POSITION_TYPE_CLASS"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PositionTypeClass  extends BaseEntity implements java.io.Serializable {
	private PositionType positionType;
	private PositionClassificationType positionClassificationType;
	private String name;
	private String description;
	private Date fromDt;
	private Date toDt;
	private Integer standardHrs;

    public PositionTypeClass() {
    }

    public PositionTypeClass(String name, Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PositionTypeClass(PositionType positionType, PositionClassificationType positionClassificationType, String name, String description, Date fromDt, Date toDt, Integer standardHrs, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.positionType = positionType;
        this.positionClassificationType = positionClassificationType;
        this.name = name;
        this.description = description;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.standardHrs = standardHrs;
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
    @JoinColumn(name="POSITION_TY_ID")
    @JsonIgnore
    public PositionType getPositionType() 
    {
        return this.positionType;
    }
    
    public void setPositionType(PositionType positionType)
    {
        this.positionType = positionType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLASS_TY_ID")
    @JsonIgnore
    public PositionClassificationType getPositionClassificationType() 
    {
        return this.positionClassificationType;
    }
    
    public void setPositionClassificationType(PositionClassificationType positionClassificationType)
    {
        this.positionClassificationType = positionClassificationType;
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
	
    @Column(name="FROM_DT", nullable=false, length=10)
    public Date getFromDt() 
    {
        return this.fromDt;
    }
    
    public void setFromDt(Date fromDt) 
    {
        this.fromDt = fromDt;
    }
	
    @Column(name="TO_DT", length=10)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
    }
	
    @Column(name="STANDARD_HRS")
    public Integer getStandardHrs() 
    {
        return this.standardHrs;
    }
    
    public void setStandardHrs(Integer standardHrs) 
    {
        this.standardHrs = standardHrs;
    }
	
    

}

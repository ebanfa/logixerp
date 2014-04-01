/**
 *  Business Logic.
 */
package com.qurion.businesslogic.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Universe 
 * @author Edward Banfa
 */
@Entity
@Table(name="UNIVERSE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Universe  extends BaseEntity implements java.io.Serializable {
	
	private Universe universeByParentUniverseId;
	private String universeTyCd;
	private String businessNo;
	private String name;
	private String description;
    
    /**
	 * 
	 */
	public Universe() {
		// TODO Auto-generated constructor stub
	}
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_UNIVERSE_ID")
    @JsonIgnore
    public Universe getUniverseByParentUniverseId() 
    {
        return this.universeByParentUniverseId;
    }
    
    public void setUniverseByParentUniverseId(Universe universeByParentUniverseId)
    {
        this.universeByParentUniverseId = universeByParentUniverseId;
    }
    
    @Column(name="UNIVERSE_TY_CD", nullable=false, length=35)
    public String getUniverseTyCd() 
    {
        return this.universeTyCd;
    }
    
    public void setUniverseTyCd(String universeTyCd) 
    {
        this.universeTyCd = universeTyCd;
    }
	
    @Column(name="BUSINESS_NO", nullable=false, length=15)
    public String getBusinessNo() 
    {
        return this.businessNo;
    }
    
    public void setBusinessNo(String businessNo) 
    {
        this.businessNo = businessNo;
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
	
}

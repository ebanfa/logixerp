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

/**
 * TempRelatedActivity 
 * @author Edward Banfa
 */
@Entity
@Table(name="TEMP_RELATED_ACTIVITY"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TempRelatedActivity  extends BaseEntity implements java.io.Serializable {
	private String sourceActivity;
	private String destinationActivity;
	private String relationshipTy;

    public TempRelatedActivity() {
    }
    
    @Column(name="SOURCE_ACTIVITY", nullable=false, length=75)
    public String getSourceActivity() 
    {
        return this.sourceActivity;
    }
    
    public void setSourceActivity(String sourceActivity) 
    {
        this.sourceActivity = sourceActivity;
    }
	
    @Column(name="DESTINATION_ACTIVITY", nullable=false, length=75)
    public String getDestinationActivity() 
    {
        return this.destinationActivity;
    }
    
    public void setDestinationActivity(String destinationActivity) 
    {
        this.destinationActivity = destinationActivity;
    }
	
    @Column(name="RELATIONSHIP_TY", nullable=false, length=75)
    public String getRelationshipTy() 
    {
        return this.relationshipTy;
    }
    
    public void setRelationshipTy(String relationshipTy) 
    {
        this.relationshipTy = relationshipTy;
    }
	
    

}

/**
 *  Business Logic.
 */
package com.qurion.businesslogic.businessdata.model;

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
import com.qurion.businesslogic.businessdata.model.Uom;
import com.qurion.businesslogic.businessdata.model.Uom;

/**
 * UomConversion 
 * @author Edward Banfa
 */
@Entity
@Table(name="UOM_CONVERSION"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UomConversion  extends BaseEntity implements java.io.Serializable {
	private Uom uomByFromUomId;
	private Uom uomByToUomId;
	private Integer conversionFactor;

    public UomConversion() {
    }

    public UomConversion(Integer conversionFactor, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.conversionFactor = conversionFactor;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public UomConversion(Uom uomByFromUomId, Uom uomByToUomId, Integer conversionFactor, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.uomByFromUomId = uomByFromUomId;
        this.uomByToUomId = uomByToUomId;
        this.conversionFactor = conversionFactor;
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
    @JoinColumn(name="FROM_UOM_ID")
    @JsonIgnore
    public Uom getUomByFromUomId() 
    {
        return this.uomByFromUomId;
    }
    
    public void setUomByFromUomId(Uom uomByFromUomId)
    {
        this.uomByFromUomId = uomByFromUomId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TO_UOM_ID")
    @JsonIgnore
    public Uom getUomByToUomId() 
    {
        return this.uomByToUomId;
    }
    
    public void setUomByToUomId(Uom uomByToUomId)
    {
        this.uomByToUomId = uomByToUomId;
    }
    
    @Column(name="CONVERSION_FACTOR", nullable=false)
    public Integer getConversionFactor() 
    {
        return this.conversionFactor;
    }
    
    public void setConversionFactor(Integer conversionFactor) 
    {
        this.conversionFactor = conversionFactor;
    }
	
    

}

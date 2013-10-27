/**
 *  Business Logic.
 */
package com.qurion.businesslogic.ordering.model;

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
import com.qurion.businesslogic.ordering.model.AgreementItem;
import com.qurion.businesslogic.businessdata.model.GeoBoundry;

/**
 * AgreementGeoboundryApplicability 
 * @author Edward Banfa
 */
@Entity
@Table(name="AGREEMENT_GEOBOUNDRY_APPLICABILITY"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AgreementGeoboundryApplicability  extends BaseEntity implements java.io.Serializable {
	private AgreementItem agreementItem;
	private GeoBoundry geoBoundry;

    public AgreementGeoboundryApplicability() {
    }

    public AgreementGeoboundryApplicability(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public AgreementGeoboundryApplicability(AgreementItem agreementItem, GeoBoundry geoBoundry, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.agreementItem = agreementItem;
        this.geoBoundry = geoBoundry;
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
    @JoinColumn(name="AGREEMENT_ITEM_ID")
    @JsonIgnore
    public AgreementItem getAgreementItem() 
    {
        return this.agreementItem;
    }
    
    public void setAgreementItem(AgreementItem agreementItem)
    {
        this.agreementItem = agreementItem;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GEO_BOUNDRY_ID")
    @JsonIgnore
    public GeoBoundry getGeoBoundry() 
    {
        return this.geoBoundry;
    }
    
    public void setGeoBoundry(GeoBoundry geoBoundry)
    {
        this.geoBoundry = geoBoundry;
    }
    
    

}

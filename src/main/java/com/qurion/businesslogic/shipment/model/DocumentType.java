/**
 *  Business Logic.
 */
package com.qurion.businesslogic.shipment.model;

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
import com.qurion.businesslogic.shipment.model.DocumentType;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.Document;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.DocumentType;

/**
 * DocumentType 
 * @author Edward Banfa
 */
@Entity
@Table(name="DOCUMENT_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DocumentType  extends BaseEntity implements java.io.Serializable {
	private DocumentType documentType;
	private String name;
	private String description;
	private Set<Document> documents;
	private Set<DocumentType> documentTypes;

    public DocumentType() {
    }

    public DocumentType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public DocumentType(DocumentType documentType, String name, String description, Set documents, Set documentTypes, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.documentType = documentType;
        this.name = name;
        this.description = description;
        this.documents = documents;
        this.documentTypes = documentTypes;
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
    @JoinColumn(name="PARENT_TY_ID")
    @JsonIgnore
    public DocumentType getDocumentType() 
    {
        return this.documentType;
    }
    
    public void setDocumentType(DocumentType documentType)
    {
        this.documentType = documentType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="documentType")
    @JsonIgnore
    public Set<Document> getDocuments() 
    {
        return this.documents;
    }
    
    public void setDocuments(Set<Document> documents) 
    {
        this.documents = documents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="documentType")
    @JsonIgnore
    public Set<DocumentType> getDocumentTypes() 
    {
        return this.documentTypes;
    }
    
    public void setDocumentTypes(Set<DocumentType> documentTypes) 
    {
        this.documentTypes = documentTypes;
    }			
		
    

}

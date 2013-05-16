package in.careerscale.apps.ocms.web.cases.model;

import java.util.List;


public class CaseArtifacts {
	
	private Integer caseId;
	
	private Integer caseTypeId;
    
	List<Document> caseDocuments;
	
	//TODO remove if not at all necessary
	List<DocumentType> masterTypes;
	
	
	public CaseArtifacts() {
		
	}


	public Integer getCaseId() {
		return caseId;
	}


	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}


	
	public Integer getCaseTypeId() {
		return caseTypeId;
	}


	public void setCaseTypeId(Integer caseTypeId) {
		this.caseTypeId = caseTypeId;
	}


	public List<Document> getCaseDocuments() {
		return caseDocuments;
	}


	public void setCaseDocuments(List<Document> caseDocuments) {
		this.caseDocuments = caseDocuments;
	}


	public List<DocumentType> getMasterTypes() {
		return masterTypes;
	}


	public void setMasterTypes(List<DocumentType> masterTypes) {
		this.masterTypes = masterTypes;
		/*
		 * 
		 * //TODO is this size sufficient? how about if a user want to upload multiple documents under a single
		 * category? int documentsSize = masterTypes.size(); this.caseDocuments = new
		 * ArrayList<Document>(documentsSize); for (int counter = 0; counter < documentsSize; counter++) {
		 * this.caseDocuments.add(new Document()); }
		 */
	}
	
	

}

package in.careerscale.apps.ocms.web.cases.model;

import org.springframework.web.multipart.MultipartFile;

public class Document {
	private Integer documentTypeId;
	MultipartFile  file;
	
	public Integer getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(Integer documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	

}

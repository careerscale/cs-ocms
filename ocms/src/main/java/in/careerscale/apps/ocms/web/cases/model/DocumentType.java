package in.careerscale.apps.ocms.web.cases.model;


public class DocumentType {
	private Integer id;
	private String name;
	private String supportedFormat;
	private boolean mandatory;
	private Integer maxSize;
	
	
	public DocumentType(Integer id, String name, String supportedFormat, boolean mandatory, Integer maxSize){
		this.id = id;
		this.name = name;
		this.supportedFormat =supportedFormat;
		this.mandatory = mandatory;
		this.maxSize =maxSize;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSupportedFormat() {
		return supportedFormat;
	}
	public void setSupportedFormat(String supportedFormat) {
		this.supportedFormat = supportedFormat;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	public Integer getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
	
	

}

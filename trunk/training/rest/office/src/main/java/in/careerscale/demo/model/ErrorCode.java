/**
 * This code is provided by Careerscale IT Consulting LLP, Hyderabad, India. 
 * visit us at http://careerscale.in
 * If you are looking for training(online/corporate, please get in touch with us.
 * Contact us at info@careerscale.in
 */
package in.careerscale.demo.model;

public class ErrorCode {
	private String code;
	private String description;

	public ErrorCode(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
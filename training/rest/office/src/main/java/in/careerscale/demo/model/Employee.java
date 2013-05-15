/**
 * This code is provided by Careerscale IT Consulting LLP, Hyderabad, India. 
 * visit us at http://careerscale.in
 * If you are looking for training(online/corporate, please get in touch with us.
 * Contact us at info@careerscale.in
 */
package in.careerscale.demo.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class Employee {

	private int id;
	@SerializedName("first_name")
	private String firstName;
	@SerializedName("last_name")
	private String lastName;

	public Employee(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public static void main(String[] args) {
		 Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		 Employee employee = new Employee(1,"harinath","mallepally");
		 System.out.println(gson.toJson(employee));
		
	}

}

package in.careerscale.apps.rms.model;

// Generated Jun 23, 2013 2:58:51 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Employee generated by hbm2java
 */
@Entity
@Table(name = "employee", catalog = "rms")
public class Employee implements java.io.Serializable {

	private Integer id;
	private String name;
	private String designation;
	private String department;
	private Set<JobApplications> jobApplicationses = new HashSet<JobApplications>(
			0);

	public Employee() {
	}

	public Employee(String name, String designation, String department,
			Set<JobApplications> jobApplicationses) {
		this.name = name;
		this.designation = designation;
		this.department = department;
		this.jobApplicationses = jobApplicationses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "designation", length = 45)
	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Column(name = "department", length = 45)
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<JobApplications> getJobApplicationses() {
		return this.jobApplicationses;
	}

	public void setJobApplicationses(Set<JobApplications> jobApplicationses) {
		this.jobApplicationses = jobApplicationses;
	}

}

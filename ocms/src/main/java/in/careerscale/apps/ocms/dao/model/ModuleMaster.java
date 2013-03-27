package in.careerscale.apps.ocms.dao.model;

// Generated Mar 16, 2013 8:20:08 PM by Hibernate Tools 3.4.0.CR1

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
import javax.persistence.UniqueConstraint;

/**
 * ModuleMaster generated by hbm2java
 */
@Entity
@Table(name = "module_master", catalog = "ocms", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class ModuleMaster implements java.io.Serializable {

	private Integer id;
	private String name;
	private String description;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	public ModuleMaster() {
	}

	public ModuleMaster(String name) {
		this.name = name;
	}

	public ModuleMaster(String name, String description, Set<UserRole> userRoles) {
		this.name = name;
		this.description = description;
		this.userRoles = userRoles;
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

	@Column(name = "name", unique = true, nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "moduleMaster")
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
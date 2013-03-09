package in.careerscale.apps.ocms.dao.model;

// Generated Mar 9, 2013 3:56:11 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UserRole generated by hbm2java
 */
@Entity
@Table(name = "user_role", catalog = "ocms")
public class UserRole implements java.io.Serializable {

	private UserRoleId id;
	private LoginMaster loginMaster;
	private RoleMaster roleMaster;
	private ModuleMaster moduleMaster;

	public UserRole() {
	}

	public UserRole(UserRoleId id, LoginMaster loginMaster,
			RoleMaster roleMaster) {
		this.id = id;
		this.loginMaster = loginMaster;
		this.roleMaster = roleMaster;
	}

	public UserRole(UserRoleId id, LoginMaster loginMaster,
			RoleMaster roleMaster, ModuleMaster moduleMaster) {
		this.id = id;
		this.loginMaster = loginMaster;
		this.roleMaster = roleMaster;
		this.moduleMaster = moduleMaster;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false)),
			@AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)),
			@AttributeOverride(name = "moduleId", column = @Column(name = "module_id")) })
	public UserRoleId getId() {
		return this.id;
	}

	public void setId(UserRoleId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	public LoginMaster getLoginMaster() {
		return this.loginMaster;
	}

	public void setLoginMaster(LoginMaster loginMaster) {
		this.loginMaster = loginMaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false, insertable = false, updatable = false)
	public RoleMaster getRoleMaster() {
		return this.roleMaster;
	}

	public void setRoleMaster(RoleMaster roleMaster) {
		this.roleMaster = roleMaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_id", insertable = false, updatable = false)
	public ModuleMaster getModuleMaster() {
		return this.moduleMaster;
	}

	public void setModuleMaster(ModuleMaster moduleMaster) {
		this.moduleMaster = moduleMaster;
	}

}

package in.careerscale.apps.ocms.dao.model;

// Generated Jul 7, 2013 10:49:55 AM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * UserNetwork generated by hbm2java
 */
@Entity
@Table(name = "user_network", catalog = "ocms")
public class UserNetwork implements java.io.Serializable {

	private String userNetworkId;
	private LoginMaster loginMaster;
	private SocialNetwork socialNetwork;
	private Date lastAccessDate;
	private String userAccessToken;

	public UserNetwork() {
	}

	public UserNetwork(String userNetworkId, LoginMaster loginMaster,
			SocialNetwork socialNetwork) {
		this.userNetworkId = userNetworkId;
		this.loginMaster = loginMaster;
		this.socialNetwork = socialNetwork;
	}

	public UserNetwork(String userNetworkId, LoginMaster loginMaster,
			SocialNetwork socialNetwork,String accessToken) {
		this.userNetworkId = userNetworkId;
		this.loginMaster = loginMaster;
		this.socialNetwork = socialNetwork;
		this.userAccessToken=accessToken;
	}
	
	public UserNetwork(String userNetworkId, LoginMaster loginMaster,
			SocialNetwork socialNetwork, Date lastAccessDate,
			String userAccessToken) {
		this.userNetworkId = userNetworkId;
		this.loginMaster = loginMaster;
		this.socialNetwork = socialNetwork;
		this.lastAccessDate = lastAccessDate;
		this.userAccessToken = userAccessToken;
	}

	@Id
	@Column(name = "user_network_id", unique = true, nullable = false, length = 100)
	public String getUserNetworkId() {
		return this.userNetworkId;
	}

	public void setUserNetworkId(String userNetworkId) {
		this.userNetworkId = userNetworkId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public LoginMaster getLoginMaster() {
		return this.loginMaster;
	}

	public void setLoginMaster(LoginMaster loginMaster) {
		this.loginMaster = loginMaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "network_id", nullable = false)
	public SocialNetwork getSocialNetwork() {
		return this.socialNetwork;
	}

	public void setSocialNetwork(SocialNetwork socialNetwork) {
		this.socialNetwork = socialNetwork;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "last_access_date", length = 10)
	public Date getLastAccessDate() {
		return this.lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	@Column(name = "user_access_token", length = 100)
	public String getUserAccessToken() {
		return this.userAccessToken;
	}

	public void setUserAccessToken(String userAccessToken) {
		this.userAccessToken = userAccessToken;
	}

}

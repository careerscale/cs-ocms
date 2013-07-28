package in.careerscale.apps.ocms.dao.model;

public enum LoginStatus
{
	EmailNotVerfiied(0), EmailVerified(1), SocialUser(2), AccountLocked(3);

	LoginStatus(int id)
	{
		this.id = id;
	}

	private int id;

	public int getId()
	{
		return id;
	}
}

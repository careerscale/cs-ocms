package in.careerscale.apps.ocms.dao.model;

public enum RecipientType
{
	Indiviual(1), CaseUsers(2), Admin(2), All(3);
	RecipientType(int id)
	{
		this.id = id;
	}

	private int id;

}

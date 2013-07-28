package in.careerscale.apps.ocms.dao.model;

public enum Channel
{
	Email(1), SMS(2), EmailAndSMS(3);

	Channel(int id)
	{
		this.id = id;
	}

	private int id;

}

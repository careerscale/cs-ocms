package in.careerscale.apps.ocms.service.model;

public enum RegistrationResult {
	Registered(1), RegisteredAlready(2), SocialRegistered(3), SocialRegisteredAlready(4), Error(5);
	
	RegistrationResult(int id){
		this.id=id;
	}
	
	private int id;

	
	public int getId(){
		return this.id;
	}
}

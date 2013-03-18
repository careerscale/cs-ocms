package in.careerscale.apps.ocms.service.model;

public enum SocialNetwork {
	Linkedn(1), Facebook(2), Google(3), Twitter(4);
	
	SocialNetwork(int id){
		this.id=id;
	}
	
	private int id;

	
	public int getId(){
		return this.id;
	}
}

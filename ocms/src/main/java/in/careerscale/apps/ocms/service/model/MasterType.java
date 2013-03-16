package in.careerscale.apps.ocms.service.model;

public class MasterType {

	private int id;
	private String name;

	public MasterType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}

package in.careerscale.apps.ocms.dao.model;

import in.careerscale.apps.ocms.util.CommonUtil;

public enum CaseStatus
{

	NEW(1), PENDING(2), ONHOLD(3), ACTIVE(4), REJECTED(5), RESOLVED(6), CLOSED(7);

	private CaseStatus(int id)
	{
		this.id = id;
	}

	public static CaseStatus fromString(String name)
	{
		return CommonUtil.getEnumFromString(CaseStatus.class, name);
	}

	public int getId()
	{
		return this.id;
	}

	private int id;

}

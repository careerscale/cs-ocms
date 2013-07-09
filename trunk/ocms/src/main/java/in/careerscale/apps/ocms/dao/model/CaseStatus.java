package in.careerscale.apps.ocms.dao.model;

import in.careerscale.apps.ocms.util.CommonUtil;

public enum CaseStatus
{

	New(1), Pending(2), OnHold(3), Active(4), Rejected(5), Resolved(6), Closed(7);

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

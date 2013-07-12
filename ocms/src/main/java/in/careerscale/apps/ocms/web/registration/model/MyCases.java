package in.careerscale.apps.ocms.web.registration.model;

import in.careerscale.apps.ocms.web.cases.model.Case;
import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")
public class MyCases implements Serializable
{

		private List<Case> myCases;
		private List<Case> interestedCases;
		private List<in.careerscale.apps.ocms.web.cases.model.Case> myApprovedCases;
		
		public List<Case> getMyCases()
		{
			return myCases;
		}
		public void setMyCases(List<Case> myCases)
		{
			this.myCases = myCases;
		}
		public List<Case> getInterestedCases()
		{
			return interestedCases;
		}
		public void setInterestedCases(List<Case> interestedCases)
		{
			this.interestedCases = interestedCases;
		}
		public List<in.careerscale.apps.ocms.web.cases.model.Case> getMyApprovedCases()
		{
			return myApprovedCases;
		}
		public void setMyApprovedCases(List<in.careerscale.apps.ocms.web.cases.model.Case> myApprovedCases)
		{
			this.myApprovedCases = myApprovedCases;
		}
		
		

}

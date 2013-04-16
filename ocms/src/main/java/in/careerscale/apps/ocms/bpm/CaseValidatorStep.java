package in.careerscale.apps.ocms.bpm;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CaseValidatorStep implements JavaDelegate {
	
	Log log = LogFactory.getLog(CaseValidatorStep.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		log.info("Current activity id ::" +execution.getCurrentActivityId());
		
	}

}

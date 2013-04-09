package in.careerscale.apps.ocms.scheule;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component("asyncWorker")
public class CaseUpdatesWorker {
	Log log = LogFactory.getLog(CaseUpdatesWorker.class);
	@Async
	 public void work() {

		log.info("CaseUpdatesWorker -> I am working...");
		
	}

}

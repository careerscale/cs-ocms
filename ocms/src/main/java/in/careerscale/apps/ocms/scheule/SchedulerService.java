package in.careerscale.apps.ocms.scheule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {
	Log log = LogFactory.getLog(SchedulerService.class);
	@Autowired
	@Qualifier("asyncWorker")
	private CaseUpdatesWorker worker;

	/**
	 * You can opt for cron expression or fixedRate or fixedDelay
	 * <p>
	 * 
	 * 
	 * See Spring Framework 3 Reference: Chapter 25.5 Annotation Support for
	 * Scheduling and Asynchronous Execution
	 */
	// @Scheduled(fixedDelay=5000)
	// @Scheduled(fixedRate=5000)
	@Scheduled(cron = "*/30 * * * * ?")
	public void doSchedule() {
		log.info("Start schedule");
		log.debug("Delegate to worker ");
		worker.work();

		log.debug("End schedule");
	}
}

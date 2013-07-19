package in.careerscale.apps.ocms.scheduler;

import in.careerscale.apps.ocms.dao.CaseRepository;
import in.careerscale.apps.ocms.dao.model.Fund;
import in.careerscale.apps.ocms.dao.model.FundStatus;
import in.careerscale.apps.ocms.dao.model.RecurringContribution;

import java.util.Calendar;
import java.util.List;

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

	@Autowired
	private CaseRepository caseRepository;

	/**
	 * You can opt for cron expression or fixedRate or fixedDelay
	 * <p>
	 * 
	 * 
	 * See Spring Framework 3 Reference: Chapter 25.5 Annotation Support for
	 * Scheduling and Asynchronous Execution
	 */
	// @Scheduled(fixedDelay = 5000)
	// @Scheduled(fixedRate = 5000)
	@Scheduled(cron = "*/30 * * * * ?")
	public void doSchedule() {
		log.info("Start schedule");
		log.debug("Delegate to worker ");
		worker.work();
		log.debug("End schedule");
	}

	@Scheduled(cron = "0 0 * * * *")
	public void processRecurringContributions()
	{
		List<RecurringContribution> recurringContributions = caseRepository.getRecurringContributions();
		FundStatus status = (FundStatus) caseRepository.getById(FundStatus.class, 1);
		for (RecurringContribution recurringContribution : recurringContributions)
		{
			Fund fund = new Fund(recurringContribution.getCaseMaster(), recurringContribution.getLoginMaster(),
					recurringContribution.getDonorName(), recurringContribution.getCaseMaster().getPersonName(),
					recurringContribution.getCreditAmount(), Calendar.getInstance().getTime());
			fund.setFundStatus(status);
			caseRepository.saveFund(fund);
			
		}

	}
}

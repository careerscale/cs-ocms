package in.careerscale.apps.ocms.bpm;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bpm-application-context.xml")
public class CaseValidatorTest {
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	@Rule
	public ActivitiRule activitiSpringRule;

	@Autowired
	private RepositoryService repositoryService;

	@Test
	public void simpleProcessTest() {
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("isbn", 123456L);
		variableMap.put("amount", 100);
		runtimeService.startProcessInstanceByKey("bookorder", variableMap);
		ProcessInstanceQuery query = runtimeService
				.createProcessInstanceQuery();
		List<ProcessInstance> processes = query.list();
		for (ProcessInstance i : processes) {
			System.out.println(i.getId() + "  " + i.getProcessInstanceId());
		}

	}

	@Test
	public void ocms() {
		Map<String, Object> variableMap = new HashMap<String, Object>();
		ProcessInstance instance =runtimeService.startProcessInstanceByKey("ocms");
		TaskQuery task = taskService.createTaskQuery().active();
	//	assertEquals("tasks count", task.count());
	//	taskService.complete(task.getId());
		assertEquals(0, runtimeService.createProcessInstanceQuery().count());

	}

	@Test
	public void hari() {
		Map<String, Object> variableMap = new HashMap<String, Object>();

		runtimeService.startProcessInstanceByKey("hari", variableMap);
		Task task = taskService.createTaskQuery().singleResult();
	//	assertEquals("Complete order", task.getName());
	//	taskService.complete(task.getId());
		assertEquals(0, runtimeService.createProcessInstanceQuery().count());

	}
}

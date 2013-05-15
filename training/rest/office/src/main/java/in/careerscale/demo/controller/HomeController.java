/**
 * This code is provided by Careerscale IT Consulting LLP, Hyderabad, India. 
 * visit us at http://careerscale.in
 * If you are looking for training(online/corporate, please get in touch with us.
 * Contact us at info@careerscale.in
 */
package in.careerscale.demo.controller;

import in.careerscale.demo.model.Constants;
import in.careerscale.demo.model.Employee;
import in.careerscale.demo.model.ErrorCode;
import in.careerscale.demo.model.HttpStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class HomeController {

	@Value("${standard.response.time}")
	private long standardResponseTime;

	private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	private static Map<Integer, Map<Integer, Employee>> departmentEmployeeMap = new HashMap<Integer, Map<Integer, Employee>>();

	@RequestMapping(value = "/")
	public ModelAndView test(HttpServletResponse response) throws IOException {
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/department/{department_id}/employee", method = RequestMethod.POST)
	public @ResponseBody
	String postEmployee(@PathVariable("department_id") Integer departmentId,
			HttpServletResponse response, @RequestBody String employeeData) {

		String employeeResponse = null;

		Employee employee = gson.fromJson(employeeData, Employee.class);

		employeeResponse = validateRequest(employee, departmentId, response);
		if (employeeResponse != null) {
			return employeeResponse;
		}

		addEmployeeToDepartment(departmentId, employee);

		response.setStatus(HttpStatus.NO_CONTENT.getRequestStatus());
		response.setHeader("result", "success");

		return employeeResponse;

	}

	@RequestMapping(value = "/department/{department_id}/employee/{employee_id}", method = RequestMethod.GET)
	public @ResponseBody
	String getEmployee(@PathVariable("department_id") Integer departmentId,
			@PathVariable("employee_id") Integer employeeId,
			HttpServletResponse response) {
		String employeeResponse = null;
		Employee employee = null;
		Set<ErrorCode> errors = new HashSet<ErrorCode>();
		
		employeeResponse = validateRequest(employee, departmentId, response);
		if (employeeResponse != null) {
			return employeeResponse;
		}
		// else continue

		Map<Integer, Employee> employeesMap = departmentEmployeeMap
				.get(departmentId);

		ErrorCode error = null;

		if (employeesMap == null) {
			error = new ErrorCode(Constants.InvalidDepartmentCode,
					Constants.InvalidDepartmentDescription);
			errors.add(error);
		} else {
			employee = employeesMap.get(employeeId);
			if (employee == null) {
				error = new ErrorCode(Constants.InvalidEmployeeCode,
						Constants.InvalidEmployeeDescription);
				errors.add(error);
			}
		}

		if (errors.size() > 0) {
			employeeResponse = gson.toJson(errors);
		} else {
			employeeResponse = gson.toJson(employee);
		}

		return employeeResponse;
	}

	private void addEmployeeToDepartment(Integer departmentId, Employee employee) {
		Map<Integer, Employee> employeesMap = departmentEmployeeMap
				.get(departmentId);

		if (employeesMap == null) {
			employeesMap = new HashMap<Integer, Employee>();
		}
		employeesMap.put(employee.getId(), employee);
		departmentEmployeeMap.put(departmentId, employeesMap);

	}

	/**
	 * Validate the request data for employee POST request.
	 * 
	 * @param employee
	 *            The employee object.
	 * @param departmentId
	 *            The department id.
	 * @param servletResponse
	 *            The servlet response.
	 * @return errors in JSON format(if exist) or null
	 */
	private String validateRequest(Employee employee, Integer departmentId,
			HttpServletResponse servletResponse) {
		String response = null;
		Set<ErrorCode> errors = new HashSet<ErrorCode>();
		ErrorCode error = null;

		if (departmentId > Constants.MA_DEPARTMENT_ID) {
			error = new ErrorCode(Constants.InvalidDepartmentCode,
					Constants.InvalidDepartmentDescription);
			errors.add(error);
		}

		// TODO add any other validations here.

		if (errors.size() > 0) {
			servletResponse
					.setStatus(HttpStatus.BAD_REQUEST.getRequestStatus());
			response = gson.toJson(errors);
		}
		return response;

	}

}

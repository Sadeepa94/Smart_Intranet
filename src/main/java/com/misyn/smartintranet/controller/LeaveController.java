package com.misyn.smartintranet.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.misyn.smartintranet.entity.Employee;
import com.misyn.smartintranet.entity.LeaveRequest;
import com.misyn.smartintranet.entity.LeaveType;
import com.misyn.smartintranet.service.EmployeeService;
import com.misyn.smartintranet.service.LeaveRequestService;



@Controller
@RequestMapping("leave")
public class LeaveController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	LeaveRequestService leaveService;
	
	
	 @RequestMapping("")
	    public String leaveHome(Model model, HttpSession session, SessionStatus sessionStatus){
		 	int empid=(int) session.getAttribute("userId");
	        Employee employee = employeeService.getEmployee(empid);
	        model.addAttribute("leaveList", leaveService.findByEmployeeId(employee.getId()));
	        model.addAttribute("employeeList", employeeService.getAllEmployee());
	        sessionStatus.setComplete();
	        session.setAttribute("employee", employee);
	        return "leave/index";
	    }


	    @RequestMapping(value = "apply", method = RequestMethod.GET)
	    public String applyLeaveView(Model model){
	       LeaveRequest leaveRequest = new LeaveRequest();
	       leaveRequest.setCoverupPerson(new Employee());
	       model.addAttribute("leave", leaveRequest);
	       model.addAttribute("employeeList", employeeService.getAllEmployee());
	       model.addAttribute("leaveTypes", LeaveType.values());

	        return "leave/apply";
	    }

	    @RequestMapping(value = "apply", method = RequestMethod.POST)
	    public String applyLeave(LeaveRequest leaveRequest, HttpSession session){
	    	int id=(int) session.getAttribute("userId");
	       Employee employee = employeeService.getEmployee(id);
	       leaveRequest.setEmployee(employee);

	       leaveService.saveLeaveRequest(leaveRequest);

	        return "redirect:/leave/";
	    }

	    @RequestMapping(value = "approve", method = RequestMethod.GET)
	    public String leaveApprovalView(Model model){

	       model.addAttribute("leaveRequests", leaveService.getAllLeaveRequest());

	        return "leave/approve";
	    }

	    @RequestMapping(value = "approve/{leaveId}", method = RequestMethod.POST)
	    public String approveLeave(@PathVariable("leaveId") int leaveId){
	        leaveService.approveLeave(leaveId);
	        return "redirect:/leave/approve";
	    }

	    @RequestMapping(value = "unapprove/{leaveId}", method = RequestMethod.POST)
	    public String unApproveLeave(@PathVariable("leaveId") int leaveId){
	        leaveService.unApproveLeave(leaveId);
	        return "redirect:/leave/approve";
	    }

}

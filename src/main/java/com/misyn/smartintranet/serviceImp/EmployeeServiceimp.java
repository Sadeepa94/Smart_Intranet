package com.misyn.smartintranet.serviceImp;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.misyn.smartintranet.dao.EmployeeDAO;
import com.misyn.smartintranet.dao.TypeDAO;
import com.misyn.smartintranet.entity.Employee;
import com.misyn.smartintranet.entity.Type;
import com.misyn.smartintranet.service.EmployeeService;
import com.misyn.smartintranet.util.NextValueGen;

@Service
@Transactional

public class EmployeeServiceimp implements EmployeeService {

	
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	@Autowired
	TypeDAO typeDAO;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public Employee getEmployee(int id) {
		
		return employeeDAO.getEmployee(id);
	}

	@Override
	public Employee getEmployeeByUsername(String userName) {
		
		return employeeDAO.getEmployeeByUsername(userName);
	}

	@Override
	public boolean saveEmployee(Employee employee) {
		
		Map<String, Integer> newleave=getInitialRemaningMaxLeaves(employee);
		
		int initialreamianingAnnualleave=newleave.get("annual");
		int initialreamianingusedCasualleave=newleave.get("casual");
		int initialreamianingusedSickleave=newleave.get("sick");
		int initialreamianingusedShortleave=newleave.get("short");
		
		employee.setRemainingAnnualLeaves(initialreamianingAnnualleave);
		employee.setRemainingCasualLeaves(initialreamianingusedCasualleave);
		employee.setRemainingShortLeaves(initialreamianingusedShortleave);
		employee.setRemainingSickLeaves( initialreamianingusedSickleave);
		employee.setPassword(passwordEncoder.encode("mi_syn"));
		
		return employeeDAO.saveEmployee(employee);
	}
	
	
	@Override
	public boolean updateEmployee(Employee employee) {
		return employeeDAO.updateEmployee(employee);
	}
	
	 

	@Override
	public boolean deleteEmployee(String id) {
		
		return employeeDAO.deleteEmployee(id);
	}

	@Override
	public List<Tuple> getAllEmployeeDetails(String userName) {
		return employeeDAO.getAllEmployeeDetails(userName);
	}

	@Override
	public List<Tuple>  getEmployeesByRoleName(String roleName){
		return employeeDAO. getEmployeesByRoleName(roleName);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeDAO.getAllEmployee();
	}

	
	

	@Override
	public String getNextEmployeeUsername() {
		
		String lastUsername=employeeDAO.getLastEmployeeUsername();
		if(lastUsername==null)
			return "emp0001";
		else
			return NextValueGen.getNextValue(lastUsername);

		
	}
	
	public void changeTypeAndUpdate(Employee employee , Type type){
		
		Map<String, Integer> leave=getInitialRemaningMaxLeaves(employee);
		
		int usedAnnualleave=leave.get("annual")-employee.getRemainingAnnualLeaves();
		int usedCasualleave=leave.get("casual")-employee.getRemainingCasualLeaves();
		int usedSickleave=leave.get("sick")-employee.getRemainingSickLeaves();
		int usedShortleave=leave.get("short")-employee.getRemainingShortLeaves();
		
		System.out.println(usedAnnualleave+" "+usedCasualleave+" "+usedSickleave+" "+usedShortleave);
		
		employee.setType(type);
		Map<String, Integer> newleave=getInitialRemaningMaxLeaves(employee);
		
		int initialreamianingAnnualleave=newleave.get("annual");
		int initialreamianingusedCasualleave=newleave.get("casual");
		int initialreamianingusedSickleave=newleave.get("sick");
		int initialreamianingusedShortleave=newleave.get("short");
		
		
		System.out.println(initialreamianingAnnualleave+" "+initialreamianingusedCasualleave+" "+initialreamianingusedSickleave+" "+initialreamianingusedShortleave);
		employee.setRemainingAnnualLeaves(initialreamianingAnnualleave-usedAnnualleave);
		employee.setRemainingCasualLeaves(initialreamianingusedCasualleave-usedCasualleave);
		employee.setRemainingShortLeaves(initialreamianingusedShortleave-usedShortleave);
		employee.setRemainingSickLeaves(initialreamianingusedSickleave-usedSickleave);
		employeeDAO.updateEmployee(employee);
	}
	
	public  Map<String, Integer> getInitialRemaningMaxLeaves(Employee employee){
		Map<String, Integer> leaves=new HashMap<String, Integer>();
		
		Date joinedDate=employee.getJoinedDate();
		Type type=typeDAO.getType(employee.getType().getTypeID());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(joinedDate);
		int month = cal.get(Calendar.MONTH);
			month+=1;//java util give real month-1
		int day =cal.get(Calendar.DATE);
		int year=cal.get(Calendar.YEAR);
		System.out.println(year+"rrrr");
		System.out.println(month);
		System.out.println(day);
		
		Calendar currentcal = Calendar.getInstance();
		currentcal.setTime(new Date());
		int currentyear=currentcal.get(Calendar.YEAR);
		
		int maxRemainingAnnualLeaves;
		int maxRemainingCasualLeaves;
		int maxRemainingShortLeaves;
		int maxRemainingSickLeaves;
				
		if(day<=15&&(currentyear==year))
		{
			
			maxRemainingAnnualLeaves=type.getMaxAnnualLeaves()*(12-(month-1))/12;
			maxRemainingCasualLeaves=type.getMaxCasualLeaves()*(12-(month-1))/12;
			maxRemainingShortLeaves=type.getMaxShortLeaves()*(12-(month-1))/12;
			maxRemainingSickLeaves=type.getMaxSickLeaves()*(12-(month-1))/12;
		}
		else if(day>15&&(currentyear==year))
		{
			System.out.println("2========");
			maxRemainingAnnualLeaves=type.getMaxAnnualLeaves()*(12-month)/12;
			maxRemainingCasualLeaves=type.getMaxCasualLeaves()*(12-month)/12;
			maxRemainingShortLeaves=type.getMaxShortLeaves()*(12-month)/12;
			maxRemainingSickLeaves=type.getMaxSickLeaves()*(12-month)/12;
		}
		else
		{
			System.out.println("3========");
			maxRemainingAnnualLeaves=type.getMaxAnnualLeaves();
			maxRemainingCasualLeaves=type.getMaxCasualLeaves();
			maxRemainingShortLeaves=type.getMaxShortLeaves();
			maxRemainingSickLeaves=type.getMaxSickLeaves();
		}
		
		leaves.put("annual", maxRemainingAnnualLeaves);
		leaves.put("casual", maxRemainingCasualLeaves);
		leaves.put("short", maxRemainingShortLeaves);
		leaves.put("sick", maxRemainingSickLeaves);

		return leaves;
	}

	@Scheduled(cron = "0 55 23 31 12 ?")
	public void updateEmployeeLeaveEndOftheYear() {
	
		List<Employee> emplist=employeeDAO.getAllEmployee();
		for( Employee employee : emplist)
		{
			employee.setRemainingAnnualLeaves(employee.getType().getMaxAnnualLeaves());
			employee.setRemainingCasualLeaves(employee.getType().getMaxCasualLeaves());
			employee.setRemainingShortLeaves(employee.getType().getMaxShortLeaves());
			employee.setRemainingSickLeaves(employee.getType().getMaxSickLeaves());
			employeeDAO.updateEmployee(employee);
		}
	}
	
}

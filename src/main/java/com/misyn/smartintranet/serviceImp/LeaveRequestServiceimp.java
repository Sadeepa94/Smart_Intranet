package com.misyn.smartintranet.serviceImp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.misyn.smartintranet.dao.EmployeeDAO;
import com.misyn.smartintranet.dao.LeaveRequestDAO;
import com.misyn.smartintranet.entity.Employee;
import com.misyn.smartintranet.entity.LeaveRequest;
import com.misyn.smartintranet.entity.LeaveStatus;
import com.misyn.smartintranet.service.LeaveRequestService;

@Service
public class LeaveRequestServiceimp implements LeaveRequestService {
	
	@Autowired 
	LeaveRequestDAO leaveDAO;
	
	@Autowired 
	EmployeeDAO employeeDAO;

	@Override
	@Transactional
	public LeaveRequest getLeaveRequest(int id) {
		
		return leaveDAO.getLeaveRequest(id);
	}

	@Override
	@Transactional
	public boolean saveLeaveRequest(LeaveRequest leaveRequest) {
		return leaveDAO.saveLeaveRequest(leaveRequest);
	}

	@Override
	@Transactional
	public boolean deleteLeaveRequest(int id) {
		return leaveDAO.deleteLeaveRequest(id);
	}

	@Override
	@Transactional
	public boolean updateLeaveRequest(LeaveRequest leaveRequest) {
		return leaveDAO.updateLeaveRequest(leaveRequest);
	}

	@Override
	@Transactional
	public List<LeaveRequest> getAllLeaveRequest() {
		return leaveDAO.getAllLeaveRequest();
	}

	@Override
	@Transactional
	public List<LeaveRequest> findByEmployeeId(int employeeId) {
		return leaveDAO.findByEmployeeId(employeeId);
	}

	@Override
	@Transactional
	public List<LeaveRequest> findByLeaveStatus(LeaveStatus leaveStatus) {
		return leaveDAO.findByLeaveStatus(leaveStatus);
	}

	@Override
	@Transactional
	public boolean approveLeave(int leaveId) {
		LeaveRequest request = leaveDAO.getLeaveRequest(leaveId);
        request.setLeaveStatus(LeaveStatus.APPROVED);
        leaveDAO.updateLeaveRequest(request);


        Employee requester = request.getEmployee();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        try {
            Date startDate = sdf.parse(request.getStartDate());
            Date endDate = sdf.parse(request.getEndDate());
            request.setDuration(calculateDuration(startDate, endDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        leaveDAO.saveLeaveRequest(request);

        switch (request.getLeaveType()) {
            case ANNUAL_LEAVE:
                int annualLeaveCount = requester.getRemainingAnnualLeaves();
                requester.setRemainingAnnualLeaves(annualLeaveCount-request.getDuration());
                break;
            case CASUAL_LEAVE:
                int casualLeaveCount = requester.getRemainingCasualLeaves();
                requester.setRemainingCasualLeaves(casualLeaveCount-request.getDuration());
                break;
            case SHORT_LEAVE:
                int shortLeaveCount = requester.getRemainingShortLeaves();
                requester.setRemainingShortLeaves(shortLeaveCount-request.getDuration());
                break;
            case SICK_LEAVE:
                int sickLeaveCount = requester.getRemainingSickLeaves();
                requester.setRemainingSickLeaves(sickLeaveCount-request.getDuration());
                break;
        }


       return employeeDAO.updateEmployee(requester);
	}

	@Override
	@Transactional
	public boolean unApproveLeave(int leaveId) {
		LeaveRequest request = leaveDAO.getLeaveRequest(leaveId);
        request.setLeaveStatus(LeaveStatus.UNAPPROVED);
        leaveDAO.updateLeaveRequest(request);

        Employee requester = request.getEmployee();

        switch (request.getLeaveType()) {
            case ANNUAL_LEAVE:
                int annualLeaveCount = requester.getRemainingAnnualLeaves();
                requester.setRemainingAnnualLeaves(annualLeaveCount+request.getDuration());
                break;
            case CASUAL_LEAVE:
                int casualLeaveCount = requester.getRemainingCasualLeaves();
                requester.setRemainingCasualLeaves(casualLeaveCount+request.getDuration());
                break;
            case SHORT_LEAVE:
                int shortLeaveCount = requester.getRemainingShortLeaves();
                requester.setRemainingShortLeaves(shortLeaveCount+request.getDuration());
                break;
            case SICK_LEAVE:
                int sickLeaveCount = requester.getRemainingSickLeaves();
                requester.setRemainingSickLeaves(sickLeaveCount+request.getDuration());
                break;
        }

        return employeeDAO.updateEmployee(requester);
	}

	@Override
	@Transactional
	public int getUnapprovedCount() {
		return leaveDAO.findByLeaveStatus(LeaveStatus.UNAPPROVED).size();
	}
	
	
	 public static int calculateDuration(Date startDate, Date endDate) {
	        Calendar startCal = Calendar.getInstance();
	        startCal.setTime(startDate);

	        Calendar endCal = Calendar.getInstance();
	        endCal.setTime(endDate);

	        int workDays = 0;

	        if (startCal.getTimeInMillis() > endCal.getTimeInMillis())
	        {
	            startCal.setTime(endDate);
	            endCal.setTime(startDate);
	        }

	        do
	        {
	            startCal.add(Calendar.DAY_OF_MONTH, 1);
	            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
	            {
	                workDays++;
	            }
	        }
	        while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

	        return workDays;
	    }

}

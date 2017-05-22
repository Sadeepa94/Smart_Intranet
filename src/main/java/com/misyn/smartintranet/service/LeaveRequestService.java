package com.misyn.smartintranet.service;

import java.util.List;

import com.misyn.smartintranet.entity.LeaveRequest;
import com.misyn.smartintranet.entity.LeaveStatus;

public interface LeaveRequestService {
	
	
	public LeaveRequest getLeaveRequest(int id);
	public boolean saveLeaveRequest(LeaveRequest leaveRequest);
	public boolean deleteLeaveRequest(int id);
	public boolean updateLeaveRequest(LeaveRequest leaveRequest);
	public List<LeaveRequest> getAllLeaveRequest();
	public List<LeaveRequest> findByEmployeeId(int employeeId);
    public List<LeaveRequest> findByLeaveStatus(LeaveStatus leaveStatus);
    
    public boolean approveLeave(int leaveId);
    public boolean unApproveLeave(int leaveId);
    
    public int getUnapprovedCount();

}

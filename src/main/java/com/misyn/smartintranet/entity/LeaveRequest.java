package com.misyn.smartintranet.entity;



import javax.persistence.*;

/**
 * Created by Heshi on 3/10/17.
 */
@Entity
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    @OneToOne(fetch = FetchType.EAGER)
    private Employee coverupPerson;
    @Enumerated(EnumType.STRING)
    private LeaveStatus leaveStatus = LeaveStatus.UNAPPROVED;
    private int duration;


    public LeaveRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Employee getCoverupPerson() {
        return coverupPerson;
    }

    public void setCoverupPerson(Employee coverupPerson) {
        this.coverupPerson = coverupPerson;
    }

    public LeaveStatus getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(LeaveStatus leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object obj) {
        return ((LeaveRequest)obj).getId()==this.id;
    }
}

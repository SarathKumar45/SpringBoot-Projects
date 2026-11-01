package com.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LeaveRequest {

    private Long employeeId;

    @NotBlank(message = "Employee name is required")
    private String employeeName;

    @NotBlank(message = "Leave type is mandatory")
    private String leaveType;

    @NotNull(message = "Number of days is mandatory")
    @Min(value = 1, message = "Leave days should be minimum 1")
    private Integer numberOfDays;

    private String reason;
    private String status;

    public LeaveRequest() {}

    public LeaveRequest(Long employeeId, String employeeName, String leaveType, Integer numberOfDays, String reason, String status) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.leaveType = leaveType;
        this.numberOfDays = numberOfDays;
        this.reason = reason;
        this.status = status;
    }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getLeaveType() { return leaveType; }
    public void setLeaveType(String leaveType) { this.leaveType = leaveType; }

    public Integer getNumberOfDays() { return numberOfDays; }
    public void setNumberOfDays(Integer numberOfDays) { this.numberOfDays = numberOfDays; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
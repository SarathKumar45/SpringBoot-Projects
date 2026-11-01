package com.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.exception.InvalidLeaveException;
import com.exception.LeaveNotFoundException;
import com.model.LeaveRequest;

@Service

public class LeaveService {

    private final List<LeaveRequest> leaveList = new ArrayList<>();
    private Long idCounter = 1L;

    public List<LeaveRequest> getAllLeaves() {
        return leaveList;
    }

    public LeaveRequest getLeaveById(Long id) {
        return leaveList.stream()
                .filter(l -> l.getEmployeeId().equals(id))
                .findFirst()
                .orElseThrow(() -> new LeaveNotFoundException("Leave request with ID " + id + " not found"));
    }

    public LeaveRequest applyLeave(LeaveRequest request) {
        if (request.getNumberOfDays() < 1) {
            throw new InvalidLeaveException("Leave days should be minimum 1");
        }
        request.setEmployeeId(idCounter++);
        if (request.getStatus() == null || request.getStatus().isBlank()) {
            request.setStatus("PENDING");
        }
        leaveList.add(request);
        return request;
    }

    public LeaveRequest updateLeave(Long id, LeaveRequest updatedRequest) {
        LeaveRequest existing = getLeaveById(id);

        if (updatedRequest.getNumberOfDays() < 1) {
            throw new InvalidLeaveException("Leave days should be minimum 1");
        }

        existing.setEmployeeName(updatedRequest.getEmployeeName());
        existing.setLeaveType(updatedRequest.getLeaveType());
        existing.setNumberOfDays(updatedRequest.getNumberOfDays());
        existing.setReason(updatedRequest.getReason());
        existing.setStatus(updatedRequest.getStatus() != null ? updatedRequest.getStatus() : existing.getStatus());

        return existing;
    }

    public LeaveRequest patchLeave(Long id, LeaveRequest partialRequest) {
        LeaveRequest existing = getLeaveById(id);

        if (partialRequest.getEmployeeName() != null) {
            existing.setEmployeeName(partialRequest.getEmployeeName());
        }
        if (partialRequest.getLeaveType() != null) {
            existing.setLeaveType(partialRequest.getLeaveType());
        }
        if (partialRequest.getNumberOfDays() != null) {
            if (partialRequest.getNumberOfDays() < 1) {
                throw new InvalidLeaveException("Leave days should be minimum 1");
            }
            existing.setNumberOfDays(partialRequest.getNumberOfDays());
        }
        if (partialRequest.getReason() != null) {
            existing.setReason(partialRequest.getReason());
        }
        if (partialRequest.getStatus() != null) {
            existing.setStatus(partialRequest.getStatus());
        }

        return existing;
    }

    public String cancelLeave(Long id) {
        LeaveRequest existing = getLeaveById(id);
        leaveList.remove(existing);
        return "Leave request with ID " + id + " cancelled successfully";
    }
}
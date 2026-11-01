package com.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.model.LeaveRequest;
import com.service.LeaveService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/leaves")

public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping
    public List<LeaveRequest> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @GetMapping("/{id}")
    public LeaveRequest getLeaveById(@PathVariable Long id) {
        return leaveService.getLeaveById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeaveRequest applyLeave(@Valid @RequestBody LeaveRequest request) {
        return leaveService.applyLeave(request);
    }

    @PutMapping("/{id}")
    public LeaveRequest updateLeave(@PathVariable Long id, @Valid @RequestBody LeaveRequest request) {
        return leaveService.updateLeave(id, request);
    }

    @PatchMapping("/{id}")
    public LeaveRequest patchLeave(@PathVariable Long id, @RequestBody LeaveRequest request) {
        return leaveService.patchLeave(id, request);
    }

    @DeleteMapping("/{id}")
    public String cancelLeave(@PathVariable Long id) {
        return leaveService.cancelLeave(id);
    }
}
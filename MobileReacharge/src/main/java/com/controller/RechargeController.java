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
import com.model.Recharge;
import com.service.RechargeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/recharges")

public class RechargeController {

	private final RechargeService rechargeService;

	public RechargeController(RechargeService rechargeService) {
		this.rechargeService = rechargeService;
	}

	@GetMapping
	public List<Recharge> getAllRecharges() {
		return rechargeService.getAllRecharges();
	}

	@GetMapping("/{id}")
	public Recharge getRechargeById(@PathVariable Long id) {
		return rechargeService.getRechargeById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Recharge createRecharge(@Valid @RequestBody Recharge recharge) {
		return rechargeService.addRecharge(recharge);
	}

	@PutMapping("/{id}")
	public Recharge updateRecharge(@PathVariable Long id, @Valid @RequestBody Recharge recharge) {
		return rechargeService.updateRecharge(id, recharge);
	}

	@PatchMapping("/{id}")
	public Recharge patchRecharge(@PathVariable Long id, @RequestBody Recharge recharge) {
		return rechargeService.patchRecharge(id, recharge);
	}

	@DeleteMapping("/{id}")
	public String deleteRecharge(@PathVariable Long id) {
		return rechargeService.deleteRecharge(id);
	}
}
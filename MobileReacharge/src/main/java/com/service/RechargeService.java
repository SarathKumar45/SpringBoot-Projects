package com.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.exception.InvalidRechargeException;
import com.exception.RechargeNotFoundException;
import com.model.Recharge;

@Service
public class RechargeService {

	private final List<Recharge> rechargeList = new ArrayList<>();
	private Long idCounter = 1L;

	public List<Recharge> getAllRecharges() {
		return rechargeList;
	}

	public Recharge getRechargeById(Long id) {
		return rechargeList.stream().filter(r -> r.getRechargeId().equals(id)).findFirst()
				.orElseThrow(() -> new RechargeNotFoundException("Recharge with ID " + id + " not found"));
	}

	public Recharge addRecharge(Recharge recharge) {
		if (recharge.getAmount() < 10) {
			throw new InvalidRechargeException("Minimum recharge amount is 10");
		}
		recharge.setRechargeId(idCounter++);
		rechargeList.add(recharge);
		return recharge;
	}

	public Recharge updateRecharge(Long id, Recharge updatedRecharge) {
		Recharge existing = getRechargeById(id);

		if (updatedRecharge.getAmount() < 10) {
			throw new InvalidRechargeException("Minimum recharge amount is 10");
		}

		existing.setMobileNumber(updatedRecharge.getMobileNumber());
		existing.setOperator(updatedRecharge.getOperator());
		existing.setAmount(updatedRecharge.getAmount());
		existing.setPlanType(updatedRecharge.getPlanType());

		return existing;
	}

	public Recharge patchRecharge(Long id, Recharge partialRecharge) {
		Recharge existing = getRechargeById(id);

		if (partialRecharge.getMobileNumber() != null) {
			if (!partialRecharge.getMobileNumber().matches("[0-9]{10}")) {
				throw new InvalidRechargeException("Mobile number must contain 10 digits");
			}
			existing.setMobileNumber(partialRecharge.getMobileNumber());
		}
		if (partialRecharge.getOperator() != null) {
			existing.setOperator(partialRecharge.getOperator());
		}
		if (partialRecharge.getAmount() != null) {
			if (partialRecharge.getAmount() < 10) {
				throw new InvalidRechargeException("Minimum recharge amount is 10");
			}
			existing.setAmount(partialRecharge.getAmount());
		}
		if (partialRecharge.getPlanType() != null) {
			existing.setPlanType(partialRecharge.getPlanType());
		}

		return existing;
	}

	public String deleteRecharge(Long id) {
		Recharge existing = getRechargeById(id);
		rechargeList.remove(existing);
		return "Recharge with ID " + id + " deleted successfully";
	}
}
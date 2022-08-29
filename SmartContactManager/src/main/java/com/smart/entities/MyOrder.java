package com.smart.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class MyOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long myOderId;

	private String orderId;
	private String amount;
	private String receipt;
	private String status;
	@ManyToOne
	private User user;

	private String paymentId;

	public MyOrder(Long myOderId, String orderId, String amount, String receipt, String status, User user,
			String paymentId) {
		super();
		this.myOderId = myOderId;
		this.orderId = orderId;
		this.amount = amount;
		this.receipt = receipt;
		this.status = status;
		this.user = user;
		this.paymentId = paymentId;
	}

	public MyOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getMyOderId() {
		return myOderId;
	}

	public void setMyOderId(Long myOderId) {
		this.myOderId = myOderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	@Override
	public String toString() {
		return "MyOrder [myOderId=" + myOderId + ", orderId=" + orderId + ", amount=" + amount + ", receipt=" + receipt
				+ ", status=" + status + ", user=" + user + ", paymentId=" + paymentId + "]";
	}

}

package io.nem.model;

import java.io.Serializable;

import org.nem.core.model.Account;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.time.TimeInstant;

public class TransactionBlock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Account sender;
	private Account recipient;
	private Long amount;
	private TransferTransactionAttachment attachment;
	private TimeInstant timeInstant;

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	public Account getRecipient() {
		return recipient;
	}

	public void setRecipient(Account recipient) {
		this.recipient = recipient;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public TransferTransactionAttachment getAttachment() {
		return attachment;
	}

	public void setAttachment(TransferTransactionAttachment attachment) {
		this.attachment = attachment;
	}

	public TimeInstant getTimeInstant() {
		return timeInstant;
	}

	public void setTimeInstant(TimeInstant timeInstant) {
		this.timeInstant = timeInstant;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionBlock other = (TransactionBlock) obj;

		if (recipient == null) {
			if (other.recipient != null)
				return false;
		} else if (!recipient.equals(other.recipient))
			return false;

		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;

		if (timeInstant == null) {
			if (other.timeInstant != null)
				return false;
		} else if (!timeInstant.equals(other.timeInstant))
			return false;
		
		if (attachment == null) {
			if (other.attachment != null)
				return false;
		} else if (!attachment.equals(other.attachment))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransactionBlock [sender=" + sender + ", recipient=" + recipient + ", amount=" + amount
				+ ", attachment=" + attachment.toString() + ", timeInstant=" + timeInstant + "]";
	}

}

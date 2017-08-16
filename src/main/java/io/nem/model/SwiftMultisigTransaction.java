package io.nem.model;

import java.io.Serializable;

import org.nem.core.crypto.Signature;
import org.nem.core.model.Account;
import org.nem.core.model.MultisigSignatureTransaction;
import org.nem.core.model.MultisigTransaction;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.primitive.Amount;
import org.nem.core.time.TimeInstant;

public class SwiftMultisigTransaction implements Serializable {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private Amount fee;
	
	private Signature signature;
	
	public TimeInstant getDeadline() {
		return deadline;
	}

	public void setDeadline(TimeInstant deadline) {
		this.deadline = deadline;
	}

	private TimeInstant deadline;
	
	private MultisigSignatureTransaction multisigSignature;

	/** The sender. */
	private Account senderAccount;

	/** The recipient. */
	private Account recipientAccount;

	public Amount getFee() {
		return fee;
	}

	public void setFee(Amount fee) {
		this.fee = fee;
	}

	public Signature getSignature() {
		return signature;
	}

	public void setSignature(Signature signature) {
		this.signature = signature;
	}

	public MultisigSignatureTransaction getMultisigSignature() {
		return multisigSignature;
	}

	public void addMultisigSignature(MultisigSignatureTransaction multisigSignature) {
		this.multisigSignature = multisigSignature;
	}

	/**
	 * Gets the mulit sig.
	 *
	 * @return the mulit sig
	 */
	public Account getMultisigAccount() {
		return multisig;
	}

	/**
	 * Sets the mulit sig.
	 *
	 * @param mulitSig
	 *            the new mulit sig
	 */
	public void setMultisigAccount(Account multisig) {
		this.multisig = multisig;
	}

	/** The mulit sig. */
	private Account multisig;

	/** The amount. */
	private Long amount = 0l;

	/** The attachment. */
	private TransferTransactionAttachment attachment;

	/** The payload. */
	private String payload;

	/** The transaction message type. */
	private TransactionMessageType transactionMessageType;

	/** The time instant. */
	private TimeInstant timeInstant;

	/**
	 * Gets the sender.
	 *
	 * @return the sender
	 */
	public Account getSenderAccount() {
		return senderAccount;
	}

	/**
	 * Sets the sender.
	 *
	 * @param sender
	 *            the new sender
	 */
	public void setSenderAccount(Account sender) {
		this.senderAccount = sender;
	}

	/**
	 * Gets the recipient.
	 *
	 * @return the recipient
	 */
	public Account getRecipientAccount() {
		return recipientAccount;
	}

	/**
	 * Sets the recipient.
	 *
	 * @param recipient
	 *            the new recipient
	 */
	public void setRecipientAccount(Account recipient) {
		this.recipientAccount = recipient;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public Long getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount
	 *            the new amount
	 */
	public void setAmount(Long amount) {
		this.amount = amount;
	}

	/**
	 * Gets the attachment.
	 *
	 * @return the attachment
	 */
	public TransferTransactionAttachment getAttachment() {
		return attachment;
	}

	/**
	 * Sets the attachment.
	 *
	 * @param attachment
	 *            the new attachment
	 */
	public void setAttachment(TransferTransactionAttachment attachment) {
		this.attachment = attachment;
	}

	/**
	 * Gets the time instant.
	 *
	 * @return the time instant
	 */
	public TimeInstant getTimeInstant() {
		return timeInstant;
	}

	/**
	 * Gets the payload.
	 *
	 * @return the payload
	 */
	public String getPayload() {
		return payload;
	}

	/**
	 * Sets the payload.
	 *
	 * @param payload
	 *            the new payload
	 */
	public void setPayload(String payload) {
		this.payload = payload;
	}

	/**
	 * Sets the time instant.
	 *
	 * @param timeInstant
	 *            the new time instant
	 */
	public void setTimeInstant(TimeInstant timeInstant) {
		this.timeInstant = timeInstant;
	}

	/**
	 * Gets the transaction message type.
	 *
	 * @return the transaction message type
	 */
	public TransactionMessageType getTransactionMessageType() {
		return transactionMessageType;
	}

	/**
	 * Sets the transaction message type.
	 *
	 * @param transactionMessageType
	 *            the new transaction message type
	 */
	public void setTransactionMessageType(TransactionMessageType transactionMessageType) {
		this.transactionMessageType = transactionMessageType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SwiftMultisigTransaction other = (SwiftMultisigTransaction) obj;

		if (recipientAccount == null) {
			if (other.recipientAccount != null)
				return false;
		} else if (!recipientAccount.equals(other.recipientAccount))
			return false;

		if (senderAccount == null) {
			if (other.senderAccount != null)
				return false;
		} else if (!senderAccount.equals(other.senderAccount))
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
		return "TransactionBlock [sender=" + senderAccount + ", recipient=" + recipientAccount + ", amount=" + amount
				+ ", attachment=" + attachment.toString() + ", timeInstant=" + timeInstant + "]";
	}

}

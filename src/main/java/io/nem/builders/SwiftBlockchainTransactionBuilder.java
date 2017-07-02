package io.nem.builders;

import org.nem.core.model.Account;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransactionAttachment;
import io.nem.model.TransactionBlock;
import io.nem.model.TransactionMessageType;
import io.nem.service.Globals;
import io.nem.service.BlockchainTransactionService;

/**
 * The Class TransactionBuilder.
 */
public class SwiftBlockchainTransactionBuilder {

	/** The t block. */
	private TransactionBlock tBlock = new TransactionBlock();

	/** The instance. */
	private static SwiftBlockchainTransactionBuilder instance;

	/**
	 * Gets the single instance of TransactionBuilder.
	 *
	 * @return single instance of TransactionBuilder
	 */
	public static SwiftBlockchainTransactionBuilder getInstance() {
		if (instance == null) {

			return new SwiftBlockchainTransactionBuilder();
		}
		return instance;
	}

	/**
	 * Sets the sender.
	 *
	 * @param sender
	 *            the sender
	 * @return the transaction builder
	 */
	public SwiftBlockchainTransactionBuilder setSender(Account sender) {
		this.tBlock.setSender(sender);
		return this;
	}

	/**
	 * Sets the recipient.
	 *
	 * @param recipient
	 *            the recipient
	 * @return the transaction builder
	 */
	public SwiftBlockchainTransactionBuilder setRecipient(Account recipient) {
		this.tBlock.setRecipient(recipient);
		return this;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount
	 *            the amount
	 * @return the transaction builder
	 */
	public SwiftBlockchainTransactionBuilder setAmount(Long amount) {
		this.tBlock.setAmount(amount);
		return this;
	}

	/**
	 * Sets the attachment.
	 *
	 * @param attachment
	 *            the attachment
	 * @return the transaction builder
	 */
	public SwiftBlockchainTransactionBuilder setAttachment(TransferTransactionAttachment attachment) {
		this.tBlock.setAttachment(attachment);
		return this;
	}

	/**
	 * Builds the transaction.
	 *
	 * @return the transaction
	 */
	public Transaction buildTransaction() {
		if (this.tBlock.getTimeInstant() == null) {
			this.tBlock.setTimeInstant(Globals.TIME_PROVIDER.getCurrentTime());
		}
		return BlockchainTransactionService.createTransaction(this.tBlock.getTimeInstant(), this.tBlock.getSender(),
				this.tBlock.getRecipient(), this.tBlock.getAmount(), this.tBlock.getAttachment());
	}

	/**
	 * Builds the and send transaction.
	 */
	public void buildAndSendTransaction() {
		if (this.tBlock.getTimeInstant() == null) {
			this.tBlock.setTimeInstant(Globals.TIME_PROVIDER.getCurrentTime());
		}

		if (this.tBlock.getAttachment() == null) {
			BlockchainTransactionService.createAndSendTransaction(this.tBlock.getSender(), this.tBlock.getRecipient(),
					this.tBlock.getAmount());
		} else {
			
			BlockchainTransactionService.createAndSendTransaction(this.tBlock.getSender(), this.tBlock.getRecipient(),
					this.tBlock.getAmount(), this.tBlock.getAttachment());
		}
	}
}

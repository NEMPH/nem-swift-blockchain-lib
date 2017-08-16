package io.nem.builders;

import org.nem.core.model.Account;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.TransferTransactionAttachment;
import io.nem.model.TransactionBlock;
import io.nem.service.Globals;
import io.nem.service.BlockchainTransactionService;


/**
 * The Class TransactionBuilder.
 */
public class SwiftBlockchainTransactionBuilder {

	/** The t block. */
	private static TransactionBlock tBlock = new TransactionBlock();

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
		tBlock = new TransactionBlock();
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
		tBlock.setSender(sender);
		return this;
	}

	/**
	 * Sets the multisig.
	 *
	 * @param multiSig the multi sig
	 * @return the swift blockchain transaction builder
	 */
	public SwiftBlockchainTransactionBuilder setMultisig(Account multiSig) {
		tBlock.setMulitSig(multiSig);
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
		tBlock.setRecipient(recipient);
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
		tBlock.setAmount(amount);
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
		tBlock.setAttachment(attachment);
		return this;
	}

	/**
	 * Builds the transaction.
	 *
	 * @return the transaction
	 */
	public Transaction buildTransaction() {
		if (tBlock.getTimeInstant() == null) {
			tBlock.setTimeInstant(Globals.TIME_PROVIDER.getCurrentTime());
		}
		return BlockchainTransactionService.createTransaction(tBlock.getTimeInstant(), tBlock.getSender(),
				tBlock.getRecipient(), tBlock.getAmount(), tBlock.getAttachment());
	}

	/**
	 * Builds the multisig transaction.
	 *
	 * @return the transaction
	 */
	public Transaction buildMultisigTransaction() {
		if (tBlock.getTimeInstant() == null) {
			tBlock.setTimeInstant(Globals.TIME_PROVIDER.getCurrentTime());
		}
		
		Transaction transaction = BlockchainTransactionService.createTransaction(tBlock.getTimeInstant(),
				tBlock.getSender(), tBlock.getRecipient(), tBlock.getAmount(), tBlock.getAttachment());

		return BlockchainTransactionService.createMultisigTransaction(tBlock.getTimeInstant(), tBlock.getSender(),
				tBlock.getMulitSig(), tBlock.getAmount(), transaction);
	}

	/**
	 * Builds the and send transaction.
	 */
	public void buildAndSendTransaction() {
		if (tBlock.getTimeInstant() == null) {
			tBlock.setTimeInstant(Globals.TIME_PROVIDER.getCurrentTime());
		}

		BlockchainTransactionService.createAndSendTransaction(tBlock);

	}

	/**
	 * Builds the and send multisig transaction.
	 */
	public void buildAndSendMultisigTransaction() {
		if (tBlock.getTimeInstant() == null) {
			tBlock.setTimeInstant(Globals.TIME_PROVIDER.getCurrentTime());
		}

		BlockchainTransactionService.createAndSendMultisigTransaction(tBlock);

	}
}

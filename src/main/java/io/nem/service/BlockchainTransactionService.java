package io.nem.service;


import java.util.logging.Logger;
import org.nem.core.model.Account;
import org.nem.core.model.MultisigSignatureTransaction;
import org.nem.core.model.MultisigTransaction;
import org.nem.core.model.NetworkInfos;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.primitive.Amount;
import org.nem.core.time.TimeInstant;

import io.nem.model.SwiftMultisigTransaction;
import io.nem.model.SwiftTransaction;
import io.nem.model.TransactionBlock;
import io.nem.util.AppPropertiesUtil;
import io.nem.util.TransactionSenderUtil;



// TODO: Auto-generated Javadoc
/**
 * The Class TransactionService.
 */
public class BlockchainTransactionService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(BlockchainTransactionService.class.getName());

	static {
		NetworkInfos
				.setDefault(NetworkInfos.fromFriendlyName(AppPropertiesUtil.getProperty("node.endpoint.networkname")));
	}

	/**
	 * Creates the and send transaction.
	 *
	 * @param sender
	 *            the sender
	 * @param recipient
	 *            the recipient
	 * @param amount
	 *            the amount
	 */
	public static void createAndSendTransaction(final Account sender, final Account recipient, final long amount) {

		final Transaction transaction = createTransaction(Globals.TIME_PROVIDER.getCurrentTime(), sender, recipient,
				amount, null);
		
		TransactionSenderUtil.sendTransaction(transaction);
	}

	/**
	 * Creates the and send transaction.
	 *
	 * @param tBlock the t block
	 */
	public static void createAndSendTransaction(final SwiftTransaction tBlock) {

		final Transaction transaction = createTransaction(tBlock.getTimeInstant(), tBlock.getSenderAccount(),
				tBlock.getRecipientAccount(), tBlock.getAmount(), tBlock.getAttachment());
		transaction.sign();
		TransactionSenderUtil.sendTransaction(transaction);
		
		
	}

	/**
	 * Creates the and send multisig transaction.
	 *
	 * @param tBlock the t block
	 */
	public static void createAndSendMultisigTransaction(final SwiftMultisigTransaction tBlock) {

		final Transaction transaction = createTransaction(tBlock.getTimeInstant(), tBlock.getMultisigAccount(),
				tBlock.getRecipientAccount(), tBlock.getAmount(), tBlock.getAttachment());
		
		final Transaction multiSigSignedTransaction = createMultisigTransaction(tBlock.getTimeInstant(),
				tBlock.getSenderAccount(), tBlock.getRecipientAccount(), tBlock.getAmount(), transaction);
		
		multiSigSignedTransaction.sign();
		TransactionSenderUtil.sendTransaction(multiSigSignedTransaction);
	}

	/**
	 * Creates the and send multisig signature transaction.
	 *
	 * @param tBlock the t block
	 */
	public static void createAndSendMultisigSignatureTransaction(final SwiftMultisigTransaction tBlock) {

		final Transaction transaction = createTransaction(tBlock.getTimeInstant(), tBlock.getMultisigAccount(),
				tBlock.getRecipientAccount(), tBlock.getAmount(), tBlock.getAttachment());
		
		final Transaction multiSigSignedTransaction = createMultisigSignatureTransaction(tBlock.getTimeInstant(),
				tBlock.getSenderAccount(), tBlock.getMultisigAccount(), tBlock.getAmount(), transaction);
		
		multiSigSignedTransaction.sign();
		TransactionSenderUtil.sendTransaction(multiSigSignedTransaction);
	}
	
	/**
	 * Creates the and send transaction.
	 *
	 * @param sender
	 *            the sender
	 * @param recipient
	 *            the recipient
	 * @param amount
	 *            the amount
	 * @param attachment
	 *            the attachment
	 */
	public static void createAndSendTransaction(final Account sender, final Account recipient, final long amount,
			final TransferTransactionAttachment attachment) {

		final Transaction transaction = createTransaction(Globals.TIME_PROVIDER.getCurrentTime(), sender, recipient,
				amount, attachment);
		
		TransactionSenderUtil.sendTransaction(transaction);
	}

	/**
	 * Creates the transaction.
	 *
	 * @param timeInstant
	 *            the time instant
	 * @param sender
	 *            the sender
	 * @param recipient
	 *            the recipient
	 * @param amount
	 *            the amount
	 * @param attachment
	 *            the attachment
	 * @return the transaction
	 */
	public static Transaction createTransaction(final TimeInstant timeInstant, final Account sender,
			final Account recipient, final long amount, final TransferTransactionAttachment attachment) {

		final TransferTransaction transaction = new TransferTransaction(timeInstant, // instant
				sender, recipient, // recipient
				Amount.fromMicroNem(amount), // amount in micro xem
				attachment); // attachment (message, mosaics)

		transaction.setFee(Amount.fromNem(0));
		transaction.setDeadline(timeInstant.addHours(23));
		return transaction;
	}
	

	/**
	 * Creates the multisig transaction.
	 *
	 * @param timeInstant the time instant
	 * @param sender the sender
	 * @param recipient the recipient
	 * @param amount the amount
	 * @param transaction the transaction
	 * @return the transaction
	 */
	public static Transaction createMultisigTransaction(final TimeInstant timeInstant, final Account sender,
			final Account recipient, final long amount, final Transaction transaction) {

		final MultisigTransaction multiSigSignedTransaction = new MultisigTransaction(timeInstant, sender, transaction);
		multiSigSignedTransaction.setDeadline(timeInstant.addHours(23));
		
		return multiSigSignedTransaction;
	}

	
	/**
	 * Creates the multisig signature transaction.
	 *
	 * @param timeInstant the time instant
	 * @param sender the sender
	 * @param multisig the multisig
	 * @param amount the amount
	 * @param transaction the transaction
	 * @return the transaction
	 */
	public static Transaction createMultisigSignatureTransaction(final TimeInstant timeInstant, final Account sender,
			final Account multisig, final long amount, final Transaction transaction) {

		final MultisigSignatureTransaction multiSigSignedTransaction = new MultisigSignatureTransaction(timeInstant, sender,multisig,transaction);
		multiSigSignedTransaction.setDeadline(timeInstant.addHours(23));
		
		return multiSigSignedTransaction;
	}
}

package io.nem.builders;

import org.nem.core.crypto.Signature;
import org.nem.core.model.Account;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.primitive.Amount;
import org.nem.core.time.TimeInstant;
import io.nem.model.SwiftTransaction;
import io.nem.service.Globals;
import io.nem.service.BlockchainTransactionService;


/**
 * The Class TransactionBuilder.
 */
public class SwiftTransactionBuilder {

	/**
	 * Instantiates a new swift transaction builder.
	 */
	private SwiftTransactionBuilder() {
	}

	/**
	 * Sender.
	 *
	 * @param sender the sender
	 * @return the i sender
	 */
	public static ISender sender(Account sender) {
		return new SwiftTransactionBuilder.Builder(sender);
	}

	/**
	 * The Interface ISender.
	 */
	public interface ISender {
		
		/**
		 * Recipient.
		 *
		 * @param recipient the recipient
		 * @return the i recipient
		 */
		IRecipient recipient(Account recipient);
	}

	/**
	 * The Interface IRecipient.
	 */
	public interface IRecipient {
		
		/**
		 * Amount.
		 *
		 * @param amount the amount
		 * @return the i build
		 */
		IBuild amount(Long amount);
	}

	/**
	 * The Interface IBuild.
	 */
	public interface IBuild {
		
		/**
		 * Attachment.
		 *
		 * @param attachment the attachment
		 * @return the i build
		 */
		IBuild attachment(TransferTransactionAttachment attachment);
		
		/**
		 * Fee.
		 *
		 * @param amount the amount
		 * @return the i build
		 */
		IBuild fee(Amount amount);

		/**
		 * Deadline.
		 *
		 * @param timeInstant the time instant
		 * @return the i build
		 */
		IBuild deadline(TimeInstant timeInstant);

		/**
		 * Signature.
		 *
		 * @param signature the signature
		 * @return the i build
		 */
		IBuild signature(Signature signature);

		/**
		 * Builds the transaction.
		 *
		 * @return the transfer transaction
		 */
		TransferTransaction buildTransaction();

		/**
		 * Builds the and send transaction.
		 *
		 * @return the swift transaction
		 */
		SwiftTransaction buildAndSendTransaction();
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISender, IRecipient, IBuild {
		
		/** The instance. */
		private SwiftTransaction instance = new SwiftTransaction();

		/**
		 * Instantiates a new builder.
		 *
		 * @param sender the sender
		 */
		public Builder(Account sender) {
			instance.setSenderAccount(sender);
		}

		/* (non-Javadoc)
		 * @see io.nem.builders.SwiftTransactionBuilder.IRecipient#amount(java.lang.Long)
		 */
		@Override
		public IBuild amount(Long amount) {
			instance.setAmount(amount);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.builders.SwiftTransactionBuilder.ISender#recipient(org.nem.core.model.Account)
		 */
		@Override
		public IRecipient recipient(Account recipient) {
			instance.setRecipientAccount(recipient);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.builders.SwiftTransactionBuilder.IBuild#attachment(org.nem.core.model.TransferTransactionAttachment)
		 */
		@Override
		public IBuild attachment(TransferTransactionAttachment attachment) {
			instance.setAttachment(attachment);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.builders.SwiftTransactionBuilder.IBuild#buildAndSendTransaction()
		 */
		@Override
		public SwiftTransaction buildAndSendTransaction() {
			if (instance.getTimeInstant() == null) {
				instance.setTimeInstant(Globals.TIME_PROVIDER.getCurrentTime());
			}

			BlockchainTransactionService.createAndSendTransaction(instance);
			return instance;
		}

		/* (non-Javadoc)
		 * @see io.nem.builders.SwiftTransactionBuilder.IBuild#fee(org.nem.core.model.primitive.Amount)
		 */
		@Override
		public IBuild fee(Amount amount) {
			instance.setFee(amount);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.builders.SwiftTransactionBuilder.IBuild#deadline(org.nem.core.time.TimeInstant)
		 */
		@Override
		public IBuild deadline(TimeInstant timeInstant) {
			instance.setDeadline(timeInstant);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.builders.SwiftTransactionBuilder.IBuild#signature(org.nem.core.crypto.Signature)
		 */
		@Override
		public IBuild signature(Signature signature) {
			instance.setSignature(signature);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.builders.SwiftTransactionBuilder.IBuild#buildTransaction()
		 */
		@Override
		public TransferTransaction buildTransaction() {
			if (instance.getTimeInstant() == null) {
				instance.setTimeInstant(Globals.TIME_PROVIDER.getCurrentTime());
			}
			return (TransferTransaction) BlockchainTransactionService.createTransaction(instance.getTimeInstant(),
					instance.getSenderAccount(), instance.getRecipientAccount(), instance.getAmount(),
					instance.getAttachment());
		}

	}

}

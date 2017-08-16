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

	private SwiftTransactionBuilder() {
	}

	public static ISender sender(Account sender) {
		return new SwiftTransactionBuilder.Builder(sender);
	}

	public interface ISender {
		IRecipient recipient(Account recipient);
	}

	public interface IRecipient {
		IBuild amount(Long amount);
	}

	public interface IBuild {
		
		IBuild attachment(TransferTransactionAttachment attachment);
		
		IBuild fee(Amount amount);

		IBuild deadline(TimeInstant timeInstant);

		IBuild signature(Signature signature);

		TransferTransaction buildTransaction();

		SwiftTransaction buildAndSendTransaction();
	}

	private static class Builder implements ISender, IRecipient, IBuild {
		private SwiftTransaction instance = new SwiftTransaction();

		public Builder(Account sender) {
			instance.setSenderAccount(sender);
		}

		@Override
		public IBuild amount(Long amount) {
			instance.setAmount(amount);
			return this;
		}

		@Override
		public IRecipient recipient(Account recipient) {
			instance.setRecipientAccount(recipient);
			return this;
		}

		@Override
		public IBuild attachment(TransferTransactionAttachment attachment) {
			instance.setAttachment(attachment);
			return this;
		}

		@Override
		public SwiftTransaction buildAndSendTransaction() {
			if (instance.getTimeInstant() == null) {
				instance.setTimeInstant(Globals.TIME_PROVIDER.getCurrentTime());
			}

			BlockchainTransactionService.createAndSendTransaction(instance);
			return instance;
		}

		@Override
		public IBuild fee(Amount amount) {
			instance.setFee(amount);
			return this;
		}

		@Override
		public IBuild deadline(TimeInstant timeInstant) {
			instance.setDeadline(timeInstant);
			return this;
		}

		@Override
		public IBuild signature(Signature signature) {
			instance.setSignature(signature);
			return this;
		}

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

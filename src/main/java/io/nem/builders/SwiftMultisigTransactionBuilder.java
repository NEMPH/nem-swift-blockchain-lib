package io.nem.builders;

import org.nem.core.crypto.Signature;
import org.nem.core.model.Account;
import org.nem.core.model.MultisigSignatureTransaction;
import org.nem.core.model.MultisigTransaction;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.primitive.Amount;
import org.nem.core.time.TimeInstant;
import io.nem.model.SwiftMultisigTransaction;
import io.nem.service.Globals;
import io.nem.service.BlockchainTransactionService;

public class SwiftMultisigTransactionBuilder {

	private SwiftMultisigTransactionBuilder() {
	}

	public static ISender sender(Account sender) {
		return new SwiftMultisigTransactionBuilder.Builder(sender);
	}

	public interface ISender {
		IRecipient recipient(Account recipient);
	}

	public interface IRecipient {
		IBuild multisig(Account multisig);
	}

	public interface IBuild {
		
		IBuild amount(Long amount);
		
		IBuild attachment(TransferTransactionAttachment attachment);
		
		IBuild fee(Amount amount);

		IBuild deadline(TimeInstant timeInstant);

		IBuild signature(Signature signature);

		IBuild addSignature(MultisigSignatureTransaction signature);

		MultisigTransaction buildMultisigTransaction();

		SwiftMultisigTransaction buildAndSendMultisigTransaction();
	}

	private static class Builder implements ISender, IRecipient, IBuild {
		private SwiftMultisigTransaction instance = new SwiftMultisigTransaction();

		public Builder(Account sender) {
			instance.setSenderAccount(sender);
		}

		@Override
		public IBuild amount(Long amount) {
			instance.setAmount(amount);
			return this;
		}

		@Override
		public IBuild multisig(Account multisig) {
			instance.setMultisigAccount(multisig);
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
		public MultisigTransaction buildMultisigTransaction() {
			if (instance.getTimeInstant() == null) {
				instance.setTimeInstant(Globals.TIME_PROVIDER.getCurrentTime());
			}
			Transaction transaction = BlockchainTransactionService.createTransaction(instance.getTimeInstant(),
					instance.getSenderAccount(), instance.getRecipientAccount(), instance.getAmount(),
					instance.getAttachment());
			return (MultisigTransaction) BlockchainTransactionService.createMultisigTransaction(
					instance.getTimeInstant(), instance.getSenderAccount(), instance.getRecipientAccount(),
					instance.getAmount(), transaction);
		}

		@Override
		public SwiftMultisigTransaction buildAndSendMultisigTransaction() {
			if (instance.getTimeInstant() == null) {
				instance.setTimeInstant(Globals.TIME_PROVIDER.getCurrentTime());
			}
			BlockchainTransactionService.createAndSendMultisigTransaction(instance);
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
		public IBuild addSignature(MultisigSignatureTransaction signature) {
			instance.addMultisigSignature(signature);
			return this;
		}

	}

}

package io.nem.model;
import org.nem.core.model.Message;
import org.nem.core.model.TransferTransactionAttachment;

public class AttachmentFactory {
	public static TransferTransactionAttachment createTransferTransactionAttachment() {
		return new TransferTransactionAttachment();
	}
	public static TransferTransactionAttachment createTransferTransactionAttachment(Message message) {
		return new TransferTransactionAttachment(message);
	}
}

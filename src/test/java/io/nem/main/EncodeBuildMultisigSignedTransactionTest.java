package io.nem.main;

import org.junit.Test;
import org.nem.core.messages.PlainMessage;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.test.Utils;
import com.prowidesoftware.swift.io.ConversionService;
import io.nem.builders.SwiftMultisigTransactionBuilder;
import io.nem.factories.AttachmentFactory;
import io.nem.swift.crypto.SecureMessageSwiftPayloadEncoder;

/**
 * The Class BuildTransactionTest.
 */
public class EncodeBuildMultisigSignedTransactionTest extends TransactionUnitTest {

	/** The sample swift msg. */
	final String sampleSwiftMsg = "{1:F21FOOLHKH0AXXX0304009999}{4:{177:1608140809}{451:0}}{1:F01FOOLHKH0AXXX0304009999}{2:O9401609160814FOOLHKH0AXXX03040027341608141609N}{4:\n"
			+ ":20:USD940NO1\n" + ":21:123456/DEV\n" + ":25:USD234567\n" + ":28C:1/1\n" + ":60F:C160418USD672,\n"
			+ ":61:160827C642,S1032\n" + ":86:ANDY\n" + ":61:160827D42,S1032\n" + ":86:BANK CHARGES\n"
			+ ":62F:C160418USD1872,\n" + ":64:C160418USD1872,\n" + "-}{5:{CHK:0FEC1E4AEC53}{TNG:}}{S:{COP:S}}";

	/**
	 * Test cb build and send transaction.
	 */
	@Test
	public void testCbBuildAndSendSwiftStringTransaction() {

		// Build a transaction and send it.
		try {

			final SecureMessage message = SecureMessage.fromDecodedPayload(this.senderPrivateAccount,
					this.recipientPublicAccount, sampleSwiftMsg.getBytes());

			final PlainMessage msg = new PlainMessage(sampleSwiftMsg.getBytes());

			SwiftMultisigTransactionBuilder.sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount)
					.multisig(this.multiSigAccount).amount(0l)
					.attachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendMultisigTransaction();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test cb build and send swift string XML transaction.
	 */
	@Test
	public void testCbBuildAndSendSwiftStringXMLTransaction() {

		// Build a transaction and send it.
		try {

			final SecureMessage message = SecureMessage.fromDecodedPayload(this.senderPrivateAccount,
					this.recipientPublicAccount, new ConversionService().getXml(this.sampleSwiftMsg, true).getBytes());

			SwiftMultisigTransactionBuilder.sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount)
					.multisig(this.multiSigAccount).amount(0l)
					.attachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendMultisigTransaction();
			// it!
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test cb build and send swift transaction with mosaic.
	 */
	@Test
	public void testCbBuildAndSendSwiftTransactionWithMosaic() {

		// Build a transaction and send it.
		try {

			SecureMessage message = SecureMessageSwiftPayloadEncoder.encodeAndGzipCompress(this.senderPrivateAccount,
					this.recipientPublicAccount, sampleSwiftMsg);
			TransferTransactionAttachment attachment = new TransferTransactionAttachment(message);
			attachment.addMosaic(Utils.createMosaic(1).getMosaicId(), new Quantity(12));
			SwiftMultisigTransactionBuilder.sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount)
					.multisig(this.multiSigAccount).amount(0l)
					.attachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendMultisigTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test cb build and send swift file transaction.
	 */
	@Test
	public void testCbBuildAndSendSwiftFileTransaction() {

		SecureMessage message = null;
		try {
			message = SecureMessage.fromDecodedPayload(this.senderPrivateAccount, this.recipientPublicAccount,
					sampleSwiftMsg.getBytes());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// Build a transaction and send it.
		try {
			SwiftMultisigTransactionBuilder.sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount)
					.multisig(this.multiSigAccount).amount(0l)
					.attachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendMultisigTransaction();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

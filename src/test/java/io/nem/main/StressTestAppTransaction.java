package io.nem.main;

import java.io.IOException;

import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.TransferTransactionAttachment;

import io.nem.builders.SwiftBlockchainTransactionBuilder;
import io.nem.factories.EntityFactory;
import io.nem.util.GzipUtils;

public class StressTestAppTransaction {
	/** The sample swift msg. */

	public StressTestAppTransaction() {
		String sampleSwiftMsg = "{1:F21FOOLHKH0AXXX0304009999}{4:{177:1608140809}{451:0}}{1:F01FOOLHKH0AXXX0304009999}{2:O9401609160814FOOLHKH0AXXX03040027341608141609N}{4:\n"
				+ ":20:USD940NO1\n" + ":21:123456/DEV\n" + ":25:USD234567\n" + ":28C:1/1\n" + ":60F:C160418USD672,\n"
				+ ":61:160827C642,S1032\n" + ":86:ANDY\n" + ":61:160827D42,S1032\n" + ":86:BANK CHARGES\n"
				+ ":62F:C160418USD1872,\n" + ":64:C160418USD1872,\n" + "-}{5:{CHK:0FEC1E4AEC53}{TNG:}}{S:{COP:S}}";

		try {

			for (int i = 0; i < 500; i++) {

				// new Thread(new Runnable() {

				// @Override
				// public void run() {
				// test from a string.
				final Account senderAccount = EntityFactory
						.buildAccountFromPrivateKey("c9d930757f69584fc414d0b2b54a0c3aa064996f9b13b70d32c89879724153c1");
				final Account recipientAccount = EntityFactory
						.buildAccountFromPublicKey("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec");

				SecureMessage message;
				try {
					message = SecureMessage.fromDecodedPayload(senderAccount, recipientAccount,
							GzipUtils.compress(sampleSwiftMsg));

					TransferTransactionAttachment attachment = new TransferTransactionAttachment(message);

					SwiftBlockchainTransactionBuilder.getInstance().setSender(senderAccount)
							.setRecipient(recipientAccount).setAttachment(attachment).buildAndSendTransaction();
				} catch (IOException e) {
					e.printStackTrace();
				}

				// }
				// }).start();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new StressTestAppTransaction();
	}
}

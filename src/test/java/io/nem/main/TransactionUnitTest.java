package io.nem.main;

import org.junit.Assume;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;

public abstract class TransactionUnitTest {

	protected KeyPair senderPrivateKeyPair;
	protected KeyPair recipientPublicKeyPair;
	protected KeyPair multiSigKeyPair;

	protected Account senderPrivateAccount;
	protected Account recipientPublicAccount;
	protected Account multiSigAccount;

	public TransactionUnitTest() {
		Assume.assumeTrue(this.isTestable());
	}

	protected boolean isTestable() {
		if (senderPrivateKeyPair == null)
			return false;
		if (recipientPublicKeyPair == null)
			return false;

		return true;
		// multisig is subjective, transaction might not be
	}

	protected boolean isMultiSigTestable() {
		if (senderPrivateKeyPair == null)
			return false;
		if (recipientPublicKeyPair == null)
			return false;
		if (multiSigKeyPair == null)
			return false;

		return true;
	}

	protected TransactionUnitTest setKeyPairSenderPrivateKey(String privateKey) {
		this.senderPrivateKeyPair = new KeyPair(PrivateKey.fromHexString(privateKey));
		return this;
	}

	protected TransactionUnitTest setKeyPairRecipientPublicKey(String publicKey) {
		this.recipientPublicKeyPair = new KeyPair(PublicKey.fromHexString(publicKey));
		return this;
	}

	protected TransactionUnitTest setKeyPairMultisigAccountPublicKey(String publicKey) {
		this.multiSigKeyPair = new KeyPair(PublicKey.fromHexString(publicKey));
		return this;
	}

	protected TransactionUnitTest setAccountSenderPrivateKey(String privateKey) {
		this.senderPrivateAccount = new Account(new KeyPair(PrivateKey.fromHexString(privateKey)));
		return this;
	}

	protected TransactionUnitTest setAccountRecipientPublicKey(String publicKey) {
		this.recipientPublicAccount = new Account(new KeyPair(PublicKey.fromHexString(publicKey)));
		return this;
	}

	protected TransactionUnitTest setAccountMultisigAccountPublicKey(String publicKey) {
		this.multiSigAccount = new Account(new KeyPair(PublicKey.fromHexString(publicKey)));
		return this;
	}

}

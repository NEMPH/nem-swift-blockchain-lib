package io.nem.swift.crypto;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;

import io.nem.factories.EntityFactory;
import io.nem.util.GzipUtils;

/**
 * 
 * @author Alvin
 *
 */
public class SecureMessageSwiftPayloadEncoder {

	public static SecureMessage encode(Account senderPrivateKey, Account recipientPublicKey, String swiftMessage) {
		return SecureMessage.fromDecodedPayload(senderPrivateKey, recipientPublicKey, swiftMessage.getBytes());
	}

	public static SecureMessage encode(Account senderPrivateKey, Account recipientPublicKey, byte[] swiftMessage) {
		return SecureMessage.fromDecodedPayload(senderPrivateKey, recipientPublicKey, swiftMessage);
	}

	public static SecureMessage encode(String senderPrivateKey, String recipientPublicKey, String swiftMessage) {
		final Account senderAccount = EntityFactory.buildAccountFromPrivateKey(senderPrivateKey);
		final Account recipientAccount = EntityFactory.buildAccountFromPublicKey(recipientPublicKey);
		return SecureMessage.fromDecodedPayload(senderAccount, recipientAccount, swiftMessage.getBytes());
	}

	public static SecureMessage encode(String senderPrivateKey, String recipientPublicKey, byte[] swiftMessage) {
		final Account senderAccount = EntityFactory.buildAccountFromPrivateKey(senderPrivateKey);
		final Account recipientAccount = EntityFactory.buildAccountFromPublicKey(recipientPublicKey);
		return SecureMessage.fromDecodedPayload(senderAccount, recipientAccount, swiftMessage);
	}

	public static SecureMessage encodeAndGzipCompress(Account senderPrivateKey, Account recipientPublicKey,
			String swiftMessage) throws IOException {
		return SecureMessage.fromDecodedPayload(senderPrivateKey, recipientPublicKey, GzipUtils.compress(swiftMessage));
	}

	public static SecureMessage encodeAndGzipCompress(Account senderPrivateKey, Account recipientPublicKey,
			byte[] swiftMessage) throws UnsupportedEncodingException, IOException {
		return SecureMessage.fromDecodedPayload(senderPrivateKey, recipientPublicKey,
				GzipUtils.compress(new String(swiftMessage, "UTF-8")));
	}

	public static SecureMessage encodeAndGzipCompress(String senderPrivateKey, String recipientPublicKey,
			String swiftMessage) throws IOException {
		final Account senderAccount = EntityFactory.buildAccountFromPrivateKey(senderPrivateKey);
		final Account recipientAccount = EntityFactory.buildAccountFromPublicKey(recipientPublicKey);
		return SecureMessage.fromDecodedPayload(senderAccount, recipientAccount, GzipUtils.compress(swiftMessage));
	}

	public static SecureMessage encodeAndGzipCompress(String senderPrivateKey, String recipientPublicKey,
			byte[] swiftMessage) throws UnsupportedEncodingException, IOException {
		final Account senderAccount = EntityFactory.buildAccountFromPrivateKey(senderPrivateKey);
		final Account recipientAccount = EntityFactory.buildAccountFromPublicKey(recipientPublicKey);
		return SecureMessage.fromDecodedPayload(senderAccount, recipientAccount,
				GzipUtils.compress(new String(swiftMessage, "UTF-8")));
	}
}

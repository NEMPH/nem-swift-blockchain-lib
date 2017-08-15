package io.nem.swift.crypto;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;

import io.nem.factories.EntityFactory;
import io.nem.util.GzipUtils;

/**
 * The Class SecureMessageSwiftPayloadEncoder.
 *
 * @author Alvin
 */
public class SecureMessageSwiftPayloadEncoder {

	/**
	 * Encode.
	 *
	 * @param senderPrivateKey the sender private key
	 * @param recipientPublicKey the recipient public key
	 * @param swiftMessage the swift message
	 * @return the secure message
	 */
	public static SecureMessage encode(Account senderPrivateKey, Account recipientPublicKey, String swiftMessage) {
		return SecureMessage.fromDecodedPayload(senderPrivateKey, recipientPublicKey, swiftMessage.getBytes());
	}

	/**
	 * Encode.
	 *
	 * @param senderPrivateKey the sender private key
	 * @param recipientPublicKey the recipient public key
	 * @param swiftMessage the swift message
	 * @return the secure message
	 */
	public static SecureMessage encode(Account senderPrivateKey, Account recipientPublicKey, byte[] swiftMessage) {
		return SecureMessage.fromDecodedPayload(senderPrivateKey, recipientPublicKey, swiftMessage);
	}

	/**
	 * Encode.
	 *
	 * @param senderPrivateKey the sender private key
	 * @param recipientPublicKey the recipient public key
	 * @param swiftMessage the swift message
	 * @return the secure message
	 */
	public static SecureMessage encode(String senderPrivateKey, String recipientPublicKey, String swiftMessage) {
		final Account senderAccount = EntityFactory.buildAccountFromPrivateKey(senderPrivateKey);
		final Account recipientAccount = EntityFactory.buildAccountFromPublicKey(recipientPublicKey);
		return SecureMessage.fromDecodedPayload(senderAccount, recipientAccount, swiftMessage.getBytes());
	}

	/**
	 * Encode.
	 *
	 * @param senderPrivateKey the sender private key
	 * @param recipientPublicKey the recipient public key
	 * @param swiftMessage the swift message
	 * @return the secure message
	 */
	public static SecureMessage encode(String senderPrivateKey, String recipientPublicKey, byte[] swiftMessage) {
		final Account senderAccount = EntityFactory.buildAccountFromPrivateKey(senderPrivateKey);
		final Account recipientAccount = EntityFactory.buildAccountFromPublicKey(recipientPublicKey);
		return SecureMessage.fromDecodedPayload(senderAccount, recipientAccount, swiftMessage);
	}

	/**
	 * Encode and gzip compress.
	 *
	 * @param senderPrivateKey the sender private key
	 * @param recipientPublicKey the recipient public key
	 * @param swiftMessage the swift message
	 * @return the secure message
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static SecureMessage encodeAndGzipCompress(Account senderPrivateKey, Account recipientPublicKey,
			String swiftMessage) throws IOException {
		return SecureMessage.fromDecodedPayload(senderPrivateKey, recipientPublicKey, GzipUtils.compress(swiftMessage));
	}

	/**
	 * Encode and gzip compress.
	 *
	 * @param senderPrivateKey the sender private key
	 * @param recipientPublicKey the recipient public key
	 * @param swiftMessage the swift message
	 * @return the secure message
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static SecureMessage encodeAndGzipCompress(Account senderPrivateKey, Account recipientPublicKey,
			byte[] swiftMessage) throws UnsupportedEncodingException, IOException {
		return SecureMessage.fromDecodedPayload(senderPrivateKey, recipientPublicKey,
				GzipUtils.compress(new String(swiftMessage, "UTF-8")));
	}

	/**
	 * Encode and gzip compress.
	 *
	 * @param senderPrivateKey the sender private key
	 * @param recipientPublicKey the recipient public key
	 * @param swiftMessage the swift message
	 * @return the secure message
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static SecureMessage encodeAndGzipCompress(String senderPrivateKey, String recipientPublicKey,
			String swiftMessage) throws IOException {
		final Account senderAccount = EntityFactory.buildAccountFromPrivateKey(senderPrivateKey);
		final Account recipientAccount = EntityFactory.buildAccountFromPublicKey(recipientPublicKey);
		return SecureMessage.fromDecodedPayload(senderAccount, recipientAccount, GzipUtils.compress(swiftMessage));
	}

	/**
	 * Encode and gzip compress.
	 *
	 * @param senderPrivateKey the sender private key
	 * @param recipientPublicKey the recipient public key
	 * @param swiftMessage the swift message
	 * @return the secure message
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static SecureMessage encodeAndGzipCompress(String senderPrivateKey, String recipientPublicKey,
			byte[] swiftMessage) throws UnsupportedEncodingException, IOException {
		final Account senderAccount = EntityFactory.buildAccountFromPrivateKey(senderPrivateKey);
		final Account recipientAccount = EntityFactory.buildAccountFromPublicKey(recipientPublicKey);
		return SecureMessage.fromDecodedPayload(senderAccount, recipientAccount,
				GzipUtils.compress(new String(swiftMessage, "UTF-8")));
	}
}

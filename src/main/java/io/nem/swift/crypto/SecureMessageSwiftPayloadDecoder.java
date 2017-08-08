package io.nem.swift.crypto;

import java.io.IOException;

import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.utils.HexEncoder;

import io.nem.util.GzipUtils;

public class SecureMessageSwiftPayloadDecoder {

	public static SecureMessage decode(String senderPublicKey, String recipientPrivateKey, String encryptedPayload) {
		final KeyPair senderKeyPair = new KeyPair(PublicKey.fromHexString(senderPublicKey));
		final KeyPair recipientKeyPairPriv = new KeyPair(PrivateKey.fromHexString(recipientPrivateKey));
		return SecureMessage.fromEncodedPayload(new Account(senderKeyPair), new Account(recipientKeyPairPriv),
				HexEncoder.getBytes(encryptedPayload));
	}

	public static SecureMessage decode(String senderPublicKey, String recipientPrivateKey, byte[] encryptedPayload) {
		final KeyPair senderKeyPair = new KeyPair(PublicKey.fromHexString(senderPublicKey));
		final KeyPair recipientKeyPairPriv = new KeyPair(PrivateKey.fromHexString(recipientPrivateKey));
		return SecureMessage.fromEncodedPayload(new Account(senderKeyPair), new Account(recipientKeyPairPriv),
				HexEncoder.getString(encryptedPayload).getBytes());
	}

	public static String decodeAndGzipUncompress(String senderPublicKey, String recipientPrivateKey,
			String encryptedPayload) throws IOException {
		final KeyPair senderKeyPair = new KeyPair(PublicKey.fromHexString(senderPublicKey));
		final KeyPair recipientKeyPairPriv = new KeyPair(PrivateKey.fromHexString(recipientPrivateKey));
		SecureMessage secureMessage = SecureMessage.fromEncodedPayload(new Account(senderKeyPair),
				new Account(recipientKeyPairPriv), HexEncoder.getBytes(encryptedPayload));

		return GzipUtils.decompress(secureMessage.getDecodedPayload());
	}

	public static String decodeAndGzipUncompress(String senderPublicKey, String recipientPrivateKey,
			byte[] encryptedPayload) throws IOException {
		final KeyPair senderKeyPair = new KeyPair(PublicKey.fromHexString(senderPublicKey));
		final KeyPair recipientKeyPairPriv = new KeyPair(PrivateKey.fromHexString(recipientPrivateKey));
		SecureMessage secureMessage = SecureMessage.fromEncodedPayload(new Account(senderKeyPair),
				new Account(recipientKeyPairPriv), HexEncoder.getString(encryptedPayload).getBytes());

		return GzipUtils.decompress(secureMessage.getDecodedPayload());
	}

	public static String decodeAndGzipUncompress(KeyPair senderPublicKey, KeyPair recipientPrivateKey,
			String encryptedPayload) throws IOException {
		SecureMessage secureMessage = SecureMessage.fromEncodedPayload(new Account(senderPublicKey),
				new Account(recipientPrivateKey), HexEncoder.getBytes(encryptedPayload));
		return GzipUtils.decompress(secureMessage.getDecodedPayload());
	}

	public static String decodeAndGzipUncompress(KeyPair senderPublicKey, KeyPair recipientPrivateKey,
			byte[] encryptedPayload) throws IOException {
		SecureMessage secureMessage = SecureMessage.fromEncodedPayload(new Account(senderPublicKey),
				new Account(recipientPrivateKey), HexEncoder.getString(encryptedPayload).getBytes());
		return GzipUtils.decompress(secureMessage.getDecodedPayload());
	}

	public static String decodeAndGzipUncompress(Account senderPublicKey, Account recipientPrivateKey,
			String encryptedPayload) throws IOException {
		SecureMessage secureMessage = SecureMessage.fromEncodedPayload(senderPublicKey, recipientPrivateKey,
				HexEncoder.getBytes(encryptedPayload));
		return GzipUtils.decompress(secureMessage.getDecodedPayload());
	}

	public static String decodeAndGzipUncompress(Account senderPublicKey, Account recipientPrivateKey,
			byte[] encryptedPayload) throws IOException {
		SecureMessage secureMessage = SecureMessage.fromEncodedPayload(senderPublicKey, recipientPrivateKey,
				HexEncoder.getString(encryptedPayload).getBytes());
		return GzipUtils.decompress(secureMessage.getDecodedPayload());
	}

}

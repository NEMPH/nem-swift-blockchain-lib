package io.nem.factories;

import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.model.Account;
import org.nem.core.model.Address;

/**
 * A factory for creating Entity objects.
 */
public class EntityFactory {
	
	/**
	 * Creates a new Entity object.
	 *
	 * @param address the address
	 * @return the account
	 */
	public static Account createAccount(Address address) {
		return new Account(address);
	}
	
	/**
	 * Creates a new Entity object.
	 *
	 * @param keyPair the key pair
	 * @return the account
	 */
	public static Account createAccount(KeyPair keyPair) {
		return new Account(keyPair);
	}
	
	/**
	 * Creates a new Entity object.
	 *
	 * @param hex the hex
	 * @return the account
	 */
	public static Account createAccount(String hex) {
		return new Account(new KeyPair(PrivateKey.fromHexString(hex)));
	}
}

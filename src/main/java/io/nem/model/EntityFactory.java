package io.nem.model;

import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.model.Account;
import org.nem.core.model.Address;

public class EntityFactory {
	
	public static Account createAccount(Address address) {
		return new Account(address);
	}
	
	public static Account createAccount(KeyPair keyPair) {
		return new Account(keyPair);
	}
	
	public static Account createAccount(String hex) {
		return new Account(new KeyPair(PrivateKey.fromHexString(hex)));
	}
}

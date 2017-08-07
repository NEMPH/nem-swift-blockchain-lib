package io.nem.main;

import org.junit.Test;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.utils.HexEncoder;
import io.nem.util.GzipUtils;

/**
 * The Class BuildTransactionTest.
 */
public class DecodeBuildTransactionTest {

	/** The sample swift msg. */
	final String sampleSwiftMsg = "c621b0e0a3f20523f60f9d99394e716e598c575b9f3c06a613b5d1483805dcb22c08e09de166ac08ebbf7c3f953dda74d634e60d81ba35059a53bb6662b624d7243315d3af013630f17f2b2120869a364e39152b35aa502067166cd80c215db3c023ec4c28c9438d33d0c75e15093bbb7d84154476288d49017918f3cc2f90d9734400c45283d258068c6d2b4db5c1243ff32d008bb92d8841455f38b611fa67ecc4afc0761a5ec3931c4875850d98d64d00e87a93f1284847b6d62390ead93f714784f658c6651ae56b1cb9684339ee654204bdef601e2df33f68d51463e58d2f4cdb5570a33084717f7b24c2bdf9e745d6154c0260182f59d7901d04131aa636dc3f368fb4622a5fb8e65fc24cd3f9";
	final String simplePayload = "2e66cc91dc415cb8c51ab5ffc8f38724119a417d86ecfcfc2169576ac224ca92f1081ea1541eb0ef1badfc3cd0f8f4a22967f31f1775057b4b690baf07262fb5eb0e9682df212ce774d4956736c16b9c";
	/**
	 * Test transaction hash.
	 */
	@Test
	public void testDecode() {
	
		final KeyPair senderKeyPairPriv = new KeyPair(PublicKey.fromHexString("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec"));
		final KeyPair senderKeyPair = new KeyPair(PublicKey.fromHexString("d72ebcdf243f9d217f448d937ac9431ba6ee4984b167015abcfc5b22e169ca23"));
		final KeyPair recipientKeyPairPriv = new KeyPair(PrivateKey.fromHexString("c9d930757f69584fc414d0b2b54a0c3aa064996f9b13b70d32c89879724153c1"));
		final KeyPair recipientKeyPair = new KeyPair(PublicKey.fromHexString("8043f36622be5c91e00d9977c870935c887ff9050ba0a62207db76dba1a87385"));

		SecureMessage secureMessage = SecureMessage.fromEncodedPayload(new Account(senderKeyPair), new Account(recipientKeyPairPriv), HexEncoder.getBytes(sampleSwiftMsg));
		
		String decodedPayloadString = "";
		try {
			byte [] decodedPayload = secureMessage.getDecodedPayload();
			String a = GzipUtils.decompress(decodedPayload);
			//System.out.println(cipher.decrypt(decodedPayload).toString());
			System.out.println(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

}

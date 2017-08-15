[![Build Status](https://travis-ci.org/alvin-reyes/swift-blockchain-lib.svg?branch=master)](https://travis-ci.org/alvin-reyes/swift-blockchain-lib)

# Swift / Blockchain Library for NEM.io Platform

Java library to convert and send Swift Messages to NEM Blockchain and vice versa
<center>
<img src="http://arcabots.com/swift_bc_nem.png"></img>
</center>
<h3>Usage</h3>

Set up your Node first via app.properties.

The library uses the Builder Pattern to create and send transactions.

<h4>Set up your node connection</h4>
Create a app.properties file on your classpath and add the following:

```properties

node.endpoint.networkname=mijinnet
node.endpoint.protocol=http
node.endpoint.uri=a1.nem.foundation
node.endpoint.port=7895
```

<h4>Encode and Gzip Swift File/Text</h4>

```java

	//	 swift message
	final String sampleSwiftMsg = "{1:F21FOOLHKH0AXXX0304009999}{4:{177:1608140809}{451:0}}{1:F01FOOLHKH0AXXX0304009999}{2:O9401609160814FOOLHKH0AXXX03040027341608141609N}{4:\n"
			+ ":20:USD940NO1\n" + ":21:123456/DEV\n" + ":25:USD234567\n" + ":28C:1/1\n" + ":60F:C160418USD672,\n"
			+ ":61:160827C642,S1032\n" + ":86:ANDY\n" + ":61:160827D42,S1032\n" + ":86:BANK CHARGES\n"
			+ ":62F:C160418USD1872,\n" + ":64:C160418USD1872,\n" + "-}{5:{CHK:0FEC1E4AEC53}{TNG:}}{S:{COP:S}}";
			
	//	set the account 
	final Account senderAccount = EntityFactory
			.buildAccountFromPrivateKey("<privatekey>");
	final Account recipientAccount = EntityFactory
			.buildAccountFromPublicKey("<publickey>");
	
	//	prepare the payload message.
	SecureMessage message = SecureMessageSwiftPayloadEncoder.encodeAndGzipCompress(senderAccount, recipientAccount, sampleSwiftMsg);

	// use the builder to build and send the transaction.
	SwiftBlockchainTransactionBuilder.getInstance()
			.setSender(senderAccount)
			.setRecipient(recipientAccount)
			.setAttachment(AttachmentFactory.createTransferTransactionAttachment(message))
			.buildAndSendTransaction(); // build and send it!
```

<h4>Decode Swift File/Text</h4>

```java

	//	 encrypted swift message
	final String encryptedMessage = "c621b0e0a3f20523f60f9d99394e716e598c575b9f3c06a613b5d1483805dcb22c08e09de166ac08ebbf7c3f953dda74d634e60d81ba35059a53bb6662b624d7243315d3af013630f17f2b2120869a364e39152b35aa502067166cd80c215db3c023ec4c28c9438d33d0c75e15093bbb7d84154476288d49017918f3cc2f90d9734400c45283d258068c6d2b4db5c1243ff32d008bb92d8841455f38b611fa67ecc4afc0761a5ec3931c4875850d98d64d00e87a93f1284847b6d62390ead93f714784f658c6651ae56b1cb9684339ee654204bdef601e2df33f68d51463e58d2f4cdb5570a33084717f7b24c2bdf9e745d6154c0260182f59d7901d04131aa636dc3f368fb4622a5fb8e65fc24cd3f9";
			
	//	set the account 
	final Account senderAccount = EntityFactory
			.buildAccountFromPrivateKey("<privatekey>");
	final Account recipientAccount = EntityFactory
			.buildAccountFromPublicKey("<publickey>");
	
	//	Get the decoded message.
	String decodedPayload = SecureMessageSwiftPayloadDecoder.decodeAndGzipUncompress(senderKeyPair, recipientKeyPairPriv,encryptedMessage);
	
```

<h3>Testing</h3>

Run the JUnit Test cases using Maven or use it as a reference.

	mvn test

<h3>Build</h3>

Like any other maven project, just do the maven command below.

    mvn clean install

<sub>Copyright (c) 2017</sub>

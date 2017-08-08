[![Build Status](https://travis-ci.org/alvin-reyes/swift-blockchain-lib.svg?branch=master)](https://travis-ci.org/alvin-reyes/swift-blockchain-lib)

# Swift / Blockchain Library for NEM.io Platform

Java library to convert and send Swift Messages to NEM Blockchain and vice versa

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

bank.endpoint.protocol=http
bank.endpoint.uri=localhost
bank.endpoint.port=80
bank.endpoint.method=post

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
			.buildAccountFromPrivateKey("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec");
	final Account recipientAccount = EntityFactory
			.buildAccountFromPublicKey("c9d930757f69584fc414d0b2b54a0c3aa064996f9b13b70d32c89879724153c1");
	
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

	//	 swift message
	final String sampleSwiftMsg = "{1:F21FOOLHKH0AXXX0304009999}{4:{177:1608140809}{451:0}}{1:F01FOOLHKH0AXXX0304009999}{2:O9401609160814FOOLHKH0AXXX03040027341608141609N}{4:\n"
			+ ":20:USD940NO1\n" + ":21:123456/DEV\n" + ":25:USD234567\n" + ":28C:1/1\n" + ":60F:C160418USD672,\n"
			+ ":61:160827C642,S1032\n" + ":86:ANDY\n" + ":61:160827D42,S1032\n" + ":86:BANK CHARGES\n"
			+ ":62F:C160418USD1872,\n" + ":64:C160418USD1872,\n" + "-}{5:{CHK:0FEC1E4AEC53}{TNG:}}{S:{COP:S}}";
			
	//	set the account 
	final Account senderAccount = EntityFactory
			.buildAccountFromPrivateKey("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec");
	final Account recipientAccount = EntityFactory
			.buildAccountFromPublicKey("c9d930757f69584fc414d0b2b54a0c3aa064996f9b13b70d32c89879724153c1");
	
	//	Get the decoded message.
	String decodedPayload = SecureMessageSwiftPayloadDecoder.decodeAndGzipUncompress(senderKeyPair, recipientKeyPairPriv,sampleSwiftMsg);
	
```

<h3>Testing</h3>

Run the JUnit Test cases using Maven or use it as a reference.

	mvn test

<h3>Build</h3>

Like any other maven project, just do the maven command below.

    mvn clean install

<sub>Copyright (c) 2017</sub>

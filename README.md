[![Build Status](https://travis-ci.org/alvin-reyes/swift-blockchain-lib.svg?branch=master)](https://travis-ci.org/alvin-reyes/swift-blockchain-lib)

# Swift / Blockchain Library for NEM.io Platform

Java library to convert and send Swift Messages to NEM Blockchain and vice versa

<h3>Usage</h3>

Set up your Node first via app.properties.

The library uses the Builder Pattern to create and send transactions.
	
	//	set the account
	final Account senderAccount = EntityFactory
			.buildAccount("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec");
	final Account recipientAccount = EntityFactory
			.buildAccount("c9d930757f69584fc414d0b2b54a0c3aa064996f9b13b70d32c89879724153c1");
	
	//	prepare the payload message.
	final SecureMessage message = SecureMessage.fromDecodedPayload(senderAccount, recipientAccount,
			new ConversionService().getXml(this.sampleSwiftMsg, true).getBytes());

	// use the builder to build and send the transaction.
	SwiftBlockchainTransactionBuilder.getInstance().setSender(senderAccount).setRecipient(recipientAccount)
			.setAttachment(AttachmentFactory.createTransferTransactionAttachment(message))
			.buildAndSendTransaction(); // build and send it!


<h3>Testing</h3>

Run the JUnit Test cases using Maven or use it as a reference.

	mvn test

<h3>Build</h3>

Like any other maven project, just do the maven command below.

    mvn clean install

<sub>Copyright (c) 2017</sub>

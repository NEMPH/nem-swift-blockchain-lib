package io.nem.service;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import org.nem.core.connect.HttpJsonPostRequest;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.Account;
import org.nem.core.model.NetworkInfos;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.ncc.RequestAnnounce;
import org.nem.core.model.primitive.Amount;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.BinarySerializer;
import org.nem.core.serialization.Deserializer;
import org.nem.core.time.TimeInstant;

import io.nem.util.AppPropertiesUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionService.
 */
public class BlockchainTransactionService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(BlockchainTransactionService.class.getName());
	
	static {
		NetworkInfos.setDefault(NetworkInfos.fromFriendlyName(AppPropertiesUtil.getProperty("node.endpoint.networkname")));
	}

	/**
	 * Creates the and send transaction.
	 *
	 * @param sender the sender
	 * @param recipient the recipient
	 * @param amount the amount
	 */
	public static void createAndSendTransaction(final Account sender, final Account recipient, final long amount) {

		final Transaction transaction = createTransaction(Globals.TIME_PROVIDER.getCurrentTime(), sender, recipient,
				amount, null);
		final byte[] data = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());

		final RequestAnnounce request = new RequestAnnounce(data, transaction.getSignature().getBytes());
		final CompletableFuture<Deserializer> future = send(Globals.NODE_ENDPOINT, request);
		future.thenAccept(d -> {
			final NemAnnounceResult result = new NemAnnounceResult(d);
			switch (result.getCode()) {
			case 1:
				LOGGER.info(String.format("successfully send %d micro xem from %s to %s", amount, sender.getAddress(),
						recipient.getAddress()));
				break;
			default:
				LOGGER.warning(String.format("could not send xem from %s to %s, reason: %s", sender.getAddress(),
						recipient.getAddress(), result.getMessage()));
			}
		}).exceptionally(e -> {
			LOGGER.warning(String.format("could not send xem from %s to %s, reason: %s", sender.getAddress(),
					recipient.getAddress().getEncoded(), e.getMessage()));
			return null;
		}).join();
	}
	

	/**
	 * Creates the and send transaction.
	 *
	 * @param sender the sender
	 * @param recipient the recipient
	 * @param amount the amount
	 * @param attachment the attachment
	 */
	public static void createAndSendTransaction(final Account sender, final Account recipient, final long amount,
			final TransferTransactionAttachment attachment) {
		
		final Transaction transaction = createTransaction(Globals.TIME_PROVIDER.getCurrentTime(), sender, recipient,
				amount, attachment);
		
		final byte[] data = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());
		final RequestAnnounce request = new RequestAnnounce(data, transaction.getSignature().getBytes());

		final CompletableFuture<Deserializer> future = send(Globals.NODE_ENDPOINT, request);
		future.thenAccept(d -> {
			final NemAnnounceResult result = new NemAnnounceResult(d);
			switch (result.getCode()) {
			case 1:
				LOGGER.info(String.format("successfully send %d from %s to %s", amount,
						sender.getAddress(), recipient.getAddress()));
				break;
			default:
				LOGGER.warning(String.format("could not send %d from %s to %s, reason: %s", amount,
						sender.getAddress(), recipient.getAddress(), result.getMessage()));
			}
		}).exceptionally(e -> {
			
			LOGGER.warning(String.format("could not send %s from %s to %s, reason: %s", attachment.getMessage(),
					sender.getAddress(), recipient.getAddress().getEncoded(), e.getMessage()));
			
			return null;
		}).join();
		
	}
	

	/**
	 * Creates the transaction.
	 *
	 * @param timeInstant the time instant
	 * @param sender the sender
	 * @param recipient the recipient
	 * @param amount the amount
	 * @param attachment the attachment
	 * @return the transaction
	 */
	public static Transaction createTransaction(final TimeInstant timeInstant, final Account sender,
			final Account recipient, final long amount, final TransferTransactionAttachment attachment) {
		final TransferTransaction transaction = new TransferTransaction(2, // version
				timeInstant, // time instant
				sender, // sender
				recipient, // recipient
				Amount.fromMicroNem(amount), // amount in micro xem
				attachment); // attachment (message, mosaics)
		
		transaction.setFee(Amount.fromNem(0));
		transaction.setDeadline(timeInstant.addHours(23));
		transaction.sign();
		
		return transaction;
	}

	/**
	 * Send.
	 *
	 * @param endpoint the endpoint
	 * @param request the request
	 * @return the completable future
	 */
	private static CompletableFuture<Deserializer> send(final NodeEndpoint endpoint, final RequestAnnounce request) {
		return Globals.CONNECTOR.postAsync(endpoint, NisApiId.NIS_REST_TRANSACTION_ANNOUNCE,
				new HttpJsonPostRequest(request));
	}

}

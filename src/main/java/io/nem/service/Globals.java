package io.nem.service;

import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.time.SystemTimeProvider;
import org.nem.core.time.TimeProvider;

import io.nem.factories.ConnectorFactory;

/**
 * The Class Globals.
 */
public class Globals {
	
	/** The Constant TIME_PROVIDER. */
	public static final TimeProvider TIME_PROVIDER = new SystemTimeProvider();
	
	/** The Constant MIJIN_NODE_ENDPOINT. */
	public static final NodeEndpoint NODE_ENDPOINT = new NodeEndpoint("http","50.3.87.123", 7890);
	
	/** The Constant CONNECTOR. */
	public static final DefaultAsyncNemConnector<ApiId> CONNECTOR = ConnectorFactory.createConnector();
}
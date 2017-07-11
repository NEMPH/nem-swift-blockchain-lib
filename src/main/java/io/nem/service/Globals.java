package io.nem.service;

import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.time.SystemTimeProvider;
import org.nem.core.time.TimeProvider;

import io.nem.factories.ConnectorFactory;
import io.nem.util.AppPropertiesUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class Globals.
 */
public class Globals {
	
	/** The Constant TIME_PROVIDER. */
	public static final TimeProvider TIME_PROVIDER = new SystemTimeProvider();
	
	/** The Constant NODE_ENDPOINT. */
	public static final NodeEndpoint NODE_ENDPOINT = new NodeEndpoint(
			AppPropertiesUtil.getProperty("node.endpoint.protocol"),
			AppPropertiesUtil.getProperty("node.endpoint.uri"), 
			Integer.valueOf(AppPropertiesUtil.getProperty("node.endpoint.port")));
	
	/** The Constant CONNECTOR. */
	public static final DefaultAsyncNemConnector<ApiId> CONNECTOR = ConnectorFactory.createConnector();
}
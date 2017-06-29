package io.nem.service;

import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.time.SystemTimeProvider;
import org.nem.core.time.TimeProvider;

public class Globals {
	public static final TimeProvider TIME_PROVIDER = new SystemTimeProvider();
	public static final NodeEndpoint MIJIN_NODE_ENDPOINT = new NodeEndpoint("http","d1.dfintech.com", 7895);
	public static final DefaultAsyncNemConnector<ApiId> CONNECTOR = ConnectorFactory.createConnector();
}
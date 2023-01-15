package com.joshlong.esper.autoconfiguration;

/**
 * Used to configure an Esper
 * {@link com.espertech.esper.common.client.configuration.Configuration} configuration.
 * Useful to, for example, register types that'll be persisted.
 *
 * @author Josh Long
 * @since 0.0.1
 */

public interface EsperConfigurationCustomizer {

	void customize(com.espertech.esper.common.client.configuration.Configuration configuration) throws Exception;

}

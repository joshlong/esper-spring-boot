package com.joshlong.esper.autoconfiguration;

public interface EsperConfigurationCustomizer {

	/**
	 * Used to configure an Esper
	 * {@link com.espertech.esper.common.client.configuration.Configuration}
	 * configuration. Useful to, for example, register types that'll be persisted.
	 * @param configuration the configuration on which to act.
	 * @throws Exception in case something goes wrong, it'll bubble up to the framework.
	 */
	void customize(com.espertech.esper.common.client.configuration.Configuration configuration) throws Exception;

}

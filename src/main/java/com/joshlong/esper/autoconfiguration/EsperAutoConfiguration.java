package com.joshlong.esper.autoconfiguration;

import com.espertech.esper.compiler.client.EPCompiler;
import com.espertech.esper.compiler.client.EPCompilerProvider;
import com.espertech.esper.runtime.client.EPDeploymentService;
import com.espertech.esper.runtime.client.EPEventService;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPRuntimeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides default implementations of much of the infrastructure required to run the
 * Esper CEP engine.
 *
 * @author Josh Long
 */

@AutoConfiguration
class EsperAutoConfiguration {

    private final Logger log  = LoggerFactory.getLogger(getClass());

    @Bean
    @ConditionalOnMissingBean
    EPCompiler esperCompiler() {
        return EPCompilerProvider.getCompiler();
    }

    @Bean
    @ConditionalOnMissingBean
    com.espertech.esper.common.client.configuration.Configuration esperConfiguration(
            @Autowired(required = false) EsperConfigurationCustomizer[] configurationCustomizers) throws Exception {
        var listOfConfigs = !(null == configurationCustomizers || configurationCustomizers.length == 0)
                ? new ArrayList<>(List.of(configurationCustomizers)) : new ArrayList<EsperConfigurationCustomizer>();
        if (listOfConfigs.size() == 0)
            log.warn("you probably should configure at least one bean of type "
                    + EsperConfigurationCustomizer.class.getSimpleName() + "...");
        var esperConfiguration = new com.espertech.esper.common.client.configuration.Configuration();
        for (var cc : listOfConfigs)
            cc.customize(esperConfiguration);
        return esperConfiguration;
    }

    @Bean
    @ConditionalOnMissingBean
    EPRuntime esperRuntime(com.espertech.esper.common.client.configuration.Configuration configuration) {
        return EPRuntimeProvider.getDefaultRuntime(configuration);
    }

    @Bean
    @ConditionalOnMissingBean
    EPDeploymentService esperDeploymentService(EPRuntime runtime) {
        return runtime.getDeploymentService();
    }

    @Bean
    @ConditionalOnMissingBean
    EPEventService esperEventService(EPRuntime runtime) {
        return runtime.getEventService();
    }

}

package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class FeignMicrometerConfiguration {
	@Bean
	public Capability micrometerCapability(MeterRegistry meterRegistry) {
		return new MicrometerCapability(meterRegistry);
	}


}

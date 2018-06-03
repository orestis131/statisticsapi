package n26.code.challenge.cxf;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CXFConfiguration {

	public LoggingInInterceptor logInInterceptor() {
		return new LoggingInInterceptor();
	}

	public LoggingOutInterceptor logOutInterceptor() {
		return new LoggingOutInterceptor();
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		SpringBus springBus = new SpringBus();
		springBus.getInInterceptors().add(logInInterceptor());
		springBus.getInFaultInterceptors().add(logInInterceptor());
		springBus.getOutInterceptors().add(logOutInterceptor());
		springBus.getOutFaultInterceptors().add(logOutInterceptor());
		return springBus;
	}

	@Bean
	public JacksonJsonProvider getJsonProvider() {
		return new JacksonJsonProvider();
	}
}

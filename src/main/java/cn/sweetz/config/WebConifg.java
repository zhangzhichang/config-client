package cn.sweetz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConifg extends WebMvcConfigurationSupport {
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(new String[] { "swagger-ui.html" })
				.addResourceLocations(new String[] { "classpath:/META-INF/resources/" });

		registry.addResourceHandler(new String[] { "/webjars/**" })
				.addResourceLocations(new String[] { "classpath:/META-INF/resources/webjars/" });
	}
}
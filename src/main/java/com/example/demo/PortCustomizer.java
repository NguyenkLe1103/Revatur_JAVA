package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:port-override.properties")
public class PortCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

  // Pull value from our custom file
  @Value("${app.server.port:0}")
  private int customPort;

  @Override
  public void customize(ConfigurableWebServerFactory factory) {
    // If customPort > 0, it overrides server.port from application.properties
    if (customPort > 0) {
      factory.setPort(customPort);
    }
  }
}
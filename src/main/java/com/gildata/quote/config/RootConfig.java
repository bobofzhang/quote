package com.gildata.quote.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.gildata.quote.service" })
public class RootConfig {


}

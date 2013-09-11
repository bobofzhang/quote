package com.gildata.quote.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = { "com.gildata.quote" }, excludeFilters = { @ComponentScan.Filter(Controller.class) })
public class RootConfig {

}

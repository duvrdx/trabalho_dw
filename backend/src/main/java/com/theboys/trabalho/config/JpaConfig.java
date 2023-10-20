package com.theboys.trabalho.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("com.theboys.trabalho.models")
public class JpaConfig {
}

package com.dsm.athena.global.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(basePackages = ["com.dsm.athena.global.**.env"])
class PropertiesScanConfig () {}

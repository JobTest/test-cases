package com.paazl.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"com.paazl"})
@Import(DataConfiguration.class)
public class ApplicationConfiguration {}

package com.poscodx.container.config.soundsystem;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
//annotation-config를 대신하는 코드 추가
@ComponentScan(basePackages={"com.poscodx.container.soundsystem"})
public class CDPlayerConfig {
	
}

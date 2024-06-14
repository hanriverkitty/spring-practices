package com.poscodx.container.soundsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.poscodx.container.config.soundsystem.CDPlayerConfig;

// junit을 자신의 컨테이너 환경에 통합시켜준다
@ExtendWith(SpringExtension.class)

// configuration을 알려준다 (자바버전)
@ContextConfiguration(classes={CDPlayerConfig.class})
public class CDPlayerJavaConfigTest {
	@Autowired
	private CDPlayer cdPlayer;
	
	@Test
	public void testCDPlyerNotNull() {
		assertNotNull(cdPlayer);
	}
	@Test
	public void testPlay() {
		assertEquals("Playing : 붕붕 by 김하온",cdPlayer.play());
	}
}

package com.poscodx.container.videosystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.poscodx.container.config.videosystem.DVDPlayerConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {DVDPlayerConfig.class})
public class DVDPlayerJavaConfigTest {
	
	// DVDPlayer DI도 두가지 방식을 선언했기때문에 문제가 생길 수 있다
	
	@Autowired
	// 같은 타입의 빈이 2개 이상 있는 경우
	// 설정 클래스의 빈 생성 메소드의 @Bean의 name(default) 속성으로 Qualifier 하기
	@Qualifier("dvdPlayer") // 메소드이름이 ID가 된다
	private DVDPlayer dvdPlayer01;
	

	@Autowired
	// 설정 클래스의 빈 생성 메소드의 이름으로 속성으로 Qualifier 하기
	@Qualifier("dvdPlayer2")
	private DVDPlayer dvdPlayer02;
	
	@Test
	public void testDVDPlayerNotNull() {
		assertNotNull(dvdPlayer01);	
	}
	
	@Test
	public void testPlay() {
		assertEquals("Playing Movie : Marvel's Avengers", dvdPlayer01.play());
	}
	
	@Test
	public void testDVDPlayer02NotNull() {
		assertNotNull(dvdPlayer02);	
	}
	
	@Test
	public void testDVDPlayer02Play() {
		assertEquals("Playing Movie : Marvel's Avengers", dvdPlayer02.play());
	}
}

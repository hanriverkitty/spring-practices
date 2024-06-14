package com.poscodx.container.config.videosystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.poscodx.container.videosystem.Avengers;
import com.poscodx.container.videosystem.DVDPlayer;
import com.poscodx.container.videosystem.DigitalVideoDisc;

@Configuration
public class DVDPlayerConfig {

	// 메소드이름이 아이디가 된다
	// @Bean을 붙여준 애들만 실행해서 return 된 애들을 컨테이너가 실행해서 Bean등록을 한다
	@Bean
	public DigitalVideoDisc avengers() {
		return new Avengers();
	}

	// 주입(Injection)하기-1
	// Bean 생성 메소드를 직접 호출하는 방법
	// 생성자 주입
	@Bean ("dvdPlayer") // ID 직접선언
	public DVDPlayer dvdPlayer1() {
		return new DVDPlayer(avengers());
	}

	// 주입(Injection)하기-2
	// Parameter로 Bean을 전달하는 방법
	// DigitalVideoDisc를 구현아 bean이 여러개 있으면 문제가 발생할 수 있다
	// 생성자 주입 (가장 많이 쓰는 방법)
	@Bean
	public DVDPlayer dvdPlayer2(DigitalVideoDisc dvd) {
		return new DVDPlayer(dvd);
	}
	
	// 주입(Injection)하기-3
	// setter 주입
	@Bean("dvdPlayer3")
	public DVDPlayer dvdPlayer3(DigitalVideoDisc dvd) {
		DVDPlayer dvdPlayer = new DVDPlayer();
		dvdPlayer.setDvd(dvd);
		return dvdPlayer;
	}
	
}

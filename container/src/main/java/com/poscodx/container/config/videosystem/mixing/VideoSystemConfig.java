package com.poscodx.container.config.videosystem.mixing;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.poscodx.container.videosystem.DVDPlayer;

/**
 *  <----- JavaComfig1.JavaComfig2
 */

@Configuration
@Import({DVDConfig.class,DVDPlayer.class})
public class VideoSystemConfig {

}

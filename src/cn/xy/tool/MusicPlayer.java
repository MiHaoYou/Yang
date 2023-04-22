package cn.xy.tool;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class MusicPlayer {

    public void beginPlay() throws Exception {

        String str = System.getProperty("user.dir")+"\\res\\PPY.mp3";
        System.out.println(str);

        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(str));
        Player player = new Player(buffer);
        player.play();
    }
}

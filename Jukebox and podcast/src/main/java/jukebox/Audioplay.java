package jukebox;

import jukebox.dao.Audiodao;
import jukebox.dao.Audiodaoimpl;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Audioplay {
    Scanner sc = new Scanner(System.in);

    Audiodao adi = new Audiodaoimpl();

    public void playSong(String songname) throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        String getUrl = adi.retrieveSongURL(songname);

        File file = new File(getUrl);
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(ais);

        String option = "";

        while(!option.equalsIgnoreCase("Q"))
        {
            System.out.println("P  =  Play,  R = Reset, S = Stop, Q = Quit ");
            option = sc.next();

            switch (option)
            {
                case "p":
                {
                    clip.start();
                    System.out.println();
                    System.out.println("Song Playing");
                    System.out.println();
                    break;
                }
                case "r":
                {
                    clip.setMicrosecondPosition(0);
                    break;
                }
                case "s":
                {
                    clip.stop();
                }
            }
        }
    }
}

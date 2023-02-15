package jukebox;

import jukebox.dao.*;
import jukebox.models.Song;
import jukebox.userdefinedExceptions.MyException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class audio {
    Scanner sc = new Scanner(System.in);
    public void playSong() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, IOException, LineUnavailableException, MyException
    {
        Userdao ud = new Userdaoimpl();
        PlaylistDao pd = new PlaylistDaoImpl();

        System.out.println("Enter user name");
        String username = sc.next();
        System.out.println("Enter your password");
        String pwd = sc.next();
        String pass = ud.getUserPassword(username);
        int userId = ud.getUserIdFromTable(username);

        if (pass.equals(pwd)) {

            System.out.println("Enter the playlist name : ");
            String playlistname = sc.next();
            SongDao songDaoObj = new SongDaoImpl();

            //For displaying Songs
            List<Song> allSongsList = songDaoObj.allSongs();

            System.out.println("\n********ALL AVAILABLE SONGS********\n");
            System.out.format("%-20s %20s %20s %20s %20s %20s", "Song_id", "Song_name", "Song_duration", "Song_genre", "Artist_name", "Album_name" + "\n");
            System.out.println("==========================================================================================================================================\n");
            allSongsList.forEach(s -> {
                System.out.format("%-20s %20s %20s %20s %20s %20s", s.getSong_id(), s.getSong_name(), s.getSong_duration(), s.getSong_genre(), s.getArtist_name(), s.getAlbum_name() + "\n");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
            });
            int no;
            do {
                Audioplay ao =new Audioplay();

                System.out.println("Enter the song name which you want to play");
                String sid = sc.next();
                ao.playSong(sid);

                System.out.println("PRESS 1 FOR CONTINUE  ||  PRESS 2 FOR EXIT!!");
                no = sc.nextInt();
            }while(no!=2);
        }
        else
        {
            System.out.println("wrong password");
        }
    }
}

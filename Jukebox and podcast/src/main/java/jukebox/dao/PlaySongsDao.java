package jukebox.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import jukebox.models.Playlist;
import jukebox.userdefinedExceptions.MyException;
import jukebox.models.Playlist;
import jukebox.userdefinedExceptions.MyException;

public interface PlaySongsDao {
		
		//for playing in forward direction
		public void forward(List<Playlist> myPlaylist, String playlistName) throws SQLException,ClassNotFoundException, MyException, UnsupportedAudioFileException, IOException,LineUnavailableException;
		
		//for playing in backward direction
		public void backward(List<Playlist> myPlaylist,String playlistName) throws SQLException,ClassNotFoundException,MyException, UnsupportedAudioFileException, IOException,LineUnavailableException, Exception;
		
		//for playing in random order
		public void shuffle(List<Playlist> myPlaylist, String playlistName) throws SQLException,ClassNotFoundException, UnsupportedAudioFileException, IOException, LineUnavailableException, MyException  ;
		
		//for restarting song or podcast
		public void restart();
		
		//for pause 
		public long pause();
		
		//for resuming a song
		public void resume(long position);
}

package jukebox.dao;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


import jukebox.dbconfiguration.DBConfiguration;
import jukebox.models.Playlist;
import jukebox.userdefinedExceptions.MyException;

public  class PlaySongsDaoImpl implements PlaySongsDao {

	//declaring of objects
	PreparedStatement prepareObj;
	ResultSet resultObj;
	File file;
	AudioInputStream audioinputObj;
	Clip clip;

	//playing in forward direction 
	public void forward(List<Playlist> myPlaylist, String playlistName) throws SQLException, ClassNotFoundException, MyException, UnsupportedAudioFileException, IOException, LineUnavailableException {


	}

	@Override
	public void backward(List<Playlist> myPlaylist, String playlistName) throws SQLException, ClassNotFoundException, MyException, UnsupportedAudioFileException, IOException, LineUnavailableException, Exception {

	}

	@Override
	public void shuffle(List<Playlist> myPlaylist, String playlistName) throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, IOException, LineUnavailableException, MyException {

	}

	@Override
	public void restart() {

	}

	@Override
	public long pause() {
		return 0;
	}

	@Override
	public void resume(long position) {

	}
}

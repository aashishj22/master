package jukebox.dao;

import java.sql.SQLException;
import java.util.List;

import jukebox.models.Playlist;
import jukebox.models.Podcast;
import jukebox.models.Song;
import jukebox.userdefinedExceptions.MyException;
import jukebox.models.Song;

public interface PlaylistDao {


	//function for adding songs
	public void addSongs(List<Song> allSongs, int songId, String playlistName ) throws ClassNotFoundException, SQLException;


	void addPodcast(int podcastId, String playlistName);

	//function for adding pod cast
	public void addPodcast(List<Podcast> allPodcastList,int podcastId,String playlistName) throws ClassNotFoundException, SQLException;


//	//for displaying play list
//	public List<Playlist> displayPlayList(String playlistName) throws ClassNotFoundException, SQLException, MyException;

	public void addPlaylist(Playlist p) throws SQLException, ClassNotFoundException, MyException;
	public int deletePlaylist(String playlistname) throws SQLException, ClassNotFoundException, MyException;
	//public void retrievePlaylist(String playlistname) throws SQLException, ClassNotFoundException;
	public int getPlaylistId() throws SQLException, ClassNotFoundException;

}

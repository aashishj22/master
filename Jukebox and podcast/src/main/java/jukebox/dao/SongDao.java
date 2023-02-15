package jukebox.dao;
import jukebox.models.*;
import jukebox.userdefinedExceptions.MyException;
import jukebox.models.Song;

import java.util.List;
import java.util.function.Predicate;
import java.sql.SQLException;

public interface SongDao
{
	//Displaying all songs

	public List<Song> allSongs() throws SQLException,ClassNotFoundException;

	//displaying song according to particular genre,album,artist

	public List<Song> searchByGenre(List<Song> allSongsList,Predicate predicate,String genre);

	//displaying song according to particular album

	public List<Song> searchByAlbum(List<Song> allSongsList,Predicate predicate,String album);

	//displaying song according to particular artist

	public List<Song> searchByArtist(List<Song> allSongsList,Predicate predicate,String artist);
	
}

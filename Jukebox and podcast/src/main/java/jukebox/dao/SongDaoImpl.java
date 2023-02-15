package jukebox.dao;
import jukebox.dbconfiguration.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import jukebox.dbconfiguration.DBConfiguration;
import jukebox.models.Song;
import jukebox.userdefinedExceptions.MyException;
import jukebox.models.Song;

public class SongDaoImpl implements SongDao{

	//Scanner class object	
	Scanner sc=new Scanner(System.in);

	List<Song> songsList=new ArrayList<>();

	//this function will return list contains all songs	

	public List<Song> allSongs() throws SQLException,ClassNotFoundException
	{
		
	//		Executing query
		PreparedStatement preparedObj= DBConfiguration.getConnection().prepareStatement("Select * from SONGSSS");
	
	//	Creating object of ResultSet to store result given by query
		ResultSet resultSetObj=preparedObj.executeQuery();
		
		
		while(resultSetObj.next())
		{
//			storing song duration in duration array

			String duration[]=resultSetObj.getString(3).split(":");


//			adding object to songlist
			songsList.add( new Song(resultSetObj.getInt(1),resultSetObj.getString(2),LocalTime.of(Integer.parseInt(duration[0]),Integer.parseInt(duration[1]),Integer.parseInt(duration[2])),resultSetObj.getString(4),resultSetObj.getString(5),resultSetObj.getString(6)));
		}
		
		return songsList;
	}
	
	//Searching by genre
	public List<Song> searchByGenre(List<Song> allSongsList,Predicate predicate,String genre)                                //parameters in interface class
	{
	songsList=allSongsList.stream().filter(s->s.getSong_genre().substring(0,genre.length()).equalsIgnoreCase(genre)).collect(Collectors.toList());
//		searching by first character
		if(genre.length()==1) {
			songsList=allSongsList.stream().filter(s->s.getSong_genre().substring(0,1).equalsIgnoreCase(genre)).collect(Collectors.toList());
		}
//searching by full genre name		
		else {
			songsList=allSongsList.stream().filter(s->s.getSong_genre().equalsIgnoreCase(genre)).collect(Collectors.toList());
		}
		
//	Predicate to test whether list is empty or not

		if(predicate.test(songsList))
		{
			System.out.println("\n*******Songs by Genre*******\n");
			System.out.format("%-20s %20s %20s %20s %20s %20s","Song_id","Song_name","Song_duration","Song_genre","Artist_name","Album_name"+"\n");
			System.out.println("==========================================================================================================================================\n");
			songsList.forEach(s->{System.out.format("%-20s %20s %20s %20s %20s %20s",s.getSong_id(),s.getSong_name(),s.getSong_duration(),s.getSong_genre(),s.getArtist_name(),s.getAlbum_name()+"\n");
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
			});
		}
		else {
			System.out.println("No songs available");
		}
		return songsList;
	}
	//Search by album	
	@Override
	public List<Song> searchByAlbum(List<Song> allSongsList, Predicate predicate, String album)                                      //parameters in interface class
	{
		songsList=allSongsList.stream().filter(s->s.getAlbum_name().substring(0,album.length()).equalsIgnoreCase(album)).collect(Collectors.toList());

//		searching by first character		
		if(album.length()==1)
		{
			songsList=allSongsList.stream().filter(s->s.getAlbum_name().substring(0,1).equalsIgnoreCase(album)).collect(Collectors.toList());
		}
//		searching by full album name
		else {
			songsList=allSongsList.stream().filter(s->s.getAlbum_name().equalsIgnoreCase(album)).collect(Collectors.toList());
		}
		
		
		if(predicate.test(songsList)) {
			System.out.println("\n*******Songs by Album*******\n");
			System.out.format("%-20s %20s %20s %20s %20s %20s","Song_id","Song_name","Song_duration","Song_genre","Artist_name","Album_name"+"\n");
			System.out.println("==========================================================================================================================================\n");
			songsList.forEach(s->{System.out.format("%-20s %20s %20s %20s %20s %20s",s.getSong_id(),s.getSong_name(),s.getSong_duration(),s.getSong_genre(),s.getArtist_name(),s.getAlbum_name()+"\n");
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
			});
		}
		else {
			System.out.println("No songs available");
		}
		return songsList;
	}
	//Search by artist
	@Override
	public List<Song> searchByArtist(List<Song> allSongsList, Predicate predicate, String artist)                                         //parameters in interface class
	{
		
		songsList=allSongsList.stream().filter(s->s.getArtist_name().substring(0,artist.length()).equalsIgnoreCase(artist)).collect(Collectors.toList());
//		searching by first character

		if(artist.length()==1)
		{
			songsList=allSongsList.stream().filter(s->s.getArtist_name().startsWith(""+artist.charAt(0))).collect(Collectors.toList());
		}
//		searching by full Artist name
		else {
			songsList=allSongsList.stream().filter(s->s.getArtist_name().equalsIgnoreCase(artist)).collect(Collectors.toList());
		}
		
		
		if(predicate.test(songsList)) {
			System.out.println("\n*******Songs by Artist*******\n");
			System.out.format("%-20s %20s %20s %20s %20s %20s","Song_id","Song_name","Song_duration","Song_genre","Artist_name","Album_name"+"\n");
			System.out.println("==========================================================================================================================================\n");
			songsList.forEach(s->{System.out.format("%-20s %20s %20s %20s %20s %20s",s.getSong_id(),s.getSong_name(),s.getSong_duration(),s.getSong_genre(),s.getArtist_name(),s.getAlbum_name()+"\n");
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
			});
		}
		else {
			System.out.println("No songs available");
		}
		return songsList;
	}
	
	
}

package jukebox.models;

import java.time.LocalTime;

public class Song {
//	Declaring variables for Song
	private int song_id;
	private String song_name;
	private LocalTime song_duration;
	private String song_genre;
	private String artist_name;
	private String album_name;
	
 //Constructors
	
	public Song()
	{
		super();

	}
	
	public Song(int song_id, String song_name, LocalTime song_duration, String song_genre, String artist_name,
			String album_name) {
		super();
		this.song_id = song_id;
		this.song_name = song_name;
		this.song_duration = song_duration;
		this.song_genre = song_genre;
		this.artist_name = artist_name;
		this.album_name = album_name;
	}
	
 //Getters and setters

	public int getSong_id()
	{
		return song_id;
	}

	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}

	public String getSong_name() {
		return song_name;
	}

	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}

	public LocalTime getSong_duration() {
		return song_duration;
	}

	public void setSong_duration(LocalTime song_duration) {
		this.song_duration = song_duration;
	}

	public String getSong_genre() {
		return song_genre;
	}

	public void setSong_genre(String song_genre) {
		this.song_genre = song_genre;
	}

	public String getArtist_name() {
		return artist_name;
	}

	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}

	public String getAlbum_name() {
		return album_name;
	}

	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}

	@Override
	public String toString() {
		return "song_id=" + song_id + ", song_name=" + song_name + ", song_duration=" + song_duration
				+ ", song_genre=" + song_genre + ", artist_name=" + artist_name + ", album_name=" + album_name ;
	}

	
}

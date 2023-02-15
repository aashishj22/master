package jukebox.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.function.Predicate;

import jukebox.models.Podcast;
import jukebox.models.Podcast;

public interface PodcastDao {
	//all pod cast available
	public List<Podcast> allPodcast() throws SQLException,ClassNotFoundException,ParseException;
	//to search pod cast by topics
	public List<Podcast> searchByTopics(List<Podcast> allPodcastList,Predicate predicate,String topic);
	//to search by pod cast artist
	public List<Podcast> searchByArtist(List<Podcast> allPodcastList,Predicate predicate,String artist);
}

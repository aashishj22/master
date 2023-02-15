package jukebox.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


import jukebox.models.Podcast;
import jukebox.dbconfiguration.DBConfiguration;
import jukebox.models.Podcast;

public class PodcastDaoImpl implements PodcastDao {
	
	// declaring of list of pod cast(generic) type
	List<Podcast> podcastList=new ArrayList<>();
	
	//this function will return list contains all podcast	
	@Override
	public List<Podcast> allPodcast() throws SQLException, ClassNotFoundException, ParseException
	{
		//		Executing query
		PreparedStatement preparedObj= DBConfiguration.getConnection().prepareStatement("Select * from Podcastsview");
		
		//		Creating object of ResultSet to store result given by query	
		ResultSet resultObj=preparedObj.executeQuery();
		
		while(resultObj.next()) {
	
			//	adding object to podcastList
			podcastList.add( new Podcast(resultObj.getInt(1),resultObj.getString(2),new SimpleDateFormat("yyyy-mm-dd").parse(resultObj.getString(3)) ,resultObj.getString(4),resultObj.getString(5)) );
		
		}
		return podcastList;
	}
	//Searching by topic
	@Override
	public List<Podcast> searchByTopics(List<Podcast> allPodcastList,Predicate predicate,String topic) {
		podcastList=allPodcastList.stream().filter(s->s.getPodcast_topics().substring(0,topic.length()).equalsIgnoreCase(topic)).collect(Collectors.toList());

//		searching by first character		
		if(topic.length()==1)
		{
			podcastList=allPodcastList.stream().filter(s->s.getPodcast_topics().substring(0,1).equalsIgnoreCase(topic)).collect(Collectors.toList());
		}
//		searching by full name	
		else
		{
			podcastList=allPodcastList.stream().filter(s->s.getPodcast_topics().equalsIgnoreCase(topic)).collect(Collectors.toList());
		}
//		Predicate to test whether list is empty or not

		if(predicate.test(podcastList)) {
			System.out.println("\n*******PODCAST BY TOPIC*******\n");
			System.out.format("%-20s %20s %40s %20s %30s","podcast_id","podcast_name","podcast_date","podcast_topics","podcast_artist"+"\n\n");
			System.out.println("==========================================================================================================================================\n");
			podcastList.forEach(s->{System.out.format("%-20s %20s %40s %20s %30s",s.getPodcast_id(),s.getPodcast_name(),s.getPodcast_date(),s.getPodcast_topics(),s.getPodcast_artist()+"\n");
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
			});
		}
		
		else {
			System.out.println("No podcast available for particular genre");
		}
		
		return podcastList;
	}
	
	//Searching by artist
	@Override
	public List<Podcast> searchByArtist(List<Podcast> allPodcastList, Predicate predicate, String artist) {
		
		podcastList=allPodcastList.stream().filter(s->s.getPodcast_artist().substring(0,artist.length()).equalsIgnoreCase(artist)).collect(Collectors.toList());
//		searching by first character		
		if(artist.length()==1) {
			podcastList=allPodcastList.stream().filter(s->s.getPodcast_artist().substring(0,1).equalsIgnoreCase(artist)).collect(Collectors.toList());
		}
//		searching by full name
		else
		{
			podcastList=allPodcastList.stream().filter(s->s.getPodcast_artist().equalsIgnoreCase(artist)).collect(Collectors.toList());
		}
//		Predicate to test whether list is empty or not	
		if(predicate.test(podcastList)) {
			System.out.println("\n*******PODCAST BY ARTIST*******\n");
			System.out.format("%-20s %20s %40s %20s %30s","podcast_id","podcast_name","podcast_date","podcast_topics","podcast_artist"+"\n\n");
			System.out.println("==========================================================================================================================================\n");
			podcastList.forEach(s->{System.out.format("%-20s %20s %40s %20s %30s",s.getPodcast_id(),s.getPodcast_name(),s.getPodcast_date(),s.getPodcast_topics(),s.getPodcast_artist()+"\n");
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
			});
		}
		else {
			System.out.println("No podcast available for particular artist");
		}
		
		return podcastList;
	}

}

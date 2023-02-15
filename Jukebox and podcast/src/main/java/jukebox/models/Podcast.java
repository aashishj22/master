package jukebox.models;
import java.util.Date;

public class Podcast {
//	Declaring variables for Podcast	
	private int podcast_id;
	private String podcast_name;
	private Date podcast_date;
	private String podcast_topics;
	private String podcast_artist;
	
	//Constructors	
	public Podcast() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Podcast(int podcast_id, String podcast_name, Date podcast_date, String podcast_topics,
			String podcast_artist) {
		super();
		this.podcast_id = podcast_id;
		this.podcast_name = podcast_name;
		this.podcast_date = podcast_date;
		this.podcast_topics = podcast_topics;
		this.podcast_artist = podcast_artist;
	}
	//Getters and Setters	
	public int getPodcast_id() {
		return podcast_id;
	}

	public void setPodcast_id(int podcast_id) {
		this.podcast_id = podcast_id;
	}

	public String getPodcast_name() {
		return podcast_name;
	}

	public void setPodcast_name(String podcast_name) {
		this.podcast_name = podcast_name;
	}

	public Date getPodcast_date() {
		return podcast_date;
	}

	public void setPodcast_date(Date podcast_date) {
		this.podcast_date = podcast_date;
	}

	public String getPodcast_topics() {
		return podcast_topics;
	}

	public void setPodcast_topics(String podcast_topics) {
		this.podcast_topics = podcast_topics;
	}

	public String getPodcast_artist() {
		return podcast_artist;
	}

	public void setPodcast_artist(String podcast_artist) {
		this.podcast_artist = podcast_artist;
	}
//Overriding of toString function
	@Override
	public String toString() {
		return "podcast_id=" + podcast_id + ", podcast_name=" + podcast_name + ", podcast_date=" + podcast_date
				+ ", podcast_topics=" + podcast_topics + ", podcast_artist=" + podcast_artist;
	}
	
	
}

package jukebox.models;

public class Playlist {

	//	Declaring variables playlist

	int playlistId;
	String playlistName;

	int userid;

//Constructors	


	public Playlist(int playlistId, String playlistName, int userid) {
		this.playlistId = playlistId;
		this.playlistName = playlistName;
		this.userid = userid;
	}

	public int getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "Playlist{" +
				"playlistId=" + playlistId +
				", playlistName='" + playlistName + '\'' +
				", userid=" + userid +
				'}';
	}


}

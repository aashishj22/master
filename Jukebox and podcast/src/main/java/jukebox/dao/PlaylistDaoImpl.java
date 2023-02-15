package jukebox.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jukebox.dbconfiguration.DBConfiguration;
import jukebox.models.Playlist;
import jukebox.models.Podcast;
import jukebox.models.Song;
import jukebox.userdefinedExceptions.MyException;

public class PlaylistDaoImpl implements PlaylistDao{

	//list used for storing content of playlist 
	List<Playlist> myContentList=new ArrayList<>();
	private List<Song> allSongs;
	private int songId;
	private String playlistName;

	//Function for adding songs

	public void addSongs( int songId,String playlistName) throws ClassNotFoundException, SQLException
	{
		this.allSongs = allSongs;
		this.songId = songId;
		this.playlistName = playlistName;

		//Performing actions on database
		PreparedStatement prepareObj=DBConfiguration.getConnection().prepareStatement("Insert into PLAYLISTS(playlist_name,song_id) values(?,?)");

		//Using PreparedStatement functions for setting parameters in sql statement 
		prepareObj.setString(1,playlistName);
		prepareObj.setInt(2,songId);
		//Executing my query with executeUpdate
		prepareObj.executeUpdate();

	}


	public void addPodcast(int podcastId, String playlistName) {

	}


	//Function for adding pod cast
	@Override
	public void addPodcast(List<Podcast> allPodcastList, int podcastId, String playlistName) throws SQLException, ClassNotFoundException
	{
		//Performing actions on database
		PreparedStatement prepareObj=DBConfiguration.getConnection().prepareStatement("Insert into playlist1(playid,playlistName,userid) values(?,?,?)");

		//Using PreparedStatement functions for setting parameters in sql statement 
		prepareObj.setString(1,playlistName);
		prepareObj.setInt(2,podcastId);
		//Executing my query with executeUpdate
		prepareObj.executeUpdate();

	}




	public void addSongs(List<Song> allSongs, int songId, String playlistName) throws SQLException, ClassNotFoundException {



	}

	//Function for displaying user entered play list

//	public List<Playlist> displayPlayList(String playlistName) throws ClassNotFoundException, SQLException, MyException {
//
//		//Performing actions on database
//		PreparedStatement prepareObj=DBConfiguration.getConnection().prepareStatement("Select * from PLAYLISTS");
//
//		//storing of retrieved info from database into ResultSet object
//		ResultSet resultSetObj=prepareObj.executeQuery();
//
//		//for execution till my rows exist in database
//		int flag=0;
//		while(resultSetObj.next()) {
//		//checking whether play list exist in which we have to add songs
//			if(resultSetObj.getString(1).equalsIgnoreCase(playlistName)) {
//				flag=1;
//				//adding my content ie songs or pod cast to list
//				myContentList.add(new Playlist(resultSetObj.getString(1),resultSetObj.getInt(2),resultSetObj.getInt(3),resultSetObj.getInt(4)));
//
//
//				//checking condition when we adding songs
//				if(resultSetObj.getInt(2)== 0) {
//
//					PreparedStatement prepareSongObj=DBConfiguration.getConnection().prepareStatement("Select * from SONGSSS where song_id=?");
//
//					prepareSongObj.setInt(1,resultSetObj.getInt(3));
//
//					ResultSet resultSetSongObj=prepareSongObj.executeQuery();
//
//
//					//printing song which i have added
//					while(resultSetSongObj.next()) {
//						System.out.format("%-20s %20s %20s %20s %20s %20s",resultSetSongObj.getInt(1),resultSetSongObj.getString(2),resultSetSongObj.getString(3),resultSetSongObj.getString(4),resultSetSongObj.getString(5),resultSetSongObj.getString(6)+"\n");
//					}
//
//				}
//
//
//				//for adding pod cast
//				else {
//					PreparedStatement preparePodObj=DBConfiguration.getConnection().prepareStatement("Select * from Podcasts where podcast_id=?");
//					preparePodObj.setInt(1,resultSetObj.getInt(2));
//					ResultSet resultSetPodObj=preparePodObj.executeQuery();
//					//displaying pod cast added to playlist
//					while(resultSetPodObj.next()) {
//						System.out.format("%-20s %20s %20s %20s %20s",resultSetPodObj.getInt(1),resultSetPodObj.getString(2),resultSetPodObj.getString(3),resultSetPodObj.getString(4),resultSetPodObj.getString(5)+"\n");
//					}
//				}
//
//			}
//
//
//		}

//		//handling exception if no play list exist
//		if(flag==0)
//		{
//			throw new MyException("No playlist for name "+playlistName);
//		}
//		return myContentList;
//	}


	public void addPlaylist(Playlist p) throws SQLException, ClassNotFoundException, MyException {
		PreparedStatement ps = null;
		Connection con = new DBConfiguration().getConnection();
		String query = "insert into playlist1(playid,playlistName,userid) values(?,?,?)";
		try{
			ps = con.prepareStatement(query);
			ps.setInt(1,p.getPlaylistId());
			ps.setString(2,p.getPlaylistName());
			ps.setInt(3,p.getUserid());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		ps.close();
		con.close();
	}

	@Override
	public int deletePlaylist(String playlistname) throws SQLException, ClassNotFoundException, MyException {
		int count = 0;
		PreparedStatement ps = null;
		Connection con = new DBConfiguration().getConnection();
		String query = "delete from playlist1 where playid = ?";
		try{
			ps = con.prepareStatement(query);
			ps.setString(1,playlistname);
			count = ps.executeUpdate();

		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		ps.close();
		con.close();

		return count;
	}


	/*public void retrievePlaylist(String playlistname) throws SQLException, ClassNotFoundException {

		PreparedStatement ps = null;
		Connection con = new DBConfiguration().getConnection();
		String query = "select playid,playlistName,userid from playlist1 where userid = ? ";
		try{
			ps = con.prepareStatement(query);
			ps.setString(1,playlistname);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int playlistId = rs.getInt(1);
				String playlistName = rs.getString(2);
				int userd = rs.getInt(3);
				Playlist p = new Playlist(playlistId,playlistName,userd);
				System.out.println(p);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		ps.close();
		con.close();
	}*/

	@Override
	public int getPlaylistId() throws SQLException, ClassNotFoundException {
		int playlistId = 0;
		String query = "select max(playid)+1 from playlist1";
		Connection con = new DBConfiguration().getConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(query);
		while(rs.next())
		{
			playlistId = rs.getInt(1);
			if(playlistId<=0)
			{
				playlistId = 1;
			}
		}
		s.close();
		con.close();

		return playlistId;
	}

}

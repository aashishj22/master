import jukebox.dao.PlaylistDao;
import jukebox.dao.PlaylistDaoImpl;
import jukebox.dao.SongDao;
import jukebox.dao.SongDaoImpl;
import jukebox.dbconfiguration.DBConfiguration;
import jukebox.models.Song;
import jukebox.userdefinedExceptions.MyException;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

public class JukeboxTest {
	
	//declaring of object
	PlaylistDao playlistDaoObj;
	SongDao SongDaoObj;
	PreparedStatement preparedObj;
	//creating of objects before running test case
	@BeforeEach
	void setUp(){
		playlistDaoObj=new PlaylistDaoImpl();
		SongDaoObj=new SongDaoImpl();
		
	}
	//flushing of objects after running test case
	 @AfterEach
	    void tearDown() {
		 playlistDaoObj=null;
			SongDaoObj=null;
	    }
	 //for checking database
	 @Test
	 public void checkConnectivity() throws ClassNotFoundException, SQLException {
		 preparedObj=DBConfiguration.getConnection().prepareStatement("Select * from Songss where song_name='yeh dooriyan'");
		 ResultSet resultObj=preparedObj.executeQuery();
		 int song_id=0;
		 while(resultObj.next()) {
			 song_id=resultObj.getInt(1);
		 }
		 assertEquals(1,song_id);
	 }
	 //for checking play list
//	@Test
//	public void  displayPlayListTest() throws ClassNotFoundException, SQLException, MyException {
//		assertEquals(1,playlistDaoObj.displayPlayList("ypo").get(0).getSong_id());
//
//	}
//	//for checking songs
//	@Test
//	public void songsTest() throws ClassNotFoundException, SQLException
//	{
//
//			List<Song> songsList = this.SongDaoObj.allSongs();
//			Predicate predicate = (s) -> {return ((List)s).size() > 0;
//			};
//
//		songsList = SongDaoObj.allSongs();
//		predicate = s -> ((List) s).size() > 0;
//		assertEquals(searchByGenre(songsList), this.SongDaoObj.searchByGenre(songsList, predicate, "pop").size());
////		assertEquals(, this.SongDaoObj.searchByAlbum(songsList, predicate, "justin bieber").size());
////		assertEquals(, this.SongDaoObj.searchByAlbum(songsList, predicate, "love aaj kal").size());
////		assertEquals(, this.SongDaoObj.searchByArtist(songsList, predicate, "justin bieber").size());
//
//	}
//
//	private int searchByGenre(List<Song> songsList) {
//		return 2;
//	}


}


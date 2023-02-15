
import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import jukebox.audio;
import jukebox.dao.*;
import jukebox.models.Playlist;
import jukebox.models.Podcast;
import jukebox.models.Song;
import jukebox.models.User;
import jukebox.userdefinedExceptions.MyException;


import java.util.function.Predicate;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class MainJukeBox {

	public static void main(String[] args) throws Exception {
		//Scanner object

		Scanner sc = new Scanner(System.in);
		//to return to main menu		
		String menu = "";
		//Created list to store all content in playlist		
		List myPlaylist = new ArrayList<>();
		//playlist name which must be entered by user		
		String playlistName = "";


		int playlistChoice = 0;
		do {
			//Choice for songs,playlist or podcast

			System.out.println("\nEnter your choice");
			System.out.println("Press 1 For user");
			System.out.println("Press 2 create playlist");
			System.out.println("Press 3 DISPLAY Songs");
			System.out.println("Press 4 DISPLAY Podcast");
			System.out.println("Press 5 ADD SONG OR PODCAST INTO PLAYLIST");
			System.out.print("Enter choice:");

			int choice = sc.nextInt();

			//condition for checking list is empty or not		
			Predicate predicate = s -> ((List) s).size() > 0;

			//Switch case for particular choice ie songs or podcast
			switch (choice) {

				case 1: {
					Userdao ud = new Userdaoimpl();


					System.out.println("1.   Create New USer ");
					System.out.println("2.   Delete user Details ");
					int n = sc.nextInt();
					switch (n) {
						case 1: {
							System.out.println("Enter user Name");
							String un = sc.next();
							System.out.println("Create your password");
							String pwd = sc.next();
							int userId = ud.getUserId();
							User u = new User(userId, un, pwd);
							try {
								ud.addUser(u);
								System.out.println("User Added successful!!!!");
							} catch (Exception e) {
								System.out.println(e);
							}
							break;
						}


						case 2: {
							String pwd = null;
							System.out.println("Enter Your user name");
							String un = sc.next();
							System.out.println("Enter your password");
							String pass = sc.next();
							pwd = ud.getUserPassword(un);
							if (pwd == null) {
								System.out.println("Enter a valid user ID \n");
							} else if (pwd.equals(pass)) {
								try {
									ud.deleteUser(un, pass);
									System.out.println("Record Deleted Successful !!!!!");
								} catch (Exception e) {
									System.out.println(e);
								}
							} else {
								System.out.println("Enter valid password");
								System.out.println();
							}
							break;
						}

					}
					break;

				}

				case 2: {
					Userdao user = new Userdaoimpl();
					System.out.println("Enter user name");
					String username = sc.next();
					System.out.println("Enter your password");
					String pwd = sc.next();
					String pass = user.getUserPassword(username);
					int userId = user.getUserIdFromTable(username);

					if (pass.equals(pwd)) {


						PlaylistDao pd = new PlaylistDaoImpl();
						System.out.println("1.      Create Playlist");
						System.out.println("2.      Delete Playlist");
						System.out.println("3.      Check Playlist");
						int n = sc.nextInt();

						switch (n) {
							case 1: {

								System.out.println("Enter your new playlist Name");
								String name = sc.next();
								int playlistId = pd.getPlaylistId();
								Playlist p = new Playlist(playlistId, name, userId);
								pd.addPlaylist(p);

								break;
							}
							case 2: {
								/*pd.retrievePlaylist(username);*/
								//System.out.println();
								System.out.println("Which playlist you want to delete (Enter playlist name) :");
								String playlistname = sc.next();
								try {
									int i = pd.deletePlaylist(playlistname);
									if (i == 1) {
										System.out.println("Playlist Deleted !!");
									} else {
										System.out.println("Playlist not deleted, please try again !!");
									}
								} catch (Exception e) {
									System.out.println(e);
								}
								break;
							}

							/*case 3: {
								System.out.println("Which playlist you want to check :");
								String playlistname = sc.next();
								pd.retrievePlaylist(playlistname);
								break;
							}*/

						}
						break;
					}
				}


				//SONGS

				case 3:
					//Calling function allSongs to retrieve songs list

					SongDao songDaoObj = new SongDaoImpl();

					//For displaying Songs
					List<Song> allSongsList = songDaoObj.allSongs();

					System.out.println("\n********ALL AVAILABLE SONGS********\n");
					System.out.format("%-20s %20s %20s %20s %20s %20s", "Song_id", "Song_name", "Song_duration", "Song_genre", "Artist_name", "Album_name" + "\n");
					System.out.println("==========================================================================================================================================\n");
					allSongsList.forEach(s -> {
						System.out.format("%-20s %20s %20s %20s %20s %20s", s.getSong_id(), s.getSong_name(), s.getSong_duration(), s.getSong_genre(), s.getArtist_name(), s.getAlbum_name() + "\n");
						System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
					});

					//Calling function for Searching by Genre,Album,Artist

					//Taking user input for performing task on songs

					System.out.println("\nEnter your choice for searching");
					System.out.println("Press 1 to search by Genre ");
					System.out.println("Press 2 to search by Album ");
					System.out.println("Press 3 to search by Artist ");
					System.out.print("Enter choice:");

					//Taking user input for searching

					int songSearchChoice = sc.nextInt();

					switch (songSearchChoice) {
						// Genre
						case 1:
							System.out.println("\nEnter ur song genre");
							//	Taking user input for genre
							String genre = sc.next();
							//calling function to print all songs by genre
							songDaoObj.searchByGenre(allSongsList, predicate, genre);
							break;
						//album
						case 2:
							System.out.println("Enter ur song album");
							//	taking	album from user
							sc.nextLine();
							String album = sc.nextLine();

							//printing songs by album
							songDaoObj.searchByAlbum(allSongsList, predicate, album);
							break;
						//Artist
						case 3:
							System.out.println("Enter ur song artist");
							//taking artist by user
							sc.nextLine();
							String artist = sc.nextLine();
							;
							//print songs by artist name
							songDaoObj.searchByArtist(allSongsList, predicate, artist);
							break;
						//for exception handling
						default:
							throw new MyException("Enter valid choice for song searching");
					}

					break;


				//PODCAST


				case 4:
					//Calling function all podcast to retrieve podcast list

					PodcastDao podcastDaoObj = new PodcastDaoImpl();
					List<Podcast> allPodcastList = podcastDaoObj.allPodcast();

					//For displaying Podcast

					System.out.println("\n*******ALL AVAILABLE PODCAST*******\n");
					System.out.format("%-20s %20s %40s %20s %30s", "Podcast_id", "Podcast_name", "Podcast_date", "Podcast_topics", "Podcast_artist" + "\n");
					System.out.println("==========================================================================================================================================\n");
					allPodcastList.forEach(s -> {
						System.out.format("%-20s %20s %40s %20s %30s", s.getPodcast_id(), s.getPodcast_name(), s.getPodcast_date(), s.getPodcast_topics(), s.getPodcast_artist() + "\n");
						System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
					});

					//	Running  statements before taking user input for topic name,artist name
					System.out.println("\nEnter ur choice ");
					System.out.println("Press 1 to search podcast  by topic name");
					System.out.println("Press 2 to search podcast  by artist name");
					System.out.print("Enter choice:");
					//taking input for searching podcast according to topic or artist
					int podcastChoice = sc.nextInt();

					switch (podcastChoice) {
						//Topic
						case 1:

							System.out.println("\nEnter ur podcast topic");

							//input for topic
							sc.nextLine();
							String topic = sc.nextLine();

							System.out.println("Podcast of topic " + topic);
							//printing podcast by topic name
							podcastDaoObj.searchByTopics(allPodcastList, predicate, topic);
							break;
						//Artist
						case 2:
							System.out.println("Enter ur podcast artist");

							//input for artist
							sc.nextLine();
							String artist = sc.nextLine();

							System.out.println("Podcast of artist " + artist);

							//printing podcast by artist name
							podcastDaoObj.searchByArtist(allPodcastList, predicate, artist);
							break;
						//for handling exception
						default:
							throw new MyException("Enter valid choice for podcast searching");
					}
					break;


				//playlist


				case 5:

					System.out.println("\nEnter ur choice ");
					System.out.println("Press 1 to add songs to playlist");
					System.out.println("Press 2 to add podcast to playlist");
					System.out.println("Press 3 to display or listen songs of playlist");

					System.out.print("Enter choice:");
					//Entering your choice for playlist operation
					playlistChoice = sc.nextInt();


					//refering of object of PlaylistDao
					PlaylistDao playlistDaoObj = new PlaylistDaoImpl();

					switch (playlistChoice) {
						//for adding songs or album

						case 1:

							System.out.println("Enter song or album name to add");
							//taking input for song or album
							sc.nextLine();
							String songInfo = sc.nextLine();
							//retrieving all song list
							allSongsList = new SongDaoImpl().allSongs();

							//to check whether song or album exist
							int flag = 0;
							for (Song s : allSongsList) {
								if (songInfo.equalsIgnoreCase(s.getAlbum_name()) || songInfo.equalsIgnoreCase(s.getSong_name())) {
									flag = 1;
									playlistDaoObj.addSongs(allSongsList, s.getSong_id(), playlistName);
								}

							}
							System.out.println("Songs added to Playlist  ");
							//handling exception
							if (flag != 1) {
								throw new MyException("Enter a valid song or album name to add in playlist");
							}

							break;
						//adding podcast
						case 2:

							System.out.println("\nEnter podcast name to add");

							//taking podcast name
							sc.nextLine();
							String podcastName = sc.nextLine();

							//retrieving all podcast
							allPodcastList = new PodcastDaoImpl().allPodcast();

							//check whether podcast exist or not
							int counter = 0;
							for (Podcast s : allPodcastList) {
								if (podcastName.equalsIgnoreCase(s.getPodcast_name())) {
									counter = 1;
									playlistDaoObj.addPodcast(allPodcastList, s.getPodcast_id(), playlistName);
								}
							}
							//handling exception when podcast do not exist
							if (counter != 1) {

								throw new MyException("Enter a valid podcast name to add to playlist");
							}

							break;
						//displaying playlist


						case 3:
						{
							audio  n = new audio();
							n.playSong();

						}
					}
					myPlaylist.forEach(s -> System.out.println(s));
//			if user enter 3 for displaying
					if (playlistChoice == 3) {

						PlaySongsDao playSongsDaoObj;
						playSongsDaoObj = new PlaySongsDaoImpl() {
							@Override
							public void forward(List<Playlist> myPlaylist, String playlistName) throws SQLException, ClassNotFoundException, MyException, UnsupportedAudioFileException, IOException, LineUnavailableException {

							}

							@Override
							public void backward(List<Playlist> myPlaylist, String playlistName) throws SQLException, ClassNotFoundException, MyException, UnsupportedAudioFileException, IOException, LineUnavailableException {

							}

							@Override
							public void shuffle(List<Playlist> myPlaylist, String playlistName) throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, IOException, LineUnavailableException, MyException {

							}
						};

						while (true) {

							System.out.println("Enter operation u want to perform");
							System.out.println("Press 1 for forward");
							System.out.println("Press 2 for backward");
							System.out.println("Press 3 for shuffle");
							//entering input for forward ,backward and shuffle
							int operationChoice = sc.nextInt();


							switch (operationChoice) {
								//for playing songs of playlist in forward direction
								case 1:
									playSongsDaoObj.forward(myPlaylist, playlistName);
									break;
								//for playing songs in backward direction
								case 2:
									playSongsDaoObj.backward(myPlaylist, playlistName);
									break;
								//for playing songs in backward direction
								case 3:
									playSongsDaoObj.shuffle(myPlaylist, playlistName);
									break;
								//exception handling
								default:
									throw new MyException("Enter valid operation on playlist");

							}
						}

					}

					System.out.println("\nEnter Y for main menu");
					System.out.println("Enter N for exit");
					//taking input for menu
					menu = sc.next();
					//Exception handling for exceptions
					if (menu.equalsIgnoreCase("N")) {
						break;
					} else if (!(menu.equalsIgnoreCase("N") || menu.equalsIgnoreCase("Y"))) {
						throw new MyException("Enter valid choice for menu");
					}
			}



		}
		while (menu.equalsIgnoreCase("Y")) ;
	}
}

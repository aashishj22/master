package jukebox.dao;

import jukebox.dbconfiguration.DBConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Audiodaoimpl implements Audiodao{

    @Override
    public String retrieveSongURL(String songname) throws SQLException, ClassNotFoundException {

            String songurl = null;
            PreparedStatement ps = null;
            Connection con = new DBConfiguration().getConnection();
            String query = "select url from SONGSSS  where song_name = ?";
            try{
                ps = con.prepareStatement(query);
                ps.setString(1,songname);
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    songurl = rs.getString(1);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            ps.close();
            con.close();

            return songurl;
        }
    }


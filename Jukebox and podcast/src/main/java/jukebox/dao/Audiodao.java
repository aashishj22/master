package jukebox.dao;

import java.sql.SQLException;

public interface Audiodao {
    public String retrieveSongURL(String songname) throws SQLException, ClassNotFoundException;
}

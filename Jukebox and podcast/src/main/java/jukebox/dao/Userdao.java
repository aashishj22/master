package jukebox.dao;

import jukebox.models.User;
import jukebox.userdefinedExceptions.MyException;

import java.sql.SQLException;

public interface Userdao {
    public void addUser(User u) throws SQLException, ClassNotFoundException, MyException;
    public void deleteUser(String username,String password) throws SQLException, ClassNotFoundException, MyException;
    public int getUserId() throws SQLException, ClassNotFoundException;
    public String getUserPassword(String username) throws SQLException, ClassNotFoundException, MyException;
    public int getUserIdFromTable(String username)throws SQLException, ClassNotFoundException, MyException;
}

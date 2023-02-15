package jukebox.dao;

import jukebox.dbconfiguration.DBConfiguration;
import jukebox.models.User;
import jukebox.userdefinedExceptions.MyException;

import java.sql.*;

public class Userdaoimpl implements Userdao {
    public void addUser(User u) throws MyException, SQLException, ClassNotFoundException {
        String query = null;
        PreparedStatement ps = null;
        Connection con = new DBConfiguration().getConnection();
        query = "insert into user(userId,userName,password) values(?,?,?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, u.getUserId());
            ps.setString(2, u.getUserName());
            ps.setString(3, u.getPassword());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }





    public void deleteUser(String username,String password) throws SQLException, ClassNotFoundException, MyException {
        String query = "delete from user where username = ? and password = ?";
        PreparedStatement ps = null;
        Connection con = new DBConfiguration().getConnection();
        try{
            ps = con.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.executeUpdate();

        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    public int getUserId() throws SQLException, ClassNotFoundException {
        int userId = 0;
        String query = null;
        Statement s = null;
        Connection con = new DBConfiguration().getConnection();
        s = con.createStatement();
        query = "select max(userId)+1 from user";
        ResultSet rs = s.executeQuery(query);
        while(rs.next())
        {
            userId = rs.getInt(1);
            if(userId<=0)
            {
                userId = 1;
            }
        }
        s.close();
        con.close();

        return userId;
    }

    @Override
    public String getUserPassword(String username) throws SQLException, ClassNotFoundException, MyException {
        String passwd = null;

        String query = "select password from user where username = ?";
        PreparedStatement ps = null;
        Connection con = new DBConfiguration().getConnection();
        try {
            ps = con.prepareStatement(query);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                passwd = rs.getString(1);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        ps.close();
        con.close();

        return passwd;
    }

    public int getUserIdFromTable(String username)throws SQLException, ClassNotFoundException, MyException
    {
        int userid =0;

        String query = "select userid from user where username = ?";
        PreparedStatement ps = null;
        Connection con = new DBConfiguration().getConnection();
        try {
            ps = con.prepareStatement(query);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                userid = rs.getInt(1);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        ps.close();
        con.close();

        return userid;

    }
}

package DAO;

import Util.ConnectionUtil;
import Model.Account;
import Model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    
    public Account insertAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        //this should work only if username is not blank, password is > 4 chars, and a thing with that username does not already exist
        String username = account.getUsername();
        String password = account.getPassword();
        if(username.length() > 0 && password.length() > 4) {
            if(usernameExists(username)) {      //if the username already exists, return null
                return null;
            }
        } else {
            return null;
        }
        try {
            String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            ps.executeUpdate();
            return getAccountByUsername(username);  //if we just returned by account it would be the wrong id, so I use a getter that I define in here
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account loginAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        Account returnedAccount = null;
        try {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                returnedAccount = new Account(rs.getInt("account_id"), 
                    rs.getString("username"), 
                    rs.getString("password"));
            }
            return returnedAccount;
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean usernameExists(String username) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        } 
        return false;
    }

    public Account getAccountByUsername(String username) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            Account account = new Account(rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"));
                return account;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        } 
        return null;
    }
}

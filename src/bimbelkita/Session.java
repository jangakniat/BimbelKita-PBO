/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bimbelkita;

/**
 *
 * @author asus
 */
public class Session {

    private String username;
    private boolean login;//true atau false tok

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public boolean getLogin() {
        return login;
    }

}

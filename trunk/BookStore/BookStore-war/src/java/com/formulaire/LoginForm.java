/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.formulaire;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Wikola
 */
public class LoginForm {
    
    @NotEmpty
    private String login = "lo";
    @NotEmpty
    private String pass = "lo";

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
}

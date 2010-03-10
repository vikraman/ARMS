/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arms;

import java.util.Date;

/**
 *
 * @author vh4x0r
 */
public class User {

    public User(String name, String id, String pass) {
        this.name = name;
        this.id = id;
        this.pass = pass;
        lastLogin = new Date();
    }

    public boolean validate(String id, String pass) {
        if (id.equals(this.id) && pass.equals(this.pass)) {
            return true;
        }
        return false;
    }
    private String name;
    private String id;
    private String pass;
    private Date lastLogin;
}

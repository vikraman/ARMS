/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arms;

import java.util.ArrayList;

/**
 *
 * @author vh4x0r
 */
public class Login {

    public Login() {
        teacherList = new ArrayList<Teacher>();
        userList = new ArrayList<User>();
    }

    public static boolean valid(String id, String pass, Group group) {
        new Login();
        switch (group) {
            case User:
                for (User u : userList) {
                    if (u.validate(id, pass)) {
                        return true;
                    }
                }
                break;
            case Teacher:
                for (Teacher t : teacherList) {
                    if (t.validate(id, pass)) {
                        return true;
                    }
                }
                break;
            case Administrator:
                if (id.equals("admin") && pass.equals("admin")) {
                    return true;
                }
                break;
        }
        return false;
    }
    private static ArrayList<Teacher> teacherList;
    private static ArrayList<User> userList;
}

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
public class ARMSManager {

    public ARMSManager() {
        teacherList = new ArrayList<Teacher>();
        userList = new ArrayList<User>();
        teacherList.add(new Teacher("John Smith", "john", "pass", 1));
    }

    public static Object login(String id, String pass, Group group)throws InvalidLoginException {
        new ARMSManager();
        switch (group) {
            case User:
                for (User u : userList) {
                    if (u.validate(id, pass)) {
                        return u;
                    }
                }
                break;
            case Teacher:
                for (Teacher t : teacherList) {
                    if (t.validate(id, pass)) {
                        return t;
                    }
                }
                break;
            case Administrator:
                if (id.equals("admin") && pass.equals("admin")) {
                    return null;
                }
                break;
        }
        throw new InvalidLoginException("Invalid user ID and password combination!");
    }

    public static void update() {
    }
    private static ArrayList<Teacher> teacherList;
    private static ArrayList<User> userList;
}

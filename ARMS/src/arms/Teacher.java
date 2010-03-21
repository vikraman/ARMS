/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arms;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author vh4x0r
 */
public class Teacher implements Serializable {

    public Teacher(String name, String id, String pass) {
        this.name = name;
        this.id = id;
        this.pass = pass;
        subjectList = new ArrayList<Subject>();
        lastLogin = new Date();
    }

    public boolean validate(String id, String pass) {
        if (id.equals(this.id) && pass.equals(this.pass)) {
            return true;
        }
        return false;
    }

    public void setLogin(Date d) {
        lastLogin = d;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return new SimpleDateFormat().format(lastLogin);
    }

    public ArrayList<Subject> getSubjectList() {
        return subjectList;
    }

    public void addSubject(Subject s) {
        subjectList.add(s);
    }

    public void removeSubject(int index) {
        subjectList.remove(index);
    }

    public void moveUp(int index) {
        if (index == 0) {
            return;
        }
        Subject temp = subjectList.get(index);
        subjectList.set(index, subjectList.get(index - 1));
        subjectList.set(index - 1, temp);
    }

    public void moveDown(int index) {
        if (index == subjectList.size() - 1) {
            return;
        }
        Subject temp = subjectList.get(index);
        subjectList.set(index, subjectList.get(index + 1));
        subjectList.set(index + 1, temp);
    }

    @Override
    public String toString() {
        return name;
    }
    private String name;
    private String id;
    private String pass;
    private ArrayList<Subject> subjectList;
    private Date lastLogin;
}

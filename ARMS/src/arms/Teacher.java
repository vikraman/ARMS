/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author vh4x0r
 */
public class Teacher {

    public Teacher(String name, String id, String pass, int seniority) {
        this.name = name;
        this.id = id;
        this.pass = pass;
        this.seniority = seniority;
        subjectList = new Vector<Subject>();
        subjectList.add(new Subject("Mathematics", "M001"));
        subjectList.add(new Subject("Physics", "P012"));
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

    public String getLogin() {
        return new SimpleDateFormat().format(lastLogin);
    }

    public Object[] getSubjectList() {
        return subjectList.toArray();
    }

    public void addSubject(Subject s) {
        subjectList.add(s);
    }

    public void removeSubject(Subject s) {
        subjectList.remove(s);
    }

    public void moveUp(Subject s) {
        int index = subjectList.indexOf(s);
        if (index == 0) {
            return;
        }
        subjectList.setElementAt(subjectList.elementAt(index - 1), index);
        subjectList.setElementAt(s, index - 1);
    }

    public void moveDown(Subject s) {
        int index = subjectList.indexOf(s);
        if (index == subjectList.size() - 1) {
            return;
        }
        subjectList.setElementAt(subjectList.elementAt(index + 1), index);
        subjectList.setElementAt(s, index + 1);
    }
    private String name;
    private String id;
    private String pass;
    private int seniority;
    private Vector<Subject> subjectList;
    private Date lastLogin;
}

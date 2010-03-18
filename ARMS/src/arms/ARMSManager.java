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
        subjectList = new ArrayList<Subject>();
        userList.add(new User("User", "user", "user"));
        teacherList.add(new Teacher("John Smith", "john", "smith", 1));
        teacherList.add(new Teacher("Donald Knuth", "donald", "knuth", 10));
        teacherList.add(new Teacher("Thomas Cormen", "thomas", "cormen", 8));
        teacherList.add(new Teacher("Andrew Tanenbaum", "andrew", "tanenbaum", 7));
        teacherList.add(new Teacher("Stephen Hawking", "stephen", "hawking", 15));
        teacherList.add(new Teacher("Larry Wall", "larry", "wall", 5));
        subjectList.add(new Subject("Object Oriented Programming", "P050"));
        subjectList.add(new Subject("Mathematics", "M001"));
        subjectList.add(new Subject("Physics", "P001"));
        subjectList.add(new Subject("Operating Systems", "O012"));
        subjectList.add(new Subject("Network Programming", "N010"));
        subjectList.add(new Subject("Programming Languages", "L010"));
        subjectList.add(new Subject("Data Structures", "D910"));
        subjectList.add(new Subject("Algorithmics", "A007"));
        subjectList.add(new Subject("Automata Theory", "A050"));
        subjectList.add(new Subject("Systems Programming", "S050"));
        subjectList.add(new Subject("Complexity Theory", "C050"));
    }

    public static Object login(String id, String pass, Group group) throws InvalidLoginException {
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

    public static void update(Object o) {
        /*if (o instanceof Teacher) {
        Teacher to = (Teacher) o;
        for (int i = 0; i < teacherList.size(); i++) {
        if (teacherList.get(i).getId().equals(to.getId())) {
        teacherList.set(i, to);
        break;
        }
        }
        }*/
    }

    public static ArrayList<Subject> getAvailSubjectList(ArrayList<Subject> selectList) {
        ArrayList<Subject> availSubjectList = new ArrayList<Subject>();
        for (Subject s : subjectList) {
            if (!selectList.contains(s)) {
                availSubjectList.add(s);
            }
        }
        return availSubjectList;
    }

    public static int[] generateSolution() {
        int row = teacherList.size();
        int col = subjectList.size();
        int i, j, priority, maxRow;
        int[][] solutionTable = new int[row][col];
        int[] teacherIndex = new int[col];
        //fill the solution table
        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                priority = col - teacherList.get(i).getSubjectList().indexOf(subjectList.get(j));
                priority = priority == (col + 1) ? 0 : priority;
                solutionTable[i][j] = priority * teacherList.get(i).getSeniority();
            }
        }
        //find out the maximum value in each column
        //assume subjects are more than teachers
        for (j = 0; j < col; j++) {
            maxRow = 0;
            for (i = 1; i < row; i++) {
                if (solutionTable[i][j] > solutionTable[maxRow][j]) {
                    maxRow = i;
                }
            }
            teacherIndex[j] = solutionTable[maxRow][j] == 0 ? -1 : maxRow;
        }
        return teacherIndex;
    }

    public static ArrayList<Subject> getSubjects() {
        return subjectList;
    }

    public static String getTeacher(int index) {
        return teacherList.get(index).getName();
    }

    public static ArrayList<Teacher> getTeachers() {
        return teacherList;
    }
    private static ArrayList<Teacher> teacherList;
    private static ArrayList<User> userList;
    private static ArrayList<Subject> subjectList;
}

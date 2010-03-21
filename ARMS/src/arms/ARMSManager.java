/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author vh4x0r
 */
public class ARMSManager {

    public ARMSManager() {
        try {
            configFile = getConfigFile();
            if (configFile.length() == 0) {
                subjectList = new ArrayList<Subject>();
                teacherList = new ArrayList<Teacher>();
                return;
            }
            FileInputStream fis = new FileInputStream(configFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            subjectList = (ArrayList<Subject>) ois.readObject();
            teacherList = (ArrayList<Teacher>) ois.readObject();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static File getConfigFile() throws IOException {
        String userHome = System.getProperty("user.home");
        if (userHome.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cannot find user home !", "Error", JOptionPane.ERROR_MESSAGE);
            ARMSApp.getApplication().exit();
        }
        File home = new File(userHome);
        File settingsDirectory = new File(home, ".ARMS");
        if (!settingsDirectory.exists()) {
            if (!settingsDirectory.mkdir()) {
                JOptionPane.showMessageDialog(null, "Cannot write to home directory !", "Error", JOptionPane.ERROR_MESSAGE);
                ARMSApp.getApplication().exit();
            }
        }
        File settingsFile = new File(settingsDirectory, "ARMS.config");
        if (!settingsFile.exists()) {
            if (!settingsFile.createNewFile()) {
                JOptionPane.showMessageDialog(null, "Cannot create config file !", "Error", JOptionPane.ERROR_MESSAGE);
                ARMSApp.getApplication().exit();
            }
        }
        return settingsFile;
    }

    public static Object login(String id, String pass, Group group) throws InvalidLoginException {
        switch (group) {
            case User:
                if (id.equals("user") && pass.equals("user")) {
                    ARMSOutput aRMSOutput = new ARMSOutput(ARMSManager.generateSolution());
                    aRMSOutput.setLocationRelativeTo(ARMSApp.getApplication().getMainFrame());
                    ARMSApp.getApplication().getMainFrame().setVisible(false);
                    aRMSOutput.setVisible(true);
                    return null;
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
                    AdminForm adminForm = new AdminForm(teacherList, subjectList);
                    adminForm.setLocationRelativeTo(ARMSApp.getApplication().getMainFrame());
                    ARMSApp.getApplication().getMainFrame().setVisible(false);
                    adminForm.setVisible(true);
                    return null;
                }
                break;
        }
        throw new InvalidLoginException("Invalid user ID and password combination!");
    }

    public static void update() {
        try {
            configFile = getConfigFile();
            FileOutputStream fos = new FileOutputStream(configFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(subjectList);
            oos.writeObject(teacherList);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
                solutionTable[i][j] = priority * (teacherList.size() - i);
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
    private static ArrayList<Subject> subjectList;
    private static File configFile;
}

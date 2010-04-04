/*
 * Automatic Routine Management System
 */

/*
 * ARMSManager.java
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
            configFiles = getConfigFiles();
            FileInputStream fis;
            ObjectInputStream ois;
            if (configFiles[0].length() == 0) {
                teacherList = new ArrayList<Teacher>();
            } else {
                fis = new FileInputStream(configFiles[0]);
                ois = new ObjectInputStream(fis);
                teacherList = (ArrayList<Teacher>) ois.readObject();
                ois.close();
                fis.close();
            }
            if (configFiles[1].length() == 0) {
                subjectList = new ArrayList<Subject>();
            } else {
                fis = new FileInputStream(configFiles[1]);
                ois = new ObjectInputStream(fis);
                subjectList = (ArrayList<Subject>) ois.readObject();
                ois.close();
                fis.close();
            }
            if (configFiles[2].length() == 0) {
                psr = 2.0;
            } else {
                fis = new FileInputStream(configFiles[2]);
                ois = new ObjectInputStream(fis);
                psr = ois.readDouble();
                ois.close();
                fis.close();
            }
            if (configFiles[3].length() == 0) {
                adminPass = "admin".hashCode();
            } else {
                fis = new FileInputStream(configFiles[3]);
                ois = new ObjectInputStream(fis);
                adminPass = ois.readLong();
                ois.close();
                fis.close();
            }
            if (configFiles[4].length() == 0) {
                userPass = "user".hashCode();
            } else {
                fis = new FileInputStream(configFiles[4]);
                ois = new ObjectInputStream(fis);
                userPass = ois.readLong();
                ois.close();
                fis.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid config file !", "Error", JOptionPane.ERROR_MESSAGE);
            ARMSApp.getApplication().exit();
        }
    }

    public static File[] getConfigFiles() throws IOException {
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
        File[] settingsFiles = new File[5];
        settingsFiles[0] = new File(settingsDirectory, "tlist.bin");
        if (!settingsFiles[0].exists()) {
            if (!settingsFiles[0].createNewFile()) {
                JOptionPane.showMessageDialog(null, "Cannot create config file !", "Error", JOptionPane.ERROR_MESSAGE);
                ARMSApp.getApplication().exit();
            }
        }
        settingsFiles[1] = new File(settingsDirectory, "slist.bin");
        if (!settingsFiles[1].exists()) {
            if (!settingsFiles[1].createNewFile()) {
                JOptionPane.showMessageDialog(null, "Cannot create config file !", "Error", JOptionPane.ERROR_MESSAGE);
                ARMSApp.getApplication().exit();
            }
        }
        settingsFiles[2] = new File(settingsDirectory, "psr.bin");
        if (!settingsFiles[2].exists()) {
            if (!settingsFiles[2].createNewFile()) {
                JOptionPane.showMessageDialog(null, "Cannot create config file !", "Error", JOptionPane.ERROR_MESSAGE);
                ARMSApp.getApplication().exit();
            }
        }
        settingsFiles[3] = new File(settingsDirectory, "admin.bin");
        if (!settingsFiles[3].exists()) {
            if (!settingsFiles[3].createNewFile()) {
                JOptionPane.showMessageDialog(null, "Cannot create config file !", "Error", JOptionPane.ERROR_MESSAGE);
                ARMSApp.getApplication().exit();
            }
        }
        settingsFiles[4] = new File(settingsDirectory, "user.bin");
        if (!settingsFiles[4].exists()) {
            if (!settingsFiles[4].createNewFile()) {
                JOptionPane.showMessageDialog(null, "Cannot create config file !", "Error", JOptionPane.ERROR_MESSAGE);
                ARMSApp.getApplication().exit();
            }
        }
        return settingsFiles;
    }

    public static Object login(String id, String pass, Group group) throws InvalidLoginException {
        switch (group) {
            case User:
                if (id.equals("user") && pass.hashCode() == userPass) {
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
                if (id.equals("admin") && pass.hashCode() == adminPass) {
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
            configFiles = getConfigFiles();
            FileOutputStream fos;
            ObjectOutputStream oos;
            fos = new FileOutputStream(configFiles[0]);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(teacherList);
            oos.close();
            fos.close();
            fos = new FileOutputStream(configFiles[1]);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(subjectList);
            oos.close();
            fos.close();
            fos = new FileOutputStream(configFiles[2]);
            oos = new ObjectOutputStream(fos);
            oos.writeDouble(psr);
            oos.close();
            fos.close();
            fos = new FileOutputStream(configFiles[3]);
            oos = new ObjectOutputStream(fos);
            oos.writeLong(adminPass);
            oos.close();
            fos.close();
            fos = new FileOutputStream(configFiles[4]);
            oos = new ObjectOutputStream(fos);
            oos.writeLong(userPass);
            oos.close();
            fos.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to write config file !", "Error", JOptionPane.ERROR_MESSAGE);
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
        int i, j, maxRow, index;
        double priority, seniority;
        double[][] solutionTable = new double[row][col];
        int[] teacherIndex = new int[col];
        //fill the solution table
        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                index = teacherList.get(i).getSubjectList().indexOf(subjectList.get(j));
                if (index == -1) {
                    solutionTable[i][j] = 0.0;
                } else {
                    priority = (double) (col - index) / col;
                    seniority = (double) (row - i) / row;
                    solutionTable[i][j] = psr * priority + seniority;
                }
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
            teacherIndex[j] = (solutionTable[maxRow][j] == 0.0) ? -1 : maxRow;
        }
        return teacherIndex;
    }

    public static ArrayList<Subject> getSubjects() {
        return subjectList;
    }

    public static ArrayList<Teacher> getTeachers() {
        return teacherList;
    }

    public static Double getPSR() {
        return psr;
    }

    public static void setPSR(String d) {
        try {
            psr = Double.valueOf(d);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Bad numerical value !", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void updateUser() {
        String p = JOptionPane.showInputDialog(null, "Enter new user password :", "Password", JOptionPane.QUESTION_MESSAGE);
        if (p == null || p.isEmpty()) {
            return;
        }
        userPass = p.hashCode();
    }

    public static void updateAdmin() {
        String p = JOptionPane.showInputDialog(null, "Enter new administrator password :", "Password", JOptionPane.QUESTION_MESSAGE);
        if (p == null || p.isEmpty()) {
            return;
        }
        adminPass = p.hashCode();
    }
    private static ArrayList<Teacher> teacherList;
    private static ArrayList<Subject> subjectList;
    private static Double psr;
    private static File[] configFiles;
    private static long adminPass;
    private static long userPass;
}

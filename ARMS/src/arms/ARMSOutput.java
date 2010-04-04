/*
 * Automatic Routine Management System
 */

/*
 * ARMSOutput.java
 *
 * Created on Mar 16, 2010, 1:15:00 AM
 */
package arms;

import org.jdesktop.application.Action;

/**
 *
 * @author vh4x0r
 */
public class ARMSOutput extends javax.swing.JFrame {

    /** Creates new form ARMSOutput */
    public ARMSOutput(int[] index) {
        this.index = index;
        initComponents();
    }

    @Override
    public void dispose() {
        ARMSApp.getApplication().getMainFrame().setVisible(true);
        super.dispose();
    }

    @Action
    public void logout() {
        dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subScrollPane = new javax.swing.JScrollPane();
        subList = new javax.swing.JList();
        teacherLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        subjectLabel = new javax.swing.JLabel();
        teacherScrollPane = new javax.swing.JScrollPane();
        teacherEditorPane = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(arms.ARMSApp.class).getContext().getResourceMap(ARMSOutput.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N

        subScrollPane.setName("subScrollPane"); // NOI18N

        subList.setModel(new javax.swing.AbstractListModel() {
            Object[] strings = ARMSManager.getSubjects().toArray();
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        subList.setName("subList"); // NOI18N
        subList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                subListValueChanged(evt);
            }
        });
        subScrollPane.setViewportView(subList);

        teacherLabel.setText(resourceMap.getString("teacherLabel.text")); // NOI18N
        teacherLabel.setName("teacherLabel"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(arms.ARMSApp.class).getContext().getActionMap(ARMSOutput.class, this);
        logoutButton.setAction(actionMap.get("logout")); // NOI18N
        logoutButton.setText(resourceMap.getString("logoutButton.text")); // NOI18N
        logoutButton.setName("logoutButton"); // NOI18N

        subjectLabel.setText(resourceMap.getString("subjectLabel.text")); // NOI18N
        subjectLabel.setName("subjectLabel"); // NOI18N

        teacherScrollPane.setName("teacherScrollPane"); // NOI18N

        teacherEditorPane.setEditable(false);
        teacherEditorPane.setName("teacherEditorPane"); // NOI18N
        teacherEditorPane.setContentType("text/html");
        teacherScrollPane.setViewportView(teacherEditorPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subjectLabel)
                    .addComponent(subScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(teacherScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(logoutButton))
                    .addComponent(teacherLabel))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(teacherLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(teacherScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addComponent(logoutButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(subjectLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_subListValueChanged
        // TODO add your handling code here:
        if (subList.isSelectionEmpty()) {
            return;
        }
        String text;
        if (index[subList.getSelectedIndex()] == -1) {
            text = "<color=ff0000><i>Teacher not allocated</i>";
        } else {
            Teacher t = ARMSManager.getTeachers().get(index[subList.getSelectedIndex()]);
            text = "<strong>Name:<i> " + t.getName() + "</i><br>ID: " + t.getId() + "</strong>";
        }
        teacherEditorPane.setContentType("text/html");
        teacherEditorPane.setText(text);
    }//GEN-LAST:event_subListValueChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton logoutButton;
    private javax.swing.JList subList;
    private javax.swing.JScrollPane subScrollPane;
    private javax.swing.JLabel subjectLabel;
    private javax.swing.JEditorPane teacherEditorPane;
    private javax.swing.JLabel teacherLabel;
    private javax.swing.JScrollPane teacherScrollPane;
    // End of variables declaration//GEN-END:variables
    private int[] index;
}
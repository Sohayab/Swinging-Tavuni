
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author suhai
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    JMenu file;
    JMenuItem open;
    JMenuItem New;
    JMenuItem saveAs;
    JMenuItem quit;
    JMenu help;
    JMenuItem about;
    PrintWriter oFile;
    MainPanel panel;
    public static Tavunu currentTavunu = null;
    JFrame frame;

    public MainFrame() throws IOException {
        frame = this;
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        panel = new MainPanel();
        this.add(panel);
        file = new JMenu("File");
        open = new JMenuItem("Open");
        New = new JMenuItem("New");
        saveAs = new JMenuItem("Save As");
        quit = new JMenuItem("Quit");
        help = new JMenu("Help");
        about = new JMenuItem("About");
        this.file.add(open);
        this.file.add(New);
        this.file.add(saveAs);
        this.file.add(quit);
        this.help.add(about);
        JMenuBar bar = new JMenuBar();
        bar.add(file);
        bar.add(help);
        this.setJMenuBar(bar);
        JFrame f = this;
        saveAs.setEnabled(false);
        open.addActionListener((ActionEvent ae) -> {
            try {
                OpenDialog d = new OpenDialog(f, true);
                d.setVisible(true);
                panel.name.setText(currentTavunu.name);
                panel.pava.setText(String.valueOf(currentTavunu.pava));
                panel.birthYear.setText(String.valueOf(currentTavunu.birthYear));
                panel.rank.setSelectedItem(currentTavunu.getClass().toString().substring(6));
                panel.name.setEnabled(false);
                panel.pava.setEnabled(false);
                panel.birthYear.setEnabled(false);
                panel.rank.setEnabled(false);
                saveAs.setEnabled(false);
            } catch (Exception ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        New.addActionListener((ActionEvent ae) -> {
            panel.name.setText("");
            panel.pava.setText("");
            panel.rank.setSelectedIndex(0);
            panel.birthYear.setValue("");
            panel.name.setEnabled(true);
            panel.birthYear.setEnabled(true);
            panel.pava.setEnabled(true);
            saveAs.setEnabled(true);
        });
        panel.name.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                if (!panel.name.getText().isEmpty() && !panel.name.getText().startsWith("T") && !panel.name.getText().startsWith("D")) {
                    JOptionPane.showMessageDialog(frame, "Name should start with T or D", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if (!panel.name.getText().isEmpty() && !panel.name.getText().startsWith("T") && !panel.name.getText().startsWith("D")) {
                    JOptionPane.showMessageDialog(frame, "Name should start with T or D", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                if (!panel.name.getText().isEmpty() && !panel.name.getText().startsWith("T") && !panel.name.getText().startsWith("D")) {
                    JOptionPane.showMessageDialog(frame, "Name should start with T or D", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.pava.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                try {
                    updateRank();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Enter a number please", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                try {
                    updateRank();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Enter a number please", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                try {
                    updateRank();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Enter a number please", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        saveAs.addActionListener((var ae) -> {
            if (panel.name.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "name is required !", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!panel.birthYear.isValid()) {
                JOptionPane.showMessageDialog(frame, "birth year is required !", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (panel.pava.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "pava is required !", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                try {
                    String commands
                            = "o\n"
                            + "a\n"
                            + panel.name.getText()
                            + "\n"
                            + panel.birthYear.getText()
                            + "\n"
                            + panel.pava.getText()
                            + "\ns\n"
                            + "q";
                    System.setIn(new ByteArrayInputStream(commands.getBytes()));
                    TavunuApp.main(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }//like that i can input anything like cmd by writing on yourFile File
            } catch (Exception ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }//like that i can input anything like cmd by writing on yourFile File
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(frame, "This is a Swing application for creating, saving,\n" +
"and showing details about individual tavuni.\nYou cannot edit previously created tavuni\n" +
"with this application, but you can view their details.\nBrought to you by Suhaib Abugdera", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void updateRank() {
        if (panel.pava.getText().isEmpty()) {
            return;
        }
        if (Integer.parseInt(panel.pava.getText()) > 80) {
            if (panel.birthYear.isValid() && panel.birthYear.getText().isEmpty() && Integer.parseInt(panel.birthYear.getText()) % 2 == 0) {
                panel.rank.setSelectedItem("CrodeExalted");
            } else {
                panel.rank.setSelectedItem("Crode");
            }

        } else if (Integer.parseInt(panel.pava.getText()) > 20) {
            panel.rank.setSelectedItem("Beele");
        } else {
            panel.rank.setSelectedItem("Avo");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//info.getClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainFrame().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

package UI;
import System.*;
import ManageFile.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class deletePage extends JFrame implements ActionListener {
    private JButton backButton, deleteButton;
    private JTextField inputSlangField;
    public deletePage(){
        setTitle("SLANG WORD");
        setSize(480, 240);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(createAndShowGUI());
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }

    public JPanel createAndShowGUI(){
        Color color = new Color(255,0,0);
        JLabel title = new JLabel("Delete Slang Word", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 28));
        title.setForeground(color);
        title.setBounds(0, 50, 500, 30);
        add(title);

        JPanel panel = new JPanel();
        int panelWidth = 480;
        int panelHeight = 240;
        int panelX = (getWidth() - panelWidth) / 2;
        int panelY = title.getY() + title.getHeight() + 25;
        panel.setBounds(panelX, panelY, panelWidth, panelHeight);

        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel slangWord = new JLabel("Slang word: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        content.add(slangWord, gbc);

        inputSlangField = new JTextField();
        inputSlangField.setPreferredSize(new Dimension(240, 25));
        gbc.gridx = 1;
        gbc.gridy = 0;
        content.add(inputSlangField, gbc);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        content.add(deleteButton, gbc);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 2;
        content.add(backButton, gbc);

        panel.add(content);
        return panel;
    }
    public static void main(String[] args) {
        new deletePage();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            dispose();
            new Home();
        }
        if(e.getSource() == deleteButton) {
            String inputSlangString = inputSlangField.getText();
            if(inputSlangString.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter slang word to delete");
            } else {
                ArrayList<String> def = (ArrayList<String>) Main.listOfSlang.searchSlangWord(inputSlangString);
                if (def == null) {
                    JOptionPane.showMessageDialog(null, "Slang word don't exist");
                } else {
                    int confirm = JOptionPane.showConfirmDialog(null, "Do you want delete?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if(confirm == JOptionPane.YES_OPTION){
                        Main.listOfSlang.deleteSlangWord(inputSlangString);
                        JOptionPane.showMessageDialog(null, "Delete successfully");
                        FileManager.saveFile();
                        inputSlangField.setText("");
                    }
                }
            }
        }
    }
}

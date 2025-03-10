package UI;
import System.*;
import ManageFile.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class addPage extends JFrame implements ActionListener {
    private JButton backButton, addButton;
    private JTextField inputSlangField, inputDefField;
    public addPage () {
        setTitle("SLANG WORD");
        setSize(480, 320);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(createAndShowGUI());
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }
    public JPanel createAndShowGUI(){
        Color color = new Color(255,0,0);
        JLabel title = new JLabel("ADD NEW", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 28));
        title.setForeground(color);
        title.setBounds(0, 50, 500, 30);
        add(title);

        JPanel panel = new JPanel();
        int panelWidth = 480;
        int panelHeight = 320;
        int panelX = (getWidth() - panelWidth) / 2;
        int panelY = title.getY() + title.getHeight() + 25;
        panel.setBounds(panelX, panelY, panelWidth, panelHeight);

        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel labelSlang = new JLabel("Input Slang: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        content.add(labelSlang, gbc);

        inputSlangField = new JTextField();
        inputSlangField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 0;
        content.add(inputSlangField, gbc);

        JLabel labelDef = new JLabel("Input Definition: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        content.add(labelDef, gbc);

        inputDefField = new JTextField();
        inputDefField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        content.add(inputDefField, gbc);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        content.add(backButton, gbc);

        addButton = new JButton("Add");
        addButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 2;
        content.add(addButton, gbc);

        panel.add(content);
        return panel;
    }

    public static void main(String[] args) {
        new addPage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            dispose();
            new Home();
        }
        if(e.getSource() == addButton){
            String slang = inputSlangField.getText();
            String def = inputDefField.getText();
            if(slang.isEmpty() || def.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter full information");
            } else {
                String[] data = def.split("\\|");
                List<String> definition = new ArrayList<>(Arrays.asList(data));
                ArrayList<String> result = (ArrayList<String>) Main.listOfSlang.searchSlangWord(slang);
                if(result == null){
                    Main.listOfSlang.addBySlangAndDef(slang, definition);
                    FileManager.saveFile();
                    JOptionPane.showMessageDialog(null, "Add successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
                    inputSlangField.setText("");
                    inputDefField.setText("");
                } else {
                    new alertScreen(slang, definition);
                    inputSlangField.setText("");
                    inputDefField.setText("");
                }
            }
        }
    }
}

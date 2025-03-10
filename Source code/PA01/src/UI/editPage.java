package UI;
import System.*;
import ManageFile.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class editPage extends JFrame implements ActionListener {
    private JButton editButton, backButton;
    private JTextField inputSlangField, inputDefField;
    public editPage(){
        setTitle("SLANG WORD");
        setSize(640, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(createAndShowGUI());
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }
    public JPanel createAndShowGUI(){
        Color color = new Color(255,0,0);
        JLabel title = new JLabel("Edit Slang Word", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 28));
        title.setForeground(color);
        title.setBounds(90, 50, 500, 30);
        add(title);

        JPanel panel = new JPanel();
        int panelWidth = 640;
        int panelHeight = 360;
        int panelX = (getWidth() - panelWidth) / 2;
        int panelY = title.getY() + title.getHeight() + 25;
        panel.setBounds(panelX, panelY, panelWidth, panelHeight);

        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10,10,10);

        JLabel slangLabel = new JLabel("Slang Word: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        content.add(slangLabel, gbc);

        inputSlangField = new JTextField();
        inputSlangField.setPreferredSize(new Dimension(240, 25));
        gbc.gridx = 1;
        gbc.gridy = 0;
        content.add(inputSlangField, gbc);

        JLabel defLabel = new JLabel("Definition: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        content.add(defLabel, gbc);

        inputDefField = new JTextField();
        inputDefField.setPreferredSize(new Dimension(240, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        content.add(inputDefField, gbc);

        editButton = new JButton("Edit");
        editButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        content.add(editButton, gbc);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 2;
        content.add(backButton, gbc);

        panel.add(content);
        return panel;
    }

    public static void main(String[] args) {
        new editPage();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            dispose();
            new Home();
        }
        if (e.getSource() == editButton) {
            String inputSlangString = inputSlangField.getText();
            String inputDefString = inputDefField.getText();
            if(inputSlangString.isEmpty() || inputDefString.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter full information to edit.");
            } else {
                ArrayList<String> def = (ArrayList<String>) Main.listOfSlang.searchSlangWord(inputSlangString);
                if (def == null) {
                    JOptionPane.showMessageDialog(null, "Word definition don't exist");
                } else if (!def.isEmpty()) {
                    String[] btn = {"Edit All", "Choose", "Cancel"};
                    String[] data = inputDefString.split("\\|");
                    List<String> newDefList = new ArrayList<>(Arrays.asList(data));
                    int choose = JOptionPane.showOptionDialog(null, "Slang word " + inputSlangString + " has " + def.size() + " element!",
                            "Notification", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, btn, 0);
                    if(choose == 0){
                        Main.listOfSlang.overwriteSlangWord(inputSlangString, newDefList);
                        FileManager.saveFile();
                    } else if (choose == 1) {
                        String[] opt = {"Choose", "Cancel"};
                        List<String> newDefByOne = new ArrayList<>(def);
                        int indexToOverride = -1;
                        for(int i = 0; i < def.size(); i++) {
                            int myChoice = JOptionPane.showOptionDialog(null, "Definition " + (i + 1) + ": " + def.get(i),
                                    "Notification", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, 0);
                            if(myChoice == 0) { indexToOverride = i; break; }
                            if(myChoice == 1) { continue; }
                        }
                        newDefByOne.set(indexToOverride, inputDefString);
                        Main.listOfSlang.overwriteSlangWord(inputSlangString, newDefByOne);
                        FileManager.saveFile();
                        inputSlangField.setText("");
                        inputDefField.setText("");
                    }
                }
            }
        }
    }
}

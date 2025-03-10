package UI;
import Model.*;
import System.*;
import ManageFile.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class searchDefPage extends JFrame implements ActionListener {
    private JTextField inputField;
    private JTable listTable;
    private JButton searchButton;
    private JButton backButton;
    public searchDefPage () {
        setTitle("SLANG WORD");
        setSize(640, 360);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(createAndShowGUI());
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }
    public JPanel createAndShowGUI() {
        Color color = new Color(255,0,0);
        JLabel title = new JLabel("Search by Definition", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 28));
        title.setForeground(color);
        title.setBounds(70, 50, 500, 30);
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
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel searchLabel = new JLabel("Search: ");
        content.add(searchLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 0;

        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(240, 25));
        gbc.gridx = 1;
        gbc.gridy = 0;
        content.add(inputField, gbc);

        searchButton = new JButton("OK");
        searchButton.setMargin(new Insets(5, 10, 5, 10));
        searchButton.setPreferredSize(new Dimension(50, 25));
        searchButton.addActionListener(this);
        gbc.gridx = 2;
        gbc.gridy = 0;
        content.add(searchButton, gbc);

        String[] columnNames = {"Slang", "Definition"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 15);
        model.setColumnIdentifiers(columnNames);
        listTable = new JTable(model);
        listTable.setPreferredScrollableViewportSize(new Dimension(450, 90));
        listTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(listTable);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        content.add(scrollPane, gbc);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        GridBagConstraints backButtonGBC = new GridBagConstraints();
        backButtonGBC.fill = GridBagConstraints.HORIZONTAL;
        backButtonGBC.insets = new Insets(5, 10, 5, 10);
        backButtonGBC.gridx = 0;
        backButtonGBC.gridy = 2;
        backButtonGBC.gridwidth = 2;
        content.add(backButton, backButtonGBC);

        panel.add(content);
        return panel;
    }

    public static void main(String[] args) {
        new  searchDefPage();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            dispose();
            new Home();
        }
        if(e.getSource() == searchButton) {
            String inputString = inputField.getText();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss a");
            LocalDateTime now = LocalDateTime.now();
            String time = dtf.format(now);

            if(inputString.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter string to search");
            } else {
                ArrayList<String> res = Main.listOfSlang.searchDefinition(inputString);
                if (res == null) {
                    History historyList = new History(time, "NOT FOUND", "NOT FOUND", inputString);
                    FileManager.saveHistory(historyList);
                    JOptionPane.showMessageDialog(null, "Word definition don't exist");
                } else {
                    History historyList = new History(time, Main.listOfSlang.getSlangWordList(res), inputString, inputString);
                    FileManager.saveHistory(historyList);
                    //show result
                    DefaultTableModel model = (DefaultTableModel) listTable.getModel();
                    model.setRowCount(0);
                    for (String re : res) {
                        model.addRow(new Object[]{re, Main.listOfSlang.getDefinitionString(re)});
                    }
                }
            }
        }
    }
}


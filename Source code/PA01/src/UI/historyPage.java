package UI;
import System.*;
import ManageFile.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.table.*;
public class historyPage extends JFrame implements ActionListener{
    private JTable historyTable;
    private JButton deleteButton, backButton;
    public historyPage(){
        setTitle("SLANG WORD");
        setSize(840, 360);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(createAndShowGUI());
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }
    public JPanel createAndShowGUI() {
        Color color = new Color(255,0,0);
        JLabel title = new JLabel("History Search", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 28));
        title.setForeground(color);
        title.setBounds(170, 50, 500, 30);
        add(title);

        JPanel panel = new JPanel();
        int panelWidth = 840;
        int panelHeight = 360;
        int panelX = (getWidth() - panelWidth) / 2;
        int panelY = title.getY() + title.getHeight() + 25;
        panel.setBounds(panelX, panelY, panelWidth, panelHeight);

        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        String[] columnNames = {"NO.","Time", "Slang", "Definition", "KeyWord"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 100);
        model.setColumnIdentifiers(columnNames);
        historyTable = new JTable(model);
        historyTable.setPreferredScrollableViewportSize(new Dimension(720, 90));
        historyTable.setFillsViewportHeight(true);

        TableColumn noColumn = historyTable.getColumnModel().getColumn(0);
        TableColumn timeColumn = historyTable.getColumnModel().getColumn(1);
        TableColumn slangColumn = historyTable.getColumnModel().getColumn(2);
        TableColumn keyColumn = historyTable.getColumnModel().getColumn(4);

        noColumn.setMinWidth(15);
        noColumn.setMaxWidth(30);

        keyColumn.setMinWidth(75);
        keyColumn.setMaxWidth(150);

        timeColumn.setMinWidth(135);
        timeColumn.setMaxWidth(140);

        slangColumn.setMinWidth(150);
        slangColumn.setMaxWidth(200);
        loadTable();

        JScrollPane scrollPane = new JScrollPane(historyTable);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        content.add(scrollPane, gbc);

        GridBagConstraints optButton = new GridBagConstraints();
        optButton.fill = GridBagConstraints.HORIZONTAL;
        optButton.insets = new Insets(5, 10, 5, 10);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        optButton.gridx = 0;
        optButton.gridy = 1;
        optButton.gridwidth = 1;
        content.add(deleteButton, optButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        optButton.gridx = 1;
        optButton.gridy = 1;
        content.add(backButton, optButton);

        panel.add(content);
        return panel;
    }
    public void loadTable() {
        FileManager.loadHistory();
        for (int i = 0; i < Main.historyList.size(); i++) {
            historyTable.setValueAt(i + 1, i, 0);
            historyTable.setValueAt(Main.historyList.get(i).getTime(), i, 1);
            historyTable.setValueAt(Main.historyList.get(i).getSlang(), i, 2);
            historyTable.setValueAt(Main.historyList.get(i).getDef(), i, 3);
            historyTable.setValueAt(Main.historyList.get(i).getKeyword(), i, 4);
        }
    }

    public static void main(String[] args) {
        new historyPage();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            dispose();
            new Home();
        }
        if(e.getSource() == deleteButton){
            try {
                FileManager.deletetHistory();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(null,
                    "Reset successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new historyPage();
        }
    }
}

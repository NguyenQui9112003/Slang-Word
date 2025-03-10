package UI;
import ManageFile.FileManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Home extends JFrame implements ActionListener {
    private JButton searchButton, searchDefButton, historyButton;
    private JButton addButton, updateButton, deleteButton, resetButton;
    private JButton randomButton, quizButton, quizDefButton;
    public Home() {
        setTitle("Slang word");
        setSize(600, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(createAndShowGUI());
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public JPanel createAndShowGUI() {
        Color color = new Color(255,0,0);
        JLabel title = new JLabel("SLANG WORD", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 28));
        title.setForeground(color);
        title.setBounds(50, 50, 500, 30);
        add(title);

        searchButton = new JButton("Search");
        searchDefButton = new JButton("Search by definition");
        historyButton = new JButton("History");
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        resetButton = new JButton("Reset");
        randomButton = new JButton("Random");
        quizButton = new JButton("Quiz - Slang Word");
        quizDefButton = new JButton("Quiz - Definition");

        JPanel panel = new JPanel();
        int panelWidth = 360;
        int panelHeight = 540;
        int panelX = (getWidth() - panelWidth) / 2;
        int panelY = title.getY() + title.getHeight() + 25;
        panel.setBounds(panelX, panelY, panelWidth, panelHeight);

        GridLayout layout = new GridLayout(10, 1);
        layout.setVgap(10);
        panel.setLayout(layout);

        panel.add(searchButton);searchButton.addActionListener(this);
        panel.add(searchDefButton);searchDefButton.addActionListener(this);
        panel.add(historyButton);historyButton.addActionListener(this);
        panel.add(addButton);addButton.addActionListener(this);
        panel.add(updateButton);updateButton.addActionListener(this);
        panel.add(deleteButton);resetButton.addActionListener(this);
        panel.add(resetButton);deleteButton.addActionListener(this);
        panel.add(randomButton);randomButton.addActionListener(this);
        panel.add(quizButton);quizButton.addActionListener(this);
        panel.add(quizDefButton);quizDefButton.addActionListener(this);

        return panel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(searchButton)) {
            searchPage page = new searchPage();
            page.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource().equals(searchDefButton)) {
            searchDefPage page = new searchDefPage();
            page.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource().equals(historyButton)) {
            historyPage page = new historyPage();
            page.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource().equals(addButton)) {
            addPage page = new addPage();
            page.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource().equals(updateButton)) {
            editPage page = new editPage();
            page.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource().equals(deleteButton)) {
            deletePage page = new deletePage();
            page.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource().equals(resetButton)) {
            FileManager.resetDictionary();
            JOptionPane.showMessageDialog(null, "Reset successfully");
        } else if (e.getSource().equals(quizButton)) {
            QuizFindSlangPage page = new QuizFindSlangPage();
            page.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource().equals(quizDefButton)) {
            QuizFindDefPage page = new QuizFindDefPage();
            page.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource().equals(randomButton)) {
            randomPage page = new randomPage();
            page.setVisible(true);
            this.setVisible(false);
        }
    }
    public static void main(String[] args) {
        new Home();
    }
}
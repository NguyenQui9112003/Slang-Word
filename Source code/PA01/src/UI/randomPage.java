package UI;
import System.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class randomPage extends JFrame implements ActionListener {
    private JButton backButton, againButton;
    private JLabel randomSlangField, randomDefField;
    public randomPage() {
        setTitle("SLANG WORD");
        setSize(640, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(createAndShowGUI());
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }
    public JPanel createAndShowGUI() {
        Color color = new Color(255, 0, 0);
        JLabel title = new JLabel("Random Slang Word", JLabel.CENTER);
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
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel slangLabel = new JLabel("Slang Word: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        content.add(slangLabel, gbc);

        String slang = Main.listOfSlang.randomSlangWordString();
        randomSlangField = new JLabel(slang);
        randomSlangField.setPreferredSize(new Dimension(240, 25));
        gbc.gridx = 1;
        gbc.gridy = 0;
        content.add(randomSlangField, gbc);

        JLabel defLabel = new JLabel("Definition: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        content.add(defLabel, gbc);

        String randDef = Main.listOfSlang.getDefinitionString(slang);
        randomDefField = new JLabel(randDef);
        randomDefField.setPreferredSize(new Dimension(240, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        content.add(randomDefField, gbc);

        againButton = new JButton("Random Again");
        againButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        content.add(againButton, gbc);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 2;
        content.add(backButton, gbc);

        panel.add(content);
        return panel;
    }

    public static void main(String[] args) {
        new randomPage();
    }
    public void setAgainButton(){
        String slang = Main.listOfSlang.randomSlangWordString();
        randomSlangField.setText(slang);
        String randDef = Main.listOfSlang.getDefinitionString(slang);
        randomDefField.setText(randDef);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose();
            new Home();
        }
        if (e.getSource() == againButton) {
            setAgainButton();
        }
    }
}

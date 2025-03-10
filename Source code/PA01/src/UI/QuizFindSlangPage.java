package UI;
import System.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
//ref: https://github.com/NgocTien0110/Slang-Dictionary/blob/master/src/GameFindSlangPage.java
public class QuizFindSlangPage extends JFrame implements ActionListener {
    private JButton res1, res2, res3, res4;
    private JButton backButton, againButton;
    private JLabel question;
    public static  int resOfQuestion;
    public QuizFindSlangPage(){
        setTitle("SLANG WORD");
        setSize(540, 560);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(createAndShowGUI());
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }
    public JPanel createAndShowGUI(){
        Color color = new Color(255,0,0);
        JLabel title = new JLabel("Quiz Game - Slang Word", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 28));
        title.setForeground(color);
        title.setBounds(0, 50, 500, 30);
        add(title);

        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        Set<Object> slangSet = createQuestion();
        gbc.gridx = 1;
        gbc.gridy = 0;
        question = new JLabel();
        String quest = createSlang(slangSet);
        question.setText("What is the meaning of " + quest + "?");
        question.setBackground(Color.WHITE);

        content.add(question, gbc);

        ArrayList<String> answerList = convertArrayString(slangSet);
        res1 = new JButton(answerList.get(0));
        res1.setPreferredSize(new Dimension(200, 100));
        res2 = new JButton(answerList.get(1));
        res2.setPreferredSize(new Dimension(200, 100));
        res3 = new JButton(answerList.get(2));
        res3.setPreferredSize(new Dimension(200, 100));
        res4 = new JButton(answerList.get(3));
        res4.setPreferredSize(new Dimension(200, 100));


        JPanel panel = new JPanel();
        int panelWidth = 480;
        int panelHeight = 560;
        int panelX = (getWidth() - panelWidth) / 2;
        int panelY = title.getY() + title.getHeight() + 25;
        panel.setBounds(panelX, panelY, panelWidth, panelHeight);

        JPanel quiz = new JPanel();
        GridLayout layout = new GridLayout(4, 1);
        layout.setVgap(10);
        layout.setHgap(10);
        quiz.setLayout(layout);

        res1.addActionListener(this);
        res2.addActionListener(this);
        res3.addActionListener(this);
        res4.addActionListener(this);
        quiz.add(res1);
        quiz.add(res2);
        quiz.add(res3);
        quiz.add(res4);
        gbc.gridx = 1;
        gbc.gridy = 1;
        content.add(quiz, gbc);

        backButton = new JButton("Back");
        gbc.gridx = 2;
        quiz.add(backButton);
        backButton.addActionListener(this);

        againButton = new JButton("Play Again");
        gbc.gridx = 2;
        quiz.add(againButton);
        againButton.addActionListener(this);

        panel.add(content);
        return panel;
    }

    public static void main(String[] args) {
        new QuizFindSlangPage();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            dispose();
            new Home();
        }
        if(e.getSource() == againButton) {
            setDefaultButton();
            setLabel();
            dispose();
            new QuizFindSlangPage();
        }
        if(e.getSource() == res1){
            if(resOfQuestion == 0){
                JOptionPane.showMessageDialog(null, "Correct answer");
            } else {
                JOptionPane.showMessageDialog(null, "Wrong answer");
            }
            res2.setEnabled(false);
            res3.setEnabled(false);
            res4.setEnabled(false);
        } else if (e.getSource() == res2) {
            if(resOfQuestion == 1){
                JOptionPane.showMessageDialog(null, "Correct answer");
            } else {
                JOptionPane.showMessageDialog(null, "Wrong answer");
            }
            res1.setEnabled(false);
            res3.setEnabled(false);
            res4.setEnabled(false);
        } else if (e.getSource() == res3) {
            if(resOfQuestion == 2){
                JOptionPane.showMessageDialog(null, "Correct answer");
            } else {
                JOptionPane.showMessageDialog(null, "Wrong answer");
            }
            res1.setEnabled(false);
            res2.setEnabled(false);
            res4.setEnabled(false);
        } else if (e.getSource() == res4) {
            if(resOfQuestion == 3){
                JOptionPane.showMessageDialog(null, "Correct answer");
            } else {
                JOptionPane.showMessageDialog(null, "Wrong answer");
            }
            res1.setEnabled(false);
            res2.setEnabled(false);
            res3.setEnabled(false);
        }
    }
    public void setDefaultButton(){
        res1.setEnabled(true); res1.setBackground(Color.white);
        res2.setEnabled(true); res2.setBackground(Color.white);
        res3.setEnabled(true); res3.setBackground(Color.white);
        res4.setEnabled(true); res4.setBackground(Color.white);
    }
    public void setLabel(){
        Set<Object> objectQuestion = createQuestion();
        ArrayList<String> anwerList = convertArrayString(objectQuestion);
        String str = createSlang(objectQuestion);
        question.setText("What is the definition of slang " + str + "? ");
        res1.setText(anwerList.get(0));
        res2.setText(anwerList.get(1));
        res3.setText(anwerList.get(2));
        res4.setText(anwerList.get(3));
    }
    private Set<Object> createQuestion() {
        Set<Object> answerObject = new HashSet<>();
        while(answerObject.size() < 4){
            String randomAnswer = Main.listOfSlang.randomSlangWordString();
            ArrayList<String> A = Main.listOfSlang.getDefinition(randomAnswer);
            answerObject.add(A);
        }
        return answerObject;
    }
    public String createSlang(Set<Object> answerObject) {
        int cnt = 0;
        Iterator<Object> key = answerObject.iterator();
        Random rand = new Random();
        resOfQuestion = rand.nextInt(4);
        System.out.println(resOfQuestion);
        String slangWord = "";
        while (cnt < 4 && key.hasNext()) {
            if (resOfQuestion == cnt) {
                ArrayList<String> listSlangWord = (ArrayList<String>) key.next();
                slangWord = Main.listOfSlang.getSlangWord(listSlangWord);
                break;
            }
            key.next();
            ++cnt;
        }
        return slangWord;
    }
    public ArrayList<String> convertArrayString(Set<Object> ansObject){
        Iterator<Object> item = ansObject.iterator();
        ArrayList<String> answerQuestion = new ArrayList<>();
        while(item.hasNext()){
            ArrayList<String> str = (ArrayList<String>) item.next();
            answerQuestion.add(String.join(",", str));
        }
        return answerQuestion;
    }
}





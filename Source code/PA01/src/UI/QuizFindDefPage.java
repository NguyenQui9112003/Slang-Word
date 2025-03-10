package UI;
import System.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
public class QuizFindDefPage extends JFrame implements ActionListener{
    private JButton res1, res2, res3, res4;
    private JButton backButton, againButton;
    private JLabel question;
    public static  int resOfQuestion;
    public QuizFindDefPage(){
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

        Random ran = new Random();
        resOfQuestion = ran.nextInt(4);
        System.out.println(resOfQuestion);
        Set<Object> obj = createSlang();
        ArrayList<String> answerList = convertArrayString(obj);

        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        question = new JLabel();
        String x = Main.listOfSlang.getDefinitionString(answerList.get(resOfQuestion));
        question.setText("What is the meaning of " + x + "?");
        question.setBackground(Color.WHITE);

        content.add(question, gbc);

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
        gbc.gridx = 1;
        gbc.gridx = 2;
        quiz.add(backButton);
        backButton.addActionListener(this);

        againButton = new JButton("Play Again");
        gbc.gridx = 0;
        gbc.gridx = 2;
        quiz.add(againButton);
        againButton.addActionListener(this);

        panel.add(content);
        return panel;
    }
    public static void main(String[] args) {
        new QuizFindDefPage();
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
            new QuizFindDefPage();
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
        Random random = new Random();
        resOfQuestion = random.nextInt(4);
        Set<Object> objectAnswer = createSlang();
        ArrayList<String> listAns = convertArrayString(objectAnswer);
        String str = Main.listOfSlang.getDefinitionString(listAns.get(resOfQuestion));
        question.setText("What is the definition of slang " + str + "? ");
        res1.setText(listAns.get(0));
        res2.setText(listAns.get(1));
        res3.setText(listAns.get(2));
        res4.setText(listAns.get(3));
    }
    public Set<Object> createSlang(){
        Set<Object> resultObject = new HashSet<>();
        while(resultObject.size() < 4){
            String slang = Main.listOfSlang.randomSlangWordString();
            resultObject.add(slang);
        }
        return resultObject;
    }
    private Set<Object> createQuestion() {
        Set<Object> resultObject = new HashSet<>();
        while(resultObject.size() < 4){
            String randomAns = Main.listOfSlang.randomSlangWordString();
            resultObject.add(randomAns);
        }
        return resultObject;
    }
    public String createSlang(Set<Object> answerObject) {
        int cnt = 0;
        Iterator<Object> key = answerObject.iterator();
        Random rand = new Random();
        resOfQuestion = rand.nextInt(4);
        String slangWord = null;
        while (cnt < 4 && key.hasNext()) {
            if (resOfQuestion == cnt) {
                ArrayList<String> aaa = (ArrayList<String>) key.next();
                slangWord = Main.listOfSlang.getSlangWord(aaa);
                break;
            }
            ++cnt;
            key.next();
        }
        return slangWord;
    }
    public ArrayList<String> convertArrayString(Set<Object> answerObject){
        Iterator<Object> item = answerObject.iterator();
        ArrayList<String> answerQuestion = new ArrayList<>();
        while(item.hasNext()){
            answerQuestion.add((String)item.next());
        }
        return answerQuestion;
    }
}

package UI;
import System.*;
import ManageFile.*;
import javax.swing.*;
import java.util.List;

public class alertScreen extends JFrame {
    public alertScreen(String slangWord, List<String> definition) {
        String[] btn = {"Duplicate", "Overwrite", "Cancel"};
        int option = JOptionPane.showOptionDialog(null,
                "Slang word " + slangWord + " has been existed!", "Notification", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, btn, 0);
        if(option == 0) {
            Main.listOfSlang.duplicateSlangWord(slangWord, definition);
            FileManager.saveFile();
            JOptionPane.showMessageDialog(null,
                    "Duplicate successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else if (option == 1) {
            Main.listOfSlang.overwriteSlangWord(slangWord, definition);
            FileManager.saveFile();
            JOptionPane.showMessageDialog(null,
                    "Overwrite successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else if(option == 2) {
            dispose();
            new addPage();
        }
    }
}

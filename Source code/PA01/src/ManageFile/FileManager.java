package ManageFile;
import Model.*;
import java.io.*;
import java.util.*;
import System.Main;
//ref: https://github.com/NgocTien0110/Slang-Dictionary/blob/master/src/FileManager.java
public class FileManager {
    public static String RAW_DATA_DIR = "../../slang.txt";
    public static String DATA_DIR = "../../dictionary.txt";
    public static String HISTORY = "../../history.txt";
    private static SlangWordList slangWordList;
    public static SlangWordList loadFile(int choose){
        File file = null;
        if(choose == 1){
            File newFile = new File(DATA_DIR);
            if(newFile.exists()){
                file = newFile;
            }else{
                file = new File(RAW_DATA_DIR);
            }
        } else if (choose == 2){
            file = new File(RAW_DATA_DIR);
        }
        if(file != null && file.exists()){
            SlangWordList listOfSlang = new SlangWordList();
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                String w;
                while((w = br.readLine()) != null){
                    String[] wordAndDef = w.split("`");
                    if (wordAndDef.length == 2){
                        String[] listDef = wordAndDef[1].split("\\|");
                        List<String> def = new ArrayList<>(Arrays.asList(listDef));
                        listOfSlang.addBySlang(new SlangWord(wordAndDef[0], def));
                    }
                }
                br.close();
            }catch (IOException e){
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("File exists");
            return listOfSlang;
        }
        else{
            System.out.println("File not exists");
            return null;
        }
    }
    public static void saveHistory(History historySearch){
        File file = new File(HISTORY);
        BufferedWriter bw = null;
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(historySearch.getTime() + "`" + historySearch.getSlang() + "`" + historySearch.getDef() + "`" + historySearch.getKeyword());
            bw.newLine();
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }finally {
            try{
                assert bw != null;
                bw.close();
            }catch (IOException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    public static void saveFile()
    {
        File file = new File(DATA_DIR);
        Iterator<Map.Entry<String, List<String>>> iterator = Main.listOfSlang.getListOfSlang().entrySet().iterator();
        System.out.println("Save file");
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            while (iterator.hasNext()){
                Map.Entry<String, List<String>> entry = iterator.next();
                String slang = entry.getKey();
                List<String> definition = entry.getValue();
                StringBuilder line = new StringBuilder(slang + "`");
                for(String s : definition){
                    line.append(s).append("|");
                }
                line = new StringBuilder(line.substring(0, line.length() - 1));
                bw.write(line.toString());
                bw.newLine();
            }
            System.out.println("Save file successfully");
            bw.close();
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void loadHistory(){
        Main.historyList = new ArrayList<>();
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(new File("history.txt")));
            String line = "";
            while((line = br.readLine()) != null){
                String[] history = line.split("`");
                History historySearch = new History(history[0], history[1], history[2], history[3]);
                Main.historyList.add(historySearch);
            }
            br.close();
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void resetDictionary() {
        Main.listOfSlang = Main.listRawOfSlang;
        System.out.println("Reset success");
    }
    public static void deletetHistory() throws IOException {
        File file = new File("history.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("");
        System.out.println("Delete history successfully");
    }
}

package Model;
import java.util.*;
public class SlangWordList {
    private HashMap<String, List<String>> listOfSlang;
    public SlangWordList() {
        listOfSlang = new HashMap<>();
    }
    public SlangWordList(HashMap<String, List<String>> listOfSlang) {
        this.listOfSlang = listOfSlang;
    }
    public void setListOfSlang(HashMap<String, List<String>> listOfSlang) {
        this.listOfSlang = listOfSlang;
    }
    public HashMap<String, List<String>> getListOfSlang() {
        return listOfSlang;
    }
    public List<String> searchSlangWord(String slangWord) {
        return this.listOfSlang.get(slangWord);
    }
    public ArrayList<String> searchDefinition(String sWord) {
        ArrayList<String> res = new ArrayList<>();
        sWord = sWord.toLowerCase();
        for (Map.Entry<String, List<String>> entry : listOfSlang.entrySet()) {
            List<String> def = entry.getValue();
            for (String item : def) {
                item = item.toLowerCase();
                if (item.contains(sWord)) {
                    res.add(entry.getKey());
                }
            }
        }
        return (!res.isEmpty()) ? res : null;
    }
    public String getDefinitionString(String slang) {
        StringBuilder res = new StringBuilder();
        List<String> def = this.listOfSlang.get(slang);
        if (def != null) {
            for (String item : def) {
                res.append(item).append(",");
            }
        } else {
            res = new StringBuilder("NOT FOUND");
        }
        return res.toString();
    }
    public String getSlangWordList(ArrayList<String> listSlang) {
        StringBuilder res = new StringBuilder();
        for (String sWord : listSlang) {
            res.append(sWord).append(", ");
        }
        return res.toString();
    }
    public void addBySlangAndDef(String slangWord, List<String> definition) {
        this.listOfSlang.put(slangWord, definition);
    }
    public void addBySlang(SlangWord slangWord) {
        this.listOfSlang.put(slangWord.getsWord(), slangWord.getDef());
    }
    public void overwriteSlangWord(String slang, List<String> definition) {
        this.listOfSlang.replace(slang, definition);
    }
    public void duplicateSlangWord(String slang, List<String> definition) {
        List<String> def = this.listOfSlang.get(slang);
        def.addAll(definition);
        this.listOfSlang.replace(slang, def);
    }
    public void deleteSlangWord(String slang){
        this.listOfSlang.remove(slang);
    }

    // ref: https://github.com/NgocTien0110/Slang-Dictionary/blob/master/src/SlangWordList.java
    public String getSlangWord(ArrayList<String> s){
        Iterator iter = listOfSlang.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry mapElement = (Map.Entry)iter.next();
            String slang = (String) mapElement.getKey();
            List<String> definition = (List<String>) mapElement.getValue();
            if(definition.equals(s)){
                return slang;
            }
        }
        return null;
    }
    public String randomSlangWordString() {
        Random random = new Random();
        String[] keyList = listOfSlang.keySet().toArray(new String[0]);
        return keyList[random.nextInt(listOfSlang.size())];
    }
    public ArrayList<String> getDefinition(String slang){
        return (ArrayList<String>) listOfSlang.get(slang);
    }
}

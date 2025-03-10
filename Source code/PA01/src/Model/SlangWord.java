package Model;
import java.util.*;
public class SlangWord {
    private String sWord;
    private List<String> def;
    public SlangWord(){ sWord = ""; def = null; }
    public SlangWord(String sWord, List<String> definition) {
        this.sWord = sWord;
        this.def = definition;
    }
    public SlangWord (SlangWord slangword) {
        sWord = slangword.sWord;
        def = slangword.def;
    }
    public String getsWord() { return sWord; }
    public List<String> getDef() {return def; }
    public void setsWord(String sWord) { this.sWord = sWord; }
    public void setDef(List<String> def) { this.def = def; }
}

package Model;
public class History {
    private String time, slang, def, keyword;
    History() { time = ""; slang = ""; def = ""; keyword =""; }
    public History(String time, String slang, String def, String keyword) {
        this.time = time;
        this.slang = slang;
        this.def = def;
        this.keyword = keyword;
    }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getSlang() { return slang; }
    public void setSlang(String slang) { this.slang = slang; }
    public String getDef() { return def; }
    public void setDef(String def) { this.def = def; }
    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
}

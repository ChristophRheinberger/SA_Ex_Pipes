package Exercise1B;

/**
 * Created by ClemensB on 28.10.17.
 */
public class Line {
    private StringBuilder sb = new StringBuilder();
    private String line;

    public Line (Line n) {
        this.sb = n.sb;
        this.line = n.toString();
    }

    public Line() {
        this.line = "";
    }

    public void addWord(Word w){
        sb.append(w);
        sb.append(" ");
        line = sb.toString();
    }

    public void setLine(String s) {
        this.line = s;
    }

    public int length(){
        return line.length();
    }

    public Line copy() { return new Line(this); }

    public String toString(){
        return line;
    }
}

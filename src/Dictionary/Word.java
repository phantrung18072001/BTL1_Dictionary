package Dictionary;

public class Word {
    public String word_target;
    public String word_explain;
    public Word() {

    }
    public Word (String target, String explain) {
        this.word_explain = explain;
        this.word_target = target;
    }
    public void display () {
        System.out.println(word_target + "\t|" + word_explain);
    }
}


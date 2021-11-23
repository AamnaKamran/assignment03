public class Word {
    int frequency;
    String word;

    public Word(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public void incFrequency(){
        this.frequency++;
    }
}

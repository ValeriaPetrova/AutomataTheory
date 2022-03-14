package ru.nsu;

public class Concatenation implements Word {
    private Word s1;
    private Word s2;

    public Concatenation(Word w2, Word w1) {
        this.s1 = w1;
        this.s2 = w2;
    }

    public Word getS1() {
        return s1;
    }

    public Word getS2() {
        return s2;
    }

    @Override
    public String toString () {
        String s = "concat(" + s1.toString() + " , " + s2.toString() + ")";
        return s;
    }
}

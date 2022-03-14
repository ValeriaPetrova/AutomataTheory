package ru.nsu;

public class Or implements Word{
    private final Word s1;
    private final Word s2;

    public Or(Word w2, Word w1) {
        this.s1 = w2;
        this.s2 = w1;
    }

    public Word getS1() {
        return s1;
    }

    public Word getS2() {
        return s2;
    }

    @Override
    public String toString () {
        String s = "or(" + s1.toString() + " , " + s2.toString() + ")";
        return s;
    }
}

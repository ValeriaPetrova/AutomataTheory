package ru.nsu;

public class KleeneStar implements Word {
    private Word s;

    public KleeneStar(Word w) {
        this.s = w;
    }

    public Word getS() {
        return s;
    }
    @Override
    public String toString () {
        String str = "kleene(" + s.toString() + ")";
        return str;
    }
}

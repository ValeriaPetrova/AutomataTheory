package ru.nsu;

public class Symbol implements Word {
    private char s;

    public Symbol(char c) {
        this.s = c;
    }

    public char getS() {
        return s;
    }

    @Override
    public String toString () {
        String str = "symbol(" + s + ")";
        return str;
    }
}

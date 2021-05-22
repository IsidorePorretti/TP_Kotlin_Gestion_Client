package fr.epf.min1.gcIsidore;


public class Paire<A,B> {

    private A one;
    private B two;

    public Paire(A one, B two) {
        this.one = one;
        this.two = two;
    }

    public B getTwo() {
        return two;
    }

    public void setOne(A one) {
        this.one = one;
    }

    public static void main(String[] args) {
        Paire<Integer, String> test = new Paire(12, "test");
    }
}
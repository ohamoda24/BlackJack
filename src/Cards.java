import java.util.ArrayList;

public class Cards {
    public String suit;
    public int suitNumber = 0;
    public int value;
    public boolean isAce;
    public int pointValue;
    public String name;
    public boolean aceHandling;


    public Cards(int pSuit, int pValue) {
        suitNumber = pSuit;
        value = pValue;
        if (suitNumber == 0) {
            suit = "hearts";
        }
        if (suitNumber == 1) {
            suit = "spades";
        }
        if (suitNumber == 2) {
            suit = "diamonds";
        }
        if (suitNumber == 3) {
            suit = "clubs";
        }

        name = value + "";
        if (value == 1) {
            pointValue = 11;
            /*if (aceHandling = true) {
                pointValue = 1;
            }*/
            name = "ace";
        }

        if (value == 2) {
            pointValue = 2;
        }

        if (value == 3) {
            pointValue = 3;
        }

        if (value == 4) {
            pointValue = 4;
        }

        if (value == 5) {
            pointValue = 5;
        }

        if (value == 6) {
            pointValue = 6;
        }

        if (value == 7) {
            pointValue = 7;
        }

        if (value == 8) {
            pointValue = 8;
        }

        if (value == 9) {
            pointValue = 9;
        }

        if (value == 10) {
            pointValue = 10;
        }

        if (value == 11) {
            pointValue = 10;
            name = "Jack";
        }
        if (value == 12) {
            pointValue = 10;
            name = "Queen";
        }

        if (value == 13) {
            pointValue = 10;
            name = "King";
        }

    }

    public void printCard() {
        System.out.println(name + " of " + suit);
    }


}

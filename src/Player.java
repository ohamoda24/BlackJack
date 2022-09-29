import java.util.ArrayList;

public class Player {

    public int score = 0;
    public boolean isBust;
    public String name;
    public boolean isBlackJack;
    public ArrayList<Cards> hand = new ArrayList<>();

   public int getScore(){
       return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player() {

        if (isBust) {
System.out.println("YOU LOST LOL");
        }

        if (isBlackJack) {
            System.out.println("OMG YOU WON CONGRATS");
        }



    }

    public void hitOrStand() {

    }

    public void totalScore() {
        score = 0;
        for (int i = 0; i < hand.size(); i++) {
            score += hand.get(i).pointValue;
        }
        if (hand.size() >= 5) {
            score = 21;
            isBlackJack = true;
        }
    }

    public void printHand() {
        for (int i = 0; i < hand.size(); i++) {
            hand.get(i).printCard();
        }
    }

}

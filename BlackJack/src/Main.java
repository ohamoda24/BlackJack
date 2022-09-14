import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;


public class Main implements Runnable, KeyListener {

    final int WIDTH = 100;
    final int HEIGHT = 70;


    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;


    public ArrayList<Cards> deck = new ArrayList<>();
    public Player[] players;
    public Dealer[] dealers;
    public int currentPlayer = 0;
    public boolean blackjack;
    public boolean dealerTurn;


    public static void main(String[] args) {
        Main ex = new Main();
        // new Thread((Runnable) ex).start();
    }


    public Main() {
        //constructor
        setUpGraphics();

        System.out.println("You're about to play blackjack. Get your chips ready!");
        System.out.println("Click the H key to hit, S to hit, R to restart, and A for ace handling (reducing the value of an ace from 11 to 1. This is a strategic opportunity so use it wisely!");

        for (int i = 0; i <= 3; i++) {
            for (int j = 1; j <= 13; j++) {
                deck.add(new Cards(i, j));

            }
        }

        //construct deck

        players = new Player[3];
        // dealers = new Dealer[1];

        for (int i = 0; i < players.length; i++) {
            players[i] = new Player();
        }

        shuffle();

        deal();
    }

    public void shuffle() {
        int randomNumber;
        Cards placeholder;

        for (int i = 0; i < 500; i++) {
            randomNumber = (int) (Math.random() * 52);
            placeholder = deck.get(randomNumber);
            deck.set(randomNumber, deck.get(0));
            deck.set(0, placeholder);
        }


    }

    public void playerScore() {
        for (int i = 0; i < players.length; i++) {
//                for (int c = 0; c < players[i].hand.size(); c++) {

            players[i].totalScore(); // += players[i].hand.get(c).pointValue;
            System.out.println(players[i].score);
//                }
        }
    }

    public void printDeck() {
        for (int i = 0; i <= 51; i++) {
            deck.get(i).printCard();
        }
    }

    public void deal() {
        for (int i = 0; i < 2; i++) { // cards
//
            for (int j = 0; j < players.length; j++) { // players
                if (j < players.length) {
                    players[j].hand.add(deck.get(0));
                    deck.remove(0);
                    players[j].score += players[j].hand.get(i).pointValue;

//                        players[j].hand.get(i).printCard();
                } else {

                }

            }


        } // done dealing

        printHands();

    }

    public void printHands() {
        for (int i = 0; i < players.length; i++) {
            players[i].printHand();
            System.out.println("Player " + i + " score: " + players[i].score);
        }
    }

    public void hitOrStand() {
        System.out.println("Player " + currentPlayer + " hits");

        if (currentPlayer < 2) {
            players[currentPlayer].hand.add(deck.get(0));
            players[currentPlayer].score += deck.get(0).pointValue;
            deck.get(0).printCard();
            deck.remove(0);
            System.out.println("Player " + currentPlayer + " score: " + players[currentPlayer].score);
            players[currentPlayer].totalScore();

            if (players[currentPlayer].score == 21) {
                blackjack = true;
                currentPlayer++;
            }
            if (players[currentPlayer].score > 21) {
                players[currentPlayer].isBust = true;
                blackjack = false;
                System.out.println("YOU LOST!");
            }
            if (players[currentPlayer].isBust) {
                currentPlayer++;
                if (currentPlayer == 2) {
                    dealerTurn = true;
                }
            }
        }

        if (currentPlayer == 2) {
            dealerTurn = true;
            if (players[currentPlayer].isBust) {
                currentPlayer++;
            }

            dealerPlays();

        }

    }



    public void dealerPlays() {
        System.out.println("Dealer:");
        players[2].printHand();
        while (currentPlayer == 2) {
            players[2].score = 0;
            for (int j = 0; j < players[2].hand.size(); j++){
                players[2].score += players[2].hand.get(j).pointValue;
            }
            if (players[2].score < 17) {
                System.out.println("Dealer hits");
                players[2].score += deck.get(0).pointValue;
                players[2].hand.add(deck.get(0));
                players[2].printHand();
            }
            if (players[2].score == 21) {
                players[2].isBlackJack = true;
                System.out.println("Dealer = BLACKJACK, dealer wins! Click r to restart");
                if (currentPlayer == 0){
                    currentPlayer++;
                } else if (currentPlayer == 2){
                    currentPlayer = 0;
                } else {
                    currentPlayer++;
                }
                break;
            }
            if (players[2].score > 21) {
                players[2].isBust = true;
                System.out.println("Dealer lost, the common man will eat today! Click r to restart");
            }
            if (players[2].score < 21 || players[2].score >= 17) {
                System.out.println("Dealer score: " + players[2].score);
                if (players[2].isBust = false) {
                    System.out.println("Dealer stands.");
                }
                currentPlayer = 0;
                for (int i=0; i<2; i++){
                    if (players[currentPlayer].hand.get(i).pointValue == 11) {
                        players[currentPlayer].score -= 10;
                    }
                }

            }
        }
    }

    public void run() {
        while (true) {
            render();
            pause(20);
        }
    }

    public void restart() {
        setUpGraphics();

        for (int i = 0; i <= 3; i++) {
            for (int j = 1; j <= 13; j++) {
                deck.add(new Cards(i, j));

            }
        }

        //construct deck

        players = new Player[3];
        // dealers = new Dealer[1];

        for (int i = 0; i < players.length; i++) {
            players[i] = new Player();
        }


        shuffle();

        deal();
    }

    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.dispose();
        bufferStrategy.show();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode;
        keyCode = e.getKeyCode();


        if (keyCode == KeyEvent.VK_S) {

            System.out.println("Player " + currentPlayer + " stands");


    if (currentPlayer < 2) {
        currentPlayer++;
    } else {
        currentPlayer = 0;
            }

        }
        if (keyCode == KeyEvent.VK_H) {

            hitOrStand();
            if (players[currentPlayer].isBust) {
                currentPlayer++;
            }

            if (currentPlayer > 2) {
                currentPlayer = 0;
            }

        }

        if (keyCode == KeyEvent.VK_R) {
            restart();
        }

        if (keyCode == KeyEvent.VK_A) { //ace handling
            System.out.println("Score before ace handling: " + players[currentPlayer].score);

            for (int i=0; i<2; i++){
                if (players[currentPlayer].hand.get(i).pointValue == 11) {
                    players[currentPlayer].score -= 10;
                }
            }

           /* if (players[currentPlayer].hand.get(0).pointValue == 11) {
                players[currentPlayer].score -= 10;
                //players[currentPlayer].hand.get(0).pointValue = 1;
            }*/

            /*if (players[currentPlayer].hand.get(1).pointValue == 11) {
                players[currentPlayer].score -= 10;
            }*/


           // players[currentPlayer].hand.get(0).aceHandling = true;
         //   players[currentPlayer].hand.get(1).aceHandling = true;

            System.out.println("Score after ace handling: " + players[currentPlayer].score);
        }
    }

    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    private void setUpGraphics() {
        frame = new JFrame("My First Game");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!


        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        canvas.addKeyListener(this);
        System.out.println("DONE graphic setup");


    }

}



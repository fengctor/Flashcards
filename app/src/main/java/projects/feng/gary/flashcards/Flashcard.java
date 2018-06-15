package projects.feng.gary.flashcards;

public class Flashcard {
    private String[] card;
    private int side;

    public Flashcard(String[] card, int side) {
        if (card.length != 2) {
            throw new IllegalArgumentException("Invalid Card");
        }
        if (side != 0 && side != 1) {
            throw new IllegalArgumentException("Invalid Side");
        }
        this.card = card;
        this.side = side;
    }

    public Flashcard(String[] card) {
        this(card, 0);
    }

    public String getFront() {
        return this.card[0];
    }

    public String getBack() {
        return this.card[1];
    }

    public String getFacingUp() {
        return this.card[side];
    }

    public void switchSide() {
        side ^= 1;
    }
}

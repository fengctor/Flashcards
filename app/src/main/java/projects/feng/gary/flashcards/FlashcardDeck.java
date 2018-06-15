package projects.feng.gary.flashcards;

import java.util.ArrayList;

public class FlashcardDeck {
    private ArrayList<Flashcard> flashcards;

    public FlashcardDeck(ArrayList<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }

    public ArrayList<Flashcard> getFlashcards() {
        return flashcards;
    }

}

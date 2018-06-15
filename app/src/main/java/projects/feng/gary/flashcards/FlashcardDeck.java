package projects.feng.gary.flashcards;

import java.util.ArrayList;

public class FlashcardDeck {
    private Flashcard[] flashcards;

    public FlashcardDeck(ArrayList<Flashcard> flashcards) {
        this.flashcards = flashcards.toArray(new Flashcard[flashcards.size()]);
    }

    public Flashcard[] getFlashcards() {
        return flashcards;
    }

    public Flashcard flashcardAt(int position) {
        return this.flashcards[position];
    }

    public int numCards() {
        return flashcards.length;
    }

    // TODO: add shuffle method
}

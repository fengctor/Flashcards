package projects.feng.gary.flashcards;

import java.util.ArrayList;
import java.util.Random;

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

    public void shuffle() {
        Random random = new Random();

        for (int i = 0; i < numCards(); ++i) {
            int swapIndex = random.nextInt(numCards());
            Flashcard temp = flashcards[i];
            flashcards[i] = flashcards[swapIndex];
            flashcards[swapIndex] = temp;

            flashcards[i].setSide(random.nextInt(500) & 1);
        }
    }
}

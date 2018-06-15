package projects.feng.gary.flashcards;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class FlashcardDeckViewModel extends ViewModel {
    private MutableLiveData<FlashcardDeck> mFlashcardDeck = new MutableLiveData<>();

    public FlashcardDeckViewModel() {
    }

    public MutableLiveData<FlashcardDeck> getFlashcardDeck() {
        return mFlashcardDeck;
    }
}

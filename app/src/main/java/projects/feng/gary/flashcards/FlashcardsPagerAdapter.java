package projects.feng.gary.flashcards;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

public class FlashcardsPagerAdapter extends FragmentPagerAdapter {
    public static final String ARG_CARD = "card";
    public static final String ARG_SIDE = "side";

    FlashcardDeck mFlashcardDeck;
    HashMap<Integer, FlashcardFragment> mCardReferenceMap;

    public FlashcardsPagerAdapter(FragmentManager fm, FlashcardDeck flashcardDeck) {
        super(fm);
        mFlashcardDeck = flashcardDeck;
        mFlashcardDeck.shuffle();
        mCardReferenceMap = new HashMap<>();
    }

    @Override
    public Fragment getItem(int position) {
        FlashcardFragment fragment = new FlashcardFragment();
        Bundle args = new Bundle();

        Flashcard flashcard = mFlashcardDeck.flashcardAt(position);
        args.putStringArray(ARG_CARD, flashcard.getCard());
        args.putInt(ARG_SIDE, flashcard.getSide());

        fragment.setArguments(args);

        mCardReferenceMap.put(position, fragment);

        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        mCardReferenceMap.remove(position);
    }

    @Override
    public int getCount() {
        return mFlashcardDeck.numCards();
    }

    public FlashcardFragment getFragment(int key) {
        return mCardReferenceMap.get(key);
    }
}

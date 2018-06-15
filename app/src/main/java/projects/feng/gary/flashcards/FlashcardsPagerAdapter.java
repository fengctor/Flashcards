package projects.feng.gary.flashcards;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

public class FlashcardsPagerAdapter extends FragmentPagerAdapter {
    public static final String ARG_POS = "pos";

    private int mNumCards;
    private HashMap<Integer, FlashcardFragment> mCardReferenceMap;

    public FlashcardsPagerAdapter(FragmentManager fm, int numCards) {
        super(fm);
        mNumCards = numCards;
        mCardReferenceMap = new HashMap<>();
    }

    @Override
    public Fragment getItem(int position) {
        FlashcardFragment fragment = new FlashcardFragment();
        Bundle args = new Bundle();

        args.putInt(ARG_POS, position);

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
        return mNumCards;
    }

    public FlashcardFragment getFragment(int key) {
        return mCardReferenceMap.get(key);
    }
}

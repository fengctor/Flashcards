package projects.feng.gary.flashcards;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FlashcardFragment extends Fragment {
    private Flashcard mFlashcard;
    private TextView mFlashcardDisplay;
    private int mPosition;
    private FlashcardDeckViewModel mViewModel;

    public FlashcardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mPosition = args.getInt(FlashcardsPagerAdapter.ARG_POS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flashcard, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mFlashcardDisplay = view.findViewById(R.id.flashcardDisplay);

        // flip card when it is clicked
        mFlashcardDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard();
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up ViewModel and Observer
        mViewModel = ViewModelProviders.of(getActivity()).get(FlashcardDeckViewModel.class);

        final Observer<FlashcardDeck> storageListObserver = new Observer<FlashcardDeck>() {
            @Override
            public void onChanged(FlashcardDeck flashcardDeck) {
                // update list items shown
                mFlashcard = flashcardDeck.flashcardAt(mPosition);
                updateCardDisplay();
            }
        };

        mViewModel.getFlashcardDeck().observe(this, storageListObserver);
    }

    public void flipCard() {
        FlashcardDeck newDeck = mViewModel.getFlashcardDeck().getValue();
        newDeck.flashcardAt(mPosition).switchSide();
        mViewModel.getFlashcardDeck().setValue(newDeck);
    }

    private void updateCardDisplay() {
        mFlashcardDisplay.setText(mFlashcard.getFacingUp());
    }
}

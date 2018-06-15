package projects.feng.gary.flashcards;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FlashcardFragment extends Fragment {
    private Flashcard mFlashcard;
    private TextView mFlashcardDisplay;

    public FlashcardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        String[] card = args.getStringArray(FlashcardsPagerAdapter.ARG_CARD);
        int side = args.getInt(FlashcardsPagerAdapter.ARG_SIDE);

        mFlashcard = new Flashcard(card, side);
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
        mFlashcardDisplay.setText(mFlashcard.getFacingUp());

        // flip card when it is clicked
        mFlashcardDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard();
            }
        });
    }

    public void flipCard() {
        mFlashcard.switchSide();
        mFlashcardDisplay.setText(mFlashcard.getFacingUp());
    }
}

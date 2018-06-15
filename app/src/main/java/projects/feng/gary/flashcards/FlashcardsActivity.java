


package projects.feng.gary.flashcards;

import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FlashcardsActivity extends AppCompatActivity {
    int mPosition;
    int mNumCards;

    ViewPager mPager;
    FlashcardsPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards);

        // Parse text into a flashcard deck
        try {
            fileToFlashcardDeck(getIntent().getData());
        } catch (IOException e) {
            Log.e("TAG", "no file lol");
        }

        mPosition = 0;

        findViewById(R.id.flipButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.getFragment(mPosition).flipCard();
            }
        });

        // set up ViewPager
        mPager = findViewById(R.id.viewPager);
        mAdapter = new FlashcardsPagerAdapter(getSupportFragmentManager(), mNumCards);
        mPager.setAdapter(mAdapter);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void fileToFlashcardDeck(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        String line;
        ArrayList<Flashcard> flashcards = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String[] flashcard = line.split("\\|");
            flashcards.add(new Flashcard(flashcard));
        }
        inputStream.close();

        FlashcardDeckViewModel viewModel = ViewModelProviders.of(this).get(FlashcardDeckViewModel.class);

        FlashcardDeck flashcardDeck = new FlashcardDeck(flashcards);
        flashcardDeck.shuffle();
        mNumCards = flashcardDeck.numCards();
        viewModel.getFlashcardDeck().setValue(flashcardDeck);
    }


}

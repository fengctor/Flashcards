


package projects.feng.gary.flashcards;

import android.net.Uri;
import android.os.Bundle;
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
    FlashcardDeck mFlashcardDeck;
    TextView mCardDisplay;
    int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards);

        // Parse text into a flashcard deck
        try {
            fileToFlashcardDeck(getIntent().getData());
        } catch (IOException e) {
            Log.e("TAG", "no file nibba");
        }


        /*TextView front = findViewById(R.id.leftText);
        TextView back = findViewById(R.id.rightText);
        for (int i = 0; i < mFlashcardDeck.getFlashcards().size(); ++i) {
            front.append(mFlashcardDeck.getFlashcards().get(i).getFront() + "\n");
            back.append(mFlashcardDeck.getFlashcards().get(i).getBack() + "\n");
        }
        */
        mCardDisplay = findViewById(R.id.cardDisplay);
        mPosition = 0;

        mCardDisplay.setText(mFlashcardDeck.flashcardAt(mPosition).getFacingUp());

        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPosition == mFlashcardDeck.numCards() - 1) {
                    return;
                }

                ++mPosition;
                mCardDisplay.setText(mFlashcardDeck.flashcardAt(mPosition).getFacingUp());
            }
        });

        findViewById(R.id.prevButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPosition == 0) {
                    return;
                }

                --mPosition;
                mCardDisplay.setText(mFlashcardDeck.flashcardAt(mPosition).getFacingUp());
            }
        });

        findViewById(R.id.flipButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFlashcardDeck.flashcardAt(mPosition).switchSide();
                mCardDisplay.setText(mFlashcardDeck.flashcardAt(mPosition).getFacingUp());
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
        mFlashcardDeck = new FlashcardDeck(flashcards);
    }


}





package projects.feng.gary.flashcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FlashcardsActivity extends AppCompatActivity {
    FlashcardDeck mFlashcardDeck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards);

        String path = getIntent().getStringExtra(MainActivity.EXTRA_FILE_PATH);

        ((TextView) findViewById(R.id.testText)).setText(path);

        try {
            FileReader in = new FileReader(path);
        } catch (FileNotFoundException e) {
            Log.e("TAG", "File not found");
        }
    }


}

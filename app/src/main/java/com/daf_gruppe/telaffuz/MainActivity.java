package com.daf_gruppe.telaffuz;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.daf_gruppe.telaffuz.adapter.ListAdapter;
import com.daf_gruppe.telaffuz.dto.Word;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Word> words = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter searchAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    TextView textView;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    String selected;
    private String LOG_TAG = "ANA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        words.add(new Word("Auto das"));
        words.add(new Word("Haus das"));
        words.add(new Word("Familie"));
        words.add(new Word("Vater"));
        words.add(new Word("Mutter"));
        words.add(new Word("Hallo"));
        words.add(new Word("kommen"));
        words.add(new Word("heißen"));
        words.add(new Word("gehen"));
        words.add(new Word("spielen"));
        words.add(new Word("Zahlen"));
        words.add(new Word("wohnen"));
        words.add(new Word("Lehrer"));
        words.add(new Word("Fußball"));
        words.add(new Word("Schule"));
        words.add(new Word("anrufen"));
        words.add(new Word("aufstehen"));
        words.add(new Word("singen"));
        words.add(new Word("lesen"));
        words.add(new Word("tanzen"));
        words.add(new Word("sechs"));
        words.add(new Word("eins"));
        words.add(new Word("zehn"));
        words.add(new Word("elf"));
        words.add(new Word("zwölf"));
        words.add(new Word("drei"));

        mRecyclerView = (RecyclerView) findViewById(R.id.yaprak_recykler);
        textView = (TextView) findViewById(R.id.konusmaMetni);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        searchAdapter = new ListAdapter(this, words);
        mRecyclerView.setAdapter(searchAdapter);
        ((ListAdapter) searchAdapter).setOnItemClickListener(new ListAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Word odev = words.get(position);
                selected = odev.getText();
                promptSpeechInput();

            }
        });
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "KOnusablrsinz");
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        //intent.putExtra(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES,"de-DE");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "de");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "de");
        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, "de");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textView.setText(result.get(0));
                    if (result.get(0).equals(selected)) {
                        Toast.makeText(getApplicationContext(), "Bravooo Yaptin", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }

        }
    }

}

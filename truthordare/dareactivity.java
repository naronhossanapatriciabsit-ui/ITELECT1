package com.example.truthordare;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.content.Intent;

public class DareActivity extends AppCompatActivity {

    String[] dares = {
            "Talk without closing your mouth for 1 minute.",
            "Sing a song loudly!",
            "Dance for 15 seconds!",
            "Say two nice things to the person on your left.",
            "Maintain eye contact with someone for 10 seconds.",
            "Let someone choose a pose and take a picture of you doing it.",
            "Show the group your last saved photo (no explanations).",
            "Let someone choose a person and you must like their latest post.",
            "Reveal the most random thing you saved in your notes.",
            "Use a British accent for the next 3 minutes."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dare);

        TextView dareTask = findViewById(R.id.dareQuestion);
        Button nextDareButton = findViewById(R.id.dareBtn);

        Random random = new Random();
        dareTask.setText(dares[random.nextInt(dares.length)]);

        nextDareButton.setText("DONE");

        nextDareButton.setOnClickListener(v -> {
            Intent intent = new Intent(DareActivity.this, GameActivity.class);
            startActivity(intent);
            finish();
        });
    }
}

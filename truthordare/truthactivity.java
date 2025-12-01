package com.example.truthordare;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.content.Intent;

public class TruthActivity extends AppCompatActivity {

    String[] truths = {
            "What’s one thing you’re still trying to understand about yourself?",
            "Do you think you’re living the life you want, or just going with the flow?",
            "When was the last time you felt truly proud of yourself and why?",
            "What’s a truth about yourself that you think people wouldn’t expect?",
            "Do you easily catch feelings, or does it take a long time?",
            "What’s the biggest pressure you feel at this stage of your life?",
            "What fear holds you back the most in life?",
            "What’s the biggest challenge you’ve overcome so far?",
            "What mindset are you trying to let go of?",
            "What behavior of yours makes you feel guilty afterward?"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truth);

        TextView truthQuestion = findViewById(R.id.truthQuestion);
        Button nextTruthButton = findViewById(R.id.truthBtn);

        Random random = new Random();
        truthQuestion.setText(truths[random.nextInt(truths.length)]);

        nextTruthButton.setText("DONE");

        nextTruthButton.setOnClickListener(v -> {
            Intent intent = new Intent(TruthActivity.this, GameActivity.class);
            startActivity(intent);
            finish();
        });
    }
}

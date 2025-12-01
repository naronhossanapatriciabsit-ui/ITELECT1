package com.example.truthordare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageButton;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Button btnStartBubble, btnTruth, btnDare;
    TextView tvPlayerName;
    ImageButton btnBack;

    DatabaseHelper db;
    ArrayList<String> players = new ArrayList<>();
    Random random = new Random();

    Animation fadeIn;
    Animation fadeOut;
    Handler handler = new Handler();

    boolean animating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btnStartBubble = findViewById(R.id.btnStartBubble);
        btnTruth = findViewById(R.id.btnTruth);
        btnDare = findViewById(R.id.btnDare);
        tvPlayerName = findViewById(R.id.tvPlayerName);

        btnTruth.setEnabled(false);
        btnDare.setEnabled(false);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(GameActivity.this, AddPlayersActivity.class);
            startActivity(intent);
            finish();
        });

        db = new DatabaseHelper(this);
        players = db.getAllPlayers();

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        btnTruth.setOnClickListener(v ->
                startActivity(new Intent(GameActivity.this, TruthActivity.class))
        );

        btnDare.setOnClickListener(v ->
                startActivity(new Intent(GameActivity.this, DareActivity.class))
        );

        btnStartBubble.setOnClickListener(v -> {
            if (animating) return;
            if (players == null || players.isEmpty()) {
                tvPlayerName.setText("No players!");
                return;
            }
            startShuffleAnimation();
        });
    }

    private void startShuffleAnimation() {
        animating = true;
        tvPlayerName.setText("");

        btnTruth.setEnabled(false);
        btnDare.setEnabled(false);

        final int cycles = 10;
        final int delay = 100;

        final int finalIndex = random.nextInt(players.size());

        for (int i = 0; i <= cycles; i++) {
            final int step = i;

            handler.postDelayed(() -> {

                if (step == cycles) {
                    tvPlayerName.setText(players.get(finalIndex));
                } else {
                    tvPlayerName.setText(players.get(random.nextInt(players.size())));
                }

                tvPlayerName.startAnimation(fadeOut);
                tvPlayerName.startAnimation(fadeIn);

                if (step == cycles) {
                    handler.postDelayed(() -> {
                        animating = false;
                        btnTruth.setEnabled(true);
                        btnDare.setEnabled(true);
                    }, 100);
                }

            }, step * delay);
        }
    }
}

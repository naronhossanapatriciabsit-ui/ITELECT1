package com.example.truthordare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddPlayersActivity extends AppCompatActivity {

    EditText etName;
    ImageButton btnAdd;
    Button btnStart;
    ListView listPlayers;

    DatabaseHelper db;
    ArrayList<String> players;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        etName = findViewById(R.id.etName);
        btnAdd = findViewById(R.id.btnAdd);
        btnStart = findViewById(R.id.btnStart);
        listPlayers = findViewById(R.id.listPlayers);

        db = new DatabaseHelper(this);

        players = new ArrayList<>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, players) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView tv = view.findViewById(android.R.id.text1);
                tv.setTypeface(ResourcesCompat.getFont(AddPlayersActivity.this, R.font.fredoka));
                tv.setTextSize(20);
                tv.setTextColor(Color.BLACK);

                return view;
            }
        };

        listPlayers.setAdapter(adapter);

        loadPlayers();

        listPlayers.setOnItemLongClickListener((parent, view, position, id) -> {
            String name = players.get(position);

            db.deletePlayer(name);
            loadPlayers();

            Toast.makeText(AddPlayersActivity.this, "Deleted: " + name, Toast.LENGTH_SHORT).show();

            return true;
        });

        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();

            if (!name.isEmpty()) {
                db.addPlayer(name);
                etName.setText("");
                loadPlayers();
            }
        });

        btnStart.setOnClickListener(v -> {
            Intent i = new Intent(AddPlayersActivity.this, GameActivity.class);
            startActivity(i);
        });
    }

    void loadPlayers() {
        players.clear();
        players.addAll(db.getAllPlayers());
        adapter.notifyDataSetChanged();
    }
}

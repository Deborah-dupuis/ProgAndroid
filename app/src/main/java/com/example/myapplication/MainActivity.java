package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération des éléments du layout
        EditText etNom = findViewById(R.id.etNom);
        EditText etPrenom = findViewById(R.id.etPrenom);
        EditText etAge = findViewById(R.id.etAge);
        RadioGroup rgGenre = findViewById(R.id.rgGenre);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchStress = findViewById(R.id.switchStress);
        Button btnCommencer = findViewById(R.id.btnStart);

        btnCommencer.setOnClickListener(v -> {
            // Vérifier que les champs essentiels sont remplis
            String nom = etNom.getText().toString().trim();
            String prenom = etPrenom.getText().toString().trim();
            String ageStr = etAge.getText().toString().trim();

            if (nom.isEmpty() || prenom.isEmpty() || ageStr.isEmpty() || rgGenre.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Merci de remplir tous les champs 😊", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(ageStr);
            RadioButton rbGenre = findViewById(rgGenre.getCheckedRadioButtonId());
            String genre = rbGenre.getText().toString();
            boolean estStresse = switchStress.isChecked();

            int scoreInitial = 0;

            // Créer un Intent vers l’activité 2
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("score", scoreInitial);
            intent.putExtra("nom", nom);
            intent.putExtra("prenom", prenom);
            intent.putExtra("age", age);
            intent.putExtra("genre", genre);
            intent.putExtra("stresse", estStresse);

            startActivity(intent);
        });
    }
}

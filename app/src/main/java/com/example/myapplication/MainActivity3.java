package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    private int scorePage3 = 0; // Score local de cette activité

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3); // ton XML activité 3

        // Récupération des données depuis activité 2
        Intent intent = getIntent();
        int totalScore = intent.getIntExtra("score", 0);
        String nom = intent.getStringExtra("nom");
        String prenom = intent.getStringExtra("prenom");
        int age = intent.getIntExtra("age", 0);
        String genre = intent.getStringExtra("genre");
        boolean estStresse = intent.getBooleanExtra("stresse", false);

        // Récupération des vues XML
        RadioGroup rgImprevu = findViewById(R.id.rgImprevu);
        RadioGroup rgPlanning = findViewById(R.id.rgPlanning);
        SeekBar seekStressProjet = findViewById(R.id.seekStressProjet);
        Spinner spinnerSangFroid = findViewById(R.id.spinner2);
        Button btnSuivant = findViewById(R.id.btnSuivant4);

        // Remplissage du Spinner avec les options et tags de 0 à 3
        String[] optionsSpinner = {"Oui, presque toujours", "La plupart du temps", "Pas souvent", "Presque jamais"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, optionsSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSangFroid.setAdapter(adapter);

        // Gestion du clic sur le bouton "Suivant"
        btnSuivant.setOnClickListener(v -> {

            // Vérifier que toutes les questions à choix ont une réponse
            if (rgImprevu.getCheckedRadioButtonId() == -1 ||
                    rgPlanning.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Merci de répondre à toutes les questions 😊", Toast.LENGTH_SHORT).show();
                return;
            }

            // Calcul du score local
            scorePage3 = 0;
            scorePage3 += getScoreFromRadioGroup(rgImprevu);
            scorePage3 += getScoreFromRadioGroup(rgPlanning);

            // SeekBar pour stress projet
            scorePage3 += seekStressProjet.getProgress();

            // Ajouter le score du Spinner
            int spinnerScore = spinnerSangFroid.getSelectedItemPosition(); // 0 à 3
            scorePage3 += spinnerScore;

            // Score total cumulé
            int nouveauTotal = totalScore + scorePage3;

            // Envoi des infos vers l’activité 4
            Intent nextIntent = new Intent(MainActivity3.this, MainActivity4.class);
            nextIntent.putExtra("score", nouveauTotal);
            nextIntent.putExtra("nom", nom);
            nextIntent.putExtra("prenom", prenom);
            nextIntent.putExtra("age", age);
            nextIntent.putExtra("genre", genre);
            nextIntent.putExtra("stresse", estStresse);

            startActivity(nextIntent);
        });
    }

    // Méthode utilitaire pour récupérer la valeur d’un RadioGroup
    private int getScoreFromRadioGroup(RadioGroup rg) {
        int selectedId = rg.getCheckedRadioButtonId();
        RadioButton selectedButton = findViewById(selectedId);
        if (selectedButton != null && selectedButton.getTag() != null) {
            try {
                return Integer.parseInt(selectedButton.getTag().toString());
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }
}

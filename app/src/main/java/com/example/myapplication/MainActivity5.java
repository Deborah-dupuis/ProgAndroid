package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

public class MainActivity5 extends AppCompatActivity {

    private int scorePage5 = 0; // Score local de cette activité

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        // Récupération des données depuis activité 4
        Intent intent = getIntent();
        int totalScore = intent.getIntExtra("score", 0);
        String nom = intent.getStringExtra("nom");
        String prenom = intent.getStringExtra("prenom");
        int age = intent.getIntExtra("age", 0);
        String genre = intent.getStringExtra("genre");
        boolean estStresse = intent.getBooleanExtra("stresse", false);

        // Récupération des vues XML
        CheckBox cbMeditation = findViewById(R.id.cbMeditation);
        CheckBox cbMusique = findViewById(R.id.cbMusique);
        CheckBox cbChocolat = findViewById(R.id.cbChocolat);
        CheckBox cbRien = findViewById(R.id.cbRien);

        SeekBar seekRelax = findViewById(R.id.seekRelax);

        Chip chipBain = findViewById(R.id.chipBain);
        Chip chipMusique = findViewById(R.id.chipMusique);
        Chip chipNature = findViewById(R.id.chipNature);
        Chip chipSilence = findViewById(R.id.chipSilence);

        RadioGroup rgSommeil = findViewById(R.id.rgSommeil);

        Button btnNext = findViewById(R.id.btnSuivant5);

        // Gestion du clic sur le bouton "Page suivante"
        btnNext.setOnClickListener(v -> {

            // Vérification : au moins une CheckBox doit être cochée
            if (!cbMeditation.isChecked() && !cbMusique.isChecked() && !cbChocolat.isChecked() && !cbRien.isChecked()) {
                Toast.makeText(this, "Merci de choisir au moins une technique de détente 😄", Toast.LENGTH_SHORT).show();
                return;
            }

            // Calcul du score local
            scorePage5 = 0;

            // CheckBoxes : ajouter points selon le tag
            if (cbMeditation.isChecked()) scorePage5 += Integer.parseInt(cbMeditation.getTag().toString());
            if (cbMusique.isChecked()) scorePage5 += Integer.parseInt(cbMusique.getTag().toString());
            if (cbChocolat.isChecked()) scorePage5 += Integer.parseInt(cbChocolat.getTag().toString());
            if (cbRien.isChecked()) scorePage5 += Integer.parseInt(cbRien.getTag().toString());

            // SeekBar : ajouter directement la valeur
            scorePage5 += seekRelax.getProgress();

            // Chips : on peut attribuer 0 ou 1 point par chip coché
            if (chipBain.isChecked()) scorePage5 += 1;
            if (chipMusique.isChecked()) scorePage5 += 1;
            if (chipNature.isChecked()) scorePage5 += 1;
            if (chipSilence.isChecked()) scorePage5 += 1;

            // RadioGroup sommeil
            scorePage5 += getScoreFromRadioGroup(rgSommeil);

            // Score total cumulé
            int nouveauTotal = totalScore + scorePage5;

            // Envoi des infos vers l’activité finale
            Intent nextIntent = new Intent(MainActivity5.this, MainActivity6.class);
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

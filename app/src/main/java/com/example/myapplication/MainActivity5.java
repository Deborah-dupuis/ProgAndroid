package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity5 extends AppCompatActivity {

    private CheckBox cbMeditation, cbMusique, cbChocolat, cbRien;
    private SeekBar seekRelax;
    private RadioGroup rgSommeil;
    private Button btnNext;

    private int scoreActuel; // score reçu de la page précédente

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        // récupération du score précédent
        scoreActuel = getIntent().getIntExtra("scoreActuel", 0);

        // lien avec les widgets
        cbMeditation = findViewById(R.id.cbMeditation);
        cbMusique = findViewById(R.id.cbMusique);
        cbChocolat = findViewById(R.id.cbChocolat);
        cbRien = findViewById(R.id.cbRien);
        seekRelax = findViewById(R.id.seekRelax);
        rgSommeil = findViewById(R.id.rgSommeil);
        btnNext = findViewById(R.id.btnNext);

        // bouton suivant
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score5 = calculerScore();
                int scoreTotal = scoreActuel + score5;

                Intent intent = new Intent(MainActivity5.this, MainActivity6.class);
                intent.putExtra("scoreTotal", scoreTotal);
                startActivity(intent);
            }
        });
    }

    /**
     * Calcule le score total pour cette activité
     */
    private int calculerScore() {
        int score = 0;

        // chaque checkBox peut valoir 0 à 3 points selon son "stress"
        if (cbMeditation.isChecked()) score += 0;
        if (cbMusique.isChecked()) score += 1;
        if (cbChocolat.isChecked()) score += 2;
        if (cbRien.isChecked()) score += 3;

        // seekBar : de 0 à 10, on ramène à 0–3 environ
        score += seekRelax.getProgress() / 3;

        // ajout du score du RadioGroup sommeil (avec tags dans le XML)
        score += getValeur(rgSommeil);

        return score;
    }

    /**
     * Récupère la valeur du RadioButton sélectionné (depuis le tag)
     */
    private int getValeur(RadioGroup rg) {
        int selectedId = rg.getCheckedRadioButtonId();
        if (selectedId == -1) return 0;

        RadioButton rb = findViewById(selectedId);
        Object tag = rb.getTag();
        if (tag != null) {
            try {
                return Integer.parseInt(tag.toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}

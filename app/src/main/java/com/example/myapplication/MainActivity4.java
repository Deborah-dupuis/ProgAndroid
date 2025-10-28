package com.example.tonapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    // Déclaration des groupes de réponses
    private RadioGroup rgCritique, rgExpression, rgCava, rgEmotionForte;
    private Button btnSuivant4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4); // ton fichier XML

        // Récupération des éléments du layout
        rgCritique = findViewById(R.id.rgCritique);
        rgExpression = findViewById(R.id.rgExpression);
        rgCava = findViewById(R.id.rgCava);
        rgEmotionForte = findViewById(R.id.rgEmotionForte);
        btnSuivant4 = findViewById(R.id.btnSuivant4);

        // Clique sur le bouton "Suivant"
        btnSuivant4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score4 = calculerScore(); // calcul du score de cette page

                // On passe le score à l'activité suivante (MainActivity5)
                Intent intent = new Intent(MainActivity4.this, MainActivity5.class);
                intent.putExtra("scoreActuel", score4);
                startActivity(intent);
            }
        });
    }

    /**
     * Calcule le score total pour cette activité
     */
    private int calculerScore() {
        return getValeur(rgCritique)
                + getValeur(rgExpression)
                + getValeur(rgCava)
                + getValeur(rgEmotionForte);
    }

    /**
     * Récupère la valeur numérique (tag) du RadioButton sélectionné
     */
    private int getValeur(RadioGroup rg) {
        int selectedId = rg.getCheckedRadioButtonId();
        if (selectedId == -1) return 0; // aucune réponse sélectionnée

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

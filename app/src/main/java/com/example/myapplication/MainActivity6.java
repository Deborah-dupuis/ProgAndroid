package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity6 extends AppCompatActivity {
    
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        TextView tvResult = findViewById(R.id.tvResult);
        RatingBar ratingBar = findViewById(R.id.ratingBar);

        //  Récupérer le score total transmis depuis l’activité précédente
        int score = getIntent().getIntExtra("score", 0);

        // Récupérer le prénom de l’utilisateur
        String prenom = getIntent().getStringExtra("prenom");

        // Déterminer la catégorie selon le score
        String resultText;
        if (score <= 5) {
            resultText = "🧘‍♀️ Zen ultime : Tout te glisse dessus, même les catastrophes.";
        } else if (score <= 10) {
            resultText = "😌 Zen moyen : Tu gères la majorité des situations, mais parfois tu stresses.";
        } else if (score <= 15) {
            resultText = "😅 Stressé·e classique : Les imprévus te font trembler un peu, mais tu rebondis.";
        } else {
            resultText = "🌋 Stressé·e chronique : Tu es facilement submergé·e et explosif·ve sous pression.";
        }

        // Afficher le message final avec prénom
        tvResult.setText("Bravo " + prenom + " !" + resultText + "Score total : " + score);

        // Permettre à l’utilisateur de noter le questionnaire
        ratingBar.setOnRatingBarChangeListener((bar, rating, fromUser) -> {
        });
    }
}

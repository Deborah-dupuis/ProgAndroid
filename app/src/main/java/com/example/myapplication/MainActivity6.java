package com.example.myapplication;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        TextView tvResult = findViewById(R.id.tvResult);
        RatingBar ratingBar = findViewById(R.id.ratingBar);

        // 🔹 Récupérer le score transmis depuis l’activité précédente
        int score = getIntent().getIntExtra("score", 0);

        // 🔹 Déterminer la catégorie selon le score
        String resultText;
        if (score <= 5) {
            resultText = "🧘‍♀️ Zen ultime\n\nTout te glisse dessus, même les catastrophes.";
        } else if (score <= 10) {
            resultText = "😌 Zen moyen\n\nTu gères la majorité des situations, mais parfois tu stresses.";
        } else if (score <= 15) {
            resultText = "😅 Stressé·e classique\n\nLes imprévus te font trembler un peu, mais tu rebondis.";
        } else {
            resultText = "🌋 Stressé·e chronique / volcan\n\nTu es facilement submergé·e et explosif·ve sous pression.";
        }

        // 🔹 Afficher la catégorie correspondante
        tvResult.setText("Ton score : " + score + "\n\n" + resultText);

        // 🔹 Optionnel : permettre à l’utilisateur de noter le questionnaire
        ratingBar.setOnRatingBarChangeListener((bar, rating, fromUser) -> {
            // Ici tu peux enregistrer ou afficher la note, par exemple :
            // Toast.makeText(this, "Merci pour ta note : " + rating + " ★", Toast.LENGTH_SHORT).show();
        });
    }
}

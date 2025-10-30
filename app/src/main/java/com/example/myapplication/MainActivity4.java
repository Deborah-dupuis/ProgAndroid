package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    private int scorePage4 = 0; // Score local de cette activité

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // Récupération des données depuis activité 3
        Intent intent = getIntent();
        int totalScore = intent.getIntExtra("score", 0);
        String nom = intent.getStringExtra("nom");
        String prenom = intent.getStringExtra("prenom");
        int age = intent.getIntExtra("age", 0);
        String genre = intent.getStringExtra("genre");
        boolean estStresse = intent.getBooleanExtra("stresse", false);

        // Récupération des vues XML
        RadioGroup rgCritique = findViewById(R.id.rgCritique);
        RadioGroup rgExpression = findViewById(R.id.rgExpression);
        RadioGroup rgCava = findViewById(R.id.rgCava);
        RadioGroup rgEmotionForte = findViewById(R.id.rgEmotionForte);
        Button btnSuivant = findViewById(R.id.btnSuivant4);

        // Gestion du clic sur le bouton "Suivant"
        btnSuivant.setOnClickListener(v -> {

            // Vérifier que toutes les questions ont une réponse
            if (rgCritique.getCheckedRadioButtonId() == -1 ||
                    rgExpression.getCheckedRadioButtonId() == -1 ||
                    rgCava.getCheckedRadioButtonId() == -1 ||
                    rgEmotionForte.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Merci de répondre à toutes les questions 😊", Toast.LENGTH_SHORT).show();
                return;
            }

            // Calcul du score local
            scorePage4 = 0;
            scorePage4 += getScoreFromRadioGroup(rgCritique);
            scorePage4 += getScoreFromRadioGroup(rgExpression);
            scorePage4 += getScoreFromRadioGroup(rgCava);
            scorePage4 += getScoreFromRadioGroup(rgEmotionForte);

            // Score total cumulé
            int nouveauTotal = totalScore + scorePage4;

            // Envoi des infos vers l’activité 5
            Intent nextIntent = new Intent(MainActivity4.this, MainActivity5.class);
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

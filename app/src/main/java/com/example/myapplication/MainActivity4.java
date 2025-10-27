package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        RadioGroup rgCritique = findViewById(R.id.rgCritique);
        Spinner spinnerExpression = findViewById(R.id.spinnerExpression);
        Button btnSuivant = findViewById(R.id.btnSuivant);

        // Options du spinner (deuxième question)
        String[] optionsExpression = new String[] {
                "Oui, très facilement",
                "Oui, mais pas systématiquement",
                "Rarement, j’ai du mal à le faire",
                "Non, je garde tout pour moi"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                optionsExpression);
        spinnerExpression.setAdapter(adapter);

        btnSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Vérifier première question
                int selectedCritiqueId = rgCritique.getCheckedRadioButtonId();
                if (selectedCritiqueId == -1) {
                    Toast.makeText(Main3Activity.this,
                            "Choisis une réponse à la première question",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Calcul score question 1
                int scoreCritique;
                if (selectedCritiqueId == R.id.rbCritique1) {
                    scoreCritique = 0;
                } else if (selectedCritiqueId == R.id.rbCritique2) {
                    scoreCritique = 1;
                } else if (selectedCritiqueId == R.id.rbCritique3) {
                    scoreCritique = 2;
                } else {
                    scoreCritique = 3;
                }

                // Score question 2 selon position du spinner
                int position = spinnerExpression.getSelectedItemPosition();
                int scoreExpression;
                if (position == 0) {
                    scoreExpression = 0;
                } else if (position == 1) {
                    scoreExpression = 1;
                } else if (position == 2) {
                    scoreExpression = 2;
                } else {
                    scoreExpression = 3;
                }

                int totalScore = scoreCritique + scoreExpression;

                Toast.makeText(Main3Activity.this,
                        "Score de cette page : " + totalScore,
                        Toast.LENGTH_LONG).show();

                // TODO: envoyer totalScore vers l'activité suivante si tu veux cumuler.
            }
        });
    }
}

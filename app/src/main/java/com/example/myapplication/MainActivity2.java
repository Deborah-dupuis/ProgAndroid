package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private RadioGroup rgFrequence;
    private Spinner spinnerDeborde, spinnerGestion;
    private SeekBar seekTension;
    private Button btnSuivant;

    private int scoreAct1; // score total reçu de l'activité 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rgFrequence = findViewById(R.id.rgFrequence);
        spinnerDeborde = findViewById(R.id.spinnerDeborde);
        seekTension = findViewById(R.id.seekTension);
        spinnerGestion = findViewById(R.id.spinnerGestion);
        btnSuivant = findViewById(R.id.btnSuivant4);

        //  Récupérer score et infos de l'activité 1
        scoreAct1 = getIntent().getIntExtra("score", 0);

        btnSuivant.setOnClickListener(v -> {
            int scoreAct2 = 0;

            //  RadioGroup fréquence
            int selectedFrequenceId = rgFrequence.getCheckedRadioButtonId();
            if (selectedFrequenceId != -1) {
                RadioButton rb = findViewById(selectedFrequenceId);
                scoreAct2 += Integer.parseInt(rb.getTag().toString());
            }

            //  Spinner débordé (0 à 3)
            if (spinnerDeborde.getSelectedItemPosition() != AdapterView.INVALID_POSITION) {
                scoreAct2 += spinnerDeborde.getSelectedItemPosition();
            }

            //  SeekBar tension
            scoreAct2 += seekTension.getProgress();

            // Spinner gestion stress
            if (spinnerGestion.getSelectedItemPosition() != AdapterView.INVALID_POSITION) {
                scoreAct2 += spinnerGestion.getSelectedItemPosition();
            }

            //  Calcul score total jusqu'à présent
            int totalScore = scoreAct1 + scoreAct2;

            //  Passer à l'activité suivante en transmettant le score
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            intent.putExtra("score", totalScore);

            //  Transmettre aussi les infos utilisateur si besoin
            intent.putExtra("nom", getIntent().getStringExtra("nom"));
            intent.putExtra("prenom", getIntent().getStringExtra("prenom"));
            intent.putExtra("age", getIntent().getIntExtra("age", 0));
            intent.putExtra("genre", getIntent().getStringExtra("genre"));
            intent.putExtra("stresse", getIntent().getBooleanExtra("stresse", false));

            startActivity(intent);
        });
    }
}

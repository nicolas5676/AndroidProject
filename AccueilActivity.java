Ceci est le fichier accueil:

package com.example.nicolasdumas.fiestabayona;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by nicolasdumas on 16/06/2016.
 */
public class AccueilActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);

/* On demande au bouton au moment ou l'on clique dessus de nous faire basculer sur l'activité des chants*/

        final Button accueil = (Button) findViewById(R.id.button);
        accueil.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilActivity.this, MainActivity.class);
                startActivity(intent);

                /* On indique à l'utilisateur qu'il se trouve sur la vue des chants*/

                Toast.makeText(getApplicationContext(), "Voici la liste des Chants des Fêtes!", Toast.LENGTH_LONG).show();

            }
        });




    }
        }


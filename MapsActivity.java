Ceci est le fichier carte : (pas définitif)

package com.example.nicolasdumas.fiestabayona;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by nicolasdumas on 14/06/2016.
 */
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

/* Méthode permettant l'affichage de la carte*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /* Méthode permettant de configurer et de paramètrer la carte*/
    @Override
    public void onMapReady(GoogleMap map) {

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (map != null) {
            map.setTrafficEnabled(true);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            /* Affiche la position courante*/
            map.setMyLocationEnabled(true);

            /* Affiche le trafic routier */
            map.setTrafficEnabled(true);

            /* Zoom par incrément de 1.0 */
            map.animateCamera(CameraUpdateFactory.zoomIn());


            /* Ajout des marqueurs bars et penas*/
           /* class InfoMark {
                public double lat;
                public double longitude;
                public String titre;
                public String adresse;

                public InfoMark(double lat, double longitude, String titre, String adresse) {
                    this.lat = lat;
                    this.longitude = longitude;
                    this.titre = titre;
                    this.adresse = adresse;
                }
            }
                /*private String[] ItemData = new String [25];

                for(int i = 0; i<ItemData.;i++){
                    ItemData[i] = new ItemData[]{

                            new ItemData("43.489834, -1.472573", "Cave Benat", "47 rue Pannecau / Tel : 0559448660"),
                            new ItemData("43.489084, -1.473587", "Pena Mendikoak", "11 rue Pelletier"),
                            new ItemData("43.48876, -1.475708", "Xarneguak et Xuxu del MAR", "11 rue des Basques"),
                            new ItemData("43.489214, -1.473732", "Cercle Taurin Bayonnais", "7 rue Pelletier"),
                            new ItemData("43.489153, -1.472939", "Errobi Kanta", "30 rue des Cordeliers / Tel : 0559598678"),
                            new ItemData("43.489182, -1.473902", "Ontuak", "2 rue des Cordeliers"),
                            new ItemData("43.490448, -1.473656", "Jamon Jamon", "6 rue de Coursic"),
                            new ItemData("43.491372, -1.473504", "Ttipiko Kideak", "5 rue Marsan"),
                            new ItemData("43.49051, -1.473584", "Cacao", "14 rue des Remparts"),
                            new ItemData("43.488729, -1.477073", "Lagunekin", "28 rue Passemillon"),
                            new ItemData("43.488714, -1.47688", "Almadia", "22 rue Passemillon"),
                            new ItemData("43.488786, -1.476454", "Bayonne Plage", "15 rue Lagreou"),
                            new ItemData("43.489064, -1.476215", "Gela Ttiki", "10 rue Gosse"),
                            new ItemData("43.489147, -1.47613", "Betisoak", "12 rue Gosse"),
                            new ItemData("43.488907, -1.475558", "Los Yayayos", "13 rue des Basques"),
                            new ItemData("43.488464, -1.47985", "Amicale St Leon, Tipi Tapa et Bleu Blanc", "Porte d'Espagne"),
                            new ItemData("43.491457, -1.473403", "Pena ASB", "Porte de Mousserolles"),
                            new ItemData("43.49087, -1.469768", "Amicale du petit Bayonne", "Porte d'Espagne"),
                            new ItemData("43.490646, -1.478948", "Pottoroak", "Remparts Lachepaillet / Tel : 0559257987"),
                            new ItemData("43.495128, -1.468804", "Pena Taurine Bayonnaise", "1 rue du Moulin / Tel : 0559502544"),
                            new ItemData("43.489431, -1.480466", "Besteak", "rue Marechal Lautrec"),
                            new ItemData("43.490953, -1.469627", "Baiona Banda", "Porte de Mousserolles / Tel : 0559594852")


                    };
                }

            }*/


            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.489834, -1.472573))
                    .title("Cave Benat")
                    .snippet("47 rue Pannecau / Tel : 0559448660"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.489084, -1.473587))
                    .title("Pena Mendikoak")
                    .snippet("11 rue Pelletier"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.48876, -1.475708))
                    .title("Xarneguak et Xuxu del MAR")
                    .snippet("11 rue des Basques"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.489214, -1.473732))
                    .title("Cercle Taurin Bayonnais")
                    .snippet("7 rue Pelletier"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.489153, -1.472939))
                    .title("Errobi Kanta")
                    .snippet("30 rue des Cordeliers / Tel : 0559598678"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.489182, -1.473902))
                    .title("Ontuak")
                    .snippet("2 rue des Cordeliers"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.490448, -1.473656))
                    .title("Jamon Jamon")
                    .snippet("6 rue de Coursic"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.491372, -1.473504))
                    .title("Ttipiko Kideak")
                    .snippet("5 rue Marsan"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.49051, -1.473584))
                    .title("Cacao")
                    .snippet("14 rue des Remparts"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.488729, -1.477073))
                    .title("Lagunekin")
                    .snippet("28 rue Passemillon"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.488714, -1.47688))
                    .title("Almadia")
                    .snippet("22 rue Passemillon"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.488786, -1.476454))
                    .title("Bayonne Plage")
                    .snippet("15 rue Lagreou"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.489064, -1.476215))
                    .title("Gela Ttiki")
                    .snippet("10 rue Gosse"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.489147, -1.47613))
                    .title("Betisoak")
                    .snippet("12 rue Gosse"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.488907, -1.475558))
                    .title("Los Yayayos")
                    .snippet("13 rue des Basques"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.488464, -1.47985))
                    .title("Amicale St Leon, Tipi Tapa et Bleu Blanc")
                    .snippet("Porte d'Espagne"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.491457, -1.473403))
                    .title("Pena ASB")
                    .snippet("Porte de Mousserolles"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.49087, -1.469768))
                    .title("Amicale du petit Bayonne")
                    .snippet("rue Ravignan"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.490646, -1.478948))
                    .title("Pottoroak")
                    .snippet("Remparts Lachepaillet / Tel : 0559257987"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.495128, -1.468804))
                    .title("Pena Taurine Bayonnaise")
                    .snippet("1 rue du Moulin / Tel : 0559502544"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.489431, -1.480466))
                    .title("Besteak")
                    .snippet("rue Marechal Lautrec"));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(43.490953, -1.469627))
                    .title("Baiona Banda")
                    .snippet("Porte de Mousserolles / Tel : 0559594852"));

        }

    }
/* Méthode de création du menu*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    /* Méthode réagissant quand l'utilisateur clique sur un item du menu*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {


            case R.id.menu_item_2:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Voici la liste des Chants des Fêtes!", Toast.LENGTH_LONG).show();

                return true;

            case R.id.menu_search:
                //open Search();
                return true;

            case R.id.menu_localisation:
                Intent intent_map = new Intent(this, BarActivity.class);
                startActivity(intent_map);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }


    }





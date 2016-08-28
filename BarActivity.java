Voici l

package com.example.nicolasdumas.fiestabayona;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolasdumas.fiestabayona.adapter.ListSpotsAdapter;
import com.example.nicolasdumas.fiestabayona.database.DatabaseHelper2;
import com.example.nicolasdumas.fiestabayona.model.Spots;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by nicolasdumas on 05/06/2016.
 */
public class BarActivity extends MainActivity implements SearchView.OnQueryTextListener {

    private ListView lvspots;
    private ListSpotsAdapter adapter;
    private List<Spots> mSpotsList;
    private DatabaseHelper2 mDBHelper;
    private SearchView searchViewAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_penas);


        lvspots = (ListView) findViewById(R.id.listview_spots);
        mDBHelper = new DatabaseHelper2(this);

        searchViewAction = (SearchView)findViewById(R.id.menu_search);



        /* Vérifie que la DB existe*/
        File database = getApplicationContext().getDatabasePath(DatabaseHelper2.DBNAME);
        if (false == database.exists())

        {
            mDBHelper.getReadableDatabase();
            /* copie DB */
            if (copyDatabase(this)) {
                Toast.makeText(this, "Copie de la DB reussie", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copie de la DB echoue", Toast.LENGTH_SHORT).show();
                return;

            }
        }



        mSpotsList = mDBHelper.getListSpots();

        adapter = new ListSpotsAdapter(this, mSpotsList);

        lvspots.setAdapter(adapter);

        lvspots.setOnItemClickListener(new ItemList());

        lvspots.setTextFilterEnabled(true);

        /* Affichage de la liste sous forme alphabétique*/
        Collections.sort(mSpotsList, new Comparator<Spots>() {
            @Override
            public int compare(Spots u1, Spots u2) {
                return u1.getEnseigne().compareToIgnoreCase(u2.getEnseigne());
            }
        });

    }


    /* Méthode qui indique l'action de cliquer sur un élément de la liste et de renvoyer un certain affichage*/

    class ItemList implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick (AdapterView<?> parent, View view, int position, long id ){
            ViewGroup vg= (ViewGroup)view;
            TextView tvintro = (TextView)vg.findViewById(R.id.tv_spots_intro);
            tvintro.setText(mSpotsList.get(position).getIntro());
            TextView tvdescriptif = (TextView)vg.findViewById(R.id.tv_spots_descriptif);
            tvdescriptif.setText(mSpotsList.get(position).getDescriptif());
            TextView tvadresse = (TextView)vg.findViewById(R.id.tv_spots_adresse);
            tvadresse.setText(mSpotsList.get(position).getAdresse());
            TextView tvtel = (TextView)vg.findViewById(R.id.tv_spots_tel);
            tvtel.setText(mSpotsList.get(position).getTel());
        }
    }

/* Méthode de création de menu*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main_menu, menu);

        final MenuItem item = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {

            /* Méthode appelée lorsque l'item loupe du menu est sélectionné*/
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        adapter.setFilter(mSpotsList);
                        return true;
                    }
            /* Méthode appelée lorsque l'item loupe du menu n'est pas sélectionné*/
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return true;
                    }
                });

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Spots> filteredSpotsList = filter(mSpotsList, newText);
        adapter.setFilter(filteredSpotsList);

        if (TextUtils.isEmpty(newText)){
            lvspots.clearTextFilter();
        }else {
            lvspots.setFilterText(newText.toString());
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private List<Spots> filter(List<Spots> spots, String query) {
        query = query.toLowerCase();

        final List<Spots> filteredSpotsList = new ArrayList<>();
        for (Spots enseigne : spots) {
            final String text = enseigne.getEnseigne().toLowerCase();
            if (text.contains(query)) {
                filteredSpotsList.add(enseigne);
            }

        }
        return filteredSpotsList;
    }

    /* Méthode réagissant lorsque l'utilisateur clique sur un item du menu*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){


            case R.id.menu_item_2:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Voici la liste des Chants des Fêtes!", Toast.LENGTH_LONG).show();

                return true;

            case R.id.menu_search:
                //open Search();
                return true;

            case R.id.menu_localisation:
                Intent intent_map = new Intent(this, MapsActivity.class);
                startActivity(intent_map);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }



    }

    private boolean copyDatabase(Context context) {

        try {

            InputStream inputStream = context.getAssets().open(DatabaseHelper2.DBNAME);
            String outFileName = DatabaseHelper2.DBLOCATION + DatabaseHelper2.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);

            }
            outputStream.flush();
            outputStream.close();
            Log.v("BarActivity", "DB copie");
            return true;


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
}


















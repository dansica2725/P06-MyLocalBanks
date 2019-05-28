package c346.rp.edu.sg.mylocalbanks;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button dbs, ocbc, uob;
    LinearLayout linearLayout;
    boolean dbsB = false, ocbcB= false, uobB= false;

    String chosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbs = findViewById(R.id.dbsBtn);
        ocbc = findViewById(R.id.ocbcBtn);
        uob = findViewById(R.id.uobBtn);

        linearLayout = findViewById(R.id.linearLayout);

        registerForContextMenu(dbs);
        registerForContextMenu(ocbc);
        registerForContextMenu(uob);
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Options");

        int id = v.getId();

        if ( v.getId() == R.id.dbsBtn) {
            chosen = "DBS";
        }
        else if ( v.getId()  == R.id.ocbcBtn) {
            chosen = "OCBC";
        }
        else if (v.getId() == R.id.uobBtn) {
            chosen = "UOB";
        }

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(final  MenuItem item) {

            switch (item.getItemId()) {
                case R.id.websiteCM:
                    chosenWeb(chosen);
                    break;

                case R.id.callCM:
                    chosenCall(chosen);
                    break;
            }

        return super.onContextItemSelected(item);
    }


    //Changing language
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.lang_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.eng:
                dbs.setText(getString(R.string.dbs));
                ocbc.setText(getString(R.string.ocbc));
                uob.setText(getString(R.string.uob));
                break;
            case R.id.chi:
                dbs.setText(getString(R.string.dbsChi));
                ocbc.setText(getString(R.string.ocbcChi));
                uob.setText(getString(R.string.uobChi));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void chosenWeb(String s) {
        if (s.equals("DBS")) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.dbsWeb))));
        } else if (s.equals("OCBC")) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.ocbcWeb))));
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.uobWeb))));
        }
    }

    private void chosenCall(String s) {
        if (s.equals(("DBS"))) {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.dbsNum))));
        } else if (s.equals("OCBC")) {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.ocbcNum))));
        } else {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +  getString(R.string.uobNum))));
        }
    }
}

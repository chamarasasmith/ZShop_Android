package zenonideas.zshop_android;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Admin_Panel1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , Frag_Products.OnFragmentInteractionListener,
        Frag_Accounts.OnFragmentInteractionListener, Frag_Brand.OnFragmentInteractionListener,
        Frag_Category.OnFragmentInteractionListener, Frag_Products_Add.OnFragmentInteractionListener,
        Frag_Products_Update.OnFragmentInteractionListener,Frag_Brand_Add.OnFragmentInteractionListener,
        Frag_Brand_Update.OnFragmentInteractionListener,Frag_Cat_Add.OnFragmentInteractionListener,
        Frag_Cat_Update.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__panel1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Admin Panel");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Frag_Products fpr=new Frag_Products();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_view, fpr).commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin__panel1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fr=null;

        if (id == R.id.nav_pro) {
            fr=new Frag_Products();

        } else if (id == R.id.nav_acc) {
            fr=new Frag_Accounts();
        } else if (id == R.id.nav_brand) {
            fr=new Frag_Brand();
        } else if (id == R.id.nav_cat) {
            fr=new Frag_Category();
        } else if (id == R.id.nav_pro_add) {
            fr=new Frag_Products_Add();
        } else if (id == R.id.nav_pro_update) {
            fr=new Frag_Products_Update();
        }else if (id == R.id.nav_brand_add) {
            fr=new Frag_Brand_Add();
        }else if (id == R.id.nav_brand_update) {
            fr=new Frag_Brand_Update();
        }else if (id == R.id.nav_cat_add) {
            fr=new Frag_Cat_Add();
        }else if (id == R.id.nav_cat_update) {
            fr=new Frag_Cat_Update();
        }

        if (fr!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_view,fr).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

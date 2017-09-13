package trainedge.crawlmine.activity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import trainedge.crawlmine.R;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private EditText etFeedEmail;
    private String m;
    private CardView cardview5;
    private CardView cardview1;
    private CardView cardview4;
    private CardView cardview3;
    private CardView cardview2;
    private EditText etfeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        cardview5 = (CardView) findViewById(R.id.cardview5);

        cardview1 = (CardView) findViewById(R.id.cardview1);
        cardview2 = (CardView) findViewById(R.id.cardview2);
        cardview3 = (CardView) findViewById(R.id.cardview3);
        cardview4 = (CardView) findViewById(R.id.cardview4);


        cardview5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(HomeActivity.this,ResultActivity.class);
                Bundle bundle5=ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.anime1,R.anim.anime2).toBundle();
                startActivity(i,bundle5);
            }
        });

        cardview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(HomeActivity.this,CameraActivity.class);  //CameraActivity
                Bundle bundle4=ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.anime1,R.anim.anime2).toBundle();
                startActivity(i,bundle4);
            }
        });

        cardview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i= new Intent(HomeActivity.this,CameraActivity.class);  //Not Assigned
//                Bundle bundle3=ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.anime1,R.anim.anime2).toBundle();
//                startActivity(i,bundle3);
            }
        });

        cardview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i= new Intent(HomeActivity.this,CameraActivity.class);  //Not Assigned
//                Bundle bundle2=ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.anime1,R.anim.anime2).toBundle();
//                startActivity(i,bundle2);
            }
        });


        cardview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(HomeActivity.this,ViewActivity.class);  //ViewActivity
                Bundle bundle1=ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.anime1,R.anim.anime2).toBundle();
                startActivity(i,bundle1);
            }
        });





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
        getMenuInflater().inflate(R.menu.menu_home,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id=item.getItemId();
        switch (id){
            case R.id.settings:
                return true;

            case R.id.feedback:


                AlertDialog.Builder builder=new AlertDialog.Builder(HomeActivity.this);
                LayoutInflater inflater = HomeActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.feedback_dialog, null);
                etFeedEmail = (EditText)view.findViewById(R.id.etFeedEmail);
                        etfeed=(EditText)view.findViewById(R.id.etfeed);


                builder.setView(view);

                m = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                etFeedEmail.setText(m);

                builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String email = etFeedEmail.getText().toString();
                      String message1 = etfeed.getText().toString();
                        String subject1 = "My Honest Feedback Regarding App";


                        composeEmail( new String[]{ email}, subject1,message1);

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create();
                builder.show();

                break;


            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent1 = new Intent(HomeActivity.this, MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                finish();

                break;



        }
        return super.onOptionsItemSelected(item);



    }


    public void composeEmail(String[] addresses, String subject,String message1) {

        String email = etFeedEmail.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{ email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message1);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_complaint) {

            Intent intent = new Intent(HomeActivity.this, FeedbackActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

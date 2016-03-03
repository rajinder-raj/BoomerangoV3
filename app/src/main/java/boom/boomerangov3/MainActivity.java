package boom.boomerangov3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TabHost;

/*
    Original code from http://sourcey.com/beautiful-android-login-and-signup-screens-with-material-design/
 */
public class MainActivity extends AppCompatActivity {
    private String EMAIL = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createTabs();

        //validates login - raju s.
        Intent intent = new Intent(this, LoginActivity.class);
        int requestCode = 1; // Or some number you choose
        startActivityForResult(intent, requestCode);


    }

    /*
        Author: raju s.
        this allows the activity log in to return the email from the user (not really working - March 2, 2016)
    */
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //TODO; Part 2
        if (requestCode == 1)
            if (data != null) {
                String email = data.getStringExtra("UserLoginEmail");
                EMAIL = email;
            }
        else {
                EMAIL = "failed";
            }
    }

    /*
        author (Raj)
        this class creates the tab names
     */
    private void createTabs() {
        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        //first tab
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Global Pictures");
        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator("Global Pictures");
        tabHost.addTab(tabSpec);
        //second tab
        tabSpec = tabHost.newTabSpec("Favorite Pictures");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator("Favorite Pictures");
        tabHost.addTab(tabSpec);
        //third tab
        tabSpec = tabHost.newTabSpec("My Pictures");
        tabSpec.setContent(R.id.tab3);
        tabSpec.setIndicator("My Pictures");
        tabHost.addTab(tabSpec);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            item.setIntent(new Intent(this, SettingsActivity.class));
            startActivity(item.getIntent());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


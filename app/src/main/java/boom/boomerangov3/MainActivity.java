package boom.boomerangov3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/*
    Original code from http://sourcey.com/beautiful-android-login-and-signup-screens-with-material-design/
 */
public class MainActivity extends AppCompatActivity {
    private String EMAIL = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //validates login - raju s.
        Intent intent = new Intent(this, LoginActivity.class);
        int requestCode = 1; // Or some number you choose
        startActivityForResult(intent, requestCode);


        //TODO:After login - currently working on
        afterLogin();
    }

    /*
        Author: raju s.
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

    public void runSplash()
    {


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

    private void afterLogin() {

        EditText feild = (EditText) findViewById(R.id.userFeild);
        feild.setText(EMAIL);
    }
}


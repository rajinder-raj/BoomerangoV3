package boom.boomerangov3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    Login Method
     */
    public void login(View view) {
        int counter = 3;
        counter--;
        if(counter==0){
            EditText username = (EditText) findViewById(R.id.user);
            EditText password = (EditText) findViewById(R.id.pass);

            if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                Intent i = new Intent(Main.this, LoggedIn.class);
                startActivity(i);
            } else {
                //wrong password
            }
        }
        //close app if counter is cicked out
    }
}

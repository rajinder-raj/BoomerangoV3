package boom.boomerangov3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread loadingTimer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                    Intent splashIntent = new Intent("boom.boomerangov3.SPLASH");
                    startActivity(splashIntent);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                finally {
                    finish();
                }
            }
        };
        loadingTimer.start();
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

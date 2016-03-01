package boom.boomerangov3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Upload extends AppCompatActivity implements View.OnClickListener{
    private static final int RESULT_LOAD_IMAGE = 1;
    private Firebase fbdb;
    ImageView imageToUpload, downloadImage; // holds the bitmap image
    Button bUploadImage, bDownloadImage;
    EditText uploadImageName, downloadImageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        // Firebase initial setup
        Firebase.setAndroidContext(this);
        fbdb = new Firebase("https://boomerango.firebaseio.com/images");

        // Get the images
        imageToUpload = (ImageView) findViewById(R.id.imageToUpload);
        downloadImage = (ImageView) findViewById(R.id.downloadImage);

        // Get buttons
        bUploadImage = (Button) findViewById(R.id.bUploadImage);
        bDownloadImage = (Button) findViewById(R.id.bDownloadImage);

        // Get the image name
        uploadImageName = (EditText) findViewById(R.id.etUploadName);
        downloadImageName = (EditText) findViewById(R.id.etDownloadName);

        // set the listeners fo rthe buttons
        imageToUpload.setOnClickListener(this);
        bUploadImage.setOnClickListener(this);
        bDownloadImage.setOnClickListener(this);
    }

    // Determines what to activate when something is clicked
    @Override
    public void onClick(View v) {
        // switch statement that choses
        switch(v.getId()) {
            case R.id.imageToUpload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.bUploadImage:
                Bitmap image = ((BitmapDrawable) imageToUpload.getDrawable()).getBitmap();
                new UploadImage(image, uploadImageName.getText().toString()).execute();
                break;
            case R.id.bDownloadImage:

                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);
        }
    }

    /*
        Uploads the image on the background to avoid the main application from
        hanging. Note a nested class to pass the behaviour
     */
    private class UploadImage extends AsyncTask<Void, Void, Void> {
        Bitmap image;
        String name;

        // Class to handle the image
        public UploadImage(Bitmap image, String name) {
            this.image = image;
            this.name = name;
        }

        // Take the image and serializes it as a String in the background
        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

            // todo add username for image uid
            Image newImg = new Image("username", encodedImage);

            fbdb.push().setValue(newImg);
            /*
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("image", encodedImage));
            dataToSend.add(new BuildNameValuePair("name", name));
            */

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();

        }
    }

}

package boom.boomerangov3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import java.io.ByteArrayOutputStream;


/**
 * Created by jparsee(john) on 17/02/16.
 */
public class Image {
    private String image_serialized = null;
    private String username = null;
    private ArrayList<LikeDislike> likesDislikes = new ArrayList<LikeDislike>();

    public Image(String username, Bitmap bitmap) {
        image_serialized = serializeBitmap(bitmap);
        this.username = username;
    }

    // For Firebase database; do not remove!
    public Image() {    }

    // get the serialized image
    public String getImage_serialized() {
        return image_serialized;
    }

    public String getUsername() {
        return username;
    }


    public ArrayList<LikeDislike> getLikesDislikes() {
        return likesDislikes;
    }

    /**
     * Take a bitmap image, compress and serializes it
     * @param bitmap - bitmap image
     * @return - serialized bitmap, uncompressed
     */
    public String serializeBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // TODO - comment here
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream); // reduce the size of the image to a jpeg
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT); // encodes it in a string
    }

    /**
     * Get the image back as a bitmap
     * TODO - test if encoding is correct
     * @return - normal bitmap, compressed
     */
    public Bitmap getBitmap() {
        byte[] bitmapByteArray = Base64.decode(image_serialized, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapByteArray, 0, bitmapByteArray.length);
        return bitmap;
    }

    /**
     * Add a individual like/dislike by a user, if not done so already
     * @param email - unique identifier of the user
     * @param like - true for likes, false for dislikes
     */
    public void addLikeDislike(String email, boolean like) {
        if (this.didLikeDisliked(email)) {
            LikeDislike newLikeDislike = new LikeDislike(email, like);
            likesDislikes.add(newLikeDislike);
        }

    }

    /**
     * Find if the user's like/disliked the image
     * @param email - unique identifier of the user
     * @return - true if the user liked/disliked, false if never set
     */
    public boolean didLikeDisliked(String email) {
        for (LikeDislike eachLike: likesDislikes) {
            if (eachLike.getEmail().equals(email))
                return true;
        }
        return false;
    }

    /**
     * Get the total likes of the image
     * @return - total likes
     */
    public int getLikes() {
        int likes = 0;
        for (LikeDislike eachLike: likesDislikes) {
            if (eachLike.isLike())
                likes++;
        }
        return likes;
    }

    /**
     * Generates the total dislikes of the image
     * @return - total dislikes
     */
    public int getDislikes() {
        int dislikes = 0;
        for (LikeDislike eachDislike: likesDislikes) {
            if (!eachDislike.isLike())
                dislikes++;
        }
        return dislikes;
    }

}

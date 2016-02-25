package boom.boomerangov3;

import java.util.ArrayList;

/**
 * Created by jparsee(john) on 17/02/16.
 */
public class Image {
    private String image_serialized = null;
    private String username = null;
    private ArrayList<LikeDislike> likesDislikes = new ArrayList<LikeDislike>();

    public Image(String username, String image_serialized) {
        this.image_serialized = image_serialized;
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

    // Add a like/dislike of the image
    public void addLikeDislike(String email, boolean like) {
        if (this.didLikeDisliked(email)) {
            LikeDislike newLikeDislike = new LikeDislike(email, like);
            likesDislikes.add(newLikeDislike);
        }

    }

    // Find if the user's like/disliked the image
    // todo fix this
    public boolean didLikeDisliked(String email) {
        for (LikeDislike eachLike: likesDislikes) {
            if (eachLike.getEmail().equals(email))
                return true;
        }
        return false;
    }

    // Generates the total likes of the image
    public int getLikes() {
        int likes = 0;
        for (LikeDislike eachLike: likesDislikes) {
            // Check eachLike likes the image
            if (eachLike.isLike())
                likes++;
        }
        return likes;
    }

    // Generates the total dislikes of the image
    public int getDislikes() {
        int dislikes = 0;
        for (LikeDislike eachDislike: likesDislikes) {
            // Check eachLike dislikes the image
            if (!eachDislike.isLike())
                dislikes++;
        }
        return dislikes;
    }

}

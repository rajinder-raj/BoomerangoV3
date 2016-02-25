package boom.boomerangov3;

/**
 * Created by John on 2/24/2016.
 */
public class LikeDislike {
    private String email = null;
    private boolean like = false;

    public LikeDislike(String email, boolean like) {
        this.email = email;
        this.like = like;
    }

    public boolean isLike() {
        return like;
    }

    // Allow for the existing liking user to change their like
    public void setLike(boolean like) {
        this.like = like;
    }

    // Determine who liked this image or to find which
    // image they liked
    public String getEmail() {
        return email;
    }
}

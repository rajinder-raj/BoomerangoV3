package boom.boomerangov3;

/**
 * Created by sidhu on 2016-02-09.
 */
public class UserProfile {

    private String email;
    private String name = "Default";

    public UserProfile() {

    }

    public UserProfile(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

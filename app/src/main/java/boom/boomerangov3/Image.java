package boom.boomerangov3;

/**
 * Created by jparsee on 17/02/16.
 */
public class Image {

    private String image_serialized = null;
    private int img_id = 0;

    public Image(int img_id, String image_serialized) {
        this.image_serialized = image_serialized;
        this.img_id = img_id;
    }

    public Image() {

    }

    public String getImage_serialized() {
        return image_serialized;
    }

    public int getImg_id() {
        return img_id;
    }


}

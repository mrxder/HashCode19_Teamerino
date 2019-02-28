import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Photo {

    public boolean orientation;
    public int noOfTags;
    public ArrayList<String> tags;

    public Photo(boolean orientation, int noOfTags, ArrayList<String> tags) {
        this.orientation = orientation;
        this.noOfTags = noOfTags;
        this.tags = tags;
    }

    public boolean isOrientation() {
        return orientation;
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    public int getNoOfTags() {
        return noOfTags;
    }

    public void setNoOfTags(int noOfTags) {
        this.noOfTags = noOfTags;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return orientation == photo.orientation &&
                noOfTags == photo.noOfTags &&
                Objects.equals(tags, photo.tags);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orientation, noOfTags, tags);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "orientation=" + orientation +
                ", noOfTags=" + noOfTags +
                ", tags=" + tags +
                '}';
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Photo {

    public String id;
    public boolean orientation;
    public int noOfTags;
    public ArrayList<String> tags;

    public Photo(String id, boolean orientation, int noOfTags, ArrayList<String> tags) {
        this.id = id;
        this.orientation = orientation;
        this.noOfTags = noOfTags;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", orientation=" + orientation +
                ", noOfTags=" + noOfTags +
                ", tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return orientation == photo.orientation &&
                noOfTags == photo.noOfTags &&
                Objects.equals(id, photo.id) &&
                Objects.equals(tags, photo.tags);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, orientation, noOfTags, tags);
    }
}

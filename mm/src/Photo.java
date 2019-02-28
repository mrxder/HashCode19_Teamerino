import java.util.*;

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

    public static int computePointsOfTwoPhotos(Photo p1, Photo p2) {

        HashSet<String> p1Tags = new HashSet<>();
        p1Tags.addAll(p1.tags);

        HashSet<String> p2Tags = new HashSet<>();
        p2Tags.addAll(p2.tags);

        HashSet<String> intersaction = new HashSet<>(p1Tags);
        intersaction.retainAll(p2Tags);

        HashSet<String> onlyInP1 = new HashSet<>(p1Tags);
        onlyInP1.removeAll(p2Tags);

        HashSet<String> onlyInP2 = new HashSet<>(p2Tags);
        onlyInP2.removeAll(p1Tags);

        int a = intersaction.size();
        int b = onlyInP1.size();
        int c = onlyInP2.size();

        int min = a;

        if(b<min) {
            min = b;
        }

        if(c<min) {
            min = c;
        }

        return min;

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

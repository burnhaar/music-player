import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;

@Document(collection = "playlists")

public class Playlist implements Comparable<Playlist> {
    // ============ INSTANCE PROPERTIES ============
    @Id
    private String id;
    private String name;
    private boolean isPrivate;
    private ArrayList<String> songIds;

    // ============ CONSTRUCTORS ============
    /**
     * Empty Constructor
     */
    public Playlist() {
    }

    /**
     * Workhorse Constructor
     * 
     * @param id
     * @param name
     * @param isPrivate
     * @param songIds
     */
    public Playlist(String id, String name, boolean isPrivate, ArrayList<String> songIds) {
        this.setId(id);
        this.setName(name);
        this.setPrivate(isPrivate);
        this.setSongIds(songIds);
    }

    // ============ METHODS ============
    /**
     * This method formats the playlist into a neat String
     * 
     * @return formatted String
     */
    @Override
    public String toString() {
        return String.format("Playlist{id='%s', name='%s', isPrivate='%s', songIds='%s'}",
                id,
                name,
                isPrivate,
                songIds != null ? songIds.size() + " songs" : "empty");
    }

    /**
     * This method checks if two playlists are equal to each other
     *
     * @param Playlist you are checking
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Playlist)) {
            return false;
        }

        Playlist p = (Playlist) obj;
        return this.getId().equals(p.id);
    }

    /**
     * This method converts the object to a number
     * 
     * @return the hash value
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * This method checks the relationship between two playlists
     * This is done in order to put them in alphabetical order
     * 
     * @param Playlist you are checking
     * @return location of the playlist being searched
     */
    @Override
    public int compareTo(Playlist other) {
        return this.getName().compareTo(other.name);
    }

    // ============ GETTERS AND SETETRS ============
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public ArrayList<String> getSongIds() {
        return songIds;
    }

    public void setSongIds(ArrayList<String> songIds) {
        this.songIds = songIds;
    }

}

import com.sun.management.DiagnosticCommandMBean;

import java.util.*;

/**
 * Represents a sequence of playables to play in FIFO order.
 */
public class PlayList implements Playable {

    private List<Playable> aList = new LinkedList<>();
    private String aName;
    private Processor aProcessor = new Processor();


    public void clear(){
        this.aList.clear();
    }

    public void redo(){
        this.aProcessor.redo();
    }

    public void undo(){
        this.aProcessor.undo();
    }
    /**
     * Creates a new empty playlist.
     *
     * @param pName
     *            the name of the list
     * @pre pName!=null;
     */
    public PlayList(String pName) {
        assert pName != null;
        aName = pName;
    }

    public List<Playable> getaList() {
        return new ArrayList<>(this.aList);
    }

    /**
     * Adds a playable at the end of this playlist.
     *
     * @param pPlayable
     *            the content to add to the list
     * @pre pPlayable!=null;
     */
    public void addPlayable(Playable pPlayable) {
        assert pPlayable != null;
        Command c = this.createAddCommand(pPlayable);
        this.aProcessor.Do(c);
    }
    private Command createAddCommand(Playable p){
        return new Command()
        {
            @Override
            public void call() {
                assert p != null;
                aList.add(p);
            }

            @Override
            public void undo() {
                aList.remove(aList.size()-1);
            }
        };
    }

    /**
     * remove a playable from the Playlist given its index
     * @param pIndex
     *          the index of playable to be removed
     * @return the removed playable
     */
    public Playable removePlayable(int pIndex) {
        assert pIndex >= 0 && pIndex < aList.size();
        Playable p = aList.get(pIndex);
        Command c = this.createRemoveCommand(pIndex);
        this.aProcessor.Do(c);
        return p;
    }
    private Command createRemoveCommand(int i){
        return new Command()
        {
            Playable p;
            @Override
            public void call() {
                assert i >= 0 && i < aList.size();
                p=aList.remove(i);
            }

            @Override
            public void undo() {
                aList.add(i,p);
            }
        };
    }

    /**
     * @return The name of the playlist.
     */
    public String getName() {
        this.aProcessor.clear();
        return aName;
    }

    /**
     * modify the name of the playlist
     * @param pName
     *          the new name of the playlist
     */
    public void setName(String pName) {
        assert pName != null;
        Command c = this.createSetCommand(pName);
        this.aProcessor.Do(c);
    }
    private Command createSetCommand(String s){
        return new Command()
        {
            String c = aName;
            @Override
            public void call() {
                assert  s!= null;
                aName = s;
            }

            @Override
            public void undo() {
                aName = c;
            }
        };
    }

    /**
     * Iterating through the playlist to play playable content.
     */
    @Override
    public void play() {
        this.aProcessor.clear();
        for(Playable playable:aList){
            playable.play();
        }
    }

    @Override
    public PlayList clone() {
        try {
            PlayList clone = (PlayList) super.clone();
            clone.aList = new ArrayList<>();
            for(Playable p:aList){
                clone.aList.add(p.clone());
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * change the order how playlist play the playables it contains
     */
    public void shuffle() {
        Command c = this.createShuffleCommand();
        this.aProcessor.Do(c);
    }
    private Command createShuffleCommand(){
        return new Command()
        {
            List<Playable> copy = new ArrayList<>(aList);
            @Override
            public void call() {
                Collections.shuffle(aList);
            }

            @Override
            public void undo() {
                aList.clear();
                aList.addAll(copy);
            }
        };
    }

    /**
     * Checks is two playlists are equal based on playable objects and their order
     *
     * @param o
     *            The object to compare a playlist to
     * @return    This method returns true if the playlist is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayList playList = (PlayList) o;
        return this.aList.equals(playList.aList);
    }

    /**
     * Equal playlists have the same hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(aList);
    }

}

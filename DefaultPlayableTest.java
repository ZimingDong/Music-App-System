import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.testng.AssertJUnit.assertEquals;

public class DefaultPlayableTest {
    private Song s1 = new Song("A","1");
    private Song s2 = new Song("B","2");
    private PlayList pl1 = new PlayList("test1");
    private PlayList pl2 = new PlayList("test2");
    private DefaultPlayable d1 = new DefaultPlayable();
    private Podcast pc1 = new Podcast("C","Z");

    @BeforeEach
    public void set() {
        pl1 = new PlayList("test1");
        pl2 = new PlayList("test2");
    }

    @Test
    public void test_null(){
        assertThrows(AssertionError.class,()->d1.setPrototype(null));
    }
    @Test
    public void test_Song(){
        d1.setPrototype(s1);
        Playable p1 =d1.createPlayable();
        assertTrue(p1 instanceof Song);
        assertEquals(((Song) p1).getTitle(),"A");
    }
    @Test
    public void test_Episode(){
        pc1.createAndAddEpisode("D");
        Podcast.Episode e1 = pc1.getEpisode(0);
        d1.setPrototype(e1);
        Playable p2 = d1.createPlayable();
        assertTrue(p2 instanceof Podcast.Episode);
        assertEquals(e1.getTitle(),((Podcast.Episode)p2).getTitle());
    }
    @Test
    public void test_PlayList1(){
        pl1.addPlayable(s1);
        pl1.addPlayable(s2);
        d1.setPrototype(pl1);
        Playable p3 = d1.createPlayable();
        assertTrue(p3 instanceof PlayList);
        assertTrue(pl1.getaList().equals(((PlayList)p3).getaList()));
    }

    @Test
    public void test_PlayList2(){
        pl2.addPlayable(s1);
        pl2.addPlayable(s2);
        d1.setPrototype(pl2);
        Playable p3 = d1.createPlayable();
        pl2.addPlayable(s1);
        assertTrue(p3 instanceof PlayList);
        assertFalse(pl2.getaList().equals(((PlayList)p3).getaList()));
    }

}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.testng.AssertJUnit.assertEquals;

public class PlayListTest {
    private Song s1 = new Song("A", "1");
    private Song s2 = new Song("B", "2");
    private PlayList pl1;
    private PlayList pl2;


    @BeforeEach
    public void set() {
        pl1 = new PlayList("test1");
        pl2 = new PlayList("test2");
    }

    @Test
    public void test_Add_Null() {
        Assertions.assertThrows(AssertionError.class, () -> pl1.addPlayable(null));
    }

    @Test
    public void test_Add() {
        PlayList orcael = new PlayList("test1");
        orcael.addPlayable(s1);
        orcael.addPlayable(s1);
        pl1.addPlayable(s1);
        pl1.addPlayable(s1);
        assertEquals(orcael, pl1);
    }

    @Test
    public void test_Add_redo1() {
        PlayList orcael = new PlayList("test1");
        orcael.addPlayable(s1);
        orcael.addPlayable(s1);
        pl1.addPlayable(s1);
        pl1.redo();
        assertEquals(orcael, pl1);
    }

    @Test
    public void test_Add_redo2() {
        PlayList orcael = new PlayList("test1");
        orcael.addPlayable(s1);
        orcael.addPlayable(s2);
        pl1.addPlayable(s1);
        pl1.addPlayable(s2);
        pl1.undo();
        pl1.redo();
        assertEquals(orcael, pl1);
    }

    @Test
    public void test_add_redo3() {
        PlayList orcael = new PlayList("test1");
        orcael.addPlayable(s1);
        orcael.addPlayable(s2);
        pl1.addPlayable(s1);
        pl1.addPlayable(s2);
        pl1.undo();
        pl1.redo();
        pl1.redo();
        pl1.redo();
        pl1.redo();
        assertEquals(orcael, pl1);
    }

    @Test
    public void test_Add_undo1() {
        PlayList orcael = new PlayList("test2");
        orcael.addPlayable(s1);
        pl2.addPlayable(s1);
        pl2.addPlayable(s2);
        pl2.undo();
        assertEquals(orcael, pl2);
    }

    @Test
    public void test_Add_undo2() {
        PlayList orcael = new PlayList("test2");
        pl2.addPlayable(s1);
        pl2.addPlayable(s2);
        pl2.undo();
        pl2.undo();
        pl2.undo();
        pl2.undo();
        assertEquals(orcael, pl2);
    }

    @Test
    public void test_Remove_InvalidIndex() {
        Assertions.assertThrows(AssertionError.class, () -> pl1.removePlayable(-1));
    }

    @Test
    public void test_Remove_ToomuchRemove() {
        pl1.addPlayable(s1);
        pl1.addPlayable(s2);
        pl1.removePlayable(0);
        pl1.removePlayable(0);
        Assertions.assertThrows(AssertionError.class, () -> pl1.removePlayable(0));
    }

    @Test
    public void test_Remove_redo1() {
        PlayList orcael = new PlayList("test1");
        orcael.addPlayable(s1);
        orcael.addPlayable(s1);
        pl1.addPlayable(s1);
        pl1.addPlayable(s1);
        pl1.addPlayable(s2);
        pl1.addPlayable(s2);
        pl1.removePlayable(2);
        pl1.redo();
        assertEquals(orcael, pl1);
    }

    @Test
    public void test_Remove_redo2() {
        PlayList orcael = new PlayList("test1");
        orcael.addPlayable(s1);
        orcael.addPlayable(s2);
        pl1.addPlayable(s1);
        pl1.addPlayable(s2);
        pl1.addPlayable(s1);
        pl1.addPlayable(s2);
        pl1.removePlayable(2);
        pl1.redo();
        assertEquals(orcael, pl1);
    }

    @Test
    public void test_Remove_redo3() {
        PlayList orcael = new PlayList("test1");
        orcael.addPlayable(s1);
        orcael.addPlayable(s2);
        pl1.addPlayable(s1);
        pl1.addPlayable(s2);
        pl1.addPlayable(s1);
        pl1.removePlayable(2);
        pl1.undo();
        pl1.redo();
        pl1.redo();
        pl1.redo();
        pl1.redo();
        assertEquals(orcael, pl1);
    }

    @Test
    public void test_Remove_redo4() {
        pl1.addPlayable(s1);
        pl1.addPlayable(s2);
        pl1.addPlayable(s1);
        pl1.removePlayable(0);
        pl1.redo();
        pl1.redo();
        Assertions.assertThrows(AssertionError.class, () -> pl1.redo());
    }

    @Test
    public void test_Remove_undo1() {
        PlayList orcael = new PlayList("test2");
        orcael.addPlayable(s1);
        pl2.addPlayable(s1);
        pl2.removePlayable(0);
        pl2.undo();
        assertEquals(orcael, pl2);
    }

    @Test
    public void test_Remove_undo2() {
        PlayList orcael = new PlayList("test2");
        pl2.addPlayable(s1);
        pl2.removePlayable(0);
        pl2.undo();
        pl2.undo();
        pl2.undo();
        pl2.undo();
        assertEquals(orcael, pl2);
    }

    @Test
    public void test_SetName_Null() {
        Assertions.assertThrows(AssertionError.class, () -> pl1.setName(null));
    }

    @Test
    public void test_SetName() {
        String orcael = "test3";
        pl1.setName("test3");
        assertEquals(orcael, pl1.getName());
    }

    @Test
    public void test_SetName_redo1() {
        String orcael = "test3";
        pl1.setName("test3");
        pl1.redo();
        assertEquals(orcael, pl1.getName());
    }

    @Test
    public void test_SetName_redo2() {
        String orcael = "test3";
        pl1.setName("test3");
        pl1.undo();
        pl1.redo();
        pl1.redo();
        assertEquals(orcael, pl1.getName());
    }

    @Test
    public void test_SetName_undo1() {
        String orcael = "test1";
        pl1.setName("test3");
        pl1.undo();
        assertEquals(orcael, pl1.getName());
    }

    @Test
    public void test_SetName_undo2() {
        String orcael = "test1";
        pl1.setName("test3");
        pl1.redo();
        pl1.redo();
        pl1.undo();
        assertEquals(orcael, pl1.getName());
    }

    @Test
    public void test_Shuffle_redo() {
        PlayList orcael = new PlayList("test3");
        orcael.addPlayable(s1);
        orcael.addPlayable(s2);
        pl1.addPlayable(s1);
        pl1.addPlayable(s2);
        while (pl1.equals(orcael)) {
            pl1.shuffle();
            pl1.redo();
        }
        assertEquals(false, pl1.equals(orcael));
    }

    @Test
    public void test_Shuffle_undo() {
        PlayList orcael = new PlayList("test3");
        orcael.addPlayable(s1);
        orcael.addPlayable(s2);
        pl1.addPlayable(s1);
        pl1.addPlayable(s2);
        pl1.shuffle();
        pl1.undo();
        assertEquals(pl1, orcael);
    }

    @Test
    public void test_Redo_NotAfterModify(){
        PlayList orcael = new PlayList("test4");
        orcael.addPlayable(s1);
        pl1.addPlayable(s1);
        pl1.getName();
        pl1.redo();
        assertEquals(pl1,orcael);
    }

    @Test
    public void test_Undo_NotAfterModify(){
        PlayList orcael = new PlayList("test5");
        orcael.addPlayable(s1);
        pl2.addPlayable(s1);
        pl2.getName();
        pl2.undo();
        assertEquals(pl2,orcael);
    }

}







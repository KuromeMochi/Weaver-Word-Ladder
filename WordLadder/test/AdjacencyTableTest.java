import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AdjacencyTableTest {

    @Test
    public void weaver() {
        AdjacencyTable table = AdjacencyTable.weaver();
        assertEquals("easy-east-mast-matt-math", table.getPath("easy", "math"));
    }

    @Test
    public void existsPath() {
        AdjacencyTable table = AdjacencyTable.weaver();
        assertFalse(table.existsPath("easy", "balloon"));
        assertTrue(table.existsPath("easy", "zero"));
        assertTrue(table.existsPath("easy", "math"));
    }

    @Test
    public void pathLength() {
        AdjacencyTable table = AdjacencyTable.weaver();
        assertEquals(5, table.pathLength("easy", "math"));
        assertEquals(1, table.pathLength("easy", "easy"));
        assertEquals(0, table.pathLength("easy", "boohoo"));
    }
}
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.example.ChessFrame;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FrameTest {
    private FrameFixture window=null;

    @BeforeEach
    public void setUp() {
        ChessFrame frame = GuiActionRunner.execute(ChessFrame::new);
        window = new FrameFixture(frame);
        window.show();
    }
    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }
    @Test
    public void shouldOpenAndCheckTitleAndIcon() {
        assertNotEquals(window.target().getTitle(),null);
        assertNotEquals(window.target().getIconImage(),null);
        assertEquals(window.target().getTitle(),"Chess");
    }
}

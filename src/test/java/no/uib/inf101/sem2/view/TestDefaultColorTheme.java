package no.uib.inf101.sem2.view;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.pegSolitare.view.ColorTheme;
import no.uib.inf101.sem2.pegSolitare.view.DefaultColorTheme;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDefaultColorTheme {
    @Test
    public void sanityTestDefaultColorTheme() {
    ColorTheme colors = new DefaultColorTheme();
    assertEquals(null, colors.getBackgroundColor());
    assertEquals(Color.WHITE, colors.getFrameColor());
    assertEquals(Color.WHITE, colors.getCellColor('-'));
    assertEquals(Color.BLUE, colors.getCellColor('B'));
    assertEquals(Color.RED, colors.getCellColor('i'));
    assertThrows(IllegalArgumentException.class, () -> colors.getCellColor('\n'));
    }
}

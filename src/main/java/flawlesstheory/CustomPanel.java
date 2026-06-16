package flawlesstheory;

import javax.swing.*;
import java.awt.*;

public abstract class CustomPanel extends JPanel {

    protected static final int width = 800;
    protected static final int height = 600;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

}

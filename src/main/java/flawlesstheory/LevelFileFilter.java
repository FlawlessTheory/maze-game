package flawlesstheory;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class LevelFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getName().endsWith(".lvl");
    }

    @Override
    public String getDescription() {
        return "*.lvl";
    }

}

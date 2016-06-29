import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUI {
  public static void main(String[] args) {
    JFileChooser fileopen = new JFileChooser();
    FileFilter filter = new FileNameExtensionFilter("owl file", "owl");
    fileopen.addChoosableFileFilter(filter);

    int ret = fileopen.showDialog(null, "Choosing an OWL file to translate");

    if (ret == JFileChooser.APPROVE_OPTION) {
      File file = fileopen.getSelectedFile();
      System.out.println(file);
    }
  }
}

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import StandAlone.CL2FOL;
import modules.OWL2CL;
import modules.OWL2FOL;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI {

	public static void main(String argv[]) {

		// ------------JFrame set up and display------------------
		JFrame frame = new JFrame("Ontology Translator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String displayText = "<html> <b>"
							+ "Making an odd school GUI to match"
							+ "<br>the rest of the Ontology society<br>"
							+ "<br><br><br>"
							+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp "
							+ "Creator: Demoonism</html>";

		JLabel label = new JLabel(displayText, JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setHorizontalTextPosition(JLabel.CENTER);

		frame.getContentPane().add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton StartButton = new JButton("Start");
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				Component source = (Component) actionEvent.getSource();
				Object response = JOptionPane.showInputDialog(source,
						"What ontology language do you like to translate?", "Select an option:",
						JOptionPane.QUESTION_MESSAGE, null,
						new String[] { "OWL => FOL", "OWL => CL", "CL => FOL (beta)" }, "B");
				// System.out.println("Response: " + response.toString());
				String choice = response.toString();

				// ------------File filter---------------
				JFileChooser fileopen = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("owl file", "owl");
				fileopen.addChoosableFileFilter(filter);

				int ret = fileopen.showDialog(null, "Please choose an OWL file to translate");
				File fXmlFile = null;

				if (ret == JFileChooser.APPROVE_OPTION) {
					fXmlFile = fileopen.getSelectedFile();
					
					//Three possible choices:
					
					if (choice.equalsIgnoreCase("OWL => FOL")) {

						OWL2FOL FOL_mapper = new OWL2FOL();
						String output = FOL_mapper.FileGen(fXmlFile);
						
					}
					else if (choice.equalsIgnoreCase("OWL => CL")) {
						OWL2CL CL_mapper = new OWL2CL();
						String output = CL_mapper.FileGen(fXmlFile);
						
					}
					else if (choice.equalsIgnoreCase("CL => FOL (beta)")) {

						CL2FOL mapper = new CL2FOL();
						//String output = mapper.FileGen(fXmlFile);

					}
					
				}
				else{
					
				}



			}
		};

		StartButton.addActionListener(actionListener);
		Container contentPane = frame.getContentPane();
		contentPane.add(StartButton, BorderLayout.SOUTH);
		frame.setSize(450, 450);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
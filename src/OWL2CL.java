import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OWL2CL {

	public OWL2CL() {
		// TODO Auto-generated constructor stub
	}

	public void FileGen(File fXmlFile) {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("owl:Class");

			File module = null;

			module = new File(fXmlFile.getName().replace(".owl", "") + "_CL.txt");
			if (!module.exists()) {
				module.createNewFile();
			}

			FileWriter fw = new FileWriter(module, true);
			BufferedWriter br = new BufferedWriter(fw);

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String HeadNode = eElement.getAttribute("rdf:about").replace("#", "");

					NodeList SubClassList = eElement.getElementsByTagName("rdfs:subClassOf");
					for (int j = 0; j < SubClassList.getLength(); ++j) {

						Element subClass = (Element) SubClassList.item(j);
						String optionText = subClass.getAttribute("rdf:resource").replace("#", "");
						if (optionText.contains(".owl")) {

							optionText = optionText.substring(optionText.indexOf("owl") + 3);
						}
						// br.write("rdfs:subClassOf :"+optionText);
						if (!optionText.isEmpty())

						{
							br.write("(all x \n");
							br.write("    (" + HeadNode + "(x)\n");
							br.write("      -> \n");
							br.write("    " + optionText + "(x))).\n");
							br.write("\n");
						} else {

							String onPropertyText = "";
							String hasValueText = "";
							String someValueText = "";
							String allValueText = "";
							NodeList RestrictionList = subClass.getElementsByTagName("owl:Restriction");
							for (int l = 0; l < RestrictionList.getLength(); ++l) {

								Element Restriction = (Element) RestrictionList.item(l);

								NodeList onPropertyList = subClass.getElementsByTagName("owl:onProperty");

								for (int m = 0; m < onPropertyList.getLength(); ++m) {

									Element onProperty = (Element) onPropertyList.item(m);
									onPropertyText = onProperty.getAttribute("rdf:resource").replace("#", "");
									if (onPropertyText.contains(".owl")) {

										onPropertyText = onPropertyText.substring(onPropertyText.indexOf("owl") + 3);
									}

								}

								NodeList hasValueList = subClass.getElementsByTagName("owl:hasValue");
								NodeList someValueList = subClass.getElementsByTagName("owl:someValuesFrom");
								NodeList allValueList = subClass.getElementsByTagName("owl:allValuesFrom");
								for (int m = 0; m < hasValueList.getLength(); ++m) {

									Element hasValue = (Element) hasValueList.item(m);
									hasValueText = hasValue.getAttribute("rdf:resource").replace("#", "");

									if (hasValueText.contains(".owl")) {

										hasValueText = hasValueText.substring(hasValueText.indexOf("owl") + 3);
									}

								}

								for (int m = 0; m < someValueList.getLength(); ++m) {

									Element someValue = (Element) someValueList.item(m);
									someValueText = someValue.getAttribute("rdf:resource").replace("#", "");
									if (someValueText.contains(".owl"))

									{

										someValueText = someValueText.substring(someValueText.indexOf("owl") + 3);
									}

								}

								for (int m = 0; m < allValueList.getLength(); ++m) {

									Element allValue = (Element) allValueList.item(m);
									allValueText = allValue.getAttribute("rdf:resource").replace("#", "");
									if (allValueText.contains(".owl"))

									{

										allValueText = allValueText.substring(allValueText.indexOf("owl") + 3);
									}

								}

								// ??
								if (!hasValueText.isEmpty()) {
									br.write("(all x \n");
									br.write("      " + HeadNode + "(x)\n");
									br.write("      -> \n");
									br.write("  (and " + onPropertyText + "(x)\n");
									br.write("      (x =\"" + hasValueText + "\"))).\n");
									br.write("\n");
								}

								if (!someValueText.isEmpty()) {
									br.write("(all x \n");
									br.write("      " + HeadNode + "(x)\n");
									br.write("      -> \n");
									br.write("(exists  y\n");
									br.write("      (" + onPropertyText + "(x, y)\n");
									br.write("       &" + someValueText + "(y)))).\n");
									br.write("\n");

								}
								// fix it later

								if (!allValueText.isEmpty()) {
									br.write("(all x \n");
									br.write("      " + HeadNode + "(x)\n");
									br.write("      -> \n");
									br.write("	(all  y\n");
									br.write("		(" + onPropertyText + "(x, y)\n");
									br.write("		-> \n");
									br.write("		" + allValueText + "(y)))).\n");
									br.write("\n");

								}

							}

						}

					}

					NodeList optionList = eElement.getElementsByTagName("owl:equivalentClass");
					for (int k = 0; k < optionList.getLength(); ++k) {

						Element option = (Element) optionList.item(k);

						// String test=
						// "http://sweet.jpl.nasa.gov/2.3/propEnergyFlux.owlRadiativeForcing
						// x";
						// test.replace(target, replacement)

						String optionText = option.getAttribute("rdf:resource").replaceAll("#", "");
						if (optionText.contains(".owl"))

						{

							optionText = optionText.substring(optionText.indexOf("owl") + 3);
						}

						// br.write("rdfs:subClassOf :"+optionText);
						if (!optionText.isEmpty())

						{
							br.write("(all x \n");
							br.write("    (" + HeadNode + "(x)\n");
							br.write("<->\n");
							br.write("    " + optionText + "(x))).\n");
							br.write("\n");

						}

					}

					NodeList disJointList = eElement.getElementsByTagName("owl:disjointWith");
					for (int k = 0; k < disJointList.getLength(); ++k) {

						Element disJoint = (Element) disJointList.item(k);

						// String test=
						// "http://sweet.jpl.nasa.gov/2.3/propEnergyFlux.owlRadiativeForcing
						// x";
						// test.replace(target, replacement)

						String disJointText = disJoint.getAttribute("rdf:resource").replaceAll("#", "");
						if (disJointText.contains(".owl")) {
							disJointText = disJointText.substring(disJointText.indexOf("owl") + 3);
						}
						if (!disJointText.isEmpty()) {
							br.write("(all x \n");
							br.write("(" + HeadNode + "(x)\n");
							br.write(" -> \n");
							br.write(" -" + disJointText + "(x))).\n");
							br.write("\n");

						}

					}

				}

				// br.write("-----------------");
			}

			// ====================object
			// property=====================================

			NodeList ObjectPropertyList = doc.getElementsByTagName("owl:ObjectProperty");

			for (int temp = 0; temp < ObjectPropertyList.getLength(); temp++) {

				Node nNode = ObjectPropertyList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String HeadNode = eElement.getAttribute("rdf:about").replace("#", "");

					NodeList RangeList = eElement.getElementsByTagName("rdfs:range");
					NodeList TypeList = eElement.getElementsByTagName("rdf:type");

					for (int j = 0; j < RangeList.getLength(); ++j) {
						String RangeText = "";
						Element Range = (Element) RangeList.item(j);
						RangeText = Range.getAttribute("rdf:resource").replace("#", "");
						if (RangeText.contains(".owl")) {

							RangeText = RangeText.substring(RangeText.indexOf("owl") + 3);
						}
						// br.write("rdfs:subClassOf :"+optionText);

						if (!RangeText.isEmpty()) {
							br.write("(all x \n");
							br.write("	(exists  y\n");
							br.write("	" + HeadNode + "(x,y)\n");
							br.write(" -> \n");
							br.write("	" + RangeText + "(y))).\n");
							br.write("\n");

						}

					}

					for (int j = 0; j < TypeList.getLength(); ++j) {

						String TypeText = "";
						Element Type = (Element) TypeList.item(j);
						TypeText = Type.getAttribute("rdf:resource").replace("#", "");

						if (TypeText.contains(".owl")) {

							TypeText = TypeText.substring(TypeText.indexOf("owl") + 3);
						}

						if (TypeText.contains("TransitiveProperty")) {
							br.write("(all x all y all z \n");
							br.write("	 (" + HeadNode + "(x, y)\n");
							br.write("	 &" + HeadNode + "(y, z))\n");
							br.write("		-> \n");
							br.write("		" + HeadNode + "(x,z)).\n");
							br.write("\n");

						}

					}

				}

			}

		
		br.close();	
		JOptionPane.showMessageDialog(null,"<html> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Success! <br><br> File:&nbsp "+module.getName()+"</html>");
			    
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}

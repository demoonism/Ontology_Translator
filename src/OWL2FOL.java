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

public class OWL2FOL {

	public OWL2FOL() {

	}
	
	public void FileGen(File fXmlFile){
		    
		try {

  				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
  				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
  				Document doc = dBuilder.parse(fXmlFile);
  			 
  				doc.getDocumentElement().normalize();
  			 
  				//br.write("Root element :" + doc.getDocumentElement().getNodeName());
  			 
  				NodeList nList = doc.getElementsByTagName("owl:Class");
  			
  				File module = null;
  		    	
  		    	module = new File(fXmlFile.getName().replace(".owl", "")+"_FOL.txt");
  		    	if (!module.exists()) {
  		    		module.createNewFile();
  				}
  		    	
  				FileWriter fw = new FileWriter(module,true);
  				BufferedWriter br = new BufferedWriter(fw);
  				
  				for (int temp = 0; temp < nList.getLength(); temp++) {
  			 
  					Node nNode = nList.item(temp);
  					
  					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
  			 
  						Element eElement = (Element) nNode;
  			 
  						String HeadNode =  eElement.getAttribute("rdf:about").replace("#", "");
  						
  						NodeList SubClassList = eElement.getElementsByTagName("rdfs:subClassOf");
  				        for (int j = 0; j < SubClassList.getLength(); ++j)
  				        {
  				        	
  			
  				            Element subClass = (Element) SubClassList.item(j);
  				            String optionText = subClass.getAttribute("rdf:resource").replace("#", "");
  					        if(optionText.contains(".owl"))
  					            	
  				            {
  				            	
  					        	optionText = 	optionText.substring(optionText.indexOf("owl")+3);
  				            }
  				            //br.write("rdfs:subClassOf :"+optionText);
  				    		if(!optionText.isEmpty())
  				    			
  				    		{
  				    			br.write("(forall (x) \n");
  				    			br.write("(if  (" + HeadNode+ " x)\n");
  				    			br.write("     (" + optionText + " x)))\n");
  				    			br.write("\n");
  				    		}
  				    		else
  				    		{
  				    			
  				    			String   onPropertyText="";
  				    			 String   hasValueText="";
  				    			 String   someValueText="";
  				    			NodeList RestrictionList = subClass.getElementsByTagName("owl:Restriction");
  				    			 for (int l = 0; l < RestrictionList.getLength(); ++l)
  				    		        {
  				    		        	
  			//Restriction not used???
  				    		            Element Restriction = (Element) RestrictionList.item(l);
  				    	    			
  				    		            NodeList onPropertyList = subClass.getElementsByTagName("owl:onProperty");
  				    		            
  				   	    			    for (int m = 0; m < onPropertyList.getLength(); ++m)
  				   	    		        {
  				   	    		        	
  			
  				   	    		            Element onProperty = (Element) onPropertyList.item(m);
  				   	    		            onPropertyText = onProperty.getAttribute("rdf:resource").replace("#", "");
  				   	    		            if(onPropertyText.contains(".owl"))
  					    		            	
  					    		            {
  					    		            	
  				   	    		            	onPropertyText = 	onPropertyText.substring(onPropertyText.indexOf("owl")+3);
  					    		            }
  				   	    		            
  			
  				   	    		       
  				   	    		    	}
  				   	    			    
  				   	    			 NodeList hasValueList = subClass.getElementsByTagName("owl:hasValue");
  				    		         NodeList someValueList = subClass.getElementsByTagName("owl:someValue");
  				    		         
  				   	    			    for (int m = 0; m < hasValueList.getLength(); ++m)
  				   	    		        {
  				   	    		        	
  			   	    		            Element hasValue = (Element) hasValueList.item(m);
  			   	    		            hasValueText = hasValue.getAttribute("rdf:resource").replace("#", "");
  			   	    		            if(hasValueText.contains(".owl"))
  				    		            	
  				    		            {
  				    		            	
  			   	    		            	hasValueText = 	hasValueText.substring(hasValueText.indexOf("owl")+3);
  				    		            }
  				   	    		    	}
  				   	    			    
  				   	    			    for (int m = 0; m < someValueList.getLength(); ++m)
  				   	    		        {
  			
  			   	    		           Element someValue = (Element) someValueList.item(m);
  			   	    		        	someValueText = someValue.getAttribute("rdf:resource").replace("#", "");
  			   	    		            if(someValueText.contains(".owl"))
  				    		            	
  				    		            {
  				    		            	
  			   	    		            	someValueText = 	someValueText.substring(someValueText.indexOf("owl")+3);
  				    		            }
  				   	    		    	}
  				   	    			    
  				   	    			    
  				   	    			    
  				    	    		       if(someValueText.isEmpty()){
  				    	   	    		        br.write("(forall (x)\n");
  				    	   		    			br.write("(if  (" + HeadNode+ " x)\n");
  				    	   		    			br.write("     (" + onPropertyText + " x  " +hasValueText+")))\n");
  				    	   		    			br.write("\n");
  				    	    		       }
  				    	   	    		    
  				    	   	    		       if(hasValueText.isEmpty()){
  				    	   	    		        br.write("(forall (x)\n");
  				    	   		    			br.write("(if  (" + HeadNode+ " x)\n");
  				    	   		    			br.write("(exist  y\n");
  				    	   		    			br.write(" and  (" + onPropertyText + " x  y)\n");
  				    	   		    			br.write("      (" + onPropertyText + "    y)))))\n");
  				    	   		    			br.write("\n");
  				    	   		    			
  				    	   	    		       }

  				    		    	}
  				    		        
  				    		}
  			
  				    		
  				    	}
  				        
  				        
  				        
  						NodeList optionList = eElement.getElementsByTagName("owl:equivalentClass");
  				        for (int k = 0; k < optionList.getLength(); ++k)
  				        {
  				        	
  			
  				            Element option = (Element) optionList.item(k);
  				            
  				            //String test=  "http://sweet.jpl.nasa.gov/2.3/propEnergyFlux.owlRadiativeForcing x";
  				            //test.replace(target, replacement)
  				            
  				            
  				            
  				            String optionText = option.getAttribute("rdf:resource").replaceAll("#", "");
  					            if(optionText.contains(".owl"))
  					            	
  				            {
  				            	
  					            	optionText = 	optionText.substring(optionText.indexOf("owl")+3);
  				            }
  				            //br.write("rdfs:subClassOf :"+optionText);
  				    		if(!optionText.isEmpty())
  				    			
  				    		{
  				    			br.write("(forall (x) \n");
  				    			br.write("(iff  (" + HeadNode+ " x)\n");
  				    			br.write("     (" + optionText + " x)))\n");
  				    			br.write("\n");
  				    			
  				    			
  				    		}
  				    		
  				    			}
  						
  				        
  				        
  						NodeList disJointList = eElement.getElementsByTagName("owl:disjointWith");
  				        for (int k = 0; k < disJointList.getLength(); ++k)
  				        {
  				        	
  			
  				            Element option = (Element) disJointList.item(k);
  				            
  				            //String test=  "http://sweet.jpl.nasa.gov/2.3/propEnergyFlux.owlRadiativeForcing x";
  				            //test.replace(target, replacement)
  				            
  				            
  				            
  				            String optionText = option.getAttribute("rdf:resource").replaceAll("#", "");
  				            if(optionText.contains(".owl"))
  				            	
  			            {
  			            	
  				            	optionText = 	optionText.substring(optionText.indexOf("owl")+3);
  			            }
  				            //br.write("rdfs:subClassOf :"+optionText);
  				    		if(!optionText.isEmpty())
  				    			
  				    		{
  				    			br.write("(forall (x)\n");
  				    			br.write("(if  (" + HeadNode+ " x)\n");
  				    			br.write(" not (" + optionText + " x)))\n");
  				    			br.write("\n");
  				    			
  				    			
  				    		}
  				    		
  				    			}
  						
  						
  						
  			//			
  			//			br.write(eElement.getElementsByTagName("rdfs:subClassOf").item(0).getTextContent());
  			//			
  			//			
  				
  						//System.out.print(innerXml(nNode));
  						//br.write("-------hehe----------");
  			//			if(sCurrentLine.contains("<!ENTITY"))
  			//			{
  			//			if(innerXml(nNode).contains("<rdfs:subClassOf rdf:resource="))
  			//			{
  			//				
  			//				
  			//			}
  						
  						
  					}
  					
  					
  			
  				}

  				br.close();	
  				JOptionPane.showMessageDialog(null,"<html> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Success! <br><br> File:&nbsp "+module.getName()+"</html>");
  			    
  			    } catch (Exception e) {
  				e.printStackTrace();
  			    }
		
	}

}

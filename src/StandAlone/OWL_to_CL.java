package StandAlone;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import java.io.File;
 
public class OWL_to_CL{
 
  public static void main(String argv[]) {
 
    try {
 
	File fXmlFile = new File("C:\\Users\\xians\\Desktop\\sweet\\human.owl");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
 
	doc.getDocumentElement().normalize();
 
	//NodeList nList = doc.getElementsByTagName("owl:Class");
	Element eElement = doc.getDocumentElement();
	
	System.out.println(eElement.getAttribute("rdf:about"));
	

 /*
	for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
		
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
 
			String HeadNode =  eElement.getAttribute("rdf:about").replace("#", "");

			//String HeadNode = eElement.getAttributes().getNamedItem("rdf:about").getNodeValue().replace("#", "");		
			//String HeadNode = eElement.getAttributes().getNamedItem("rdf:about").getNodeValue().replace("#", "");
			
//			 NodeList optionList = question.getElementsByTagName("option");
			
			
			NodeList SubClassList = eElement.getElementsByTagName("rdfs:subClassOf");
	        for (int j = 0; j < SubClassList.getLength(); ++j)
	        {
	        	

	            Element subClass = (Element) SubClassList.item(j);
	            String optionText = subClass.getAttribute("rdf:resource").replace("#", "");
	            if(optionText.contains(".owl")) 
	            {
	            	
	            	optionText = 	optionText.substring(optionText.indexOf("owl")+3);
	            }
	            //System.out.println("rdfs:subClassOf :"+optionText);
	    		if(!optionText.isEmpty())
	    			
	    		{
	    			System.out.println("(all x ");
	    			System.out.println("    (" + HeadNode+ "(x)");
	    			System.out.println("      -> ");
	    			System.out.println("    " + optionText + "(x))).");
	    			System.out.println("");
	    		}
	    		else
	    		{
	    			
	    			String   onPropertyText="";
	    			 String   hasValueText="";
	    			 String   someValueText="";
	    			 String   allValueText="";
	    			NodeList RestrictionList = subClass.getElementsByTagName("owl:Restriction");
	    			 for (int l = 0; l < RestrictionList.getLength(); ++l)
	    		        {
	    				 
	    				 
	    				 
	    		        	

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
	    		         NodeList someValueList = subClass.getElementsByTagName("owl:someValuesFrom");
	    		         NodeList allValueList = subClass.getElementsByTagName("owl:allValuesFrom");
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
	   	    			    
	   	    			    
	   	    			    for (int m = 0; m < allValueList.getLength(); ++m)
	   	    		        {

   	    		           Element allValue = (Element) allValueList.item(m);
   	    		           allValueText = allValue.getAttribute("rdf:resource").replace("#", "");
   	    		           		if(allValueText.contains(".owl"))
	    		            	
   	    		           			{
	    		            	
   	    		            	allValueText = 	allValueText.substring(allValueText.indexOf("owl")+3);
   	    		           			}
   	    		        	
	   	    		    	}
	   	    			    
	   	    			    
	   	    			    
	   	    			    
	   	    			    
	   	    //??			    
	    	    		       if(!hasValueText.isEmpty()){
	    	   	    		        System.out.println("(all x ");
	    	   		    			System.out.println("      " + HeadNode+ "(x)");
	    	   		    			System.out.println("      -> ");
	    	   		    			System.out.println("  (and " + onPropertyText + "(x)");
	    	   		    			System.out.println("      (x =\"" +hasValueText+"\"))).");
	    	   		    			System.out.println("");
	    	    		       }
	    	   	    		    
	    	   	    		   
	    	    		       if(!someValueText.isEmpty()){
	    	   	    		        System.out.println("(all x ");
	    	   		    			System.out.println("      " + HeadNode+ "(x)");
	    	   		    			System.out.println("      -> ");
	    	   		    			System.out.println("(exists  y");
	    	   		    			System.out.println("      (" + onPropertyText + "(x, y)");
	    	   		    			System.out.println("       &" + someValueText + "(y)))).");
	    	   		    			System.out.println("");
	    	   		    			
	    	   	    		       }
	    	    		    //fix it later
	    	    		       
	    	    		       if(!allValueText.isEmpty()){
	    	   	    		        System.out.println("(all x ");
	    	   		    			System.out.println("      " + HeadNode+ "(x)");
	    	   		    			System.out.println("      -> ");
	    	   		    			System.out.println("	(all  y");
	    	   		    			System.out.println("		(" + onPropertyText + "(x, y)");
	    	   		    			System.out.println("		-> ");
	    	   		    			System.out.println("		" + allValueText + "(y)))).");
	    	   		    			System.out.println("");
	    	   		    			
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
	            
	            //System.out.println("rdfs:subClassOf :"+optionText);
	    		if(!optionText.isEmpty())
	    			
	    		{
	    			System.out.println("(all x ");
	    			System.out.println("    (" + HeadNode+ "(x)");
	    			System.out.println("<->");
	    			System.out.println("    "+ optionText + "(x))).");
	    			System.out.println("");
	    			
	    			
	    		}
	    		
	    		
	    		
	        }
			
	        
	        
			NodeList disJointList = eElement.getElementsByTagName("owl:disjointWith");
	        for (int k = 0; k < disJointList.getLength(); ++k)
	        {
	        	

	            Element disJoint = (Element) disJointList.item(k);
	            
	            //String test=  "http://sweet.jpl.nasa.gov/2.3/propEnergyFlux.owlRadiativeForcing x";
	            //test.replace(target, replacement)
	            
	            
	            
	            String disJointText = disJoint.getAttribute("rdf:resource").replaceAll("#", "");
		        if(disJointText.contains(".owl"))	
	            {
		        	disJointText = 	disJointText.substring(disJointText.indexOf("owl")+3);
	            }
	    		if(!disJointText.isEmpty())
	    		{
	    			System.out.println("(all x ");
	    			System.out.println("(" + HeadNode+ "(x)");
	    			System.out.println(" -> ");
	    			System.out.println(" -" + disJointText + "(x))).");
	    			System.out.println("");
	    			
	    			
	    		}
	    		
	    	}
			
			
			
			
		}
		
		
		//System.out.println("-----------------");
	}
	
	
	
	//====================object property=====================================
	
	NodeList ObjectPropertyList = doc.getElementsByTagName("owl:ObjectProperty");

	for (int temp = 0; temp < ObjectPropertyList.getLength(); temp++) {
		 
		Node nNode = ObjectPropertyList.item(temp);

		
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
 
			String HeadNode =  eElement.getAttribute("rdf:about").replace("#", "");

			NodeList RangeList = eElement.getElementsByTagName("rdfs:range");
			NodeList TypeList = eElement.getElementsByTagName("rdf:type");
			
			
	        for (int j = 0; j < RangeList.getLength(); ++j)
	        {
	        	 String RangeText = "";
	            Element Range = (Element) RangeList.item(j);
	                    RangeText = Range.getAttribute("rdf:resource").replace("#", "");
		        if(RangeText.contains(".owl"))	
	            {
	            	
		        	RangeText = 	RangeText.substring(RangeText.indexOf("owl")+3);
	            }
	            //System.out.println("rdfs:subClassOf :"+optionText);
	    		
		        
		        
		        if(!RangeText.isEmpty())
	    		{
	    			System.out.println("(all x ");
	    			System.out.println("	(exists  y");
	    			System.out.println("	"+ HeadNode+ "(x,y)");
	      			System.out.println(" -> ");
	    			System.out.println("	"+ RangeText + "(y))).");
	    			System.out.println("");
	    			

	    		}
	    		
	    		
	    		
	        		}

	        
	        
	        
			
	        for (int j = 0; j < TypeList.getLength(); ++j)
	        {

	        	String TypeText ="";
	            Element Type = (Element) TypeList.item(j);
	            TypeText = Type.getAttribute("rdf:resource").replace("#", "");
		        
	            if(TypeText.contains(".owl"))	
	            {
	            	
		        	TypeText = 	TypeText.substring(TypeText.indexOf("owl")+3);
	            }

		        if(TypeText.contains("TransitiveProperty"))
	    		{
	    			System.out.println("(all x all y all z ");
	    			System.out.println("	 ("+ HeadNode +"(x, y)");
	    			System.out.println("	 &"+ HeadNode +"(y, z))");
	    			System.out.println("		-> ");
	    			System.out.println("		"+ HeadNode + "(x,z)).");
	    			System.out.println("");

	    		}
	    		
	        		}
	        

	        		
	        
	        
	        
						}
		
							}
	
	*/
	
	
	

    } catch (Exception e) {
	e.printStackTrace();
    }
  }
  
  
  
  public static String innerXml(Node node) {
	    DOMImplementationLS lsImpl = (DOMImplementationLS)node.getOwnerDocument().getImplementation().getFeature("LS", "3.0");
	    LSSerializer lsSerializer = lsImpl.createLSSerializer();
	    NodeList childNodes = node.getChildNodes();
	    lsSerializer.getDomConfig().setParameter("xml-declaration", false);
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < childNodes.getLength(); i++) {
	       sb.append(lsSerializer.writeToString(childNodes.item(i)));
	    }
	    return sb.toString(); 
	}
  
  
  
  
  
  
  
  
  
  
  
  
  
 
}
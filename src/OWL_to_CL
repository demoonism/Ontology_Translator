import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import java.io.File;
 
public class Translation_CL{
 
  public static void main(String argv[]) {
 
    try {
 
	File fXmlFile = new File("W:\\A2.owl");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
 
	doc.getDocumentElement().normalize();
 
	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 
	NodeList nList = doc.getElementsByTagName("owl:Class");

 
	for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);

		
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
 
			String HeadNode =  eElement.getAttribute("rdf:about").replace("#", "");

			
			
			NodeList SubClassList = eElement.getElementsByTagName("rdfs:subClassOf");
	        for (int j = 0; j < SubClassList.getLength(); ++j)
	        {

	            Element subClass = (Element) SubClassList.item(j);
	            String subClassText = subClass.getAttribute("rdf:resource").replace("#", "");
		        if(subClassText.contains(".owl"))	
	            {
	            	
		        	subClassText = 	subClassText.substring(subClassText.indexOf("owl")+3);
	            }
	            //System.out.println("rdfs:subClassOf :"+optionText);
	    		if(!subClassText.isEmpty())
	    		{
	    			System.out.println("(forall (x) ");
	    			System.out.println("(if  (" + HeadNode+ " x )");
	    			System.out.println("     (" + subClassText + " x )))");
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
	    	    			
	    		            NodeList onPropertyList = Restriction.getElementsByTagName("owl:onProperty");
	    		            
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
	   	    			    
	   	    			    
	   	    			    
	    	    		       if(!hasValueText.isEmpty()){
	    	   	    		        System.out.println("(forall (x) ");
	    	   		    			System.out.println("(if (" + HeadNode+ " x )");
	    	   		    			System.out.println("	(" + onPropertyText + " x  " +hasValueText+")))");
	    	   		    			System.out.println("");
	    	    		       }
	   
	    	    		       else if(!someValueText.isEmpty()){
	    	   	    		        System.out.println("(forall (x) ");
	    	   		    			System.out.println("	(if  (" + HeadNode+ " x )");
	    	   		    			System.out.println("		(exist  (y)");
	    	   		    			System.out.println("		(if (" + onPropertyText + " x  y)");
	    	   		    			System.out.println("		(" + someValueText + "    y)))))");
	    	   		    			System.out.println("");
	    	   	    		   }
	    	    		       
	    	    		       else if(!allValueText.isEmpty()){
	    	   	    		        System.out.println("(forall (x) ");
	    	   		    			System.out.println("	(if  (" + HeadNode+ " x )");
	    	   		    			System.out.println("		(forall  (y)");
	    	   		    			System.out.println("		(if (" + onPropertyText + " x  y)");
	    	   		    			System.out.println("		    ("+ allValueText + "    y)))))");
	    	   		    			System.out.println("");
	    	   	    		   }
	    		   	
	    		   	
	   	    		        


	    		    		
	    		    	}
	    		        
	    			
	    			
	    			
	    			
	    			
	    		}

	    		
	    	}
	        
	        
	        //Equivalent Clause
	        
	        
			NodeList equivalentList = eElement.getElementsByTagName("owl:equivalentClass");
	        for (int k = 0; k < equivalentList.getLength(); ++k)
	        {
	        	

	            Element equivalent = (Element) equivalentList.item(k);
	            
	            
	            
	            
	            String equivalentText = equivalent.getAttribute("rdf:resource").replaceAll("#", "");
		         if(equivalentText.contains(".owl"))
		            	
	            {
	            	
		            	equivalentText = 	equivalentText.substring(equivalentText.indexOf("owl")+3);
	            }
	            //System.out.println("rdfs:subClassOf :"+optionText);
	    		if(!equivalentText.isEmpty())
	    			
	    		{
	    			System.out.println("(forall (x) ");
	    			System.out.println("(iff  (" + HeadNode+ " x )");
	    			System.out.println("     (" + equivalentText + " x )))");
	    			System.out.println("");
	    			
	    			
	    		}
	    		
	    		
	    		
	    		 String   DescriptionText="";
	    	
	    		
	    		//===
	    		
	    		NodeList owlList = equivalent.getElementsByTagName("owl:Class");
		        for (int o = 0; o < owlList.getLength(); ++o)
		        {
		        	

		           
		        	Element owl = (Element) owlList.item(o);
		        	
		            NodeList unionList = owl.getElementsByTagName("owl:unionOf");
			        for (int p = 0; p < unionList.getLength(); ++p)
			        {
			            Element union  = (Element) unionList.item(p);
			            
			            NodeList DescriptionList = union.getElementsByTagName("rdf:Description");
   	    			    for (int m = 0; m < DescriptionList.getLength(); ++m)
   	    		        {

   	    			    	
   	    			    	
	    		           Element Description = (Element) DescriptionList.item(m);
	    		        	DescriptionText = Description.getAttribute("rdf:about").replace("#", "");
	    		        		if(DescriptionText.contains(".owl"))
	    		            	{
	    		        			
	    		        			DescriptionText = 	DescriptionText.substring(DescriptionText.indexOf("owl")+3);
	    		            	}
	    		            
   	    		    	}
   	    			    
 	    		      if(!DescriptionText.isEmpty()){
	   	    		        System.out.println("(forall (x) ");
	   		    			System.out.println("	(iff  (" + HeadNode+ " x )");
	   		    			System.out.println("		(or (" + DescriptionText + " x)");
	   		    			System.out.println("		    ("+ DescriptionText + " x)))))");
	   		    			System.out.println("");
	   	    		   }
			          
		
			    		
			    		
			    	}
		          
	
		    		
		       
		    	
		        
	    		}
	    		
	    		//====
	    		
	    	}
			
	        //Disjoint clause
	        
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
	            //System.out.println("rdfs:subClassOf :"+optionText);
	    		if(!optionText.isEmpty())
	    			
	    		{
	    			System.out.println("(forall (x) ");
	    			System.out.println("(not(and  (" + HeadNode+ " x )");
	    			System.out.println("          (" + optionText + " x ))))");
	    			System.out.println("");
	    			
	    			
	    		}
	    		
	    			}
			
			
		}
		
	}
	
	
	
	
	
	// Object property==========================================================================================
	
	NodeList ObjectPropertyList = doc.getElementsByTagName("owl:ObjectProperty");

	for (int temp = 0; temp < ObjectPropertyList.getLength(); temp++) {
		 
		Node nNode = ObjectPropertyList.item(temp);

		
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
 
			String HeadNode =  eElement.getAttribute("rdf:about").replace("#", "");

			NodeList RangeList = eElement.getElementsByTagName("rdfs:range");
			NodeList TypeList = eElement.getElementsByTagName("rdf:type");
			NodeList DomainList = eElement.getElementsByTagName("rdfs:domain");
			
			
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
		        
		        for (int k = 0; k < DomainList.getLength(); ++k)
		        {
		        	 String DomainText = "";
		            Element Domain = (Element) DomainList.item(k);
		            DomainText = Domain.getAttribute("rdf:resource").replace("#", "");
			        if(DomainText.contains(".owl"))	
		            {
		            	
			        	DomainText = 	DomainText.substring(RangeText.indexOf("owl")+3);
		            }
		        
		        
		        if(!RangeText.isEmpty())
	    		{
	    			System.out.println("(forall (x) ");
	    			System.out.println("	(exists  (y)");
	    			System.out.println("		(if("+ HeadNode+ " x y)");
	    			System.out.println("		   ("+ RangeText + " y))))");
	    			System.out.println("");
	    			

	    		}
	    		
	    		
	    		
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
	    			System.out.println("(forall (x y z) ");
	    			System.out.println("	(if(and	("+ HeadNode +" x y)");
	    			System.out.println("		("+ HeadNode +" y z))");	    			
	    			System.out.println("			("+ HeadNode + " x z))");
	    			System.out.println("");

	    		}
	    		
	        		}
	        

	        		
	        
	        
	        
						}
		
							}
		
	
	
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
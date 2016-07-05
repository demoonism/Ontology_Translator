package modules;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Translate a .clif file to .in format

public class CL_to_FOL {

	public static void main(String[] args) throws IOException {
	
		File folder = new File("F:\\CL");
		File[] listOfFiles = folder.listFiles();
		Search(listOfFiles);

	}
	
	

	public static void Search(File[] listOfFiles) {
		

		BufferedReader br = null;
		String  sCurrentLine;
		String clean = "";
		String  nextLine;
		for (File ontoFile : listOfFiles) {
		    if (ontoFile.isFile()) {  
		
				try {

					br = new BufferedReader(new FileReader(ontoFile.getPath()));
					
					  String condition ="";
		        	  String operator ="";
		        	  String triple ="";
				      String equation = "";
				      String Empty = "";
				      String LeftOver = "";
				      String Inequality = "";
				      String exist = "";
				      String second = "";
				      String iff = "";
				      
				      ArrayList<String> axioms = new ArrayList<String>();
				      
					while ((sCurrentLine = br.readLine()) != null) {
	

				
						     
						   
					          if (!(sCurrentLine.isEmpty())&&!(sCurrentLine.contains("cl-"))&&!(sCurrentLine.equals(")"))) {
			
					        
					        		if(sCurrentLine.contains(")))")){
					        		//	sCurrentLine = nextLine;
					        			if((nextLine =br.readLine()).isEmpty()){
					        				
					        			
					        				
//					        			if(sCurrentLine.contains("(not")){
//						        			  Matcher InequalityMatcher = Pattern.compile("not\\s\\(").matcher(sCurrentLine);
//												if(InequalityMatcher.find())
//													Inequality = sCurrentLine.substring(InequalityMatcher.end()).replace(")", "");
//												String[] relation = Inequality.split("\\s+");
//												if(relation.length == 2)
//													axioms.addln(" -> -"+relation[0]+"("+relation[1]+"))).");
//												else if(relation.length == 3)
//														axioms.addln(" -> -"+relation[0]+"("+relation[1]+","+relation[2]+"))).");
//					        			}
					        			 if(sCurrentLine.contains(")))))))")){
					        				 
					        					
													if(sCurrentLine.contains("(not"))
													{
														Matcher Equalmatcher = Pattern.compile("not[ \\t]\\(").matcher(sCurrentLine);
														if(Equalmatcher.find())
															equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
															String[] relation = equation.split("\\s+");

															if(relation.length == 2)
															{	axioms.add(operator);
																axioms.add("-");
																axioms.add(relation[0]);
																axioms.add("(");
																axioms.add(relation[1]);
															}
															else if(relation.length == 3)
															{
																axioms.add(operator+"(-"+relation[0]+"("+relation[1]+","+relation[2]);
															}
																
													}
													else if(sCurrentLine.contains("(="))
													{
														Matcher Equalmatcher = Pattern.compile("\\(=\\s").matcher(sCurrentLine);
														if(Equalmatcher.find())
															equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
															String[] relation = equation.split("\\s+");
																
																axioms.add(operator+"("+relation[0]+" = "+relation[1]);
														
													}
													else{
														Matcher Equalmatcher = Pattern.compile("\\(").matcher(sCurrentLine);
														if(Equalmatcher.find())
															equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
															String[] relation = equation.split("\\s+");

															if(relation.length == 2)
																axioms.add(operator+relation[0]+"("+relation[1]);
															else if(relation.length == 3)
																axioms.add(operator+relation[0]+"("+relation[1]+","+relation[2]);
													}
													
													 boolean flag = true;
														for(int i = 0; i<axioms.size();i++)
														{
															if((axioms.get(i).contains("->")||axioms.get(i).contains("<->"))&&flag)
															{
																System.out.print(axioms.get(i));
																flag = false;
															}
															else if(!axioms.get(i).contains("->")){
															System.out.print(axioms.get(i));
															}

														}	
														int left = Collections.frequency(axioms, "(");
														int right = Collections.frequency(axioms, ")");
														for(int j=0; j<left-right+1;j++)
															System.out.print(")");
															System.out.print(".\n");
														
													axioms.clear();
													operator = "";
					        			}
					        			else if(sCurrentLine.contains("))))))")){

											if(sCurrentLine.contains("(not"))
											{
												Matcher Equalmatcher = Pattern.compile("not[ \\t]\\(").matcher(sCurrentLine);
												if(Equalmatcher.find())
													equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
													String[] relation = equation.split("\\s+");

													if(relation.length == 2)
														axioms.add(operator+"-"+relation[0]+"("+relation[1]);
													else if(relation.length == 3)
														axioms.add(operator+"-"+relation[0]+"("+relation[1]+","+relation[2]);
											}
											else if(sCurrentLine.contains("(="))
											{
												Matcher Equalmatcher = Pattern.compile("\\(=\\s").matcher(sCurrentLine);
												if(Equalmatcher.find())
													equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
													String[] relation = equation.split("\\s+");
														
														axioms.add(operator+"("+relation[0]+" = "+relation[1]);
												
											}
											else{
												Matcher Equalmatcher = Pattern.compile("\\(").matcher(sCurrentLine);
												if(Equalmatcher.find())
													equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
													String[] relation = equation.split("\\s+");

													if(relation.length == 2)
														axioms.add(operator+relation[0]+"("+relation[1]);
													else if(relation.length == 3)
														axioms.add(operator+relation[0]+"("+relation[1]+","+relation[2]);
											}
											
											
											 boolean flag = true;
												for(int i = 0; i<axioms.size();i++)
												{
														if((axioms.get(i).contains("->")||axioms.get(i).contains("<->"))&&flag)
													{
															System.out.print(axioms.get(i));
														flag = false;
													}
													else if(!axioms.get(i).contains("->")){
													System.out.print(axioms.get(i));
													}

												}	
												int left = Collections.frequency(axioms, "(");
												int right = Collections.frequency(axioms, ")");
												for(int j=0; j<left-right+1;j++)
													System.out.print(")");
													System.out.print(".\n");
												
												
											axioms.clear();
											operator = "";
					        			
					        			}
					        			else if(sCurrentLine.contains(")))))")){

											if(sCurrentLine.contains("(not"))
											{
												Matcher Equalmatcher = Pattern.compile("not[ \\t]\\(").matcher(sCurrentLine);
												if(Equalmatcher.find())
													equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
													String[] relation = equation.split("\\s+");

													if(relation.length == 2)
														axioms.add(operator+"-"+relation[0]+"("+relation[1]);
													else if(relation.length == 3)
														axioms.add(operator+"-"+relation[0]+"("+relation[1]+","+relation[2]);
											}
											else if(sCurrentLine.contains("(="))
											{
												Matcher Equalmatcher = Pattern.compile("\\(=\\s").matcher(sCurrentLine);
												if(Equalmatcher.find())
													equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
													String[] relation = equation.split("\\s+");
														
														axioms.add(operator+"("+relation[0]+" = "+relation[1]);
												
											}
											else{
												Matcher Equalmatcher = Pattern.compile("\\(").matcher(sCurrentLine);
												if(Equalmatcher.find())
													equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
													String[] relation = equation.split("\\s+");

													if(relation.length == 2)
														axioms.add(operator+relation[0]+"("+relation[1]);
													else if(relation.length == 3)
														axioms.add(operator+relation[0]+"("+relation[1]+","+relation[2]);
											}

											
											 boolean flag = true;
												for(int i = 0; i<axioms.size();i++)
												{
														if((axioms.get(i).contains("->")||axioms.get(i).contains("<->"))&&flag)
													{
															System.out.print(axioms.get(i));
														flag = false;
													}
													else if(!axioms.get(i).contains("->")){
													System.out.print(axioms.get(i));
													}

												}	
												int left = Collections.frequency(axioms, "(");
												int right = Collections.frequency(axioms, ")");
												for(int j=0; j<left-right+1;j++)
													System.out.print(")");
													System.out.print(".\n");
													
											axioms.clear();
											operator = "";	
					        			}
					        			
					        			else if(sCurrentLine.contains("))))")){

											if(sCurrentLine.contains("(not"))
											{
												if(sCurrentLine.contains("(=")){
													Matcher Equalmatcher = Pattern.compile("\\(=\\s").matcher(sCurrentLine);
													if(Equalmatcher.find())
														equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
														String[] relation = equation.split("\\s+");
															
															axioms.add(operator+"("+relation[0]+"! = "+relation[1]);
												}
												else{
												Matcher Equalmatcher = Pattern.compile("not[ \\t]\\(").matcher(sCurrentLine);
												if(Equalmatcher.find())
													equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
													String[] relation = equation.split("\\s+");

													if(relation.length == 2)
														axioms.add(operator+"-"+relation[0]+"("+relation[1]);
													else if(relation.length == 3)
														axioms.add(operator+"-"+relation[0]+"("+relation[1]+","+relation[2]);
												}
												
											}
											else if(sCurrentLine.contains("(="))
											{
												Matcher Equalmatcher = Pattern.compile("\\(=\\s").matcher(sCurrentLine);
												if(Equalmatcher.find())
													equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
													String[] relation = equation.split("\\s+");
														
														axioms.add(operator+"("+relation[0]+" = "+relation[1]);
												
											}
											else{
												Matcher Equalmatcher = Pattern.compile("\\(").matcher(sCurrentLine);
												if(Equalmatcher.find())
													equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
													String[] relation = equation.split("\\s+");

													if(relation.length == 2)
														axioms.add(operator+relation[0]+"("+relation[1]);
													else if(relation.length == 3)
														axioms.add(operator+relation[0]+"("+relation[1]+","+relation[2]);
											}

											
											 boolean flag = true;
												for(int i = 0; i<axioms.size();i++)
												{
														if((axioms.get(i).contains("->")||axioms.get(i).contains("<->"))&&flag)
													{
														System.out.print(axioms.get(i));
														flag = false;
													}
													else if(!axioms.get(i).contains("->")){
													System.out.print(axioms.get(i));
													}
	
												}	
												int left = Collections.frequency(axioms, "(");
												int right = Collections.frequency(axioms, ")");
												for(int j=0; j<left-right+1;j++)
													System.out.print(")");
													System.out.print(".\n");
												
											axioms.clear();
											operator = "";	
					        			}
					        		
					        			else if(sCurrentLine.contains(")))")){

											if(sCurrentLine.contains("(not"))
											{

												if(sCurrentLine.contains("(=")){
													Matcher Equalmatcher = Pattern.compile("\\(=\\s").matcher(sCurrentLine);
													if(Equalmatcher.find())
														equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
														String[] relation = equation.split("\\s+");
															
															axioms.add(operator+"("+relation[0]+"! = "+relation[1]);
															
												}
												else{
													
													Matcher Equalmatcher = Pattern.compile("not[ \\t]\\(").matcher(sCurrentLine);
													if(Equalmatcher.find())
														equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
														String[] relation = equation.split("\\s+");

														if(relation.length == 2)
															axioms.add(operator+"-"+relation[0]+"("+relation[1]);
														else if(relation.length == 3)
															axioms.add(operator+"-"+relation[0]+"("+relation[1]+","+relation[2]);

												}
												
												
											
											}
											else if(sCurrentLine.contains("(="))
											{
												Matcher Equalmatcher = Pattern.compile("\\(=\\s").matcher(sCurrentLine);
												if(Equalmatcher.find())
													equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
													String[] relation = equation.split("\\s+");
														
														axioms.add(operator+"("+relation[0]+" = "+relation[1]);
												
											}
											else{
												Matcher Equalmatcher = Pattern.compile("\\(").matcher(sCurrentLine);
												if(Equalmatcher.find())
													equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
													String[] relation = equation.split("\\s+");

													if(relation.length == 2)
														axioms.add(operator+relation[0]+"("+relation[1]);
													else if(relation.length == 3)
														axioms.add(operator+relation[0]+"("+relation[1]+","+relation[2]);
											}
											
											
											 boolean flag = true;
												for(int i = 0; i<axioms.size();i++)
												{
														if((axioms.get(i).contains("->")||axioms.get(i).contains("<->"))&&flag)
													{
														System.out.print(axioms.get(i));
														flag = false;
													}
													else if(!axioms.get(i).contains("->")){
													System.out.print(axioms.get(i));
													}
	
												}	
												
												int left = Collections.frequency(axioms, "(");
												int right = Collections.frequency(axioms, ")");
												for(int j=0; j<left-right+1;j++)
													System.out.print(")");
													System.out.print(".\n");
													
											axioms.clear();
											operator = "";
									
					        			}
					        			else{
					        				
					        				
					        			}
					        			 
					        			
					        		}
					        			
					        		else if(nextLine.contains("exists")){

					        			  Matcher Existmatcher = Pattern.compile("exists\\s\\(").matcher(nextLine);
											if(Existmatcher.find())
												second = nextLine.substring(Existmatcher.end()).replace(")", "");
												String[] relation = second.split("\\s+");

												axioms.add(iff);
												axioms.add("(");
												for(String i: relation)
												{
													axioms.add("exists "+i+" ");
												}
												
												axioms.add("(");
												
												operator = "";
												

					        				
					        			}
					        		else if(nextLine.contains("forall")){
					        			  Matcher Emptymatcher = Pattern.compile("forall[ \\t]\\(").matcher(nextLine);
														if(Emptymatcher.find())
															second  = nextLine.substring(Emptymatcher.end()).replace(")", "");
															String[] relation = second.split("\\s+");
															axioms.add(iff);
													
															axioms.add(operator);
															axioms.add("(");
															for(String i: relation)
															{
																axioms.add("all "+i+" ");
															}
															
															axioms.add("(");
					        				
					        			}
					        			
					        			
					        			
					        		}
								
					        		
					        		else if(sCurrentLine.contains("(forall ("))
									{
										
										String str = sCurrentLine;
										Matcher ForallMatcher = Pattern.compile("forall \\(").matcher(str);
										if(ForallMatcher.find())
										    clean = str.substring(ForallMatcher.end()).replace(")", "");


										String[] Var = clean.split("\\s+");
										
										axioms.add(operator);
										axioms.add("(");
										for(String i: Var)
										{
											axioms.add("all "+i+" ");
										}
										
										axioms.add("(");
									}	        
				        			  else if(sCurrentLine.contains("(exists"))
										{
				        		  

					        		
						        			  Matcher Existmatcher = Pattern.compile("exists\\s\\(").matcher(sCurrentLine);
												if(Existmatcher.find())
													exist = sCurrentLine.substring(Existmatcher.end()).replace(")", "");
													String[] relation = exist.split("\\s+");

													axioms.add(iff);
													axioms.add("(");
													for(String i: relation)
													{
														axioms.add("exists "+i+" ");
													}
													
													axioms.add("(");
													operator = "";
										
									}
									else if(sCurrentLine.contains("(if"))
					        	  {
										
										if(sCurrentLine.contains("(iff"))
												iff = " <-> ";
											else	
												iff = " -> ";
											

					        		  if(sCurrentLine.contains("and")){
					        			  Matcher Operatormatcher = Pattern.compile("and[ \\t]\\(").matcher(sCurrentLine);
											if(Operatormatcher.find())
											  condition  = sCurrentLine.substring(Operatormatcher.end()).replace(")", "");
												operator = "&";
												String[] relation = condition.split("\\s+");
												if(relation.length == 2)
													{
													axioms.add("(");
													axioms.add(relation[0]+"("+relation[1]+")");
													}
												else if(relation.length == 3)
												{	axioms.add("(");
													axioms.add(relation[0]+"("+relation[1]+","+relation[2]+")");
												}
											

					        		  }
					        		  else if(sCurrentLine.contains("or")){
					        			  Matcher Operatormatcher = Pattern.compile("or[ \\t]\\(").matcher(sCurrentLine);
											if(Operatormatcher.find())
											  condition  = sCurrentLine.substring(Operatormatcher.end()).replace(")", "");
												operator = "|";
												String[] relation = condition.split("\\s+");
												if(relation.length == 2)
													axioms.add(relation[0]+"("+relation[1]+")");
												else if(relation.length == 3)
													axioms.add(relation[0]+"("+relation[1]+","+relation[2]+")");
								
					        		  }		
					        		  else{
											
					        			  Matcher RelationMatcher = Pattern.compile("f\\s\\(").matcher(sCurrentLine);
												if(RelationMatcher.find())
													LeftOver  = sCurrentLine.substring(RelationMatcher.end()).replace(")", "");
												
												String[] TripleSet = LeftOver.split("\\s+");
												if(TripleSet.length == 2)
													{axioms.add(TripleSet[0]+"("+TripleSet[1]+") ");
														axioms.add(iff);}
												else if(TripleSet.length == 3)
													{axioms.add(TripleSet[0]+"("+TripleSet[1]+","+TripleSet[2]+") ");
													axioms.add(iff);}
										}
										

					        	 } // end of if - --> only

					        	  else if(sCurrentLine.contains("(and"))
											{
					        		  
						        			if(sCurrentLine.contains("(=")){
						        			  Matcher Operatormatcher = Pattern.compile("\\(=\\s").matcher(sCurrentLine);
												if(Operatormatcher.find())
												  condition  = sCurrentLine.substring(Operatormatcher.end()).replace(")", "");
													operator = "&";
													String[] relation = condition.split("\\s+");
													if(relation.length == 2)
														{	axioms.add(iff);
															axioms.add("("+relation[0]+" = "+relation[1]+"))).\n");
														}
													
						        			}
						        			else{
							        			  Matcher Operatormatcher = Pattern.compile("and[ \\t]\\(").matcher(sCurrentLine);
													if(Operatormatcher.find())
													  condition  = sCurrentLine.substring(Operatormatcher.end()).replace(")", "");
														operator = "&";
														String[] relation = condition.split("\\s+");
														if(relation.length == 2)
															axioms.add(relation[0]+"("+relation[1]+")");
														else if(relation.length == 3)
															axioms.add(relation[0]+"("+relation[1]+","+relation[2]+")");
													
						        			}
											}
					        	  else if(sCurrentLine.contains("(or"))
									{
			        		  
				        			if(sCurrentLine.contains("(=")){
				        			  Matcher Operatormatcher = Pattern.compile("\\(=\\s").matcher(sCurrentLine);
										if(Operatormatcher.find())
										  condition  = sCurrentLine.substring(Operatormatcher.end()).replace(")", "");
											operator = "|";
											String[] relation = condition.split("\\s+");
											if(relation.length == 2)
												{	axioms.add(iff);
													axioms.add("("+relation[0]+" = "+relation[1]+")");
												}
										
				        			}
				        			else{
					        			  Matcher Operatormatcher = Pattern.compile("or[ \\t]\\(").matcher(sCurrentLine);
											if(Operatormatcher.find())
											  condition  = sCurrentLine.substring(Operatormatcher.end()).replace(")", "");
												operator = "|";
												String[] relation = condition.split("\\s+");
												if(relation.length == 2)
													axioms.add(relation[0]+"("+relation[1]+")");
												else if(relation.length == 3)
													axioms.add(relation[0]+"("+relation[1]+","+relation[2]+")");
										
				        				
				        			}
				        			
									}
	
				        			  else if(sCurrentLine.contains("not"))
										{
				        		  
				        				  if(sCurrentLine.contains("(=")){
				        					  Matcher Equalmatcher = Pattern.compile("\\(=\\s").matcher(sCurrentLine);
												if(Equalmatcher.find())
													equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
													String[] relation = equation.split("\\s+");
														axioms.add(operator+"("+relation[0]+" != "+relation[1]+")");
				        					  
				        				  }
	
				        				  
				        				  else{
						        			  Matcher InequalityMatcher = Pattern.compile("not\\s\\(").matcher(sCurrentLine);
															if(InequalityMatcher.find())
																Inequality = sCurrentLine.substring(InequalityMatcher.end()).replace(")", "");
															String[] relation = condition.split("\\s+");
															if(relation.length == 2)
																axioms.add("-"+relation[0]+"("+relation[1]+")");
															else if(relation.length == 3)
																	axioms.add("-"+relation[0]+"("+relation[1]+","+relation[2]+")");
				        				  }
										
									}
				        			  else if(sCurrentLine.contains("(="))
											{
					        		  

						        		
							        			  Matcher Equalmatcher = Pattern.compile("\\(=\\s").matcher(sCurrentLine);
													if(Equalmatcher.find())
														equation = sCurrentLine.substring(Equalmatcher.end()).replace(")", "");
														String[] relation = equation.split("\\s+");
															axioms.add(operator+"("+relation[0]+" = "+relation[1]+")");
			
											
										}
					        		
					        		
									
							        		else{
							        			
							        		
												
												

							        			  Matcher RelationMatcher = Pattern.compile("\\(").matcher(sCurrentLine);
													if(RelationMatcher.find())
														LeftOver  = sCurrentLine.substring(RelationMatcher.end()).replace(")", "");
													
													String[] TripleSet = LeftOver.split("\\s+");
													if(TripleSet.length == 2)
														axioms.add(" "+operator+" "+TripleSet[0]+"("+TripleSet[1]+")");
													else if(TripleSet.length == 3)
														axioms.add(" "+operator+" "+TripleSet[0]+"("+TripleSet[1]+","+TripleSet[2]+")");
													
													if(sCurrentLine.contains("))"))
														{
														
														axioms.add(")"+iff);
														 operator = "";
														}
														
							        		}
							        	
					        	  
					        	  
					          }

						
				
						


						
					  }
					
	
		
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (br != null)br.close();
						} catch (IOException ex) {
						ex.printStackTrace();
						}
					}	
			
		    }
		}
	

		
		return;
		
    }
	
	
}

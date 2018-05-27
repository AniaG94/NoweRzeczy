/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvexchangingvalues;

/**
 *
 * @author Ania
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CSVExchangingValues {
    
    //static String originalFileName = "C:\\Users\\Ania\\Documents\\POLSOURCE-praca\\PRACA\\NIKE-11.05\\SF_Incident_Test_20180511.csv.001.part.csv";
    static String originalFileName = "C:\\Users\\Ania\\Desktop\\IncidentTest.csv";
    static String mappingFileName = "C:\\Users\\Ania\\Desktop\\status.csv";
    static String purposeFileName = "C:\\Users\\Ania\\Desktop\\Purposes.csv";
    static String newFileName = "C:\\Users\\Ania\\Desktop\\IncidentTest2.csv";
    static File newFile = new File(newFileName);
    static BufferedReader br;
    static String splitBy = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    static String [] values;
    static String line;
    
    static int StatusNr;
    static int OriginNr;
    static int PriorityNr;
    static int PurposeNr;
    
    static FileWriter fw;
    static int k =0;
    
    static Map mappingFile = new HashMap();
    
    
    
    
    //Metoda do pozyskania wartości nazw kolumn
            public static List ReadLabels() throws FileNotFoundException, IOException{
                
         
                
                br = new BufferedReader(new FileReader(originalFileName));
                String firstline = br.readLine();
                fw  = new FileWriter(newFile);
                fw.write(firstline +"\n");
                
                
                String [] labels = firstline.split(splitBy);
                
                for(int i=0; i<labels.length; i++){
                    switch (labels[i]) {
                        case "Origin":       
                            OriginNr =i;
                            break;
                        case "Priority":
                            PriorityNr =i;
                            break;
                        case "Status":
                            StatusNr =i;
                            break;
                        case "Purpose__c":
                            PurposeNr=i;
                            break;
                        default:
                            break;
                    }
                }
                
                List <Integer> numbers = new ArrayList<>();
                numbers.add(OriginNr);
                numbers.add(PriorityNr);
                numbers.add(StatusNr);
                numbers.add(PurposeNr);
                
                
                System.out.println("Origin " + OriginNr + " Priority " + PriorityNr + " Status " + StatusNr);
                
                return numbers;
 
            }
            
            public static void ReadMappingFile() throws FileNotFoundException, IOException{
                
                
                br = new BufferedReader(new FileReader(purposeFileName));
                String ln = "";
                while((ln = br.readLine())!=null){
                    String [] mappingValues = ln.split(splitBy);
                    System.out.println(mappingValues[0]);
                    mappingFile.put(mappingValues[0], mappingValues[1]);
                }
                System.out.println(mappingFile);
                
                
                
            }
            
            
            
            public static void ChangeStatusValues() throws IOException, FileNotFoundException{
               

                values = line.split(splitBy);
                
                //Sprawdza wartości priosity i zamiena według arkusza mapowań. Dodać tylko TBD ? I sprawdzić wartości w orgu ?
                String newValue = (String) mappingFile.get(values[StatusNr]); //= mappingFile[values[StatusNr]];
                values[StatusNr] = newValue;

                System.out.println("value " + values[StatusNr]);
               
            }
            
            public static void ChangePriorityValues() throws IOException{
              
                values = line.split(splitBy);
                //Sprawdza wartości priority i zamiena według arkusza mapowań. Dodać tylko TBD ? I sprawdzić wartości w orgu ?
                String newValue = (String) mappingFile.get(values[PriorityNr]); //= mappingFile[values[StatusNr]];
                values[PriorityNr] = newValue;

            }
            
              public static void ChangePurposeValues() throws IOException{
              
                values = line.split(splitBy);
                //Sprawdza wartości priority i zamiena według arkusza mapowań. Dodać tylko TBD ? I sprawdzić wartości w orgu ?
                String newValue = (String) mappingFile.get(values[PurposeNr]); //= mappingFile[values[StatusNr]];
                values[PurposeNr] = newValue;
                System.out.println("value " + values[PurposeNr]);

            }
            
            public static void ChangeOriginValues() throws IOException{
              
                values = line.split(splitBy);
                //Sprawdza wartości priority i zamiena według arkusza mapowań. Dodać tylko TBD ? I sprawdzić wartości w orgu ?
                String newValue = (String) mappingFile.get(values[OriginNr]); //= mappingFile[values[StatusNr]];
                values[OriginNr] = newValue;

            }
            
            
            
            // Save chnged values in new file
            public static void CreateNewFile() throws FileNotFoundException, IOException{
                for(int j= 0; j<values.length-1; j++){
                fw.write(values[j]+",");
                }
                fw.write(values[values.length-1]+"\r\n");
                k++;
                System.out.println("K " + k);
                //System.out.println("Saved");
            }
    

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public static void main(String[] args){
    
        try{
            ReadLabels();
            ReadMappingFile();
            
            br = new BufferedReader(new FileReader(originalFileName));
            br.readLine();
            while((line =br.readLine()) != null){
                //ChangeStatusValues();
                //ChangePriorityValues();
                //ChangeOriginValues();
                ChangePurposeValues();
                CreateNewFile();
            }
            fw.close();
        }catch(FileNotFoundException e){System.out.println(e.getMessage());}
          catch (IOException e){System.out.println(e.getMessage());}
    }
}
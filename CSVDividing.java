/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvdividing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class CSVDividing {

    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        int FileNumber = 0;
        String FileName = "C://Users//Ania//Desktop//SF_Incident_Test_20180503.csv.002.part//SF_Incident_Test_20180503.csv.002.part"+FileNumber+".csv";
        String csvFile = "C://Users//Ania//Desktop//NIKE_CSSTAGING//SF_Incident_Test_20180503.csv.002.part.csv";
        String line;
        int row = 0;
        String FirstLine;
        FileWriter fw = new FileWriter(FileName); //plik, do którego będą zapisywane dane.
        //ArrayList<String> ArrayOf100kRows = new ArrayList<>(); //ArrayLista przechouje 100k wierszy
   
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            
            line = br.readLine();
            FirstLine = line;
            System.out.println(FirstLine);
            
            while ((line = br.readLine()) != null){ //póki nie napotka znaku końca linii
                
                if(row ==0){fw.write(FirstLine + "\n");}
                
                
                String dataRow = line +"\n";
                //System.out.println(dataRow);
                fw.write(dataRow);  
                row++;
                
                if(row == 500000){
                    fw.close();
                    System.out.println(FileName + "saved");
                    FileNumber++;
                    FileName = "C://Users//Ania//Desktop//SF_Incident_Test_20180503.csv.002.part//SF_Incident_Test_20180503.csv.002.part"+FileNumber+".csv";
                    fw = new FileWriter(FileName);
                    row =0;
                    continue;
                }
                
                
                
            }
            
            } catch (IOException e) {
            e.printStackTrace();
            }
        
              fw.close();
        }
       
    }
    


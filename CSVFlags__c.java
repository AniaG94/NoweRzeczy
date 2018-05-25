/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvflags__c;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CSVFlags__c {

   
    
    
    
    public static void main(String[] args) throws IOException, FileNotFoundException, NullPointerException {
        
    String FileName = "C:\\Users\\Ania\\Desktop\\SF_Contact_Test_20180503.csv.002.part.csv";
    BufferedReader br;
    String Fraud = "Risk_Fraud";
    String Risk_Claim = "Risk_Claim";
    String Chargeback = "Risk_Chargeback";
    String failed_billing = "Risk_Failed_Billing";
    String splitBy = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    int first = 0;
    int second = 0;
    int third= 0;
    int fourth=0;
    String value = "";
    String line = "";
    
        
            try{
                br = new BufferedReader(new FileReader(FileName));
                String firstline = br.readLine();
                String [] labels = firstline.split(splitBy);
                
                for (int i =0; i<labels.length; i++){
                    if(labels[i].equals(Chargeback)){
                        first = i;
                    }
                    else if(labels[i].equals(Risk_Claim)){
                        second = i;
                    }
                    else if(labels[i].equals(failed_billing)){
                        third = i;
                    }
                    else if(labels[i].equals(Fraud)){
                        fourth = i;
                    }
                }
                System.out.println(first+ " " + second + " " + third + " " + fourth);
                while((line = br.readLine()) != null){
                    //System.out.println(line);
                //String line = br.readLine();
                String [] values = line.split(splitBy);
                //System.out.println(values[23]);
                //System.out.println(values.length);
                                   // System.out.print(values[first]);

                if (values[first].equals("\"True\"")){
                    value = "Chargeback";
                }
                if(values[second].equals("\"True\"")){
                    value = value +";Claim Risk";
                }
                if(values[third].equals("\"True\"")){
                    value = value + ";Failed Billing";
                 }
                if(values[fourth].equals("\"True\"")){
                    value = value + ";Fraud";
                }
                //else
                    //System.out.println("No value");
                
                System.out.println(value);
                values[first] = value;
                //Usunąć second, third, fourth
                //Zapisać do pliku
                }
            }    catch(FileNotFoundException e){}
            
                
                
    }
    
}

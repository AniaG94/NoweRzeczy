/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvfindingid;

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


public class CSVFindingID {

static String FileName = "C:\\Users\\Ania\\Desktop\\NIKE_11.05\\SF_Incident_Thread_Notes_20180511.csv.001.part.csv";
static BufferedReader br;
static BufferedReader br2;
static String AccountmappingFileName = "C:\\Users\\Ania\\Desktop\\Account-exported.csv";
static String CasemappingFileName = "C:\\Users\\Ania\\Desktop\\Case-exported.csv";
static String newFileName = "C:\\Users\\Ania\\Desktop\\SF_Incident_Thread_Notes_20180511.csv.001.part_test.csv";
static String splitBy = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
static String line;
String [] values;
static Map AccountmappingFile = new HashMap();
static Map CasemappingFile = new HashMap();
static String AccId = "RNW_ID_C";
static String CsId = "RNW_Incident_Id_c";
static int AccountId;
static int CaseId;
static File newFile = new File(newFileName);



public static void ReadMappingFile() throws FileNotFoundException, IOException{
    
    br = new BufferedReader(new FileReader(AccountmappingFileName));
    
    while((line = br.readLine()) != null){
                    String [] mappingAccValues = line.split(splitBy);
                    //System.out.println(mappingValues[2]);
                    AccountmappingFile.put(mappingAccValues[2], mappingAccValues[1]);   
    }
    br2 = new BufferedReader(new FileReader(CasemappingFileName));
    
    while((line = br2.readLine()) != null){
                    String [] mappingCsValues = line.split(splitBy);
                    //System.out.println(mappingValues[2]);
                    AccountmappingFile.put(mappingCsValues[2], mappingCsValues[1]);   
    }
    
}

public static void AddColumn() throws IOException{
    br = new BufferedReader(new FileReader(FileName));
     FileWriter fw = new FileWriter(newFile);
     
     
    String firstline = br.readLine();
    firstline = firstline.concat(",CaseId,AccountId");
    
    fw.write(firstline+"\n");
    String [] headers = firstline.split(splitBy);
    
   
    
    for (int i=0; i<headers.length; i++){
        System.out.println(headers[i]);
        if(headers[i].equals(AccId)){
            AccountId = i;
        }
        else if(headers[i].equals(CsId)){
            CaseId = i;
        }    
    }
    System.out.println(CaseId);
    System.out.println(AccountId);
    
    while((line = br.readLine()) != null){
        
    String [] Values = line.split(splitBy);
    
    String newCaseValue = (String) CasemappingFile.get(Values[CaseId]);
    String newAccountValue = (String) AccountmappingFile.get(Values[AccountId]);
    System.out.println("While");
    String valuesLine = String.join(",",Values);
    valuesLine = valuesLine.concat(newCaseValue+","+newAccountValue);
    System.out.println(newCaseValue+","+newAccountValue);
    fw.write(valuesLine +"\n");
    //System.out.println(valuesLine);
    
}
    fw.close();
}

    
    
    public static void main(String[] args) throws IOException {
        ReadMappingFile();
        AddColumn();
    }
    
}

/*
 * LOGS
 * Feb 2nd Project started
 * Feb 10th Code complete, requires mannual input
 * Feb 15th 40+ photos, cannot hand type, need automation
 * Feb 18th Major Overhaul on logic, now looks for unique appearancces
 * Feb 20th Code Complete, test successful
 * Mar 20th Comment added for future use
 * Aug 30th Entire program now obsolete, Calvin too good
 */
package litmag;

/**
 *
 * @author Ethan
 */

import java.util.*;
import java.io.*;

public class LitMag {
    public static int returnScore(String s, int i){
      //method for reading csv
        int holder = s.indexOf(',',i);
        //System.out.println(s + "   " + i);
        return Integer.parseInt(s.substring(holder+2,holder+3));
    }

    public static void main(String[] args) throws Exception{
      //Because I cant be bothered to write the actual name
        String fileName= "test.csv";
        File file= new File(fileName);
        ArrayList <String> targetBuffer=new ArrayList<>();
        double sum=0;
        double count=0;
        PrintWriter out = new PrintWriter(new FileWriter("ImageNames.txt"));
        PrintWriter out2 = new PrintWriter(new FileWriter("ImageResults.txt"));

         // this gives you a 2-dimensional array of strings
        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;

        //Code to initialize target photos
        //Old code, updated on Feb 18, 2019
        /*user = new Scanner(System.in);
        System.out.println("Enter the name of the picture");
        name=user.nextLine();
        System.out.println("Enter the number");
        picCount = user.nextInt();
        user.close();
        String [] targetArray = new String [picCount];
        for(int i=0;i<targetArray.length;i++)
            targetArray[i]=name+rotation[i];*/

        //parsing the csv
        try{
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] values = line.split(",");
                // this adds the currently parsed line to the 2-dimensional string array
                lines.add(Arrays.asList(values));
            }

            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(List<String> line: lines)
            if(line.size()>3)
                if(!targetBuffer.contains(line.get(2)))
                    targetBuffer.add(line.get(2));

        while(targetBuffer.size()>0){
            String t = targetBuffer.get(0);
            for(List<String> toNum:lines)
                if(toNum.size()>3)
                    if(toNum.get(2).equals(t)){
                        sum+=Integer.parseInt(toNum.get(3));
                        count++;
                    }
            System.out.println("The average for " + targetBuffer.get(0) + " is " + sum/count + " entered by " + (int)count);
            out.println(targetBuffer.remove(0));
            out2.println(sum/count);
            count=sum=0;
        }
        out.close();
        out2.close();
        System.out.println("Process Complete");
        }
}


//Old code for reference
//Became obsolete on Feb 18, 2019
        // the following code lets you iterate through the 2-dimensional array
        /*int lineNo = 1;
        for(List<String> line: lines) {

            int columnNo = 1;
            for (String value: line) {
                System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
                columnNo++;
            }
            lineNo++;
           System.out.println(line);
            if(line.size()>3)
                if(!targetBuffer.contains(line.get(2)))
                    targetBuffer.add(line.get(2));
        }*/

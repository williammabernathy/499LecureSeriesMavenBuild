/*
    Write a command-line program to sort the contents of this file in 
    1. ascending order by the length of the name, 
    2. then alphabetically
*/
package com.mycompany.csc499lectureseries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class WilliamAbernathyCSC499Homework1 
{
    private static Scanner input = new Scanner(System.in);
    private static String userInput;
    private static String sortChoice;
    private static String fileContents[];           // array of file contents
    private static int fileSize = 0;                // how many entries in file
    private static BufferedReader br;               // file reader for Sort Me.txt
    private static String line;
    private static String file = "Sort Me.txt";
    
    private static void getInput()
    {
        // check if user wants to print current file contents
        System.out.println("Would you like to view the original file contents? [yes/no]");
        userInput = input.next();
        userInput.toLowerCase();
        
        // check that input is correct
        while (!userInput.equals("yes") && !userInput.equals("y") && !userInput.equals("no") && !userInput.equals("n") ) 
        {
            System.out.println("Please type yes/no [y/n].");
            userInput = input.next();
            userInput.toLowerCase();
        }
    }
    
    private static void listCurrentContents() throws Exception
    {
        // if the user entered yes, print the file contents
        if (userInput.equals("y") || userInput.equals("yes"))
        {
            // print the contents of the original file. .read() will return
            // -1 if no char or string is found, signaling the end of the file
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) 
            {
                try 
                {
                    System.out.println(line.trim());
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
            br.close();
        }
    }
    
    private static void buildContentArray() throws Exception
    {
        // first get the number of contents
        br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) 
        {
            try
            {
                // if there's an empty line, skip it
                if (line.trim().isEmpty() || line == null) 
                {
                    continue;
                } 
                else 
                {
                    fileSize++;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        br.close();
        
        // then init and fill the array
        int pos = 0;
        fileContents = new String[fileSize];
        br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) 
        {
            try
            {
                // skip over empty lines
                if (line.trim().isEmpty() || line == null) 
                {
                    continue;
                } 
                else 
                {
                    fileContents[pos] = line.trim();
                    pos++;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        br.close();
    }
    
    private static void sortArrayOfContents()
    {
        System.out.println("How would you like to sort the file?");
        System.out.println("Ascending? [a]");
        System.out.println("Descending? [d]");
        sortChoice = input.next();
        sortChoice.toLowerCase();
        
        // check that input is correct
        while (!sortChoice.equals("d") && !sortChoice.equals("a")) 
        {
            System.out.println("Please type [a or d] for [ascending or descending], respectfully.");
            sortChoice = input.next();
            sortChoice.toLowerCase();
        }
        
        // descending
        if(sortChoice.equals("d"))
        {
            //this sorts them alphabetically
            Arrays.sort(fileContents);
            
            
            int nameFirstCharToASCII;
            int currentChar = 97;
            int currentArrayPos = 0;
            int startPos = 0;
            int endPos = 0;
            
            nameFirstCharToASCII = (int) fileContents[currentArrayPos].toLowerCase().charAt(0);

            for (currentArrayPos = 0; currentArrayPos < fileContents.length; currentArrayPos++) 
            {
                nameFirstCharToASCII = (int) fileContents[currentArrayPos].toLowerCase().charAt(0);
                endPos++;
                
                if (nameFirstCharToASCII != currentChar) 
                {
                    startPos = currentArrayPos;
                    currentChar = nameFirstCharToASCII;
                }

                Arrays.sort(fileContents, startPos, endPos, Comparator.comparingInt(String::length).reversed());
            }
        }
        // ascending
        else
        {
            // sort alphabetically
            Arrays.sort(fileContents);
            
            // sort by length. using the string's length to compare first and second
            Arrays.sort(fileContents, (a, b) -> Integer.compare(a.length(), b.length()));
        }
    }
    
    private static void writeToNewFile() throws Exception
    {
        // use a bufferedwriter to writer the contents of fileContents
        // to a new file called Sorted Names.txt
        if (sortChoice.equals("a")) 
        {
            BufferedWriter wr = new BufferedWriter(new FileWriter("Sorted Names Ascending.txt"));
            for (int i = 0; i < fileContents.length; i++) 
            {
                try 
                {
                    // write contents to our created file
                    wr.write(fileContents[i]);
                    // if it's the last line, don't add a new line
                    if (i < fileContents.length - 1) 
                    {
                        wr.newLine();
                    }
                } catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }

            System.out.println("The sorted contents of Sort Me.txt have been placed"
                    + " in a newly created file, Sorted Names Ascending.txt");
            System.out.println("Exiting..");
            wr.close();
        } 
        else 
        {
            BufferedWriter wr = new BufferedWriter(new FileWriter("Sorted Names Descending.txt"));
            for (int i = 0; i < fileContents.length; i++) 
            {
                try 
                {
                    // write contents to our created file
                    wr.write(fileContents[i]);
                    // if it's the last line, don't add a new line
                    if (i < fileContents.length - 1) 
                    {
                        wr.newLine();
                    }
                } catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }

            System.out.println("The sorted contents of Sort Me.txt have been placed"
                    + " in a newly created file, Sorted Names Descending.txt");
            System.out.println("Exiting..");
            wr.close();
        }
    }

    public static void main(String[] args) throws Exception
    {
        /* get user input */
        getInput();
        
        /* list the current, unsort contents of file */
        listCurrentContents();
        
        /* build array of file contents */
        buildContentArray();
        
        /* sort the array */
        sortArrayOfContents();
        
        /* writing to a new file */
        writeToNewFile();
    }
    
}
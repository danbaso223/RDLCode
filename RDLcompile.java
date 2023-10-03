import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.*;
public class RDLcompile {
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    // ram for saving ints 
    int ram = 0;
    Integer result = 0;

    // Compiler and Runner 
    try {
      // read the file defined in the args (java RDLcompile.java FILENAME.dco)
      File program = new File(args[0]+".dco");
      Scanner reader = new Scanner(program);
      // Displays this if open is successful
      System.out.println("DCO File Opened");

      while (reader.hasNextLine()){
        // Reads entire code file
        String currentLine = reader.nextLine();
        // splits each line into tokens
        String[] tokens = currentLine.split(" ");
        //  Start of reader. Reads each token and executes
        reader:
        for (int i=0;i<tokens.length;i++){
          // Command tokens as an array ()
          String currentToken = tokens[i].toString();

          // Param 1 (x)
          Integer x = 0;
          // Param 2 (y)
          Integer y = 0;
          

          // Math Parameters
          // Parse ram into Parameter if its called else convert token into int
          try{
            if (tokens[i+1].toString().equals("ram")){
              x = ram;
            }else{
              x = Integer.parseInt(tokens[i+1].toString());
            }
            if (tokens[i+2].toString().equals("ram")){
              y = ram;
            }else{
              y = Integer.parseInt(tokens[i+2].toString());
            }
          } catch(Exception e) {}

          // available commands (instructions)
          switch(currentToken){
            case ":":
              // comment, ignore if this is placed in code
              break reader;
            case "add":
              // simple add
              if(x>9){x=9;}
              if(y>9){y=9;}
              result = x + y;
              break reader;
            case "sub":
              // simple subtract
              if(x>9){x=9;}
              if(y>9){y=9;}
              result =  x - y;
              break reader;
            case "ram":
              // test if ram == param1
              if(ram==x){
                System.out.println("true");
                break reader;
              } else {
                System.out.println("false");
                break reader;
              }
            case "result":
              // test if ram == param1
              if(result==x){
                System.out.println("true");
                break reader;
              } else {
                System.out.println("false");
                break reader;
              }
            case "dispram":
              // display ram
              System.out.println(ram);
              break;
            case "input":
              // ask user for input
              System.out.print("Integer: ");
              ram = Integer.parseInt(scan.nextLine());
              break;
            case "sav":
              // saves result of last math command
              ram = result;
            case "acc":
              ram = ram + result;
          }

        }
      }
      // Close reader, end compile
      reader.close();
    } 
    catch (Exception e) {
      // Print errors (idk)
      e.printStackTrace();
    }
    
  }



  
}
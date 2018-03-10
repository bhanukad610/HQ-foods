// ratio = bought / ate
import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

class Guy{
    private String name;
    private int bought,ate;
    private double ratio;
    Guy(String name){
        this.name = name;
        bought = 0;
        ate = 0 ;
        ratio = 0;
    }
    public void set(int bought, int ate){
        this.bought = bought;
        this.ate = ate;
    }

    public int getAte() {
        return ate;
    }

    public int getBought() {
        return bought;
    }

    public void Ate(){
        ate++;
        Calculate();
    }
    public void Bought(){
        bought++;
        Calculate();
    }
    public void Calculate(){
        if (ate != 0){
            ratio = (double) bought/(double) ate;
        }
        else {
            ratio = 1; // have to correct
        }
    }
    public double getRatio(){
        return ratio;
    }

    public String getName() {
        return name;
    }
}
class Buffer {
    private int size;
    private Guy[] buffer;
    private int node;

    Buffer(int size) {
        this.size = size;
        node = 0;
        buffer = new Guy[size];
    }

    public boolean Insert(Guy guy) {
        if (node <= size) {
            buffer[node++] = guy;
            return true;
        } else {
            return false;
        }
    }
    public Guy getGuy(int index){
        return buffer[index];
    }
// Used bubble sort for the sorting :)
    public void sort(){
        for (int i=0; i< size; i++){
            for (int j = 1; j<(size-1); j++){
                if(buffer[j-1].getRatio() > buffer[j].getRatio()){
                    Guy temp = new Guy(buffer[j-1].getName());
                    temp.set(buffer[j-1].getBought(),buffer[j-1].getAte());
                    temp.Calculate();
                    buffer[j-1] = buffer[j];
                    buffer[j] = temp;
                }
            }
        }
    }


    public void print() {
        String fileName = "NameList.txt";

        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < node; i++) {
                String toprint = (i+1) + "." + buffer[i].getName() + " bought:" + buffer[i].getBought() + " ate:" + buffer[i].getAte() + " ratio:" + buffer[i].getRatio();
                System.out.println(toprint);
                bw.write(buffer[i].getName() + " " +  buffer[i].getBought() + " " +  buffer[i].getAte());
                bw.newLine();
            }
            bw.close();
        }
            catch(FileNotFoundException ex) {
                System.out.println(
                        "Unable to open file '" +
                                fileName + "'");
            }
            catch(IOException ex) {
                System.out.println(
                        "Error reading file '"
                                + fileName + "'");
                // Or we could just do this:
                // ex.printStackTrace();
            }
        }
    }


class HQ {
    public static void main(String[] args) {
        System.out.println("|HQ food application| by HQ ICE16");
        System.out.println("Latest rankings :");
        Buffer buffer = new Buffer(10);
        // The name of the file to open.
        String fileName = "NameList.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                String[] words = line.split("\\s");//splits the string based on whitespace
                Guy guy = new Guy(words[0]);  // line should split at this point and set to the guys values
                guy.set(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
                guy.Calculate();
                buffer.Insert(guy);
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        buffer.print();
        System.out.println("According to list,");
        System.out.println("1." + buffer.getGuy(0).getName());
        System.out.println("2." + buffer.getGuy(1).getName());
        while (true) {
            System.out.println("Please give numbers: bought:1, ate:0 none:any integer");
            for (int i = 0; i < 10; i++) {
                System.out.println(buffer.getGuy(i).getName() + ":");
                Scanner scanner = new Scanner(System.in);
                if (scanner.hasNextInt()) {
                    int number = scanner.nextInt();
                    if (number == 1) {
                        buffer.getGuy(i).set(buffer.getGuy(i).getBought() + 1, buffer.getGuy(i).getAte());
                    }
                    if (number == 0) {
                        buffer.getGuy(i).set(buffer.getGuy(i).getBought(), buffer.getGuy(i).getAte() + 1);
                    }
                    buffer.getGuy(i).Calculate();

                } else {
                    System.out.println("Wrong input. Please enter agian.");
                    i = i - 1;
                }
            }
            buffer.sort();
            System.out.println("New rankings:");
            buffer.print();
            System.out.println("According to list,");
            System.out.println("1." + buffer.getGuy(0).getName());
            System.out.println("2." + buffer.getGuy(1).getName());
            System.out.println("Have to bring food");
            System.out.println("Do you want to exit?: yes(1)/no(press any key)");
            Scanner scanner1 = new Scanner(System.in);
            if (scanner1.hasNextInt()) {
                int termin = scanner1.nextInt();
                if (termin == 1) {
                    System.exit(0);
                }
            }
        }
    }
}

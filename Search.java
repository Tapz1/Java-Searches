/*
Title: Searches!
Programmer: Chris Tapia
Program Description: Different ways for searching and sorting data
Last Modified: 12/09/18
*/
package search;


import java.io.*;
import java.util.*;

import java.util.stream.Collectors;




public class Search {

    static List<Integer> list;
    
    public static void main(String[] args) throws IOException {
        file2list();    //importing the file as a list
        //Search.cleancsvList();
        int option;
        boolean input;
    // main menu
        System.out.println("Which would you like to do? (#)");
            System.out.println("1. Search for a # through a linear search");
            System.out.println("2. Find the range of the numbers");
            System.out.println("3. Find the most common & least common element");
            System.out.println("4. I just want to view a clean list without duplicates");
            System.out.println("5. Run an index search");
            System.out.println("6. Display all as an index");
            System.out.println("7. Cut the list into smaller buckets");
            System.out.println("8. Run a bubble sort");
            
        
        Scanner in = new Scanner(System.in);
        option = System.in.read();
        
        switch(option){
            case '1':
                Search.linearSearch();
                break;
            case '2':
                Search.range();
                break;
            case '3':
                Search.common();
                break;
            case '4':
                Search.cleanList();
                break;
            case '5':
                Search.indexSearch();
                break;
            case '6':
                Search.indexDisplay();
                break;
            case '7':
                Search.bucketSorter();
                break;
            case '8':
                Search.bubbleSort();
                break;
            
        }
    
    }
    
    //this is a bubble sort
    public static void bubbleSort() throws IOException {
        long start = System.currentTimeMillis();
        
        System.out.println("Here is your bubble sort: ");
        System.out.println("...this might take a minute...");
        
        int temp;
        boolean sort = false;
        
        while(sort == false){
            sort = true;
            for(int i=0; i<list.size()-1; i++){
            
            if(list.get(i) > list.get(i+1)){
                temp = list.get(i+1);
                list.set(i+1, list.get(i));
                list.set(i, temp);
                sort = false;
            }
        }
        }
        for(int k =0; k<list.size(); k++){
            System.out.println(list.get(k));
        }
        long end = System.currentTimeMillis(); 
        long runtime = (end-start)/1000;
        System.out.println("Program runtime: " + runtime + " seconds");   
    }
    
    // this is using a local text as it's data source
    // in theory, you can replace it with another file
    public static void file2list() throws IOException{
    //importing the file as a list
        Scanner numbList = new Scanner(new File("C:\\ListForClass.txt"));
        
        
        list = new ArrayList<Integer>();
        
        while(numbList.hasNextLine()){
            String line = numbList.nextLine();
            
            Scanner file = new Scanner(line);
            file.useDelimiter(",");
            while(file.hasNextInt()){
                list.add(file.nextInt());
            }
            file.close();
        }
        numbList.close();
    //end of file importing
    }
    
    
    // creates sub lists from existing list
    public static void createList(String fileName, int int1, int int2)throws IOException{
        
        //List<Integer> index = new ArrayList<Integer>(); //for the index of the number
        String newFile1 = String.format("C:\\%s", fileName);
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(newFile1));
        
        for(int i = 0; i<list.size();i++){
                if(list.get(i)>=int1 && list.get(i)<int2)
                    bw.write(list.get(i)+System.getProperty("line.separator"));
        }
        bw.close();
        
        
    }
    // creates files for each list made
    public static void bucketSorter()throws IOException{
        Scanner in = new Scanner(System.in);
        System.out.println("How many buckets do you want to create?");
        int list_num = in.nextInt();
        
        int counter = 0;
        do{
            System.out.println("Name of the file bucket (including if it's .txt or .csv): ");
            String fileName = in.next();
            System.out.println("Enter the minimum number: ");
            int min_num = in.nextInt();
            System.out.println("Enter the maximum number: ");
            int max_num = in.nextInt();
            createList(fileName, min_num, max_num);
            counter++;
        
        }
        while(counter < list_num);
    }
        
//displays the list as an index
    public static void indexDisplay() throws IOException{
        long start = System.currentTimeMillis();  
        List<Integer> index = new ArrayList<Integer>(); //for the index of the number
        Map<Integer,Integer> mapIndex = new HashMap<Integer,Integer>();
    
        
        
        for(int i=0; i<list.size(); i++){
            index.add(i);
            mapIndex.put(index.get(i), list.get(i));
        }
        System.out.println("Index #|| Number in List");
        mapIndex.forEach((k,v)-> System.out.println("#"+k+",  "+v));
        
        long end = System.currentTimeMillis(); 
        long runtime = (end-start)/1000;
        System.out.println("Program runtime: " + runtime + " seconds");
    }
    public static void indexSearch() throws IOException{
        
        List<Integer> index = new ArrayList<Integer>(); //for the index of the number
        Map<Integer, Integer> mapIndex = new HashMap<Integer,Integer>();
        
        Scanner in = new Scanner(System.in);
    
    //commence searching
        System.out.println("Would you like to search by index or number?");
        String option = in.next();
        
        if(option.contains("index")){       //search by number's index #
            for(int i=0; i<list.size(); i++){
            index.add(i);
            mapIndex.put(index.get(i), list.get(i));
            }
            System.out.println("What index number would you like to find?");
        int choice = in.nextInt();
        
            if(mapIndex.containsKey(choice)){
                Object value = mapIndex.get(choice);
                System.out.println("Index #: "+ choice + " , Value in Index: "+ value);
            }
        }
        else if(option.contains("number")){     //search by number in list
            for(int i=0; i<list.size(); i++){
            index.add(i);
            mapIndex.put(list.get(i), index.get(i));
            }
            System.out.println("What number would you like to find?");
            int choice = in.nextInt();
        
            if(mapIndex.containsKey(choice)){
                Object value = mapIndex.get(choice);
                System.out.println("Index #: "+ value + " , Value in Index: "+ choice);
            }
        }
    }
    
    public static void linearSearch() throws IOException{
        //int list [] = {2, 8, 4, 9, 1, 7, 6};
        Scanner in = new Scanner(System.in);
       
        
        System.out.println("Enter a 5-digit number to search for:");
        int selectedNumb = in.nextInt();
        System.out.println("Here they are:");
        for(int i:list)
            if(i == selectedNumb)
                System.out.println(i);
    }
        
    public static void range() throws IOException{
        file2list();    //importing the file as a list
        
        System.out.println("Here is the range of the numbers: ");
        
        int largest = Collections.max(list);
        int smallest = Collections.min(list);
        int range = largest - smallest; //the range
        System.out.println(largest + " is the largest number in the list");
        System.out.println(smallest + " is the smallest number in the list");
        System.out.println("Therefore, " + range + " is the range of the list");
    }
    
    public static void common() throws IOException{
    
    //finding the most common number//
        HashMap<Integer, Integer> countMap = new HashMap<Integer, Integer>();
    ///by adding the list to a map, we put the number as keys and its frequency as values
        
    //this method uses java stream and returns multiple common integers
        Map<Integer, Long> mostCom = list.stream().collect(Collectors.groupingBy(w->w, Collectors.counting()));
        List<Map.Entry<Integer, Long>> mostComList = mostCom.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(10).collect(Collectors.toList());
        System.out.println("The most common numbers in the list are: "+ mostComList);
        
        Map<Integer, Long> leastCom = list.stream().collect(Collectors.groupingBy(w->w, Collectors.counting()));
        List<Map.Entry<Integer, Long>> leastComList = leastCom.entrySet().stream().sorted(Map.Entry.comparingByValue()).limit(10).collect(Collectors.toList());
        System.out.println("The least common number in the list are: "+ leastComList);
    //  
    
    }
    
    public static void cleanList() throws IOException{
        
        Set<Integer> cleanList = new HashSet<>();
        cleanList.addAll(list);
        list.clear();
        list.addAll(cleanList);
        //System.out.println(cleanList);
        PrintWriter newFile = new PrintWriter("C:\\neeew FILE.txt");
        
        newFile.println(list);
        newFile.close();
        System.out.println("A file has been created for you of the list without duplicates");
    }
    
    
}


    
//end code//

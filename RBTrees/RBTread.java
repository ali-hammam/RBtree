package RBTrees;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RBTread {
    private static final RBtree dictionary= new RBtree("Rockne's");

    private RBTread(){}

    public static void run(){
        Scanner input = new Scanner(System.in);
        boolean start = true;
        System.out.println("Welcome to RBTree dictionary application");
        while (start){
            System.out.println();
            System.out.println("1- Load Dictionary");
            System.out.println("2- Print Dictionary Size");
            System.out.println("3- Insert Word");
            System.out.println("4- LookUp a word");
            System.out.println("5- print Height of RB");
            System.out.println("6- close");
            System.out.print("Choose: ");
            int flag = input.nextInt();

            switch (flag){
                case 1:
                    loadDictionary();
                break;

                case 2:
                    System.out.println("dictionary has " + dictionary.size(dictionary.getRoot()) + " words");
                break;

                case 3:
                    System.out.print("Enter the word: ");
                    String word = input.next();
                    int checkInsertion = lookUpWord(word);
                    if(checkInsertion == 1){
                        System.out.println("word already existed");
                    }else{
                        dictionary.insert(dictionary.getRoot() , word);
                        System.out.println("word inserted correctly");
                    }
                break;

                case 4:
                    System.out.print("Enter the word: ");
                    String val = input.next();
                    int check = lookUpWord(val);
                    if(check == 1){
                        System.out.println("word Found");
                    }else{
                        System.out.println("word not found");
                    }
                break;

                case 5:
                    System.out.println(dictionary.height(dictionary.getRoot()));
                break;

                default:
                    start = false;
                break;
            }
        }
    }

    private static void loadDictionary(){
        try {
            int counter = 1;
            File myObj = new File("EN-US.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(counter == 1){
                    counter = 2;
                    continue;
                }
                dictionary.insert(dictionary.getRoot() , data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public RBtree getRbTree(){return dictionary;}

    private static int lookUpWord(String val){
        System.out.print("Enter the word: ");
        //Node node = dictionary.find(val);
        if(dictionary.ifNodeExists(dictionary.getRoot() , val)){
            return 1;
        }else{
            return 0;
        }
    }
}

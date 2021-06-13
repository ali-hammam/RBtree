package RBTrees;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RBTread {
    private RBtree dictionary;

    public RBTread(){
        this.dictionary = new RBtree("Rockne's");
    }

    public void loadDictionary(){
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
                //System.out.println(data);
                this.dictionary.insert(this.dictionary.getRoot() , data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public RBtree getRbTree(){return this.dictionary;}
}

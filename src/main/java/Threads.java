import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class Threads implements Runnable{

    private File file;
    private BST bst;
    private Vector<String> v1;
    ArrayList<Word> words = new ArrayList<Word>();

    public Threads(File file) {
        this.file = file;
    }

    @Override
    public void run(){
        System.out.println("\nThread is running...");

        try{
            //File file = new File("C:/Users/HP/IdeaProjects/assignment03/inputfile1.txt");

            if (file.createNewFile()) {
                System.out.println("File Created");
            } else {
                System.out.println("File Already Exists");
            }

            FileReader fr = new FileReader(file.getPath());
            Scanner reader = new Scanner(fr);

            if(file.getName().equals("vocabulary.txt")) {
                bst = new BST();

                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    bst.insert(data);
                    populateWords(data);
                    //System.out.println(data);
                }
                reader.close();
            }
            else if(file.getName().equals("inputfile1.txt") || file.getName().equals("inputfile2.txt")){
                v1 = new Vector<String>();

                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    System.out.println(data);

                    StringTokenizer st = new StringTokenizer(data,". ");
                    while (st.hasMoreTokens())
                    {
                        String word = st.nextToken();
                        v1.add(word);
                        //System.out.println("token added!");
                        populateWords(word);
                    }
                }
                reader.close();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void displayBST(){
        bst.inorder();
    }

    public void displayVector(){
        System.out.println("vector is: "+v1);
    }

    public void populateWords(String data){
        boolean found = false;

        if(words.size()==0){
            words.add(new Word(data,0));
        }
        else {
            for (int i = 0; i < words.size(); i++) {
                if(words.get(i).word.equals(data)){
                    //words.get(i).incFrequency();
                    found=true;
                    break;
                }
            }

            if(found==false){
                words.add(new Word(data,0));
            }
        }
    }

    public void runQuery(String text){
        boolean found = false;

        for (int i = 0; i < words.size(); i++){
            if(words.get(i).word.equals(text)) {
                System.out.println("'" + words.get(i).word + "' is repeated " + words.get(i).frequency + " times in "+file.getName()+".");
                found = true;
            }
        }
        if(found==false){
            System.out.println("'" + text + "' is not present in the file "+file.getName()+".");
        }
    }

    public void matchWords(ArrayList<Word> list){
        //System.out.println("inside matchWords\nsize of array: "+list.size());
        for (int i = 0; i < list.size(); i++){
            for(int x = 0; x < v1.size(); x++) {
                if (list.get(i).word.equals(v1.get(x))) {
                    //System.out.println("match found for " + list.get(i).word + "\n");
                    list.get(i).incFrequency();
                }
            }
        }
    }
}

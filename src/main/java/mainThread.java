import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class mainThread {
    public static void main(String args[]){

        ArrayList<Word> words = new ArrayList<Word>();

        String option = "";

        File file = new File("C:/Users/HP/IdeaProjects/assignment03/vocabulary.txt");
        Threads vocabulary = new Threads(file);
        Thread t1 = new Thread(vocabulary);

        file = new File("C:/Users/HP/IdeaProjects/assignment03/inputfile1.txt");
        Threads input1 = new Threads(file);
        Thread t2 = new Thread(input1);

        file = new File("C:/Users/HP/IdeaProjects/assignment03/inputfile2.txt");
        Threads input2 = new Threads(file);
        Thread t3 = new Thread(input2);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        while(true) {
            System.out.println("\nChoose an option from the following: ");
            System.out.println("   1. Display BST from Vocabulary File"
                    + "\n   2. Display Vectors build from Input Files"
                    + "\n   3. View Match words with frequencies"
                    + "\n   4. File Query" //find match in alll the files
                    + "\n   5. Exit"
            );

            System.out.println("\nenter option: ");
            Scanner obj = new Scanner(System.in);
            option = obj.nextLine();

            if(option.equals("5"))
                break;

            else if(option.equals("1")){
                vocabulary.displayBST();
            }

            else if(option.equals("2")){
                input1.displayVector();
                input2.displayVector();
            }

            else if(option.equals("3")){
                words = vocabulary.words;

                input1.matchWords(words);
                input2.matchWords(words);

                for (int i = 0; i < words.size(); i++){
                    System.out.println("The word: " + words.get(i).word + " is repeated " + words.get(i).frequency + " times.");
                }
            }

            else if(option.equals("4")) {

                String word = "";
                System.out.println("enter word(s) to wish to search: ");

                Scanner obj1 = new Scanner(System.in);
                String data = obj1.nextLine();

                StringTokenizer st = new StringTokenizer(data,". ");

                while (st.hasMoreTokens()) {
                    word = st.nextToken();
                    vocabulary.runQuery(word);
                    input1.runQuery(word);
                    input2.runQuery(word);
                }
            }
        }
    }
}

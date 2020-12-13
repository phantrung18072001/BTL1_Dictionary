package Dictionary;

import java.util.Scanner;

public class DictionaryCommandLine  {
    public DictionaryCommandLine() {

    }
    Scanner scanner = new Scanner(System.in);
    public DictionaryManagement dictMan = new DictionaryManagement();

    public void showAllWords () {
        System.out.println("No\t|English\t|Vietnamese");
        for (int i = 0; i < dictMan.dictionary.words.size(); i++) {
            System.out.print(i + "\t|");
            dictMan.dictionary.words.get(i).display();
        }
    }

    public void dictionaryBasic() {
        System.out.println("======= MENU =======");
        while(true){
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            if(choice == 1) {
                dictMan.insertFromCommandline();
            } else if(choice == 2) {
                showAllWords();
            } else {
                break;
            }
        }
    }

    public void dictionaryAdvanced () {
        System.out.println("======= MENU =======");
        while(true){
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            if(choice == 1) {
                dictMan.insertFromFile();
            } else if(choice == 2) {
                showAllWords();
            } else if (choice == 3) {
                dictMan.dictionaryLookup();
            } else {
                break;
            }
        }
    }

    public void dictionarySearcher () {
        System.out.println("Find: ");
        String word_find = scanner.nextLine();
        for (Word word : dictMan.dictionary.words) {
            if (word_find.length() <= word.word_explain.length()) {
                if (word_find.equals(word.word_explain.substring(0, word_find.length()))) {
                    System.out.println(word.word_explain);
                }
            }
        }
    }
}


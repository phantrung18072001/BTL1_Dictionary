package Dictionary;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
public class DictionaryManagement {
    public Dictionary dictionary = new Dictionary();
    public DictionaryManagement() {

    }
    /**
     * THEM TU VAO TU DIEN
     */
    Scanner scanner = new Scanner(System.in);

    public void insertFromCommandline () {
        System.out.println("number of words: ");
        int n = scanner.nextInt();
        for(int i = 0;i < n; i++) {
            System.out.println("word target: ");
            String a = scanner.next();
            System.out.println("explain: ");
            scanner.nextLine();
            String b = scanner.nextLine();
            Word tmp = new Word(a, b);
            dictionary.words.add(tmp);
        }
    }

    /**
     * SUA NGHIA CUA 1 TU
     */
    public void editExplainFromCommandLine () {
        System.out.println("Input Word Target you want to change explain: ");
        String knownTarget = scanner.nextLine();
        System.out.println("Input new explain: ");
        String newExplain = scanner.nextLine();
        for (int i = 0; i < dictionary.words.size(); i++) {
            if (dictionary.words.get(i).word_target.equals(knownTarget)) {
                dictionary.words.get(i).word_explain = newExplain;
                break;
            }
        }
    }

    /**
     * SUA CAI TARGET
     */
    public void editTargetFromCommandLine () {
        System.out.println("Input Word Target you want to change its explain: ");
        String knownTarget = scanner.nextLine();
        System.out.println("Input new Target: ");
        String newTarget = scanner.nextLine();
        for (int i = 0; i < dictionary.words.size(); i++) {
            if (dictionary.words.get(i).word_target.equals(knownTarget)) {
                dictionary.words.get(i).word_target = newTarget;
                break;
            }
        }
    }

    /**
     * TIM KIEM 1 TU
     */
    public void dictionaryLookup () {
        System.out.println("Find: ");
        scanner.nextLine();
        String s = scanner.nextLine();
        for (Word word : dictionary.words) {
            if (word.word_target.equals(s)) {
                System.out.println(word.word_explain);
                break;
            }
        }
    }

    String fileName = "src\\sample\\dict.txt";

    /**
     * DOC DU LIEU TU FILE
     */
    public void insertFromFile () {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fr);
            String currLine = null;
            while ((currLine = bufferedReader.readLine()) != null) {
                int index = currLine.indexOf(",");
                String word_target = currLine.substring(0, index);
                String word_explain = currLine.substring(index + 2);
                dictionary.words.add(new Word(word_target, word_explain));
                dictionary.wordTarget.add(word_target);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * CHUYEN DU LIEU TU DIEN QUA FILE DICT
     */
    public void dictionaryExportToFile () {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : dictionary.words) {
                bufferedWriter.write(word.word_target + ", " + word.word_explain + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


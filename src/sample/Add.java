package sample;

import Dictionary.DictionaryCommandLine;
import Dictionary.Word;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Add {
    @FXML
    public Button newWord;
    @FXML
    public TextField targetText;
    @FXML
    public TextField explainText;
    @FXML
    public void addNewWord() {
        DictionaryCommandLine dic = new DictionaryCommandLine();
        dic.dictMan.insertFromFile();
        String target = targetText.getText().trim().toLowerCase();
        String explain = explainText.getText().trim().toLowerCase();
        if (!target.equals("") && !explain.equals("")) {
            Word tmp = new Word(target, explain);
            dic.dictMan.dictionary.words.add(tmp);
            dic.dictMan.dictionaryExportToFile();
        }
        targetText.setText("");
        explainText.setText("");
    }
}

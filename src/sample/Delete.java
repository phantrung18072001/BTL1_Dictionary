package sample;

import Dictionary.DictionaryCommandLine;
import javafx.fxml.FXML;
import javafx.scene.text.Text;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Delete {
    @FXML
    public Button butDel;

    @FXML
    public TextField inputText;

    @FXML
    public void deleteWord() {
        DictionaryCommandLine dic = new DictionaryCommandLine();
        dic.dictMan.insertFromFile();
        String target = inputText.getText().trim().toLowerCase();
        if (!target.equals("")) {
            for (int i = 0; i < dic.dictMan.dictionary.words.size(); i++) {
                if (target.equals(dic.dictMan.dictionary.words.get(i).word_target)) {
                    dic.dictMan.dictionary.words.remove(i);
                    dic.dictMan.dictionaryExportToFile();
                    inputText.setText("");
                    break;
                }
            }
        }
    }
}

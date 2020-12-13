package sample;

import Dictionary.DictionaryCommandLine;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Change {
    @FXML
    public Button update;
    @FXML
    public TextField updateWord;
    @FXML
    public TextField updateExplain;
    @FXML
    public TextArea notification;
    @FXML
    public TextField newUpdateWord;
    @FXML
    public void setUpdateWord() {
        DictionaryCommandLine dic = new DictionaryCommandLine();
        dic.dictMan.insertFromFile();
        String updateWordText = updateWord.getText().trim().toLowerCase();
        String updateExplainText = updateExplain.getText().trim().toLowerCase();
        String newUpdateWordText = newUpdateWord.getText().trim().toLowerCase();
        if(!dic.dictMan.dictionary.words.contains(updateWordText)){
            notification.setText("Don't find word!!");
        }
        if(!updateWordText.equals("") && !updateExplainText.equals("")) {
            for (int i = 0; i < dic.dictMan.dictionary.words.size(); i++) {
                if (dic.dictMan.dictionary.words.get(i).word_target.equals(updateWordText)) {
                    dic.dictMan.dictionary.words.get(i).word_explain = updateExplainText;
                    if(!newUpdateWordText.equals("")) {
                        dic.dictMan.dictionary.words.get(i).word_target = newUpdateWordText;
                    }
                    dic.dictMan.dictionaryExportToFile();
                    notification.setText("Success!!");
                    break;
                }
            }
        }
    }
}

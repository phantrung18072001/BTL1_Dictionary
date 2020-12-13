package sample;

import Dictionary.DictionaryCommandLine;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

public class Controller implements Initializable {
    @FXML
    public Button butFind;

    @FXML
    public Button butAdd;

    @FXML
    public Button butDel;

    @FXML
    public Button butUpdate;

    @FXML
    public TextField textSearch;

    @FXML
    public ListView<String> listView;

    @FXML
    public TextArea textExplain;

    @FXML
    public Pane SEARCH;

    @FXML
    public Button sound;

    DictionaryCommandLine dic = new DictionaryCommandLine();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dic.dictMan.insertFromFile();
        listView.getItems().addAll(dic.dictMan.dictionary.wordTarget);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String item = listView.getSelectionModel().getSelectedItem();
                for (int i = 0; i < dic.dictMan.dictionary.words.size(); i++) {
                    if (dic.dictMan.dictionary.words.get(i).word_target.equals(item)) {
                        textExplain.setText(dic.dictMan.dictionary.words.get(i).word_explain);
                        textSearch.setText(dic.dictMan.dictionary.words.get(i).word_target);
                        break;
                    }
                }
            }
        });

        textSearch.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                List<String> matchedWords = new ArrayList<>(dic.dictMan.dictionary.wordTarget);
                String pre = textSearch.getText().toLowerCase();
                matchedWords.removeIf(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return !s.startsWith(pre);
                    }
                });
                listView.getItems().clear();
                listView.getItems().addAll(matchedWords);
            }
        });
    }
    @FXML
    public void search() throws Exception {
        String input = textSearch.getText().trim().toLowerCase();
        Translator translator = new Translator();
        boolean check = false;
        if(!input.equals("")) {
            for (int i = 0; i < dic.dictMan.dictionary.words.size(); i++) {
                if (dic.dictMan.dictionary.words.get(i).word_target.equals(input)) {
                    textExplain.setText(dic.dictMan.dictionary.words.get(i).word_explain);
                    check = true;
                    break;
                }
            }
            if(check == false){
                if(!input.equals(translator.translate("en","vi",input))){
                    textExplain.setText(translator.translate("en","vi",input));
                    check = true;
                }
            }
            if (check == false) {
                for (int i = 0; i < dic.dictMan.dictionary.words.size(); i++) {
                    if (StringUtils.getLevenshteinDistance(input, dic.dictMan.dictionary.words.get(i).word_target) <= 2) {
                        listView.getItems().addAll(dic.dictMan.dictionary.words.get(i).word_target);
                    }
                }
            }
        }
    }
    @FXML
    public void sound() throws IOException {
        String VOICENAME = "kevin16";
        String input = textSearch.getText();
        Voice voice;
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = VoiceManager.getInstance().getVoice(VOICENAME);
        voice.allocate();
        try{
            voice.speak(input);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void add(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("add.fxml")));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("ADD");
            primaryStage.setScene(new Scene(root, 300, 300));
            primaryStage.show();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @FXML
    public void deleteWord(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete.fxml")));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("DELETE");
            primaryStage.setScene(new Scene(root, 300, 200));
            primaryStage.show();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    @FXML
    public void update(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("change.fxml")));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("UPDATE");
            primaryStage.setScene(new Scene(root, 400, 400));
            primaryStage.show();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

package sa.osama_alharbi.k8s_exam_simulator.gui.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import sa.osama_alharbi.k8s_exam_simulator.scenario.exams.Exam;
import sa.osama_alharbi.k8s_exam_simulator.scenario.interfaces.CheckSolutionIntr;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckListItem extends InitView implements Initializable {
    public static boolean HAS_BEAN = false;
    public static String NAME = "CheckListItem";

    @FXML public VBox root;
    @FXML public Label title;
    @FXML public FontAwesomeIconView checkItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recheck(true);
    }
    //CLOCK
    //CHECK_CIRCLE
    //TIMES_CIRCLE
    //QUESTION
    //CHECK

    private void checkItemClock(){
        checkItem.getStyleClass().clear();
        checkItem.getStyleClass().add("icon-clock");
    }

    private void checkItemCorrect(){
        checkItem.getStyleClass().clear();
        checkItem.getStyleClass().add("icon-correct");
    }

    private void checkItemNotCorrect(){
        checkItem.getStyleClass().clear();
        checkItem.getStyleClass().add("icon-wrong");
    }

    private CheckSolutionIntr checkSolutionIntr = null;
    public void setCheckSolutionIntr(CheckSolutionIntr checkSolutionIntr){
        this.checkSolutionIntr = checkSolutionIntr;
        title.setText(this.checkSolutionIntr.title);
        recheck(false);
    }

    public void recheck(boolean isInit) {
        checkItemClock();
        if(!isInit)
        if(checkSolutionIntr.run()){
            checkItemCorrect();
        }else{
            checkItemNotCorrect();
        }
    }
}

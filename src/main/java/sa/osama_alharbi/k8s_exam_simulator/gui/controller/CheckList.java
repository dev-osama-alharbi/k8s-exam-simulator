package sa.osama_alharbi.k8s_exam_simulator.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.web.WebView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import sa.osama_alharbi.k8s_exam_simulator.gui.GUIControllerService;
import sa.osama_alharbi.k8s_exam_simulator.gui.IncGUI;
import sa.osama_alharbi.k8s_exam_simulator.gui.view.IndexView;
import sa.osama_alharbi.k8s_exam_simulator.scenario.exams.Exam;
import sa.osama_alharbi.k8s_exam_simulator.scenario.interfaces.CheckSolutionIntr;
import sa.osama_alharbi.k8s_exam_simulator.scenario.model.SolutionModel;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class CheckList extends InitView implements Initializable {
    public static boolean HAS_BEAN = true;
    public static String NAME = "CheckList";

    private final IncGUI incGUI;

    private HashMap<CheckSolutionIntr, CheckListItem> listItemMap = new HashMap<>();

    @FXML
    public ListView<CheckSolutionIntr> lst;
    public ObservableList<CheckSolutionIntr> obsLst;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        obsLst = FXCollections.observableArrayList();
        lst.setItems(obsLst);

        lst.setCellFactory(param -> new ListCell<>(){
            @Override
            protected void updateItem(CheckSolutionIntr item, boolean empty) {
                super.updateItem(item, empty);
                if(!empty && item != null){
                    if(!listItemMap.containsKey(item)){
                        FXMLLoader loader = incGUI.getLoader(IndexView.class.getResource(CheckListItem.NAME+".fxml"),CheckListItem.HAS_BEAN);
                        try {
                            loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        CheckListItem controller = loader.<CheckListItem>getController();
                        controller.setCheckSolutionIntr(item);
                        listItemMap.put(item,controller);
                    }
                    setGraphic(listItemMap.get(item).root);
                }else{
                    setText(null);
                    setGraphic(null);
                }
            }
        });
    }

    private Exam exam = null;
    public void setExam(Exam exam){
        this.exam = exam;
        if(exam != null){
            obsLst.clear();
            obsLst.addAll(exam.scenario.getSolutionModel().getCheckSolutionList());
        }else{
            obsLst.clear();
        }
    }

    @FXML
    public void onCLickCheck(){
        recheck();
    }

    public void recheck(){
        obsLst.forEach(checkSolutionIntr -> {
            if(listItemMap.containsKey(checkSolutionIntr)){
                listItemMap.get(checkSolutionIntr).recheck(false);
            }
        });
    }
}

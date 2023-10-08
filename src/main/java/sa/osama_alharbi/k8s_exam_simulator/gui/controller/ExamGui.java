package sa.osama_alharbi.k8s_exam_simulator.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import sa.osama_alharbi.k8s_exam_simulator.gui.view.IndexView;
import sa.osama_alharbi.k8s_exam_simulator.scenario.exams.Exam;
import sa.osama_alharbi.k8s_exam_simulator.constant.K8sScenarios;
import sa.osama_alharbi.k8s_exam_simulator.gui.GUIControllerService;
import sa.osama_alharbi.k8s_exam_simulator.gui.IncGUI;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


@Controller
@RequiredArgsConstructor
public class ExamGui extends InitView implements Initializable {
    public static boolean HAS_BEAN = true;
    public static String NAME = "ExamGui";
    private final IncGUI incGUI;
    private final GUIControllerService guiController;
    @FXML
    public BorderPane view;
    @FXML
    public ListView<Exam> questionLst;
    public ObservableList<Exam> obsQuestionLst;
    private HashMap<Exam, ExamGuiItem> listItemMap = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        obsQuestionLst = FXCollections.observableArrayList();
        questionLst.setItems(obsQuestionLst);

        questionLst.setCellFactory(param -> new ListCell<>(){
            @Override
            protected void updateItem(Exam item, boolean empty) {
                super.updateItem(item, empty);
                if(!empty && item != null){
                    if(!listItemMap.containsKey(item)){
                        FXMLLoader loader = incGUI.getLoader(IndexView.class.getResource("ExamGuiItem.fxml"),CheckListItem.HAS_BEAN);
                        try {
                            loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        ExamGuiItem controller = loader.<ExamGuiItem>getController();
                        controller.setExam(item);
                        listItemMap.put(item,controller);
                    }
                    setGraphic(listItemMap.get(item).root);
                }else{
                    setText(null);
                    setGraphic(null);
                }
            }
        });

        view.setCenter(incGUI.gui_Scenario);
        init();

        questionLst.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            guiController.scenario.setExam(newValue);
            guiController.check.setExam(newValue);
        });
        questionLst.getSelectionModel().selectFirst();
    }

    public void init(){
        if(obsQuestionLst == null){
            obsQuestionLst = FXCollections.observableArrayList();
        }else{
            obsQuestionLst.clear();
        }
        obsQuestionLst.addAll(K8sScenarios.getAllExam());

        if(questionLst.getItems() != questionLst){
            questionLst.setItems(obsQuestionLst);
        }
    }

    @FXML
    public void onClickCheck(){
        view.setCenter(incGUI.gui_Check);

//        Exam exam = questionLst.getSelectionModel().getSelectedItem();
//        if(exam == null){
//            return;
//        }
//
//        exam.scenario.getSolutionModel().getCheckSolutionList().forEach(s -> {
//            System.out.println(s.title+" => "+s.run());
//        });


//        Robot robot = new Robot();
//        Random random = new Random();
//
//        new Thread(() -> {
//            for (int i = 0; i < 50; i++) {
//                Platform.runLater(() -> {
//                    ClipboardContent content = new ClipboardContent();
////                content.putString("Some text");
////                content.putHtml("<b>Bold</b> text");
//                    System.out.println(Clipboard.getSystemClipboard().getString());
////                Clipboard.getSystemClipboard().setContent(content);
//                    System.out.println(robot.getMouseX()+" "+robot.getMouseY());
//                    robot.mouseMove(2494.5, 862.5);
//                    robot.mouseClick(MouseButton.PRIMARY);
//                });
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }).start();
    }

    @FXML
    public void onClickScenario(){
        view.setCenter(incGUI.gui_Scenario);
    }

    @FXML
    public void onClickClose(){
        incGUI.gui_Root.setCenter(incGUI.gui_InitK8sConfig);
    }
}

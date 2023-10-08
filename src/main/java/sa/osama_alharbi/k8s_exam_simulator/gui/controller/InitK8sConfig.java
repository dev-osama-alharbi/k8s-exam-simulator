package sa.osama_alharbi.k8s_exam_simulator.gui.controller;

import com.sun.javafx.PlatformUtil;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import sa.osama_alharbi.k8s_exam_simulator.constant.K8sConstant;
import sa.osama_alharbi.k8s_exam_simulator.constant.PLATFORM;
import sa.osama_alharbi.k8s_exam_simulator.gui.GUIControllerService;
import sa.osama_alharbi.k8s_exam_simulator.gui.IncGUI;
import sa.osama_alharbi.k8s_exam_simulator.service.K8sConfigService;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class InitK8sConfig extends InitView implements Initializable {
    public static boolean HAS_BEAN = true;
    public static String NAME = "InitK8sConfig";
    private final IncGUI incGUI;
    private final GUIControllerService guiController;
    private final K8sConfigService k8sConfig;

    @FXML public ComboBox<String> combContexts;
    public ObservableList<String> obsCombContexts;
    @FXML public TextField txtKubeConfigPath,txtNamespacePrefix;
    @FXML public Button btnKubeConfigChoose,btnTest,btnStart;
    @FXML public RadioButton rdoConfigHome,rdoConfigChoose;

    private PLATFORM platform = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double x = 5+20+0*8-15+15;
        System.out.println(x);

        if(PlatformUtil.isWindows()) platform = PLATFORM.WINDOWS;
        if(PlatformUtil.isLinux()) platform = PLATFORM.LINUX;
        if(PlatformUtil.isMac()) platform = PLATFORM.MAC;

        obsCombContexts = FXCollections.observableArrayList();
        combContexts.setItems(obsCombContexts);
        checkDefaultConfig();

        rdoConfigHome.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                checkDefaultConfig();
            }
        });

        txtKubeConfigPath.disableProperty().bind(rdoConfigChoose.selectedProperty().not());
        btnKubeConfigChoose.disableProperty().bind(rdoConfigChoose.selectedProperty().not());
        rdoConfigChoose.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                checkFileConfig(null);
            }
        });
    }
    @FXML
    public void onClickTest() throws FileNotFoundException {
        try {
            k8sConfig.useDefaultConfig(combContexts.getSelectionModel().getSelectedItem());
            CoreV1Api api = new CoreV1Api();
            api.readNamespace("default",null);
        } catch (ApiException e) {
        }
    }
    @FXML
    public void onClickKubeConfigChoose(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select KubeConfig");
        File file = fileChooser.showOpenDialog(incGUI.stage);
        checkFileConfig(file);
    }

    @FXML
    public void onClickStartExam(){
        K8sConstant.NAMESPACE_PREFIX = txtNamespacePrefix.getText();
        k8sConfig.useDefaultConfig(combContexts.getSelectionModel().getSelectedItem());
        incGUI.gui_Root.setCenter(incGUI.gui_Exam);
//        guiController.exam.init();
    }

    private void checkDefaultConfig(){
        obsCombContexts.clear();
        HashMap<String,Object> map = k8sConfig.defaultConfig(platform);
        List <String> contextsNames = ((List <String>)map.get("contextsNames"));
        if(contextsNames == null){
            contextsNames = new ArrayList<>();
        }
        obsCombContexts.addAll(contextsNames);

        String currentContext = (String)map.get("currentContext");
        if(currentContext == null){
            currentContext = "";
        }
        combContexts.getSelectionModel().select(currentContext);
        checkIfHasContexts();
    }

    private void checkFileConfig(File file){
        obsCombContexts.clear();
        if(file == null){
            if(!txtKubeConfigPath.getText().isEmpty()){
               file = new File(txtKubeConfigPath.getText());
            }else{
                checkIfHasContexts();
                return;
            }
        }else{
            txtKubeConfigPath.setText(file.getAbsolutePath());
        }
        HashMap<String,Object> map = k8sConfig.fileConfig(file);
        List <String> contextsNames = ((List <String>)map.get("contextsNames"));
        if(contextsNames == null){
            contextsNames = new ArrayList<>();
        }
        obsCombContexts.addAll(contextsNames);

        String currentContext = (String)map.get("currentContext");
        if(currentContext == null){
            currentContext = "";
        }
        combContexts.getSelectionModel().select(currentContext);
        checkIfHasContexts();
    }

    private void checkIfHasContexts(){
        if(obsCombContexts.size() > 0){
            if(!combContexts.getSelectionModel().getSelectedItem().isEmpty()){
                btnTest.setDisable(false);
                btnStart.setDisable(false);
                return;
            }
        }
        btnTest.setDisable(true);
        btnStart.setDisable(true);
    }
}

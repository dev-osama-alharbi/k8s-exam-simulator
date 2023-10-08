package sa.osama_alharbi.k8s_exam_simulator.gui;

import org.springframework.stereotype.Service;
import sa.osama_alharbi.k8s_exam_simulator.gui.controller.*;

@Service
public class GUIControllerService {
    public Root root;
    public SplashScreen splashScreen;
    public InitK8sConfig initK8sConfig;
    public ExamGui exam;
    public Check check;
    public Scenario scenario;
    public CheckMd checkMd;
    public CheckList checkList;
}

package sa.osama_alharbi.k8s_exam_simulator.scenario.exams;

import sa.osama_alharbi.k8s_exam_simulator.scenario.model.ScenarioModel;
import sa.osama_alharbi.k8s_exam_simulator.constant.K8sConstant;

public class Exam {
    public final ScenarioModel scenario = new ScenarioModel();
    public String scenario_id = "";
    public String namespace = K8sConstant.NAMESPACE_PREFIX;
    public String title = "title";

    public Exam(ScenarioModel scenario){
    }

}

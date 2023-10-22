package sa.osama_alharbi.k8s_exam_simulator.scenario.exams;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1Pod;
import sa.osama_alharbi.k8s_exam_simulator.constant.K8sConstant;
import sa.osama_alharbi.k8s_exam_simulator.scenario.interfaces.CheckSolutionIntr;
import sa.osama_alharbi.k8s_exam_simulator.scenario.model.ScenarioModel;


public class Q_Demo extends Exam {
    public Q_Demo() {
        super(new ScenarioModel());
        title = "pod";
        scenario_id = title;
        scenario.getQuestionModel().setTitle(title);

        namespace = K8sConstant.NAMESPACE_PREFIX+"pod";
        String podName = "pod1";



        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(1,"example") {
            @Override
            public boolean run() {
                //** YOUR LOGIC HERE **//
                return true;
            }
        });

        //Check namespace
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(1,"namespace") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Namespace v1namespace = api.readNamespace(namespace,null);
                    return v1namespace != null;
                } catch (ApiException e) {
                    return false;
                }
            }
        });

        //Check pod name
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(2,"pod name") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Pod v1pod = api.readNamespacedPod(podName,namespace,null);
                    return v1pod.getMetadata().getName().equals(podName);
                } catch (Exception e) {
                    return false;
                }
            }
        });
    }
}

package sa.osama_alharbi.k8s_exam_simulator.scenario.exams;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1Pod;
import sa.osama_alharbi.k8s_exam_simulator.scenario.interfaces.CheckSolutionIntr;
import sa.osama_alharbi.k8s_exam_simulator.scenario.model.ScenarioModel;
import sa.osama_alharbi.k8s_exam_simulator.constant.K8sConstant;


public class Q1Exam extends Exam {
    public Q1Exam() {
        super(new ScenarioModel());
        scenario_id = "pod";

        title = "pod";

        namespace = K8sConstant.NAMESPACE_PREFIX+"pod";
        String podName = "pod1";
        String imageName = "httpd:2.4.57-alpine";
        String containerName = "pod1-container";

        scenario.getQuestionModel().setTitle("Pods");

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

        //Check image
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(3,"image") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Pod v1pod = api.readNamespacedPod(podName,namespace,null);
                    return v1pod.getSpec().getContainers().get(0).getImage().equals(imageName);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //Check container name
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(4,"container name") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Pod v1pod = api.readNamespacedPod(podName,namespace,null);
                    return v1pod.getSpec().getContainers().get(0).getName().equals(containerName);
                } catch (Exception e) {
                    return false;
                }
            }
        });
    }
}

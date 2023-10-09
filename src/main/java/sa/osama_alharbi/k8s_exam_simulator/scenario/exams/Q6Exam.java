package sa.osama_alharbi.k8s_exam_simulator.scenario.exams;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1Pod;
import sa.osama_alharbi.k8s_exam_simulator.constant.K8sConstant;
import sa.osama_alharbi.k8s_exam_simulator.scenario.interfaces.CheckSolutionIntr;
import sa.osama_alharbi.k8s_exam_simulator.scenario.model.ScenarioModel;


public class Q6Exam extends Exam {
    public Q6Exam() {
        super(new ScenarioModel());
        scenario_id = "deployment";

        title = "deployment";

        namespace = K8sConstant.NAMESPACE_PREFIX+"deployment";
        String deploymentName = "my-store";
        String imageName = "nginx:1.25.2";
        int replicas  = 3;

        scenario.getQuestionModel().setTitle("deployment");

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

        //deploymentName
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(2,"deploymentName") {
            @Override
            public boolean run() {
                AppsV1Api api = new AppsV1Api();
                try {
                    V1Deployment v1Deployment = api.readNamespacedDeployment(deploymentName,namespace,null);
                    return v1Deployment.getMetadata().getName().equals(deploymentName);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //imageName
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(3,"imageName") {
            @Override
            public boolean run() {
                AppsV1Api api = new AppsV1Api();
                try {
                    V1Deployment v1Deployment = api.readNamespacedDeployment(deploymentName,namespace,null);
                    return v1Deployment.getSpec().getTemplate().getSpec().getContainers().get(0).getImage().equals(imageName);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //replicas
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(4,"replicas") {
            @Override
            public boolean run() {
                AppsV1Api api = new AppsV1Api();
                try {
                    V1Deployment v1Deployment = api.readNamespacedDeployment(deploymentName,namespace,null);
                    return v1Deployment.getSpec().getReplicas() == replicas;
                } catch (Exception e) {
                    return false;
                }
            }
        });
    }
}

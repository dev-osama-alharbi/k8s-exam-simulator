package sa.osama_alharbi.k8s_exam_simulator.scenario.exams;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.BatchV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Job;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1Service;
import sa.osama_alharbi.k8s_exam_simulator.constant.K8sConstant;
import sa.osama_alharbi.k8s_exam_simulator.scenario.interfaces.CheckSolutionIntr;
import sa.osama_alharbi.k8s_exam_simulator.scenario.model.ScenarioModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Q4Exam extends Exam {
    public Q4Exam() {
        super(new ScenarioModel());
        scenario_id = "service";

        title = "Service";

        namespace = K8sConstant.NAMESPACE_PREFIX+"service";
        String podName = "project-api";
        String imageName = "nginx:1.25.2";
        String serviceName = "project-api-svc";
        String serviceType = "ClusterIP";
        int servicePort = 3333;
        int serviceTargetPort = 80;
        HashMap<String,String> labels = new HashMap<>();
        labels.put("project","prod-api");

        scenario.getQuestionModel().setTitle("Service");

        //namespace
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

        //podName
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(2,"podName") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Pod v1Pod = api.readNamespacedPod(podName,namespace,null);
                    return v1Pod.getMetadata().getName().equals(podName);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //podLabel
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(3,"podLabel") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Pod v1Pod = api.readNamespacedPod(podName,namespace,null);
                    boolean flag = true;
                    Map<String, String> jobLabels = v1Pod.getMetadata().getLabels();
                    for (Map.Entry<String, String> ent:labels.entrySet())
                        if(!jobLabels.containsKey(ent.getKey()))
                            flag = false;
                        else
                        if(!jobLabels.get(ent.getKey()).equals(ent.getValue()))
                            flag = false;

                    return flag;
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //image
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(4,"image") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Pod v1Pod = api.readNamespacedPod(podName,namespace,null);
                    return v1Pod.getSpec().getContainers().get(0).getImage().equals(imageName);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //serviceName
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(5,"serviceName") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Service v1Service = api.readNamespacedService(serviceName,namespace,null);
                    return v1Service.getMetadata().getName().equals(serviceName);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //serviceType
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(6,"serviceType") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Service v1Service = api.readNamespacedService(serviceName,namespace,null);
                    return v1Service.getSpec().getType().equals(serviceType);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //serviceLabel
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(7,"serviceLabel") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Service v1Service = api.readNamespacedService(serviceName,namespace,null);
                    boolean flag = true;
                    Map<String, String> jobLabels = v1Service.getMetadata().getLabels();
                    for (Map.Entry<String, String> ent:labels.entrySet())
                        if(!jobLabels.containsKey(ent.getKey()))
                            flag = false;
                        else
                        if(!jobLabels.get(ent.getKey()).equals(ent.getValue()))
                            flag = false;

                    return flag;
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //servicePort
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(8,"servicePort") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Service v1Service = api.readNamespacedService(serviceName,namespace,null);
                    return v1Service.getSpec().getPorts().get(0).getPort().equals(servicePort);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //serviceTargetPort
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(9,"serviceTargetPort") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Service v1Service = api.readNamespacedService(serviceName,namespace,null);
                    return v1Service.getSpec().getPorts().get(0).getTargetPort().getIntValue().equals(serviceTargetPort);
                } catch (Exception e) {
                    return false;
                }
            }
        });

    }
}
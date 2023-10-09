package sa.osama_alharbi.k8s_exam_simulator.scenario.exams;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.BatchV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Job;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1Pod;
import sa.osama_alharbi.k8s_exam_simulator.constant.K8sConstant;
import sa.osama_alharbi.k8s_exam_simulator.scenario.interfaces.CheckSolutionIntr;
import sa.osama_alharbi.k8s_exam_simulator.scenario.model.ScenarioModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Q3Exam extends Exam {
    public Q3Exam() {
        super(new ScenarioModel());
        scenario_id = "readiness-probe";

        title = "ReadinessProbe";

        namespace = K8sConstant.NAMESPACE_PREFIX+"readiness-probe";
        String podName = "pod-readiness-probe";
        String imageName = "busybox:1.36.1";
        List<String> readinessProbeCommands = new ArrayList<>();
        readinessProbeCommands.add("sh");
        readinessProbeCommands.add("-c");
        readinessProbeCommands.add("cat /tmp/ready");
        int initially = 5;
        int periodically = 10;
        List<String> podCommands = new ArrayList<>();
        podCommands.add("sh");
        podCommands.add("-c");
        podCommands.add("touch /tmp/ready && sleep 1d");

        scenario.getQuestionModel().setTitle("ReadinessProbe");

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

        //image
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(3,"image") {
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

        //readinessProbeCommands
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(4,"readinessProbeCommands") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                //TODO check command
//                try {
//                    V1Job v1Job = api.readNamespacedJob(jobName,namespace,null);
//                    System.out.println("v1Job command => "+v1Job.getSpec().getTemplate().getSpec().getContainers().get(0).getCommand());
//                    return v1Job.getSpec().getTemplate().getSpec().getContainers().get(0).getCommand().get(0).equals(imageName);
//                } catch (Exception e) {
//                    return false;
//                }
                return false;
            }
        });

        //initially
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(5,"initially") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Pod v1Pod = api.readNamespacedPod(podName,namespace,null);
                    return v1Pod.getSpec().getContainers().get(0).getReadinessProbe().getInitialDelaySeconds() == initially;
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //periodically
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(6,"periodically") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Pod v1Pod = api.readNamespacedPod(podName,namespace,null);
                    return v1Pod.getSpec().getContainers().get(0).getReadinessProbe().getPeriodSeconds() == periodically;
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //podCommands
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(7,"podCommands") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                //TODO check command
//                try {
//                    V1Job v1Job = api.readNamespacedJob(jobName,namespace,null);
//                    System.out.println("v1Job command => "+v1Job.getSpec().getTemplate().getSpec().getContainers().get(0).getCommand());
//                    return v1Job.getSpec().getTemplate().getSpec().getContainers().get(0).getCommand().get(0).equals(imageName);
//                } catch (Exception e) {
//                    return false;
//                }
                return false;
            }
        });

    }
}
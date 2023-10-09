package sa.osama_alharbi.k8s_exam_simulator.scenario.exams;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import sa.osama_alharbi.k8s_exam_simulator.constant.K8sConstant;
import sa.osama_alharbi.k8s_exam_simulator.scenario.interfaces.CheckSolutionIntr;
import sa.osama_alharbi.k8s_exam_simulator.scenario.model.ScenarioModel;


public class Q7Exam extends Exam {
    public Q7Exam() {
        super(new ScenarioModel());
        scenario_id = "initContainer";

        title = "initContainer";

        //TODO
        namespace = K8sConstant.NAMESPACE_PREFIX+"initContainer";
        String deployName = "my-website";
        String deploymentImageName = "nginx:1.25.2";

        String initContainerName = "init-con";
        String initContainerImage = "busybox:1.36.1";

        String volumeName = "web-content";


        scenario.getQuestionModel().setTitle("initContainer");

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

        //deployName
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(2,"deployName") {
            @Override
            public boolean run() {
                AppsV1Api api = new AppsV1Api();
                try {
                    V1Deployment v1Deployment = api.readNamespacedDeployment(deployName,namespace,null);
                    return v1Deployment.getMetadata().getName().equals(deployName);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //deploymentImageName
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(2,"deploymentImageName") {
            @Override
            public boolean run() {
                AppsV1Api api = new AppsV1Api();
                try {
                    V1Deployment v1Deployment = api.readNamespacedDeployment(deploymentImageName,namespace,null);
                    return v1Deployment.getSpec().getTemplate().getSpec().getContainers().get(0).getImage().equals(deploymentImageName);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //initContainerName
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(2,"initContainerName") {
            @Override
            public boolean run() {
                AppsV1Api api = new AppsV1Api();
                try {
                    V1Deployment v1Deployment = api.readNamespacedDeployment(deploymentImageName,namespace,null);
                    return v1Deployment.getSpec().getTemplate().getSpec().getInitContainers().get(0).getName().equals(initContainerName);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //initContainerImage
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(2,"initContainerImage") {
            @Override
            public boolean run() {
                AppsV1Api api = new AppsV1Api();
                try {
                    V1Deployment v1Deployment = api.readNamespacedDeployment(deploymentImageName,namespace,null);
                    return v1Deployment.getSpec().getTemplate().getSpec().getInitContainers().get(0).getImage().equals(initContainerImage);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        // volume
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(11,"volume") {
            @Override
            public boolean run() {
                AppsV1Api api = new AppsV1Api();
                try {
                    V1Deployment v1Deployment = api.readNamespacedDeployment(deploymentImageName,namespace,null);
                    boolean isVolumesMountToDeploy = false;
                    boolean isVolumesMountToContainer = false;
                    boolean isVolumesMountToInitContainer = false;
                    for(V1Volume v1Volume : v1Deployment.getSpec().getTemplate().getSpec().getVolumes()){
                        if(v1Volume.getName().equals(volumeName) && v1Volume.getEmptyDir() != null){
                            isVolumesMountToDeploy = true;
                        }
                    }
                    if(isVolumesMountToDeploy){
                        for(V1VolumeMount v1VolumeMount : v1Deployment.getSpec().getTemplate().getSpec().getContainers().get(0).getVolumeMounts()){
                            if(v1VolumeMount.getName().equals(volumeName)){
                                if(v1VolumeMount.getMountPath().equals("/usr/share/nginx/html")){
                                    isVolumesMountToContainer = true;
                                }
                            }
                        }
                        for(V1VolumeMount v1VolumeMount : v1Deployment.getSpec().getTemplate().getSpec().getInitContainers().get(0).getVolumeMounts()){
                            if(v1VolumeMount.getName().equals(volumeName)){
                                if(v1VolumeMount.getMountPath().equals("/tmp/web-content")){
                                    isVolumesMountToInitContainer = true;
                                }
                            }
                        }
                    }
                    return isVolumesMountToDeploy && isVolumesMountToContainer && isVolumesMountToInitContainer;
                } catch (Exception e) {
                    return false;
                }
            }
        });

    }
}
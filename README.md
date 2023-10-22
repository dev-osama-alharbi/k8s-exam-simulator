![K8s](/src/main/resources/static/image/k8s.svg)

## k8s-exam-simulator
___
This project aimed at providing you with a unique learning and testing experience for Kubernetes. We are pleased to introduce the `k8s-exam-simulator` project, which allows you to enhance your knowledge and skills in Kubernetes in an interactive and enjoyable way. This project offers a simple and user-friendly interface that enables you to access a diverse set of questions and challenges related to Kubernetes.

### What sets our project apart:
* Wide Range of Questions: The project allows you to access a variety of questions about Kubernetes. You can choose the level that suits you, whether you're a beginner or a Kubernetes professional.
* Immediate Verification: Once you answer a question, you can instantly verify your answer. The correct answer and an explanation will be displayed, helping you better understand the concepts.
* Solution Display: If you encounter difficulty in answering a question, you can view the solutions and learn from them. The project provides comprehensive solutions and explanations to help you grasp the scenario better.
* Easy Navigation Between Questions: You can easily navigate between questions and select the question you want to verify or solve.
* Run on Your Own Kubernetes Cluster: We offer you the option to run the project on your own Kubernetes cluster. This allows you to practice the concepts you've learned in a real-world environment.

We believe in the importance of interactive learning and practical application when it comes to Kubernetes technology. Therefore, we are committed to providing this project to assist you in improving your skills and knowledge about Kubernetes effectively.

Whether you are a newcomer to the world of Kubernetes or a professional looking to enhance your skills, our project can be a valuable companion on your journey. Enjoy learning, testing, and challenging yourself, and we wish you an enjoyable experience with the Kubernetes Question Simulator project.



## Screenshots
___

| Choose cluster and main configurations                                    |
|---------------------------------------------------------------------------|
| ![Screenshot of Application](/src/main/resources/static/image/config.png) |   

| **Question**                                                                |
|-----------------------------------------------------------------------------|
| ![Screenshot of Application](/src/main/resources/static/image/question.png) |  

| **Solution**                                                                |
|-----------------------------------------------------------------------------|
| ![Screenshot of Application](/src/main/resources/static/image/solution.png) |

| **Check live**                                                           |
|--------------------------------------------------------------------------|
| ![Screenshot of Application](/src/main/resources/static/image/check.png) |


## How to participate? 
___
before you start you need to choose a title for your question for example "pod"
1) [Question] you just need a markdown
    * in path `src/main/resources/sa/osama_alharbi/k8s_exam_simulator/scenario/exams/question`
    * create a markdown file with same title name "pod.md"
    * fill your question
2) [Solution] you just need a markdown
    * in path `src/main/resources/sa/osama_alharbi/k8s_exam_simulator/scenario/exams/solution`
    * create a markdown file with same title name "pod.md"
    * fill your solution
3) [Logic] you need java and `io.kubernetes:client-java` library 
    * duplicate java file `sa.osama_alharbi.k8s_exam_simulator.scenario.exams.Q_Demo`
    * all your code in constracter
    * change the title to your title
    * add new logic at this runner class `scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr())`
      ```java
      scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(1,"example") {
         @Override
         public boolean run() {
            //** YOUR LOGIC HERE **//
            return true;
         }
      });
    * at class `sa.osama_alharbi.k8s_exam_simulator.constant.K8sScenarios` add your Logic class to the list
   
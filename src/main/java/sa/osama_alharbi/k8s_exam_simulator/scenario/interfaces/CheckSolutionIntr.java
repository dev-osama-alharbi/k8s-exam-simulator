package sa.osama_alharbi.k8s_exam_simulator.scenario.interfaces;

public abstract class CheckSolutionIntr {

    public CheckSolutionIntr(int id,String title){
        this.id = id;
        this.title = title;
    }
    public final int id;
    public final String title;
    public boolean answer = false;

    public abstract boolean run();

}

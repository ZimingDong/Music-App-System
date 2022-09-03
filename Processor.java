import java.util.ArrayList;

public class Processor {
    private final ArrayList<Command> HaveDone = new ArrayList<>();
    private final ArrayList<Command> UnDone = new ArrayList<>();
    private boolean check = true;

    public Processor(){
    }
    public void clear(){
        this.HaveDone.clear();
        this.UnDone.clear();
        this.check=false;
    }

    public void Do(Command c){
        c.call();
        HaveDone.add(c);
        UnDone.clear();
        this.check = true;
    }

    public void undo(){
        if (HaveDone.size()==0){
            return;
        }
        Command c = HaveDone.remove(HaveDone.size()-1);
        c.undo();
        UnDone.add(c);
    }
    public void redo(){
        if (UnDone.size()==0){
            if (this.check) {
                Command c = HaveDone.get(HaveDone.size() - 1);
                this.Do(c);
                this.check = true;
                return;
            }else{
                return;
            }
        }
        Command c = UnDone.remove(UnDone.size()-1);
        HaveDone.add(c);
        c.call();
        this.check = false;
    }

}

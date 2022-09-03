
public class DefaultPlayable {
    private Playable aPrototype;
    public void setPrototype(Playable p){
        assert p!=null;
        this.aPrototype = p;
    }
    public Playable createPlayable(){
        return this.aPrototype.clone();
    }
}

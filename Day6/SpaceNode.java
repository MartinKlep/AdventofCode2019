import java.util.ArrayList;

public class SpaceNode{
    private ArrayList<SpaceNode> children = new ArrayList<SpaceNode>();
    private SpaceNode parent = null;
    private String name = null;

    public SpaceNode(String name){
        this.name = name;
    }

    public Node(String name, SpaceNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public ArrayList<SpaceNode> getChildren() {
        return children;
    }

    public void addChild(String name) {
        SpaceNode child = new SpaceNode(name);
        child.setParent(this);
        this.children.add(child);
    }

    
    public void setParent(SpaceNode parent) {
        this.parent = parent;
    }

    public SpaceNode getParent() {
        return this.parent;
    }

    public void addChild(SpaceNode child) {
        child.setParent(this);
        this.children.add(child);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public void removeParent() {
        this.parent = null;
    }

    public int getDirectOrbits(){
        if(this.isRoot()){
            return 0;
        }
        else{
            return 1;
        }
    }

    public int getIndirectOrbits(){
        if(this.isRoot()){
            return 0;
        }
        else{
            SpaceNode orbiter = this.parent;
            int orbits = 0;
            while(!orbiter.isRoot()){
                orbits++;
                orbiter = orbiter.getParent();
            }
            return orbits;
        }
    }

    public int getOrbitSum(){
        return getDirectOrbits() + getIndirectOrbits();
    }
}
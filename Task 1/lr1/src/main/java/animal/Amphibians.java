package animal;

public abstract class Amphibians extends Animal{
    public String uniq;

    public Amphibians(String name, int age, int maxRunDist, int maxSwimDist,String uniq) {

        super(name, age, maxRunDist, maxSwimDist);
        this.uniq = uniq;
    }

    public String uniqueness(){
        return  (this.uniq);
    }
}

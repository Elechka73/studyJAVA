package animal;

public class Chameleon extends Amphibians {
    public  static int count;
    public Chameleon(String name,int age, int maxRunDist, int maxSwimDist, String uniq)
    {
        super(name, age, maxRunDist, maxSwimDist,uniq);
        count++;
    }
}

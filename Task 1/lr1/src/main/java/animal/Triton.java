package animal;

public class Triton extends Amphibians{
    public  static int count;
    public Triton(String name,int age, int maxRunDist, int maxSwimDist, String uniq)
    {
        super(name, age, maxRunDist, maxSwimDist,uniq);
        count++;
    }
}

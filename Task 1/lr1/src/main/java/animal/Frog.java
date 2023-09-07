package animal;

public class Frog extends Amphibians{
    public  static int count;
    public Frog(String name,int age, int maxRunDist, int maxSwimDist,String uniq)
    {
        super(name, age, maxRunDist, maxSwimDist,uniq);
        count++;
    }
  }

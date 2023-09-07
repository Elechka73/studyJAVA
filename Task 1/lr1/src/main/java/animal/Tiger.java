package animal;

public class Tiger extends Animal{
    public  static int count;
    public Tiger (String name,int age, int maxRunDist, int maxSwimDist) {
        super(name, age, maxRunDist, maxSwimDist);
        count++;
    }
}

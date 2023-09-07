package animal;

public class Dog extends Animal{
    public  static int count;
    public Dog(String name,int age, int maxRunDist, int maxSwimDist)
    {
        super(name, age, maxRunDist, maxSwimDist);
        count++;
    }
}

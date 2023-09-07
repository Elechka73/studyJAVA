package animal;

public class Cat extends Animal{
    public  static int count;
    public Cat(String name,int age, int maxRunDist, int maxSwimDist)
    {
        super(name, age, maxRunDist, maxSwimDist);
        count++;
    }
}

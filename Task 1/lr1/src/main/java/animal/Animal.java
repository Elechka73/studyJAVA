package animal;

public class Animal {
    String name;
    int age;
    int maxRunDist;
    int maxSwimDist;
    public Animal(String name,int age, int maxRunDist, int maxSwimDist) {
        this.name = name;
        this.age = age;
        this.maxRunDist = maxRunDist;
        this.maxSwimDist = maxSwimDist;
    }

    public void run(int dist){
        if (maxRunDist == 0) System.out.println(this.name + " не умеет бегать");
        else if (dist<= maxRunDist) System.out.println(this.name + " не пробежал " + dist);
        else System.out.println(this.name + " пробежал " + dist);
    }
    public void swim(int dist2){
        if (maxSwimDist == 0) System.out.println(this.name + " не умеет плавать");
        else if (dist2<= maxSwimDist) System.out.println(this.name + " не проплыл " + dist2);
        else System.out.println(this.name + " проплыл " + dist2);
    }
}

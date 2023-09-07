package animal;

public class MainApp {
    public static void main(String[] args){
        Animal[] animals ={new Cat("Барсик", 2,200, 0),
        new Dog("Рекс", 5,500, 10),
        new Tiger("Пушистик", 4,1000, 50),
        new Triton("Тритон", 2,1, 10,"Тритон известен своей способностью к регенерации не только тканей, органов, но и частей тела"),
        new Frog("Флафи", 1,0, 5, "Глаза лягушек могут двигаться независимо друг от друга и вращаться на 360 градусов"),
        new Chameleon("Раскраска", 3,10, 2, "Уникальная способность хамелеона к мимикрии позволяет ему менять цвет кожи, сливаясь с окружающим пространством")};

          for (Animal a:animals) {
              a.run(100);
              a.swim(3);
          }
        System.out.println("Количество котов "+Cat.count);
        System.out.println("Количество собак "+Dog.count);
        System.out.println("Количество тигров "+Tiger.count);

        System.out.println("Уникальная способность лягушки: "+ ((Amphibians) animals[4]).uniqueness());
        System.out.println("Уникальная способность тритона: "+ ((Amphibians) animals[3]).uniqueness());
        System.out.println("Уникальная способность хамелеона: "+ ((Amphibians) animals[5]).uniqueness());
    }
}

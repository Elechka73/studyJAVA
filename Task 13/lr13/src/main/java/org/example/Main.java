package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TravelService service = context.getBean("travelService", TravelService.class);
        service.printAll();
        System.out.println("===========");
        service.order("2023-12-18",  "Тюмень");
        service.order("2023-12-15", "Иннополис");
        System.out.println("===========");
        //service.refund("2023-12-12", "Иннополис", "Оказывается, там невуксно кормят");

        //service.order("2023-11-28", "Киров");
    }
}
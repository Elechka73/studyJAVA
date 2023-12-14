package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class TravelService {
    private ArrayList<Trip> trips;

    @PostConstruct
    public void generateListOfTours() {
        trips = new ArrayList<>();
        trips.add(new Trip(1, "2023-12-15", "Киров", "Иннополис"));
        trips.add(new Trip(2, "2023-12-11", "Москва", "Санкт-Петербург"));
        trips.add(new Trip(3, "2023-11-30", "Казань", "Самара"));
        trips.add(new Trip(4, "2023-12-01", "Екатеринбург", "Нижний Новгород"));
        trips.add(new Trip(5, "2023-12-02", "Ростов-на-Дону", "Волгоград"));
        trips.add(new Trip(6, "2023-12-03", "Сочи", "Адлер"));
        trips.add(new Trip(7, "2023-12-18", "Омск", "Тюмень"));
        trips.add(new Trip(8, "2023-12-05", "Челябинск", "Уфа"));
        trips.add(new Trip(9, "2023-12-06", "Красноярск", "Новосибирск"));
        trips.add(new Trip(10, "2023-12-07", "Владивосток", "Хабаровск"));
    }
    public void printAll() {
        System.out.println("Список туров:");
        for (Trip p : trips) {
            System.out.println(p.printInfo());
        }
    }
    public Trip findByArrival(String arrival) {
        for (Trip trip : trips) {
            if (trip.getArrival().equals(arrival)) {
                return trip;
            }
        }
        return null;
    }
    public Trip findByDate(String date) {
        for (Trip trip : trips) {
            if (trip.getDate().equals(date)) {
                return trip;
            }
        }
        return null;
    }
    @Autowired
    Request request;
    @Autowired
    TravelersCard travelersCard;
    @Autowired
    MileService mileService;

    public void refund(String date, String arrival, String reason) {
        travelersCard.removeFromCard(date, arrival, reason);
    }
    public void order(String date, String arrival) {
        if(request.add(date, arrival)) {
           if(travelersCard.addToCard()) {
               mileService.sendEmail();
           }
        }
    }
}
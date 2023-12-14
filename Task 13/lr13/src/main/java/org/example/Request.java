package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class Request {

    public List<Trip> tripList = new ArrayList<>();

    @Autowired
    private TravelService travelService;

    public boolean add(String date,String arrival) {
        LocalDate dateNow = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = dateNow.format(formatter);
        Trip trip = travelService.findByDate(date);
        if (trip!= null && trip.getArrival().equals(arrival) && trip.getDate().equals(date)) {
            LocalDate tourDate = LocalDate.parse(trip.getDate(), formatter);
            if (tourDate.isBefore(dateNow)) {
                System.out.println("Нельзя добавить уже прошедший тур");
                return false;
            }
            System.out.println("Дата заявки: " + formattedDate);
            tripList.add(trip);
            System.out.println("Тур " + trip.getDeparture() + " - " + trip.getArrival() + " добавлен в заявку");
            return true;
        }
        else {
            System.out.println("Тур на такую дату или на такое направление не найден");
            return false;
        }
    }
}

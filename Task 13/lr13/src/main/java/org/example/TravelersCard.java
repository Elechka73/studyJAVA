package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class TravelersCard {
    int countUpcomingTour = 0;
    public ArrayList<Trip> card = new ArrayList<>();
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = date.format(formatter);
    LocalDate dateNow = LocalDate.parse(formattedDate, formatter);

    @Autowired
    Request request;

    @PostConstruct
    public void initSomeData() {
        card.add(new Trip(3, "2023-11-30", "Казань", "Самара"));
        //card.add(new Trip(2, "2023-11-29", "Москва", "Санкт-Петербург"));
    }

    public boolean addToCard() {
        countUpcomingTour =0;
        if(!request.tripList.isEmpty()) {
            for (Trip trip : request.tripList) {
                card.add(trip);
                System.out.println("Тур " + trip.getDeparture() + " - " + trip.getArrival() + " добавлен в карту путешественника");
            }
            for (Trip trip : card) {
                LocalDate tourDate = LocalDate.parse(trip.getDate(), formatter);
                if (tourDate.isAfter(dateNow)) countUpcomingTour ++;
            }
            System.out.println("Общее количество туров: " + card.size());
            System.out.println("Количество предстоящих туров: " + countUpcomingTour);
//            request.tripList.clear();
            return true;
        }
        else {
            System.out.println("В заявке нет туров");
            return false;
        }
    }
    public void removeFromCard(String dateOfTour, String arrival, String reason) {
        for (Trip t: card) {
            if(t.getDate().equals(dateOfTour) && t.getArrival().equals(arrival)) {
                LocalDate tourDate = LocalDate.parse(t.getDate(), formatter);
                if (tourDate.isBefore(dateNow)){
                    System.out.println("Вы пытаетесь вернуть уже прошедший тур");
                    return;
                }
                card.remove(t);
                System.out.println("Тур удалён из карты путешественника. Причина: " + reason);
                return;
            }
        }
    }
}
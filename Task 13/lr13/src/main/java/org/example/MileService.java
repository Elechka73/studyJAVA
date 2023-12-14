package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MileService {

    @Autowired
    Request request;
    public void sendEmail() {
        String email = "test@mail.ru";
        if (!request.tripList.isEmpty()) {
            StringBuilder message = new StringBuilder();
            for (Trip trip : request.tripList) {
                message.append(trip.printInfo()).append("\n");
            }
            System.out.println("Сообщение: \nДобавленный тур: \n" + message + "отправлено на " + email);
            request.tripList.clear();
        }
        else System.out.println("В вашей карте нет туров");
    }
}

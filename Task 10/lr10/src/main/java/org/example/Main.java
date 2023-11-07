package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    static Connection connection = null;
    static Statement statement = null;
    public static void main(String[] args) throws SQLException {
        connect();
        statement.execute("drop table if exists public.progress;\n" +
                "\n" +
                "drop table if exists public.student;\n" +
                "\n" +
                "drop table if exists public.subject;\n" +
                "\n");
        statement.execute("create table student\n" +
                "(\n" +
                "    id serial not null primary key,\n" +
                "    name text not null,\n" +
                "    passport_series smallint not null,\n" +
                "    passport_number smallint not null\n" +
                ");");
        statement.execute("create table subject\n" +
                "(\n" +
                "    id serial not null primary key,\n" +
                "    name_subject text not null\n" +
                ");");
        statement.execute("create table progress\n" +
                "(\n" +
                "    id serial not null primary key,\n" +
                "    estimation smallint not null,\n" +
                "    student smallint not null references student(id) on delete cascade,\n" +
                "    subject smallint not null references subject(id) on delete cascade\n" +
                ");");
        statement.execute("INSERT into student (id, name, passport_series, passport_number)\n" +
                "values\n" +
                "    (1, 'Вася', 3311, 435678),\n" +
                "    (2, 'Коля', 3322, 342345),\n" +
                "    (3, 'Петя', 3333, 123456),\n" +
                "    (4, 'Маша', 3344, 234567),\n" +
                "    (5, 'Даша', 3355, 345678),\n" +
                "    (6, 'Саша', 3366, 456789),\n" +
                "    (7, 'Лена', 3377, 567890),\n" +
                "    (8, 'Катя', 3388, 678901),\n" +
                "    (9, 'Оля', 3399, 789012),\n" +
                "    (10, 'Юля', 3300, 890123);");
        statement.execute("INSERT into subject (id, name_subject)\n" +
                "values\n" +
                "    (1, 'Математика'),\n" +
                "    (2, 'Русский язык'),\n" +
                "    (3, 'Физика'),\n" +
                "    (4, 'Химия'),\n" +
                "    (5, 'Биология'),\n" +
                "    (6, 'Информатика');");
        statement.execute("INSERT into progress (id, student, subject, estimation)\n" +
                "values\n" +
                "    (1, 1, 1, 5),\n" +
                "    (2, 1, 2, 4),\n" +
                "    (3, 1, 3, 5),\n" +
                "    (4, 1, 4, 4),\n" +
                "    (5, 1, 5, 5),\n" +
                "    (6, 1, 6, 4),\n" +
                "    (7, 2, 1, 3),\n" +
                "    (8, 2, 2, 4),\n" +
                "    (9, 2, 3, 3),\n" +
                "    (10, 2, 4, 4),\n" +
                "    (11, 2, 5, 3),\n" +
                "    (12, 2, 6, 4),\n" +
                "    (13, 3, 1, 5),\n" +
                "    (14, 3, 2, 5),\n" +
                "    (15, 3, 3, 5),\n" +
                "    (16, 3, 4, 5),\n" +
                "    (17, 3, 5, 5),\n" +
                "    (18, 3, 6, 5),\n" +
                "    (19, 4, 1, 4),\n" +
                "    (20, 4, 2, 4),\n" +
                "    (21, 4, 3, 4),\n" +
                "    (22, 4, 4, 4),\n" +
                "    (23, 4, 5, 4),\n" +
                "    (24, 4, 6, 4),\n" +
                "    (25, 5, 1, 3),\n" +
                "    (26, 5, 2, 3),\n" +
                "    (27, 5, 3, 3),\n" +
                "    (28, 5, 4, 3),\n" +
                "    (29, 5, 5, 3),\n" +
                "    (30, 5, 6, 3),\n" +
                "    (31, 6, 1, 2),\n" +
                "    (32, 6, 2, 2),\n" +
                "    (33, 6, 3, 2),\n" +
                "    (34, 6, 4, 2),\n" +
                "    (35, 6, 5, 2),\n" +
                "    (36, 6, 6, 2);\n");
        statement.execute("alter  table student add constraint unicue_passport unique (passport_number,passport_series);");
        statement.execute("alter table progress add constraint check_estimation check (estimation<=5 and estimation>=2);");
        System.out.println("Вывести список студентов, сдавших определенный предмет, на оценку выше 3");
        var res1 = statement.executeQuery("select s.name, p.estimation, ss.name_subject from student s\n" +
                "                                                      inner join progress p on s.id = p.id\n" +
                "                                                      inner join subject ss on ss.id = p.id\n" +
                "where p.estimation > 3 and ss.name_subject = 'Химия';");
        while (res1.next()){
            String name = res1.getString(1);
            int est = res1.getInt(2);
            String nameS = res1.getString(3);
            System.out.println(name + " " + est + " " + nameS);
        }
        System.out.println("Посчитать средний бал по определенному предмету");
        var res2 = statement.executeQuery("select avg(p.estimation) as \"Средний балл\" from progress p\n" +
                "                                                    inner join subject s on s.id = p.subject\n" +
                "where s.name_subject = 'Физика';");
        while (res2.next()){
            float est = res2.getInt(1);
            System.out.println(est);
        }
        System.out.println("Посчитать средний балл по определенному студенту");
        var res3 = statement.executeQuery("select avg(p.estimation) as \"Средний балл\" from progress p\n" +
                "                                                    inner join subject s on  p.subject = s.id\n" +
                "                                                    inner join student s2 on p.student = s2.id\n" +
                "where s2.name = 'Петя';");
        while (res3.next()){
            float est = res3.getInt(1);
            System.out.println(est);
        }
        System.out.println("Найти три премета, которые сдали наибольшее количество студентов");
        var res4 = statement.executeQuery("SELECT count(*), s.name_subject from progress p\n" +
                "                                         inner join subject s on s.id = p.subject\n" +
                "where p.estimation > 2\n" +
                "group by s.name_subject\n" +
                "order by count(*) desc limit 3;");
        while (res4.next()){
            int count = res4.getInt(1);
            String nameS = res4.getString(2);
            System.out.println(count + " " + nameS);
        }
        disconnect();
    }
    public static void connect() {

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java", "postgres", "5555");
            statement = connection.createStatement();
            System.out.println("Connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void disconnect() {
        try{
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
            System.out.println("Disconnected");
        }
        catch (SQLException ee) {
            ee.printStackTrace();
        }
    }
}

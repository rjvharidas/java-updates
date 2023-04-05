package org.rjv.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Object> peoples= new ArrayList<>();
        peoples.add(getEmployees());
        peoples.add(getPersons());

        peoples.stream().filter(obj -> obj instanceof Employee).map(obj -> (Employee) obj).map(e -> "Name : " + e.name()).forEach(System.out::println);

//        System.out.println(peoples.stream()
////                        .filter(p-> p instanceof Employee(int id, String name, String phone, var department))
//                .map(Object::toString)
//                .collect(Collectors.joining(" \n")));
    }

    private static List<Employee> getEmployees() {
        return IntStream.range(0, 10)
                .mapToObj(i -> getEmployee())
                .collect(Collectors.toList());
    }

    private static Employee getEmployee() {
        List<String> departments = List.of("IT", "Accounts", "HR", "Operations");
        Random random = new Random();
        return new Employee<>(random.nextInt(3, 999), getRandomNames(),
                getRandomPhone(), departments.get(random.nextInt(departments.size())));
    }

    private static List<Person> getPersons() {
        return IntStream.range(0, 10)
                .mapToObj(i -> getPerson())
                .collect(Collectors.toList());
    }

    private static Person getPerson() {
        List<String> departments = List.of("IT", "Accounts", "HR", "Operations");
        Random random = new Random();
        return new Person(random.nextInt(3, 999), getRandomNames(),
                getRandomPhone(), departments.get(random.nextInt(departments.size())));
    }

    private static String getRandomPhone() {
        Random random = new Random();
        return "9658777" + random.nextInt(10000);
    }

    public static String getRandomNames() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    record Employee<Department>(int id, String name, String phone, Department department) {
    }

    record Person<Department>(int id, String name, String phone, Department department) {
    }
}
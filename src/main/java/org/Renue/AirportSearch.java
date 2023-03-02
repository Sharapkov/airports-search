package org.Renue;

import com.harium.storage.kdtree.KDTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirportSearch {


    public static void main(String[] args) {

        // загрузка данных из файла
        List<Airport> airports = loadAirports("src/main/resources/airports.dat");
        // создание kdtree
        KDTree<Airport> kdTree = new KDTree<>(2);
        for (Airport airport : airports) {
            double[] key = new double[] {airport.getLatitude(), airport.getLongitude()};
            kdTree.insert(key, airport);
        }
        // координаты пользователя
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите широту: ");
        double lat = scanner.nextDouble();
        System.out.print("Введите долготу: ");
        double lon = scanner.nextDouble();
        double[] coordinates = new double[] {lat, lon};

        long start = System.currentTimeMillis();
        //поиск 5 ближайших аэропортов к указанным координатам
        List<Airport> nearestAirports = kdTree.nearest(coordinates, 5);
        // вывод результатов
        for (Airport airport : nearestAirports) {
            System.out.println(airport.getName() + " Координаты: " + airport.getLatitude() + ", " + airport.getLongitude());
        }
        long end = System.currentTimeMillis();
        long totalTime = end - start;
        System.out.println("Затраченное время: " + totalTime + " мс");
    }

    private static List<Airport> loadAirports(String csfFile) {

        String line;
        String delimiter = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"; //мы ищем запятую, за которой следует четное количество
                                                                // кавычек (или нечетное количество, если запятая находится внутри кавычек)
        List<Airport> airports = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/airports.dat"))) {
            while ((line = br.readLine()) != null) {
                // обрабатываем строку с аэропортом
                String[] fields = line.split(delimiter);
                // сохраняем в лист имя и координаты x, y;
                String name = fields[1];
                double latitude = Double.parseDouble(fields[6]);
                double longitude = Double.parseDouble(fields[7]);
                airports.add(new Airport(name, latitude, longitude));
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла airports.dat: " + e.getMessage());
        }
        return airports;
    }
}
package com.example.airportssearch;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;



@SpringBootApplication
public class AirportsSearchApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AirportsSearchApplication.class, args);

		int a = Integer.parseInt(args[0]);
		getLinked();
		Scanner scanner = new Scanner(System.in);
		String findTo = scanner.next();
		while(!(findTo.equals("!quit"))) {
			goFind(a, findTo);
			findTo = scanner.next();
		}
		System.exit(0);
	}

	public static String getLinked() throws IOException { // get запрос яндексу, возвращает ссылку на csv file

		URL url = new URL("https://cloud-api.yandex.net/v1/disk/public/resources/download?public_key=https%3A%2F%2Fdisk.yandex.ru%2Fi%2Fg1riHSgEntfLYQ&fields=href");
		URLConnection connection = url.openConnection();
		StringBuilder result = new StringBuilder();
		String line;
		InputStreamReader input = new InputStreamReader(connection.getInputStream());
		BufferedReader buffer = new BufferedReader(input);

		try {
			while ((line = buffer.readLine()) != null) {
				result.append(line);
			}

			result.deleteCharAt(result.length() - 1); // нам приходит JSON-file с 1 полем href:{"..."}
			result.deleteCharAt(result.length() - 1); // обрезаем, чтобы осталась голая ссылка
			result.delete(0, 9);

		} catch (HttpClientErrorException.NotFound | IOException e) {
			e.printStackTrace();
		}

		return result.toString();
	}


	public static void goFind(int a, String findTo) throws IOException {

		String newUrl = getLinked();
		URL ur = new URL(newUrl);
		URLConnection connect = ur.openConnection();

		String ln;
		String csvSplit = ",";
		String pattern = ("^." + findTo + ".*");
		String s[];
		int count = 0;
		TreeMap<String, String[]> treeMap = new TreeMap<>();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()))) {

				long time = System.currentTimeMillis(); // начало отсчета времени на поиск
				while ((ln = br.readLine()) != null) {
					s = ln.split(csvSplit);
					if (s[a - 1].matches(pattern)) {
						treeMap.put(s[a-1],s);
						count++;
					}
				}
				for(Map.Entry<String, String[]> entry : treeMap.entrySet()){
					System.out.println(entry.getKey() + Arrays.toString(entry.getValue()));
				}
				System.out.println("\n" + "milliseconds per operation:" + (System.currentTimeMillis() - time) + "\n" + "matches found:" + count + "\n" + "Please enter a world for find or type !quit to exit:");

			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}



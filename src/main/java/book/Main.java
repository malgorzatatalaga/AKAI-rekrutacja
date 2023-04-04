package book;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://akai-recruitment-api.prod.ang3r.pl/books")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(Main::parse)
                .join();
    }



    public static String parse(String responseBody) {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        Multimap<String, Double> multimap = ArrayListMultimap.create();
        Map<String, Double> map = new HashMap<>();
        JSONArray books = new JSONArray(responseBody);
        createObject(bookArrayList, multimap, books);

        for (Book book : bookArrayList) {
            Collection<Double> values = multimap.get(book.getAuthor());
            OptionalDouble average = values.stream().mapToDouble(a -> a).average();
            map.put(book.getAuthor(), average.getAsDouble());
        }

        map = sortByValue(map);

//        for (Map.Entry<String, Double> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }

        print(map);



        return null;
    }

    private static void createObject(ArrayList<Book> bookArrayList, Multimap<String, Double> map, JSONArray books) {
        for (int i = 0; i < books.length(); i++) {
            JSONObject book = books.getJSONObject(i);
            int id = book.getInt("id");
            String title = book.getString("title");
            String author = book.getString("author");
            double rating = book.getDouble("rating");
            Book book1 = new Book(id, title, author, rating);
            bookArrayList.add(book1);
            map.put(book1.getAuthor(), book1.getRating());

        }
    }

    public static HashMap<String, Double> sortByValue(Map<String, Double> map) {
        List<Map.Entry<String, Double> > list = new LinkedList<>(map.entrySet());

        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));
        Collections.reverse(list);

        HashMap<String, Double> hashMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> aa : list) {
            hashMap.put(aa.getKey(), aa.getValue());
        }
        return hashMap;
    }

    public static void print(Map<String, Double> map) {
        Object[] doubles = map.values().toArray();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            for (int i = 0; i < 3; i++){
                if (Objects.equals(doubles[i], entry.getValue())) {
                    System.out.println(entry.getKey() + " - " + doubles[i]);
                }
            }
        }
    }

}



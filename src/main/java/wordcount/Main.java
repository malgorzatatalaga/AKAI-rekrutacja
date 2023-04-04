package wordcount;
import java.util.*;

public class Main {

    private static String[] sentences = {
            "Taki mamy klimat",
            "Wszędzie dobrze ale w domu najlepiej",
            "Wyskoczył jak Filip z konopii",
            "Gdzie kucharek sześć tam nie ma co jeść",
            "Nie ma to jak w domu",
            "Konduktorze łaskawy zabierz nas do Warszawy",
            "Jeżeli nie zjesz obiadu to nie dostaniesz deseru",
            "Bez pracy nie ma kołaczy",
            "Kto sieje wiatr ten zbiera burzę",
            "Być szybkim jak wiatr",
            "Kopać pod kimś dołki",
            "Gdzie raki zimują",
            "Gdzie pieprz rośnie",
            "Swoją drogą to gdzie rośnie pieprz?",
            "Mam nadzieję, że poradzisz sobie z tym zadaniem bez problemu",
            "Nie powinno sprawić żadnego problemu, bo Google jest dozwolony"
    };

    public static void main(String[] args) {
        /* TODO Twoim zadaniem jest wypisanie na konsoli trzech najczęściej występujących słów
                w tablicy 'sentences' wraz z ilością ich wystąpień..

                Przykładowy wynik:
                1. "mam" - 12
                2. "tak" - 5
                3. "z" - 2
        */
        ArrayList<String> stringArrayList = stringsArray(sentences);
        Map<String, Integer> output = wordCount(stringArrayList);
        Map<String, Integer> sorted = sortByValue(output);
        print(sorted);

    }

    public static Map<String, Integer> wordCount(ArrayList<String> strings) {
        Map<String, Integer> counterMap = new HashMap<>();
        for (String s : strings) {
            counterMap.compute(s, (k, v) -> v == null ? 1 : v + 1);
        }

        return counterMap;
    }

    public static ArrayList<String> stringsArray(String[] stringsInput) {
        ArrayList<String> stringsOutput = new ArrayList<>();
        for (String s : stringsInput) {
            String[] strings = s.replace(',', ' ').replace('?', ' ').split(" ");
            for (String split : strings) {
                stringsOutput.add(split.toLowerCase(Locale.ROOT));
            }
        }

        return stringsOutput;
    }

    public static HashMap<String, Integer> sortByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer> > list = new LinkedList<>(map.entrySet());

        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));
        Collections.reverse(list);

        HashMap<String, Integer> hashMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            hashMap.put(aa.getKey(), aa.getValue());
        }
        return hashMap;
    }

    public static void print(Map<String, Integer> map) {
        Object[] ints = map.values().toArray();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            for (int i = 0; i < 3; i++){
                if (Objects.equals(ints[i], entry.getValue())) {
                    System.out.println(i + 1 + ". " + '"' + entry.getKey() + '"' + " - " + ints[i]);
                }
            }
        }
    }

}








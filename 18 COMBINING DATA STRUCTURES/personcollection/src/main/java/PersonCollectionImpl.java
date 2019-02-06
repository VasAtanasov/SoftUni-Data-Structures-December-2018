import java.util.*;
import java.util.stream.Collectors;

public class PersonCollectionImpl implements PersonCollection {

    private Map<String, Person> byEmail;
    private Map<String, Set<Person>> byDomain;
    private Map<Integer, Set<Person>> byAge;
    private Map<String, Map<Integer, Set<Person>>> byTownAge;

    public PersonCollectionImpl() {
        this.byEmail = new TreeMap<>();
        this.byDomain = new HashMap<>();
        this.byAge = new TreeMap<>();
        this.byTownAge = new HashMap<>();
    }

    @Override
    public boolean addPerson(String email, String name, int age, String town) {
        if (this.byEmail.containsKey(email)) {
            return false;
        }
        Person person = new Person(email, name, age, town);
        this.byEmail.put(email, person);
        this.addByDomain(email, person);
        this.addByAge(age, person);
        this.addTownAge(town, age, person);
        return true;
    }

    private void addTownAge(String town, int age, Person person) {
        this.byTownAge.putIfAbsent(town, new TreeMap<>());
        this.byTownAge.get(town).putIfAbsent(age, new TreeSet<>(Comparator.comparing(Person::getEmail)));
        this.byTownAge.get(town).get(age).add(person);
    }

    private void addByAge(int age, Person person) {
        this.byAge.putIfAbsent(age, new TreeSet<>(Comparator.comparing(Person::getEmail)));
        this.byAge.get(age).add(person);
    }

    private void addByDomain(String email, Person person) {
        String domain = this.getDomain(email);
        this.byDomain.putIfAbsent(domain, new TreeSet<>(Comparator.comparing(Person::getEmail)));
        this.byDomain.get(domain).add(person);
    }

    @Override
    public int getCount() {
        return this.byEmail.size();
    }

    @Override
    public Person findPerson(String email) {
        if (! this.byEmail.containsKey(email)) {
            return null;
        }
        return this.byEmail.get(email);
    }

    @Override
    public boolean deletePerson(String email) {
        if (! this.byEmail.containsKey(email)) {
            return false;
        }

        Person person = this.byEmail.get(email);

        this.byEmail.remove(email);
        this.byDomain.get(this.getDomain(email)).remove(person);
        this.byAge.get(person.getAge()).remove(person);
        this.byTownAge.get(person.getTown()).get(person.getAge()).remove(person);

        return true;
    }

    @Override
    public Iterable<Person> findPersons(String emailDomain) {
        if (! this.byDomain.containsKey(emailDomain)) {
            return Collections.emptyList();
        }
        return this.byDomain.get(emailDomain);
    }

    @Override
    public Iterable<Person> findPersons(String name, String town) {
        if (! this.byTownAge.containsKey(town)) {
            return Collections.emptyList();
        }
        return this.byTownAge.get(town)
                .values()
                .stream()
                .flatMap(Collection::stream)
                .filter(person -> person.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge) {
        return this.byAge.entrySet()
                .stream()
                .filter(pair -> pair.getKey().compareTo(startAge) >= 0)
                .filter(pair -> pair.getKey().compareTo(endAge) <= 0)
                .flatMap(pair -> pair.getValue().stream())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge, String town) {
        if (! this.byTownAge.containsKey(town)) {
            return Collections.emptyList();
        }
        return this.byTownAge.get(town)
                .entrySet()
                .stream()
                .filter(entry -> this.inRange(startAge, endAge, entry.getKey()))
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());
    }

    private boolean inRange(int start, int end, int value) {
        return start <= value && value <= end;
    }

    private String getDomain(String email) {
        return email.substring(email.indexOf("@") + 1);
    }
}

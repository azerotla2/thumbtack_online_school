package net.thumbtack.school.database.function;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    static String str = "Hello world !!!";

    public static void main(String[] args) {
        first();
        second();
        third();
        fourth();
        fifth();
        sixth();

    }

    static void first() {
        System.out.println("\n\n\n________________________________________START_OF_FIRST_____________________________________________________\n");

        Function<String, List<String>> splitFunc = stringToSplit -> List.of(stringToSplit.split(" "));
        MyFunction<String, List<String>> mySplitFunc = stringToSplit -> List.of(stringToSplit.split(" "));
        Function<List<?>, Integer> countFunc = List::size;
        Function<List<?>, Integer> myCountFunc = List::size;

        List<String> splitResult = splitFunc.apply(str);
        Integer countResult = countFunc.apply(splitResult);
        List<String> mySplitResult = mySplitFunc.apply(str);
        Integer myCountResult = myCountFunc.apply(splitResult);

        System.out.println(splitResult);
        System.out.println(countResult);
        System.out.println(mySplitResult);
        System.out.println(myCountResult);

        countFunc.apply(splitFunc.apply(str));

        System.out.println("\n_______________________________________END_OF_FIRST______________________________________________________\n");
    }

    static void second() {
        System.out.println("\n\n\n________________________________________START_OF_SECOND_____________________________________________________\n");

        Function<String, List<String>> splitFunc = stringToSplit -> List.of(stringToSplit.split(" "));
        Function<String, Integer> func = splitFunc.andThen(List::size);
        System.out.println(func.apply(str));

        System.out.println("\n_______________________________________END_OF_SECOND______________________________________________________\n");
    }

    static void third() {
        System.out.println("\n\n\n________________________________________START_OF_THIRD_____________________________________________________\n");

        Function<String, Person> createFunc = Person::new;
        Person personOnlyFirstname = createFunc.apply("Misha");
        System.out.println(personOnlyFirstname);

        PersonFactory personFactory = Person::new;
        Person person = personFactory.create("Ivan", "Ivanov");
        System.out.println(person);

        System.out.println("\n_______________________________________END_OF_THIRD______________________________________________________\n");
    }

    static void fourth() {
        System.out.println("\n\n\n________________________________________START_OF_FOURTH_____________________________________________________\n");

        BiFunction<Integer, Integer, Integer> max = Math::max;
        Supplier<LocalDate> getCurrentDate = () -> LocalDate.now();
        Predicate<Integer> isEven = Objects::nonNull;
        BiPredicate<Integer, Integer> areEqual = Integer::equals;


        System.out.println("\n_______________________________________END_OF_FOURTH______________________________________________________\n");
    }

    static void fifth(){
        Person grandGrandPa = new Person("Oleg", null, null);
        Person grandMa = new Person("Olga", null, grandGrandPa);
        Person mother = new Person("Victoria", grandMa, null);

        getMothersMotherFather(mother);

        Person grandGrandFather = new Person(Optional.ofNullable(null), Optional.ofNullable(null));
        Person grandMother = new Person(Optional.ofNullable(null), Optional.of(grandGrandFather));
        Person motherOptional = new Person(Optional.of(grandMother), Optional.ofNullable(null));

        getMothersMotherFatherOptional(Optional.of(motherOptional));
    }

    static void sixth(){
        Person person1 = new Person("Oleg", 32);
        Person person2 = new Person("Mikhail", 24);
        Person person3 = new Person("Andrey", 41);
        Person person4 = new Person("Vladimir", 31);
        Person person5 = new Person("Ivan", 43);
        Person person6 = new Person("Vladimir", 50);

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);

        List<String> resultList = personList.stream().filter(p -> p.getAge() > 30).
                map(Person::getFirstname).distinct().
                sorted(Comparator.comparingInt(String::length)).
                collect(Collectors.toList());


        List<String> resultList2 = personList.stream().filter(p -> p.getAge() > 30).
                collect(Collectors.groupingBy(Person::getFirstname)).
                values().stream().sorted(Comparator.comparingInt(List::size)).
                flatMap(List::stream).map(Person::getFirstname).
                distinct().collect(Collectors.toList());

        int sum = personList.stream().mapToInt(p -> personList.indexOf(p) + 1).reduce(0, Integer::sum);
        int product = personList.stream().mapToInt(p -> personList.indexOf(p) + 1).reduce(1, (n1, n2) -> n1 * n2 );
    }

    static Person getMothersMotherFather(Person mother){
        Person grandMa = mother.getMother();
        if(grandMa == null)
            return null;
        else{
            Person grandGrandPa = grandMa.getFather();
            return grandGrandPa;
        }
    }

    static Optional<Person> getMothersMotherFatherOptional(Optional<Person> mother){
        if(mother.isEmpty())
            return Optional.empty();
        else{
            Optional<Person> grandmother = mother.flatMap(Person::getMotherOptional);
            if (grandmother.isEmpty())
                return Optional.empty();
            else {
                Optional<Person> grandGrandFather = grandmother.flatMap(Person::getFatherOptional);
                if(grandGrandFather.isEmpty())
                    return Optional.empty();
                else
                    return grandGrandFather;
            }
        }
    }
    IntStream transform(IntStream stream, IntUnaryOperator op){
        return stream.map(op);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}

@FunctionalInterface
interface Max {
    int max(int a, int b);
}

@FunctionalInterface
interface MyFunction <T, K>{
    K apply(T arg1);
}

@FunctionalInterface
interface PersonFactory {
    Person create(String firstname, String lastname);
}

class Person {
    String firstname;
    String lastname;
    Person mother;
    Person father;
    Integer age;
    Optional<Person> motherOptional;
    Optional<Person> fatherOptional;

    Person(String firstname) {
        this.firstname = firstname;
    }

    Person(String firstname, Person mother, Person father){
        this.firstname = firstname;
        this.mother = mother;
        this.father = father;
    }

    Person (String firstname, Integer age){
        this.firstname = firstname;
        this.age = age;
    }

    Person(Optional<Person> motherOptional, Optional<Person> fatherOptional){
        this.motherOptional = motherOptional;
        this.fatherOptional = fatherOptional;
    }

    Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Person getMother(){
        return mother;
    }

    public Person getFather(){
        return father;
    }

    public Optional<Person> getMotherOptional() {
        return motherOptional;
    }

    public Optional<Person> getFatherOptional() {
        return fatherOptional;
    }

    public Integer getAge() {
        return age;
    }
}


package crudwebapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Created by arnoldas on 17.5.30.
 */
public class StreamsTutorial {

    class Product {
    int price;
    String name;
        Product(int price, String name) {
            this.price = price;
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "price=" + price +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public Stream<String> streamOf(List<String> list) {
        return list == null || list.isEmpty() ? Stream.empty() : list.stream();
    }

    private long counter;

    @Test
    public void Baeldung_EmptyStream() {
        System.out.println("--||--");

        String[] list = {"s", "a"};
//        list = null;

        streamOf(Arrays.asList(list))
                .filter(s -> Objects.nonNull(s))
                .forEach(s -> System.out.println(s));

    }

    @Test
    public void Baeldung_StreamOfCollection() {
        System.out.println("--||--");

        Collection<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> streamOfCollection = collection.stream();
        streamOfCollection.forEach(s -> System.out.print(s));

        System.out.println();

        String[] arr = new String[] {"a", "b", "c"};
        Arrays.stream(arr, 1, 2).forEach(s -> System.out.print(s));
    }

    @Test
    public void Baeldung_StreamBuilder() {
        System.out.println("--||--");

        Stream<String> streamBuilder = Stream
                .<String>builder()
                .add("a")
                .add("b")
                .add("c")
                .build();
        streamBuilder.forEach(s -> System.out.print(s));
    }

    @Test
    public void Baeldung_StreamGenerate() {
        System.out.println("--||--");

        Stream<String> streamGenerated = Stream
                .generate(() -> "a")
                .limit(3);
        streamGenerated.forEach(s -> System.out.print(s));
    }

    @Test
    public void Baeldung_StreamIterate() {
        System.out.println("--||--");

        Stream<Integer> streamIterated = Stream
                .iterate(40, n -> n+2)
                .limit(3);
        streamIterated.forEach(s -> System.out.print(s+" "));
    }

    @Test
    public void Baeldung_StreamOfPrimitives() {
        System.out.println("--||--");

        IntStream intStream = IntStream.range(1, 3);
        intStream.forEach(s -> System.out.print(s+" "));

        System.out.println();
        LongStream longStream = LongStream.range(1, 4);
        longStream.forEach(s -> System.out.print(s+" "));

        System.out.println();
        Random random = new Random();
        DoubleStream doubleStream = random.doubles(3);
        doubleStream.forEach(s -> System.out.print(s+" "));
    }

    @Test
    public void Baeldung_StreamOfString() {
        System.out.println("--||--");

        IntStream streamOfChars = "abc".chars();
        streamOfChars.forEach(s -> System.out.print(s+" "));

        System.out.println();
        Stream<String> streamOfString = Pattern
                .compile(", ")
                .splitAsStream("a, b, c");

        streamOfString.forEach(s -> System.out.print(s+" "));

        System.out.println();
        Stream
                .of(("Word").split(""))
                .map(s -> {if(s.equals(s.toUpperCase())) {return "x";} else{return s;}})
                .forEach(s -> System.out.print(s+" "));
    }

    @Test
    public void Baeldung_StreamReferencing() {
        System.out.println("--||--");

        Stream<String> stream =
                Stream.of("a", "b", "c")
                      .filter(element -> element.contains("b"));

        Optional<String> anyElement = stream.findAny();
        System.out.print(anyElement.filter(Objects::nonNull).get());

//        attempt to reuse the same reference after calling the terminal operation will trigger the IllegalStateException
//        Optional<String> firstElement = stream.findFirst(); //IllegalStateException: stream has already been operated upon
    }

    private void wasCalled() {
        counter++;
    }

    @Test
    public void Baeldung_StreamLazyInvocation() {
        System.out.println("--||--");

        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        counter = 0L;
        Stream<String> stream = list
                .stream()
                .filter(element ->
                        {
                            wasCalled();
                            return element.contains("2");
                        }
                        );
        System.out.println("Before terminal op: "+ counter);
            stream.findFirst();
        System.out.println("After terminal op: "+ counter);

        System.out.println();

        /**
         * pipeline executes vertically.
         * In our example the first element of the stream didn’t satisfy filter’s predicate,
         * then the filter() method was invoked for the second element,
         * which passed the filter.
         * Without calling the filter() for third element we went down through
         * pipeline to the map() method.
         *
         * The findFirst() operation satisfies by just one element.
         * So, in this particular example the lazy invocation allowed
         * to avoid two method calls – one for the filter() and one for the map().
         */

        Optional<String> stream2 = list
                .stream()
                .filter(element -> {
                    System.out.println("-filter() was called");
                    return element.contains("2");
                })
                .map(element -> {
                    System.out.println("-map() was called");
                    return element.toUpperCase();
                })
                .findFirst()
                .filter(s -> {
                    System.out.print(s + " ");
                    return true;
                });
    }

    @Test
    public void Baeldung_StreamOrderOfExecution() {
        System.out.println("--||--");

        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        counter = 0L;

        /**
         * Execution of this code will increase the value of the counter by three.
         * This means that the map() method of the stream was called three times.
         * But the value of the size is one. So, resulting stream has just one
         * element and we executed the expensive map() operations for no reason
         * twice out of three times.
         */
        long size = list
                .stream()
                .map(element -> {wasCalled();
                                System.out.println("-map()");
                                return element.substring(0, 3);})
                .skip(2)
                .count();
        System.out.println(size);

        System.out.println("");

        /**
         * rule: intermediate operations which reduce the size of the stream
         * should be placed before operations which are applying to each element
         */
        long size2 = list
                .stream()
                .skip(2)
                .map(element -> {wasCalled();
                    System.out.println("-map()");
                    return element.substring(0, 3);})
                .count();
        System.out.println(size2);
    }

    @Test
    public void Baeldung_StreamReduceMethod() {
        System.out.println("--||--");

        OptionalInt reduced = IntStream
                .range(1, 4) //generate 1, 2, 3
                .reduce((a, b) -> a + b); //  ((1+2)+3)
                //reduced = 6 (1 + 2 + 3)
        System.out.println(reduced);

        System.out.println();

        int reducedTwoParams = IntStream
                .range(1, 4)
                .reduce(10, (a, b) -> {
                            System.out.println("-reduce" + " a=" + a + " b=" + b);
                            return (a + b);
                        }
                ); //(((10+1)+2)+3)
        System.out.println(reducedTwoParams);

        System.out.println();

        int reducedParallel = Arrays
                .asList(1, 2, 3)
                .parallelStream()
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                            System.out.println("-combiner" + " a=" + a + " b=" + b);
                            return a + b;
                        }
                ); //  (10+1)+(10+2)+(10+3)
        System.out.println(reducedParallel);
    }

    @Test
    public void Baeldung_StreamCollectMethod() {
        System.out.println("--||--");

        List<Product> productList = Arrays
                .asList(new Product(23, "potatoes"),
                        new Product(14, "orange"),
                        new Product(13, "lemon"),
                        new Product(23, "bread"),
                        new Product(13, "sugar"));

        List<String> productNames = productList
                .stream()
                .map(p -> {
                    System.out.println("-map() "+ p.getName());
                    return p.getName();
                })
                .collect(Collectors.toList());



        System.out.println();
        String listToString = productList
                .stream()
                .map(Product::getName)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(listToString);



        System.out.println();
        double averagePrice = productList
                .stream()
                .collect(Collectors.averagingInt(Product::getPrice));
        System.out.println(averagePrice);



        System.out.println();
        int summingPrice = productList
                .stream()
                .collect(Collectors.summingInt(Product::getPrice));
        System.out.println(summingPrice);



        System.out.println();
        IntSummaryStatistics statistics = productList
                .stream()
                .collect(Collectors.summarizingInt(Product::getPrice));
        System.out.println(statistics);



        System.out.println();
        Map<Integer, List<Product>> collectorMapOfLists = productList
                .stream()
                .collect(Collectors.groupingBy(Product::getPrice));

        for (Map.Entry<Integer, List<Product>> entry : collectorMapOfLists.entrySet())
        {
            System.out.println(entry.getKey() + " :: " + entry.getValue());
        }



        System.out.println();
        Map<Boolean, List<Product>> mapPartitioned = productList
                .stream()
                .collect(Collectors.partitioningBy(element -> element.getPrice() > 15));

        for (Map.Entry<Boolean, List<Product>> entry : mapPartitioned.entrySet())
        {
            System.out.println(entry.getKey() + " :: " + entry.getValue());
        }



        System.out.println();
        Set<Product> unmodifiableSet = productList
                .stream()
                .collect(Collectors.collectingAndThen(
                                        Collectors.toSet(),
                                        Collections::unmodifiableSet
                                        ));
        for(Product p : unmodifiableSet) {
            System.out.println(p);
        }


        System.out.println();
        System.out.println("~~~~~~~custom collector~~~~~~~");
        Collector<Product, ?, LinkedList<Product>> toLinkedList =
                Collector.of(LinkedList::new, LinkedList::add,
                             (first, second) -> {
                                 first.addAll(second);
                                 return first;
                             });
        LinkedList<Product> linkedListOfPersons = productList
                .stream()
                .collect(toLinkedList);
        for(Product p : linkedListOfPersons) {
            System.out.println(p);
        }
    }

    @Test
    public void JournalDev_StreamSorted() {
        System.out.println("--||--");

        Stream<String> names1 = Stream.of("aBc", "d", "ef", "123456");
        List<String> reverseSorted = names1
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(reverseSorted); // [ef, d, aBc, 123456]



        System.out.println();
        Stream<String> names2 = Stream.of("aBc", "d", "ef", "123456");
        List<String> naturalSorted = names2
                .sorted()
                .collect(Collectors.toList());
        System.out.println(naturalSorted); //[123456, aBc, d, ef]
    }

    @Test
    public void JournalDev_StreamFlatMap() {
        System.out.println("--||--");

        List<List<String>> lolon = new ArrayList<>();
        lolon.add(Arrays.asList("Pankaj"));
        lolon.add(Arrays.asList("David", "Lisa"));
        lolon.add(Arrays.asList("Amit"));

        System.out.println("Map\n");
        List<String> namesMap = lolon.stream()
                                     .map(l -> {
                                         System.out.println(l);
                                         return l.get(0);
                                     })
                                     .collect(Collectors.toList());
        System.out.println(namesMap);



        System.out.println();
        System.out.println("Flat map\n");
        List<String> namesFlatMap = lolon.stream()
                                         .peek(o -> System.out.println(o))
                                         .flatMap(l ->  l.stream())
                                         .collect(Collectors.toList());
        System.out.println(namesFlatMap);
    }

    @Test
    public void JournalDev_StreamMatch() {
        System.out.println("--||--");

        Integer[] numbers = new Integer[] {1,2,3,4,5};

        System.out.println("Stream contains 4? "+
                                   Stream.of(numbers)
                                         .anyMatch(i -> i==4));

        System.out.println("Stream contains all elements less than 10? "+
                                   Stream.of(numbers)
                                         .allMatch(i -> i<10));

        System.out.println("Stream doesn't contain 10? "+
                                   Stream.of(numbers)
                                         .noneMatch(i -> i==10));
    }

    @Test
    public void JournalDev_ParallelProblem() {
        System.out.println("--||--");

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        List<Integer> result = new ArrayList<>();

        integers.stream()
                .map(s -> {
                    synchronized (result) {
                        if (result.size() < 10) {
                            result.add(s);
                        }
                    }
                    return s;
                })
                .forEach(e -> {
                });
        System.out.println("Stream: "+ result);

        result.clear();
        System.out.println();

        integers.parallelStream()
                .map(s -> {
                    synchronized (result) {
                        if (result.size() < 10) {
                            result.add(s);
                        }
                    }
                    return s;
                })
                .forEach(e -> {
                });
        System.out.println("Parallel stream: "+ result);
    }

    @Test
    public void JournalDev_StreamPeek() {
        System.out.println("--||--");
        List<String> list = Arrays.asList("A", "B", "C");

        list.stream()
            .peek(s -> System.out.println(s))
            .peek(s -> System.out.println("Side effects"))
            .findFirst();
    }


















}
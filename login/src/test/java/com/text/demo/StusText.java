package com.text.demo;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StusText {

    /**
     * index.do?itemId=1&userId=10000&type=20&token=111111111111&key=index
     */
    @Test
    public void test1() {
        //将地址拆分成map集合
        String queryString = "itemId=1&userId=10000&type=20&token=111111111111&key=index";
        Map<String, String> params = Stream.of(queryString.split("&")).map(str -> str.split("=")).collect(Collectors.toMap(s -> s[0], s -> s[1]));
        System.out.println(params);
    }

    @Test
    public void test2 () {
        //将book集合类中的id提取成list集合
        List<Integer> ids = books().stream().map( book -> book.getId()).collect(Collectors.toList());
        System.out.println(ids);

        ids = books().stream().map(Book::getId).collect(Collectors.toList());
        System.out.println(ids);
        //将提取出来id以逗号连接成字符串
        String str = books().stream().map(book -> book.getId()+"").collect(Collectors.joining(","));
        System.out.println(str);
        //将提取出来id以逗号连接成字符串并在开头和结尾加上括号
        str = books().stream().map(book -> book.getId()+"").collect(Collectors.joining(",", "(", ")"));
        System.out.println(str);

        str = books().stream().map(book -> "'"+book.getId()+"'").collect(Collectors.joining(",", "(", ")"));
        System.out.println(str);
    }

    @Test
    public void test3 () {
        //获取books集合类中的所有type种类并返回成list集合
        List<String> list = books().stream().map(Book::getType).collect(Collectors.toList());
        System.out.println(list);
        //获取books集合类中的所有type种类并进行去重并返回成list集合
        list = books().stream().map(Book::getType).distinct().collect(Collectors.toList());
        System.out.println(list);

        Set<String> set = books().stream().map(Book::getType).collect(Collectors.toSet());
        System.out.println(set);
    }

    @Test
    public void test4 () {
        //对books集合类中的数据以price价格从小到大进行排序并循环输出结果,有如下两种方式
//		books().stream().sorted((book1, book2) -> Double.compare(book1.getPrice(), book2.getPrice())).forEach(System.out::println);;

//      books().stream().sorted(Comparator.comparing(Book::getPrice)).forEach(System.out::println);

        //对books集合类中的数据以price价格从大到小进行排序并循环输出结果，reversed()作用返回相反排序结果。,有如下两种方式
//		Comparator<Book> compa = (book1, book2) -> Double.compare(book1.getPrice(), book2.getPrice());
//		books().stream().sorted(compa.reversed()).forEach(System.out::println);

// 		books().stream().sorted(Comparator.comparing(Book::getPrice).reversed()).forEach(System.out::println);


        //对books集合类中的数据以price价格从小到大进行排序后再按日期date进行从早到晚排序并循环输出结果，thenComparing()作用进行多重排序，两种方式
//		Comparator<Book> compa = (book1, book2) -> Double.compare(book1.getPrice(), book2.getPrice());
//		books().stream().sorted(compa.thenComparing((book1,book2) -> book1.getPublishDate().isAfter(book2.getPublishDate()) ? -1 : 1)).forEach(System.out::println);

//      books().stream().sorted(Comparator.comparing(Book::getPrice).reversed().thenComparing(Comparator.comparing(Book::getPublishDate).reversed())).forEach(System.out::println);
    }

    @Test
    public void test5 () {
        //对books集合类中的数据以id为键名重构成map
        Map<Integer, Book> booksMap = books().stream().collect(Collectors.toMap(book -> book.getId(), book -> book));
        System.out.println(booksMap);

//        Map<Integer, Book> booksMap = books().stream().collect(Collectors.toMap(Book::getId, book -> book));
//        System.out.println(booksMap);
    }

    @Test
    public void test6 () {
        //算出books集合类中的价格平均值
//        Double avg = books().stream().collect(Collectors.averagingDouble(Book::getPrice));
//        System.out.println(avg);
    }

    @Test
    public void test7 () {
        //获取books集合类中的价格最大值
        Optional<Book> book = books().stream().collect(Collectors.maxBy(Comparator.comparing(Book::getPrice)));

        //获取books集合类中的价格最小值
//        book = books().stream().collect(Collectors.minBy(Comparator.comparing(Book::getPrice)));
//        System.out.println(book);

        //获取books集合类中的最早时间
//        book = books().stream().collect(Collectors.minBy(Comparator.comparing(Book::getPublishDate)));
//        System.out.println(book);

        //获取books集合类中的最近时间
//        book = books().stream().collect(Collectors.maxBy(Comparator.comparing(Book::getPublishDate)));
//        System.out.println(book);

        //获取books集合中的价格最大的对象后再从中获取日期最近的对象。
//        Comparator<Book> comp = Comparator.comparing(Book::getPrice);
//        book = books().stream().collect(Collectors.maxBy(comp.thenComparing(Comparator.comparing(Book::getPublishDate))));
//        System.out.println(book);
    }

    @Test
    public void test8 () {
        //对books集合数据按type种类进行分组构成map，然后进行遍历操作
//		Map<String, List<Book>> booksMap = books().stream().collect(Collectors.groupingBy(Book::getType));
//		booksMap.keySet().forEach(key -> {
//			System.out.println(key);
//			System.out.println(booksMap.get(key));
//			System.out.println("---------------------");
//		});

        //先对books集合数据按type种类进行分组构成map，然后获取各组的总数
//		Map<String, Long> booksCount = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.counting()));
//		System.out.println(booksCount);

        //先对books集合数据按type种类进行分组构成map，然后获取各组的总价格
//		Map<String, Double> booksSum = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.summingDouble(Book::getPrice)));
//		System.out.println(booksSum);

        //先对books集合数据按type种类进行分组构成map，然后获取各组的平均价格
//		Map<String, Double> booksSum = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.averagingDouble(Book::getPrice)));
//		System.out.println(booksSum);

        //先对books集合数据按type种类进行分组构成map，然后获取各组中的价格最大的对象。
//		Map<String, Optional<Book>> booksMaxPrice = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.maxBy(Comparator.comparing(Book::getPrice))));
//		System.out.println(booksMaxPrice);

        //先对books集合数据按type种类进行分组构成map，然后获取各组中的价格最小的对象。
//		Map<String, Optional<Book>> booksMinPrice = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.minBy(Comparator.comparing(Book::getPrice))));
//		System.out.println(booksMinPrice);

        //先对books集合数据按type种类进行分组构成map，然后获取各组中的日期最近的对象。
        Map<String, Optional<Book>> booksMaxPubDate = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.maxBy(Comparator.comparing(Book::getPublishDate))));
        System.out.println(booksMaxPubDate);
    }

    @Test
    public void test9 () {
        //过滤掉books集合中价格小于80的对象，再对这些对象按日期从近到远排序然后输出结果。
        books().stream().filter(book -> book.getPrice() >= 80).sorted(Comparator.comparing(Book::getPublishDate).reversed()).forEach(System.out::println);;
    }

    private List<Book> books(){
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "tomcat", 70d, "服务器", LocalDate.parse("2014-05-17")));
        books.add(new Book(2, "jetty", 60d, "服务器", LocalDate.parse("2015-12-01")));
        books.add(new Book(3, "nginx", 65d, "服务器", LocalDate.parse("2016-10-17")));
        books.add(new Book(4, "java", 66d, "编程语言", LocalDate.parse("2011-04-09")));
        books.add(new Book(5, "ruby", 80d, "编程语言", LocalDate.parse("2013-05-09")));
        books.add(new Book(6, "php", 40d, "编程语言", LocalDate.parse("2014-08-06")));
        books.add(new Book(7, "html", 44d, "编程语言", LocalDate.parse("2011-01-06")));
        books.add(new Book(8, "oracle", 150d, "数据库", LocalDate.parse("2013-08-09")));
        books.add(new Book(9, "mysql", 66d, "数据库", LocalDate.parse("2015-04-06")));
        books.add(new Book(10, "ssh", 70d, "编程语言", LocalDate.parse("2016-12-04")));
        books.add(new Book(11, "设计模式", 81d, "其他", LocalDate.parse("2017-04-06")));
        books.add(new Book(12, "重构", 62d, "其他", LocalDate.parse("2012-04-09")));
        books.add(new Book(13, "敏捷开发", 72d, "其他", LocalDate.parse("2016-09-07")));
        books.add(new Book(14, "从技术到管理", 42d, "其他", LocalDate.parse("2016-02-19")));
        books.add(new Book(15, "算法导论", 66d, "其他", LocalDate.parse("2010-05-08")));
        books.add(new Book(16, "oracle 12c", 150d, "数据库", LocalDate.parse("2017-05-08")));
        return books;
    }

    class Book {
        private int id;
        private String name;
        private double price;
        private String type;
        private LocalDate publishDate;

        public Book(int id, String name, double price, String type, LocalDate publishDate) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.type = type;
            this.publishDate = publishDate;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public double getPrice() {
            return price;
        }
        public void setPrice(double price) {
            this.price = price;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public LocalDate getPublishDate() {
            return publishDate;
        }
        public void setPublishDate(LocalDate publishDate) {
            this.publishDate = publishDate;
        }
        @Override
        public String toString() {
            return "Book [id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + ", publishDate="
                    + publishDate + "]";
        }
    }

    @Test
    public void  text10(){
        // 1. 获取当前日期(年月日) -----打印输出-----2018-01-29
        LocalDate localDate = LocalDate.now();
        // 2. 根据年月日构建Date ----打印输出-----2018-01-30
        LocalDate localDate1 = LocalDate.of(2018, 01, 30);
        // 3. 字符串转换日期,默认按照yyyy-MM-dd格式，也可以自定义格式 -----打印输出-----2018-01-30
        LocalDate localDate2 = LocalDate.parse("2018-01-30");
        // 4. 获取本月第一天 -----打印输出-----2018-01-01
        LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
        // 5. 获取本月第二天  -----打印输出-----2018-01-02
        LocalDate secondDayOfMonth = localDate.withDayOfMonth(2);
        // 6. 获取本月最后一天 -----打印输出-----2018-01-31
        LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
        // 7. 明天 -----打印输出----- 2018-01-30
        LocalDate tomorrowDay = localDate.plusDays(5);
        System.out.println(tomorrowDay);
        // 8. 昨天 -----打印输出----- 2018-01-28
        LocalDate yesterday = localDate.minusDays(1L);
        System.out.println(yesterday);
        // 9. 获取本年第12天 -----打印输出----- 2018-04-30
        LocalDate day = localDate.withDayOfYear(120);
        // 10. 计算两个日期间的天数
        long days = localDate.until(localDate1, ChronoUnit.DAYS);
        System.out.println(days);
        // 11. 计算两个日期间的周数
        long weeks = localDate.until(localDate1, ChronoUnit.WEEKS);
        System.out.println(weeks);
    }

    @Test
    public void text11(){
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        System.out.println(        passwordEncoder.encode("a66666666"));
    }

}

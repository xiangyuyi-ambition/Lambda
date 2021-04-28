package com.lambda;

import com.Entity.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: lambda
 * @description
 * @author: xiangyuyi
 * @create: 2020-10-24 14:48
 **/
public class StreamDemo {


    List<Employee> employeeList = Arrays.asList(
            new Employee("1", 21,Employee.Status.BUSY),
            new Employee("2", 25,Employee.Status.FREE),
            new Employee("3", 24,Employee.Status.BUSY),
            new Employee("4", 78,Employee.Status.VOCATION),
            new Employee("4", 50,Employee.Status.BUSY),
            new Employee("4", 36,Employee.Status.FREE)
    );

    @Test
    public void test(){
        //1。通过集合Collection的stram或者paralleStream方法得到流
        List<String> list = new ArrayList<>();
        list.stream();
        // 2.通过Array中的静态方法stream获取数据流
        Employee[] emps = new Employee[10];
        Arrays.stream(emps);
        //3.通过Stream 中的静态方法of()
        Stream<String> stream = Stream.of("aa","bb","cc");
        //4.创建无限流
        //迭代
        Stream<Integer> stream1 = Stream.iterate(0, x ->x + 2);
        stream1.limit(10).forEach(System.out::println);
        //生成
        Stream.generate(() ->Math.random()).limit(10).forEach(x -> System.out.println(x));
    }

    @Test
    public  void filter(){
        //中间操作：不会执行任何操作
        Stream<Employee> stream = employeeList.stream().filter(x ->{
            System.out.println("中间操作");
            return x.getAge() > 24;
        });
        //终止操作：只有执行终止操作，才会一次性执行
        stream.forEach(System.out::println);
    }

    @Test
    public  void limit(){
        //中间操作：不会执行任何操作
        employeeList.stream().filter( x -> {
            System.out.println("短路");
            return x.getAge() > 0;
         } ).limit(1).forEach(System.out::println);
    }

    @Test
    public void skip(){
        employeeList.stream().filter(x -> x.getAge() > 1).skip(1).forEach(System.out::println);
    }

    @Test
    public void distinct(){
        employeeList.stream().distinct().forEach(System.out::println);
    }

    @Test
    public void map(){
        /**
        映射：
        map -接收Lambda，将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数
                会被应用到每个元素上，并将其映射成一个新的元素。
        flatMap() -接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有的流连接成一个流。
         */
        List<String> list = Arrays.asList("aa", "vv", "cc");
        list.stream().map((str -> str.toString())).forEach(System.out::println);

        employeeList.stream().map(Employee ->Employee.getAge()).forEach(System.out::println);
    }


    @Test
    public void flatMap(){
        List<String> list = Arrays.asList("aa", "vv", "cc");
        Stream<Stream<Character>> stream = list.stream().map(x ->StreamDemo.filterCharacter(x));
        stream.forEach( x-> x.forEach(System.out::println));
        //使用flatmap
        Stream<Character> stream1 = list.stream().flatMap(StreamDemo::filterCharacter);
        stream1.forEach(System.out::println);
    }

    @Test
    public void sorted(){
        List<String> list = Arrays.asList("aa", "vv", "cc");
        list.stream().sorted().forEach(System.out::println);

        employeeList.stream().sorted((e1,e2) ->{
            if(e1.getAge().equals(e2.getAge())){
                return e1.getName().compareTo(e2.getName());
            }else{
                return e1.getAge().compareTo(e2.getAge());
            }
        }).forEach(System.out::println);
    }

    @Test
    public void allMatch(){
        //全匹配
        boolean b = employeeList.stream().allMatch(e ->e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);
        //至少匹配一个
        boolean b2 = employeeList.stream().anyMatch(e ->e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);
        //一个匹配的都没有
        boolean b3 = employeeList.stream().noneMatch(e ->e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);
        //返回第一个元素
        Optional<Employee> employee = employeeList.stream().sorted((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge())).findFirst();
        System.out.println(employee);

        //查找任一个
        Optional<Employee> optionalEmployee = employeeList.stream().filter(e ->e.getStatus().equals(Employee.Status.FREE)).findAny();
        System.out.println(optionalEmployee);
    }

    @Test
    public void count(){
        Long count = employeeList.stream().count();
        System.out.println(count);

        Optional<Employee> employee =  employeeList.stream().max((e1,e2) ->Double.compare(e1.getAge(), e2.getAge()));
        System.out.println(employee.get());

        Optional<Integer> salary =  employeeList.stream().map(Employee::getAge).min(Integer::compare);
        System.out.println(salary.get());
    }

    @Test
    public void reduce(){
        //规约
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        Integer sum = list.stream().reduce(55, (x, y) -> x+y);
        System.out.println(sum);

        Optional<Integer> age = employeeList.stream().map(Employee::getAge).reduce(Integer::sum);
        System.out.println(age.get());
    }

    @Test
    public void collection(){
        List<Integer> ageList = employeeList.stream().map(Employee::getAge).collect(Collectors.toList());
        ageList.forEach(System.out::println);
        Set<String> set = employeeList.stream().map(Employee::getName).collect(Collectors.toSet());
        set.forEach(System.out::println);
        HashSet<String> hashSet = employeeList.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);

        Long count = employeeList.stream().collect(Collectors.counting());
        System.out.println("count:" + count);
        employeeList.stream().collect(Collectors.averagingInt(Employee::getAge));

        Integer sumAge = employeeList.stream().collect(Collectors.summingInt(Employee::getAge));
        System.out.println(sumAge);

        Optional<Employee> employee = employeeList.stream().collect(Collectors.maxBy((e1,e2) ->Double.compare(e1.getAge(), e2.getAge())));
        System.out.println(employee.get());

        Optional<Integer> min = employeeList.stream().map(Employee::getAge).collect(Collectors.minBy(Integer::compare));
        System.out.println(min.get());

    }

    @Test
    public void groupBy(){
        Map<Employee.Status, List<Employee>> map = employeeList.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);

        Map<Employee.Status, Map<String, List<Employee>>> emps = employeeList.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e ->{
            if(((Employee) e).getAge() < 24){
                return "青年";
            }
            if(((Employee) e).getAge() < 26){
                return "中年";
            }
            return "老年";
        })));
    }

    @Test
    public  void part(){
        Map<Boolean, List<Employee>> parting = employeeList.stream().collect(Collectors.partitioningBy( x ->x.getAge() > 25));
        System.out.println(parting);
    }

    @Test
    public  void sumAll(){
        IntSummaryStatistics iss = employeeList.stream().collect(Collectors.summarizingInt(Employee::getAge));
        System.out.println(iss.getMax() + iss.getAverage());
    }

    @Test
    public  void join(){
        String str = employeeList.stream().map(Employee::getName).collect(Collectors.joining(",","-","-"));
        System.out.println(str);
    }


    public static Stream<Character> filterCharacter(String str){
        List<Character> characters = new ArrayList<>();
        for(Character character : str.toCharArray()){
            characters.add(character);
        }
        return characters.stream();
    }
}



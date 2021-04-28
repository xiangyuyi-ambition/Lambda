package com.lambda;

import com.Entity.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @program: lambda
 * @description
 * @author: xiangyuyi
 * @create: 2020-10-24 11:26
 **/
public class LambdaBasic {

    List<Employee> employeeList = Arrays.asList(
            new Employee("1", 21, Employee.Status.BUSY),
            new Employee("2", 25,Employee.Status.FREE),
            new Employee("3", 24,Employee.Status.VOCATION),
            new Employee("4", 26,Employee.Status.FREE)
    );

    @Test
    public  void test(){
      
    }

}

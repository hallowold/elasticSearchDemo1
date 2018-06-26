package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class Demo1ApplicationTests {




	@Test
	public void testLameda() {
		// 不使用lambda表达式为每个订单加上12%的税
//		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
//		for (Integer cost : costBeforeTax) {
//			double price = cost + .12*cost;
//			System.out.println(price);
//		}

// 使用lambda表达式
		List<Integer> costBeforeTax2 = Arrays.asList(100, 200, 300, 400, 500);
		costBeforeTax2.forEach(i -> System.out.println(i));
//		costBeforeTax2.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);
	}


}

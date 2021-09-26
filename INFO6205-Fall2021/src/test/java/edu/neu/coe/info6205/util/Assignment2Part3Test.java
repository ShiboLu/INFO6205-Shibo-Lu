/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.util;

import org.junit.Test;
import edu.neu.coe.info6205.sort.elementary.*;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("ALL")
public class Assignment2Part3Test {

	final List<Integer> list = new ArrayList<>();
	
    @Test
    public void testSortedArray() {
    	Benchmark<Integer> bm = new Benchmark_Timer<>(
                "testSortedArray", num -> {
                	//System.out.println("pre!");
                	orderedArray(num);
                	return num;
                	},
                num -> {
                	//System.out.println(list);
                	//System.out.println("run!");
                	InsertionSort.sort(list.toArray(new Integer[0]));
                },
                b -> {
                	//System.out.println("post!");
                    //System.out.println(b);
                });

    	//Integer[] lengths = {100, 10000, 100000, 1000000, 10000000};
    	Integer[] lengths = {100};
    	for(int length : lengths) {
    		double x = bm.run(length, 100); // length, times
            System.out.println("testSortedArray time with length " + length + ": " + x);
    	}
    }
    
    @Test
    public void testRandomArray() {
    	Benchmark<Integer> bm = new Benchmark_Timer<>(
                "testRandomArray", num -> {
                	randomArray(num);
                	return num;
                	},
                num -> {
                	//System.out.println(list);
                	InsertionSort.sort(list.toArray(new Integer[0]));
                },
                b -> {
                    //System.out.println(b);
                });

    	//Integer[] lengths = {100, 10000, 100000, 1000000, 10000000};
    	Integer[] lengths = {100};
    	for(int length : lengths) {
    		double x = bm.run(length, 100); // length, times
            System.out.println("testRandomArray time with length " + length + ": " + x);
    	}
    }
    
    @Test
    public void testReversedArray() {
    	Benchmark<Integer> bm = new Benchmark_Timer<>(
                "testReversedArray", num -> {
                	reverseOrderedArray(num);
                	return num;
                	},
                num -> {
                	//System.out.println(list);
                	InsertionSort.sort(list.toArray(new Integer[0]));
                },
                b -> {
                    //System.out.println(b);
                });

    	//Integer[] lengths = {100, 10000, 100000, 1000000, 10000000};
    	Integer[] lengths = {100};
    	for(int length : lengths) {
    		double x = bm.run(length, 100); // length, times
            System.out.println("testReversedArray time with length " + length + ": " + x);
    	}
    }
    
    @Test
    public void testPartiallyOrderedArray() {
    	Benchmark<Integer> bm = new Benchmark_Timer<>(
                "testPartiallyOrderedArray", num -> {
                	partiallyOrderedArray(num);
                	return num;
                	},
                num -> {
                	//System.out.println(list);
                	InsertionSort.sort(list.toArray(new Integer[0]));
                },
                b -> {
                    //System.out.println(b);
                });
    	
    	//Integer[] lengths = {100, 10000, 100000, 1000000, 10000000};
    	Integer[] lengths = {100};
    	for(int length : lengths) {
    		double x = bm.run(length, 100); // length, times
            System.out.println("testPartiallyOrderedArray time with length " + length + ": " + x);
    	}
    }
    
    
    
    public Integer[] orderedArray(int num) {
    	list.clear();
        for(int i = num; i < num + num; i++) {
        	list.add(i);
        }
        return list.toArray(new Integer[0]);
    }
    
    public Integer[] reverseOrderedArray(int num) {
    	list.clear();
        for(int i = num + num - 1; i >= 0; i--) {
        	list.add(i);
        }
        return list.toArray(new Integer[0]);
    }
    
    public Integer[] randomArray(int num) {
    	list.clear();
    	Random r=new Random();
        for(int i = 0; i < num; i++) {
        	list.add(r.nextInt(i + 1));
        }
        
        return list.toArray(new Integer[0]);
    }
    
    public Integer[] partiallyOrderedArray(int num) {
    	list.clear();
    	Random r=new Random();
        for(int i = 0; i < num/5; i++) {
        	list.add(r.nextInt(i + 1));
        }
        for(int i = num/5; i < num/5*2; i++) {
        	list.add(i);
        }
        for(int i = num/5*2; i < num/5*3; i++) {
        	list.add(r.nextInt(i + 1));
        }
        for(int i = num/5*3; i < num/5*4; i++) {
        	list.add(i);
        }
        for(int i = num/5*4; i < num; i++) {
        	list.add(r.nextInt(i + 1));
        }
        
        return list.toArray(new Integer[0]);
    }
    
    
    
}
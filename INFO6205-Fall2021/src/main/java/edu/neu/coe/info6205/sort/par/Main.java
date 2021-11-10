package edu.neu.coe.info6205.sort.par;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * TODO tidy it up a bit.
 */
public class Main {

	public static int threadCount = 16;
	public static int arraySize = 1000000;
	public static int cutoffTime = 1000;
    @SuppressWarnings("static-access")
	public static void main(String[] args) {
        processArgs(args);
        System.out.println("Degree of parallelism: " + ParSort.myPool.getCommonPoolParallelism());
        Random random = new Random();
        FileOutputStream fis;
		try {
			fis = new FileOutputStream("./src/result.csv");
	        OutputStreamWriter isr = new OutputStreamWriter(fis);
	        BufferedWriter bw = new BufferedWriter(isr);
	        for(int ss = 1; ss < 5; ss++) {

	            ArrayList<Long> timeList = new ArrayList<>();
	            int[] array = new int[arraySize*ss];
		        for (int j = 10; j < 250; j++) {
		            ParSort.cutoff = cutoffTime * (j + 1);
		            // for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
		            for (int t = 0; t < 5; t++) {
		            	// warm-up
		                for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
		                ParSort.sort(array, 0, array.length);
		            }
		            long time;
		            long startTime = System.currentTimeMillis();
		            for (int t = 0; t < 10; t++) {
		                for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
		                ParSort.sort(array, 0, array.length);
		            }
		            long endTime = System.currentTimeMillis();
		            time = (endTime - startTime);
		            timeList.add(time);
		
		
		            System.out.println("Threads:" + ParSort.myPool.getCommonPoolParallelism() + " size: " + array.length + "  cutoff：" + (ParSort.cutoff) + "\t10times Time:" + time + "ms");
		
		        }
		        try {
		            
		            int j = 0;
		            for (long i : timeList) {
		                @SuppressWarnings("static-access")
						String content = ParSort.myPool.getCommonPoolParallelism()+ "," + array.length + "," + (double) cutoffTime * (j + 1) + "," + (double) cutoffTime * (j + 1) / array.length + ","+ (double) i / 10 + "\n";
		                j++;
		                bw.write(content);
		                bw.flush();
		            }
		
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	        }

        try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    private static void processArgs(String[] args) {
        String[] xs = args;
        while (xs.length > 0)
            if (xs[0].startsWith("-")) xs = processArg(xs);
    }

    private static String[] processArg(String[] xs) {
        String[] result = new String[0];
        System.arraycopy(xs, 2, result, 0, xs.length - 2);
        processCommand(xs[0], xs[1]);
        return result;
    }

    private static void processCommand(String x, String y) {
        if (x.equalsIgnoreCase("N")) setConfig(x, Integer.parseInt(y));
        else
            // TODO sort this out
            if (x.equalsIgnoreCase("P")) //noinspection ResultOfMethodCallIgnored
                ForkJoinPool.getCommonPoolParallelism();
    }

    private static void setConfig(String x, int i) {
        configuration.put(x, i);
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Map<String, Integer> configuration = new HashMap<>();


}

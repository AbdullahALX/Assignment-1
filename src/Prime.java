import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Prime {
    static final int MAX_NUM = 100000000;
    static final int THREADS_NUM = 8;
    static final int NUM_PER_THREAD = MAX_NUM / THREADS_NUM;
    static int count = 0;
    static long sum = 0;
    static ArrayList<Integer> primes_list = new ArrayList<>();
    static ArrayList<Integer> top_primes_list = new ArrayList<>();


    public static void main(String[] args) throws Exception {


        //creating the out file
        File file = new File("primes.txt");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        //start calculating time and create array of 8 threads
        final long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[THREADS_NUM];


        //start all threads
        for (int i = 0; i < THREADS_NUM; i++) {
            threads[i] = new Thread(new setPrime(i));
            threads[i].start();

        }

        for (int i = 0; i < THREADS_NUM; i++) {
            threads[i].join();
        }


        // End execution time and calculate
        final long endTime = System.currentTimeMillis();
        final long duration = (endTime - startTime);

        // Write output to file
        bw.write(duration / 1000 + " Seconds, primes count = " + count + ",  primes sum = " + sum);
        bw.write(String.format("%n"));


        // find top ten maximum primes, listed in order from lowest to highest
        Collections.sort(primes_list);
        int len = primes_list.size();

        bw.write("\nTop ten maximum primes, listed in order from lowest to highest\n");

        for (int i = 10; i > 0; i--) {
            top_primes_list.add(primes_list.get(len - i));
        }
        bw.write(top_primes_list + "\n");
        // Close file
        bw.close();
    }


    public static boolean isPrime(int n) {

        if (n <= 1) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        // using formula 6k ± 1
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    public static synchronized void addPrime(int prime) {
        //adding prime to list and update sum and count
        primes_list.add(prime);
        sum += prime;
        count++;
    }
}

class setPrime implements Runnable {

    private final int threadNum;

    public setPrime(int i) {
        this.threadNum = i;
    }

    @Override
    public void run() {
        createThread(threadNum);
    }

    public static void createThread(int threadNum) {
        //split array to 8 smaller arrays
        int firstIndex = threadNum * Prime.NUM_PER_THREAD;
        int lastIndex = firstIndex + Prime.NUM_PER_THREAD - 1;


        //check for all primes in the list
        for (int index = firstIndex; index <= lastIndex; index++) {
            if (Prime.isPrime(index)) {
                Prime.addPrime(index);
            }

        }
    }
}


import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static int BUFFER_SIZE = 0;
    public static int STUDENT_COUNT = 0;
    public static final int ASSISTNET_COUNT = 1;
    public static final int PROFESSOR_COUNT = 1;

    public static void main(String[] args) {
        System.out.println("Unesite broj studenata");
        Scanner myInput = new Scanner(System.in);
        int broj = myInput.nextInt();
        myInput.close();
        BUFFER_SIZE = broj;
        STUDENT_COUNT = broj;

        Buffer buffer = new Buffer(BUFFER_SIZE);
        StudentThread studentThread = new StudentThread(buffer,STUDENT_COUNT);
        studentThread.start();
        for(int i = 0; i< ASSISTNET_COUNT;i++){
            AsistentThread at = new AsistentThread(i+1,buffer);
            at.setDaemon(true);
            at.start();
        }

        for(int i = 0; i< PROFESSOR_COUNT;i++){
            ProfessorThread professorThread = new ProfessorThread(i+1,buffer);
            professorThread.setDaemon(true);
            professorThread.start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        System.out.println(buffer.toString());
        if(buffer.total<STUDENT_COUNT) {
            System.out.println((STUDENT_COUNT - buffer.total)+" studenata je ostalo nepregledano");
        } else {
            System.out.println("Svi su zavrsili odbrane");
        }

    }
}
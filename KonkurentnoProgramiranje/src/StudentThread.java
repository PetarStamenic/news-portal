import java.util.Random;

public class StudentThread extends Thread{
    private Buffer buffer;
    private int count;

    public StudentThread(Buffer buffer,int count){
        this.buffer = buffer;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0;i < count; i++){
            buffer.write(new Student(i));
            try {
                sleep((int)(Math.random()*1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

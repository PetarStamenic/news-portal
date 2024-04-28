import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private int size;
    private Student[] data;
    private int readPos;
    private int writePos;
    public int total = 0;
    public int sum = 0;
    public double avg = 0.0;

    private ReentrantLock lock;
    private Condition readConditionAsistent;
    private Condition readConditionProfessor;
    private Condition writeCondition;

    public Buffer(int size){
        this.size = size;
        data = new Student[size];
        readPos = 0;
        writePos = 0;
        lock = new ReentrantLock(true);
        readConditionAsistent = lock.newCondition();
        readConditionProfessor = lock.newCondition();
        writeCondition = lock.newCondition();
    }

    public void write(Student value){
        lock.lock();
        try {
            while (isFull()){
                System.out.println("Nema mesta za cekanje");
                writeCondition.await();
            }
            data[writePos] = value;
            if(++writePos == size)
                writePos = 0;
            readConditionAsistent.signal();
            readConditionProfessor.signal();
            System.out.println(Student.arived());

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public double readAsistent(int id){
        lock.lock();
        Student retVal;
        double time;
        int ocena1 = (int)(Math.random()*10);
        if(ocena1<5)
            ocena1 = 5;
        try{
            while (!hasAtLeastOne()){
                System.out.println("Asistent "+id+" ceka da mu neko dodje");
                readConditionAsistent.await();
            }
            retVal = data[readPos];
            if (++readPos == size)
                readPos = 0;
            System.out.println("Asistnet "+id+" je zavrsio odbranu sa: " + retVal.toString()+" sa ocenom "+ocena1);
            time = retVal.trajanjeOdbrane;

            sum += ocena1;
            total++;
            avg = 1.0*sum/total;

            writeCondition.signal();
        } catch (Exception e){
            e.printStackTrace();
            time = 1.0;
        } finally {
            lock.unlock();
        }
        return time;
    }

    public double readProffesor(int id){
        lock.lock();
        Student retVal;
        Student retVal2;
        int ocena1 = (int)(Math.random()*10);
        if(ocena1<5)
            ocena1 = 5;
        int ocena2 = (int)(Math.random()*10);
        if(ocena2<5)
            ocena2 = 5;
        double time;
        try{
            while (!hasAtLeastTwo()){
                System.out.println("Profesor "+id+" ceka da mu neko dodje");
                readConditionProfessor.await();
            }
            retVal = data[readPos];
            if (++readPos == size)
                readPos = 0;
            System.out.println("Professor "+id+" je zavrsio odbranu sa: " + retVal.toString()+" sa ocenom "+ocena1);
            retVal2 = data[readPos];
            if (++readPos == size)
                readPos = 0;
            System.out.println("Professor "+id+" je zavrsio odbranu sa: " + retVal2.toString()+" sa ocenom "+ocena2);

            time = Math.max(retVal2.trajanjeOdbrane, retVal.trajanjeOdbrane);


            sum += ocena1;
            total++;
            avg = 1.0*sum/total;

            sum += ocena2;
            total++;
            avg = 1.0*sum/total;

            writeCondition.signal();
        } catch (Exception e){
            e.printStackTrace();
            time = 1.0;
        } finally {
            lock.unlock();
        }
        return time;
    }

    private boolean isFull(){return readPos == (writePos+1)%size;}

    private boolean hasAtLeastOne(){
        return readPos < writePos;
    }

    private boolean hasAtLeastTwo(){
        return readPos < writePos -1;
    }

    @Override
    public String toString() {
        return "pregledano je ukupno "+total+" redova sa ukupnom ocenom "+sum+" i prosekom "+avg;
    }
}

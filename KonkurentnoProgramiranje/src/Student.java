import java.util.Random;

public class Student{

    double trajanjeOdbrane;
    static int id;

    public Student(int id){
        this.id = id;
        double rng = Math.random();
        if(rng<0.5)
            rng+= 0.5;
        this.trajanjeOdbrane = rng;
    }

    public static String arived(){
        return "Student "+id+" je dosao";
    }

    @Override
    public String toString() {
        return "Student "+id+"je zavrsio odbranu nakon "+trajanjeOdbrane+" sekundi i dobio ocenu ";
    }
}

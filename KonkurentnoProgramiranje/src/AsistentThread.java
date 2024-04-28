public class AsistentThread extends Thread{

    private Buffer buffer;
    private int id;

    public AsistentThread(int id, Buffer buffer){
        this.buffer = buffer;
        this.id = id;

    }

    @Override
    public void run() {
        while(true){
            double time = buffer.readAsistent(id);
            int sleeptime = (int)(time*1000);
            try {
                sleep(sleeptime);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

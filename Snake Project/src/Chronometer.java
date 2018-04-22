public final class Chronometer{
    private long begin, end;
 
    public void start(){
        begin = System.currentTimeMillis();
    }
 
    public void stop(){
        end = System.currentTimeMillis();
    }
 
    public double getSeconds() {
        return (end - begin) / 1000.0;
    }

}
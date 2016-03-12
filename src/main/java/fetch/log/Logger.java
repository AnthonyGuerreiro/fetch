package fetch.log;

public class Logger {

    public void info(String msg) {
        info(msg, null);
    }

    public void info(Exception e) {
        info(null, e);
    }

    public void info(String msg, Exception e) {
        if (msg != null) {
            System.out.println(msg);
        }
        if (e != null) {
            e.printStackTrace();
        }
    }
}

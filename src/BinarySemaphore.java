// Code taken from http://users.ece.utexas.edu/~garg/dist/progs/BinarySemaphore.java.html

public class BinarySemaphore {
    boolean value;

    BinarySemaphore(boolean initValue) {
        value = initValue;
    }

    public synchronized void P() {
        while (!value)
            myWait(this);// in queue of blocked processes
        value = false;
    }

    public synchronized void V() {
        value = true;
        notify();
    }

    private void myWait(Object obj) {
        try {
            obj.wait();
        } catch (InterruptedException ignored) {
        }
    }
}

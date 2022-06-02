package Diningph;

import java.util.Random;

public class Philospher implements  Runnable {
    private int id;
    private volatile boolean full;
    private Chopstick leftChopStick;
    private Chopstick rightChopStick;
    private Random random;
    private int eatingCounter;

    public int getEatingCounter() {
        return eatingCounter;
    }


    public Philospher(int id, Chopstick leftChopStick, Chopstick rightChopStick) {
        this.id = id;
        this.leftChopStick = leftChopStick;
        this.rightChopStick = rightChopStick;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            //after eating a lot(1000) then we will terminate the given thread
            while(!full) {
                think();
                if (leftChopStick.pickUp(this,State.LEFT)) {
                    if (rightChopStick.pickUp(this,State.RIGHT)) {
                    eat();
                    rightChopStick.putDown(this,State.RIGHT);
                    }
                    leftChopStick.putDown(this,State.LEFT);
                }
            }
        }
        catch (Exception e) {

        }


    }

    private void think() throws  InterruptedException {
        System.out.println(this +" is thinking ");
        //philopher thinks for a random time 0 to 1 seond
        Thread.sleep(random.nextInt(1000));
    }


    private void eat() throws  InterruptedException {
        System.out.println(this +" is eating");
        eatingCounter++;
        //philopher eating for a random time 0 to 1 seond
        Thread.sleep(random.nextInt(1000));
    }

    public  void setFull(boolean full) {
        this.full = full;
    }


    public String toString() {
        return " Philohpher "+id;
    }
    public boolean isFull() {
        return this.full;
    }

}

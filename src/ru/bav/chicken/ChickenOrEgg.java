package ru.bav.chicken;

public class ChickenOrEgg {

    public static void main(String[] args) {
        Egg egg = new Egg();
        Chicken chicken = new Chicken();
        (new Thread(new Egg())).start();
        (new Thread(new Chicken())).start();
    }
    public static class Egg extends Thread {
        public void run() {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println("ЯЙЦО");
            }
        }
    }

    public static class Chicken extends Thread {
        public void run() {
            for (int i = 0; i < 20; i++) {
                try{
                    Thread.sleep(1000);
            }catch (InterruptedException e){
                }
                System.out.println("КУРИЦА");
            }
        }
    }
}

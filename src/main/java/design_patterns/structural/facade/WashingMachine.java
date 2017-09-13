package design_patterns.structural.facade;

/**
 * Created by Mati on 09.07.2017.
 */
public class WashingMachine {

    public static void main(String[] args) {
        new WashingMachine().lightlySoiled();
    }

    public void heavilySoiled() {
        setWaterTemp(60);
        setDuration(30);
        addDetergent();
        heatWater();
        startWash();
    }



    public void lightlySoiled() {
        setWaterTemp(30);
        setDuration(15);
        addDetergent();
        heatWater();
        startWash();
    }


    private void setWaterTemp(int i) {
        System.out.println("Water temp " + i + " degrees Celsius");
    }

    private void setDuration(int i) {
        System.out.println("Duration " + i + " minutes");
    }

    private void addDetergent() {
        System.out.println("Add detergent");
    }

    private void heatWater() {
        System.out.println("Heat water");
    }

    private void startWash() {
        System.out.println("Start wash");
    }
}

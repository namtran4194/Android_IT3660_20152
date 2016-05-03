package android.noelove.loving;

import java.util.Random;

public class DataProvider {
    public static Love getRandomLove(String name) {
        Love lv = null;
        Random rand = new Random();
        int type = rand.nextInt(3);
        switch (type) {
            case 0:
                lv = new Trang(name);
                break;
            case 1:
                lv = new Lan(name);
                break;
            case 2:
                lv = new Huyen(name);
                break;
        }
        return lv;
    }
}

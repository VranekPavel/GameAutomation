package utils;

import java.util.ArrayList;

public class Methods {
    public static Integer countDistance(String village1, String village2){
        ArrayList<Integer> target = new ArrayList<>();
        ArrayList<Integer> target1 = new ArrayList<>();

        target.add(Integer.parseInt(village1.substring(0, 3)));
        target.add(Integer.parseInt(village1.substring(4)));

        target1.add(Integer.parseInt(village2.substring(0, 3)));
        target1.add(Integer.parseInt(village2.substring(4)));

        int dx = Math.abs(target.get(0) - target1.get(0));
        int dy = Math.abs(target.get(1) - target1.get(1));

        return (dx > dy) ? dx : dy;
    }
}

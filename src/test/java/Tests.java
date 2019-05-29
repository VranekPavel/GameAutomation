import org.testng.annotations.Test;
import pageObjects.MainBuilding;
import pageObjects.Place;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import static utils.FileUtil.*;
//import static utils.CustomComparator.*;
import static utils.Methods.*;

public class Tests extends BaseTest {

    //@Test
    public void villageLevel(){
        List<List<String>> levels = new ArrayList<>();
        try{
            levels = readCSVFile("levels.txt");
        }catch(IOException e){}

        replaceText("villages.txt", "575|536", "575|536");


        MainBuilding mainBuilding = villagePage.goMain();
    }

    //@Test
    public void firstPass(){

        try{
            writeFile("villages.txt", villagePage.getCoordinates());
        }catch(IOException e){

        }

    }

    @Test
    public void loot(){
        Place place = villagePage.goPlace();
        int repeat = 0;
        String village = villagePage.getCoordinates();
        ArrayList<String> villages = new ArrayList<>();
        //vrať všechny vesnice pro loot v jednom dlouhém stringu (nice)
        try {
            villages = readFile("loot.txt");
        } catch (FileNotFoundException e){}
        villages.sort((a,b ) -> countDistance(village, a).compareTo(countDistance(village, b)));
        //poslat útok do každé loot vesnice
        int i = 0;
        while(repeat < 2 && villages.size() > i){

            try {Thread.sleep(Math.round(Math.random() * 100)); }catch (InterruptedException e) {Thread.currentThread().interrupt(); }

            place.selectVillage(villages.get(i));
            i += 1;
            repeat = place.selectTroopsAndAttack(repeat);
        }
    }
}

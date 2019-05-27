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
        Boolean repeat = true;
        String village = villagePage.getCoordinates();
        ArrayList<String> villages = new ArrayList<>();
        //vrať všechny vesnice pro loot v jednom dlouhém stringu (nice)
        try {
            villages = readFile("loot.txt");
        } catch (FileNotFoundException e){}
        villages.sort((a,b ) -> countDistance(village, a).compareTo(countDistance(village, b)));
        //poslat útok do každé loot vesnice
        int i = 0;
        while(repeat.equals(true) && villages.size() <= i){
            place.selectVillage(villages.get(i));
            i += 1;
            repeat = place.selectTroopsAndAttack();
        }
    }
}

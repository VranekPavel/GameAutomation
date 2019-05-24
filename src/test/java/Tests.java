import org.testng.annotations.Test;
import pageObjects.MainBuilding;
import pageObjects.Place;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import static utils.FileUtil.*;

public class Tests extends BaseTest {

    @Test
    public void villageLevel(){
        List<List<String>> levels = new ArrayList<>();
        try{
            levels = readCSVFile("levels.txt");
        }catch(IOException e){}

        replaceText("villages.txt", "575|536", "575|536");


        MainBuilding mainBuilding = villagePage.goMain();
    }

    @Test
    public void firstPass(){

        try{
            writeFile("villages.txt", villagePage.getCoordinates());
        }catch(IOException e){

        }
        try {
            readFile("villages.txt", villagePage.getCoordinates());
        } catch (FileNotFoundException e){

        }
    }

    @Test
    public void loot(){
        Place place = villagePage.goPlace();
        Boolean repeat = true;

        String villages = "";
        //vrať všechny vesnice pro loot v jednom dlouhém stringu (nice)
        try {
            villages = readFile("loot.txt", null);
        } catch (FileNotFoundException e){

        }
        //poslat útok do každé loot vesnice
        while(villages.length() > 0 && repeat.equals(true)){
                String village = villages.substring(0, 7);
            villages = villages.substring(7);

            place.selectVillage(village);
            repeat = place.selectTroopsAndAttack();
        }
    }
}

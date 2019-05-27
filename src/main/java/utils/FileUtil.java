package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileUtil {

    public static void replaceText(String fileName, String row, String newText) {
        try {
            ClassLoader classLoader = new FileUtil().getClass().getClassLoader();
            File filePath = new File(classLoader.getResource(fileName).getFile());
            BufferedReader file = new BufferedReader(new FileReader(filePath));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                if(line.contains(row)) {
                    inputBuffer.append(newText);
                    inputBuffer.append('\n');
                }
            }
            file.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(filePath);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void writeFile(String fileName, String text)
            throws IOException {
            ClassLoader classLoader = new FileUtil().getClass().getClassLoader();

            File file = new File(classLoader.getResource(fileName).getFile());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(text);
            writer.newLine();
            writer.close();
        }

    public static ArrayList<String> readFile(String fileName) throws FileNotFoundException {
        ClassLoader classLoader = new FileUtil().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        Scanner scan = new Scanner(file);
        ArrayList<String> output = new ArrayList<>();
        while(scan.hasNext()){
            output.add(scan.nextLine().toLowerCase());
        }
        return output;
    }

    public static String scanFile(String fileName, String searchStr) throws FileNotFoundException {
        ClassLoader classLoader = new FileUtil().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        Scanner scan = new Scanner(file);
        String output = "";
        Boolean run = true;
        while(scan.hasNext() && run){
            String line = scan.nextLine().toLowerCase();
                if (line.contains(searchStr)) {
                    output = line;
                    run = false;
                }
        }
        return output;
    }


    public static List<List<String>> readCSVFile(String fileName) throws FileNotFoundException {
        List<List<String>> records = new ArrayList<>();
        ClassLoader classLoader = new FileUtil().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                if(scanner.findInLine("//") == null) {
                    records.add(getRecordFromLine(scanner.nextLine()));
                }else {
                    getRecordFromLine(scanner.nextLine());
                }
            }
        }
        return records;
    }

    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    }


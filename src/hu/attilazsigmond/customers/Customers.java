package hu.attilazsigmond.customers;

import java.io.*;
import java.util.*;

public class Customers {

    public static void main(String[] args) {

        File file = new File("C:/Users/Attila/Idea_Project/a/src/hu/attilazsigmond/ugyfelek.csv");
        String sor = "";
        List lista = new ArrayList();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((sor = br.readLine()) != null) {

                String [] ugyfel = sor.split(",");
                lista.add(Arrays.asList(ugyfel));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(lista);



    }

}

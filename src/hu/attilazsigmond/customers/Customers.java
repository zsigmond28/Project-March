package hu.attilazsigmond.customers;

import java.io.*;
import java.util.*;

public class Customers implements Serializable {

    public static void main(String[] args) {

        File file = new File("C:/Users/Attila/Idea_Project/a/src/hu/attilazsigmond/ugyfelek.csv");
        String sor = null;
        List<Customer> lista = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine(); // az első sort átugorja.
            while ((sor = br.readLine()) != null) {

                String [] ugyfel = sor.split(",");
                Customer c = creatACustomer(ugyfel);
                lista.add(c);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        for (Customer c : lista){
//            System.out.println(c);
//        }


        // Sorba rendez az Anyja neve szerint
        Collections.sort(lista, new sortByMotherName());
        for (int i=0; i < lista.size(); i++){
            //System.out.println(lista.get(i));
        }

        // Kiirja az első sorbarendezést csv-be.
        File sortfile1 = new File("C:\\Users\\Attila\\Idea_Project\\a\\src\\hu\\attilazsigmond\\sortByMotherName.csv");
        try {
            FileOutputStream fos = new FileOutputStream(sortfile1);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lista);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sorba rendez a számla kezdete szerint
        Collections.sort(lista, new sortByStartDate());
        for (int i=0; i < lista.size(); i++){
            System.out.println(lista.get(i));
        }

        // Kiirja a második sorbarendezést csv-be.
        File sortfile2 = new File("C:\\Users\\Attila\\Idea_Project\\a\\src\\hu\\attilazsigmond\\sortByStartDate.csv");
        try {
            FileOutputStream fos = new FileOutputStream(sortfile2);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lista);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private static Customer creatACustomer(String[] ugyfel){
        String name = ugyfel[0];
        String motherName = ugyfel[1];
        String dateOfBirth = ugyfel[2];
        String placeOfBirth = ugyfel[3];
        String accountStart = ugyfel[4];
        String accountNumber = ugyfel[5];
        int egyenleg = Integer.parseInt(ugyfel[6]);
        int lekotottErtek = Integer.parseInt(ugyfel[7]);

        Customer c = new Customer(name,motherName,dateOfBirth,placeOfBirth,accountStart,
                                    accountNumber,egyenleg,lekotottErtek);

        return c;

    }

}

class Customer implements Serializable{
    private String name;
    public String motherName;
    private String dateOfBirth;
    private String placeOfBirth;
    public String accountStart;
    private String accountNumber;
    private int egyenleg;
    private int lekotottErtek;

    //konstruktor:
    public Customer(String name, String motherName, String dateOfBirth, String placeOfBirth,
                    String accountStart, String accountNumber, int egyenleg, int lekotottErtek){

        this.name=name;
        this.motherName=motherName;
        this.dateOfBirth=dateOfBirth;
        this.placeOfBirth=placeOfBirth;
        this.accountStart=accountStart;
        this.accountNumber=accountNumber;
        this.egyenleg=egyenleg;
        this.lekotottErtek=lekotottErtek;

    }

    @Override
    public String toString() {
        return name + " " +
                motherName + " " +
                dateOfBirth + " " +
                placeOfBirth + " " +
                accountStart + " " +
                accountNumber + " " +
                egyenleg + " " +
                lekotottErtek;
    }
}

class sortByMotherName implements Comparator<Customer> {

    public int compare(Customer a, Customer b){
        return a.motherName.compareTo(b.motherName);
    }
}

class sortByStartDate implements Comparator<Customer> {

    public int compare(Customer a, Customer b){
        return a.accountStart.compareTo(b.accountStart);
    }
}
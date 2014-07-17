package com.cj.nan.koans.impl;

import java.io.*;

public class Employee implements Serializable {
    private String name;
    private Address address;
    private double SSN;
    private Employee manager;

    public static Employee findEmployeeFromFilePath(String path) {
        Employee employee = null;
        try
        {
            FileInputStream fileOut =
                    new FileInputStream(path);

            ObjectInputStream out =
                    new ObjectInputStream(fileOut);
            employee = (Employee)out.readObject();
        
        } catch (FileNotFoundException e) {
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public static void serializeObjectToFile(Employee e, String path) {
        try
        {
            FileOutputStream fileOut =
                    new FileOutputStream(path);

            ObjectOutputStream out =
                    new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.close();
            fileOut.close();
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getSSN() {
        return SSN;
    }

    public void setSSN(double SSN) {
        this.SSN = SSN;
    }

    public void setManage(Employee manager) {
        this.manager = manager;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}

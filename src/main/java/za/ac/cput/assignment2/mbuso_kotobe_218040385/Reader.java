/*
 * The MIT License
 *
 * Copyright 2021 Mbuso Kotob's.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package za.ac.cput.assignment2.mbuso_kotobe_218040385;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *
 * @author Mbuso Kotobe 218040385
 */
public class Reader{
    private ArrayList<Customer> customerArrayList = new ArrayList<Customer>();
    private ArrayList<Supplier> supplierArrayList = new ArrayList<Supplier>();
    LinkedList<Integer> customerQueue = new LinkedList<Integer>();
    int[] objectIDs = new int[6];
    private Customer[] customerSortingArray;
    private Supplier[] supplierSortingArray;
    ObjectInputStream objectInputStream;

    public ArrayList<Customer> getCustomerArrayList()
    {
        return this.customerArrayList;
    }

    public ArrayList<Supplier> getSupplierArrayList()
    {
        return supplierArrayList;
    }

    public void openFile()
    {
        try{
            objectInputStream = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
            System.out.println("file opened without any problems");
        }
        catch (IOException e)
        {
            System.out.println("error opening stakeholder.ser file: " + e.getMessage());
            System.exit(1);
        }
    }

    public void readFile()
    {
        try{
            for(int i = 0; i < 11; ++i)
            {
                Object object = objectInputStream.readObject();
                if(isCustomer(object)) addToCustomerList((Customer)object);
                else addToSupplierList((Supplier)object);
            }
            initSortingArrays();
        }catch(IOException | ClassNotFoundException e){
            System.out.println("error reading stakeholder.ser file: " + e.getMessage());
            System.exit(1);

        }finally{
            closeFile();
        }
    }

    public void closeFile()
    {
        try{
            objectInputStream.close();
        }catch (IOException e){
            System.out.println("error closing stakeholder.ser file: " + e.getMessage());
            System.exit(1);
        }

    }

    /**
     * Sorts the customers by their ID
     * in Ascending Order.
     * */
    public void sortCustomersById()
    {
        Customer temporaryCustomer;
        addCustomerDataToSortingArray();
        for (int i = 0; i < customerSortingArray.length - 1; i++)
        {
            for (int j = i + 1; j < customerSortingArray.length; j++)
            {
                if (customerSortingArray[i].getStHolderId().
                    compareTo(customerSortingArray[j].getStHolderId()) > 0)
                {
                    temporaryCustomer = customerSortingArray[i];
                    customerSortingArray[i] = customerSortingArray[j];
                    customerSortingArray[j] = temporaryCustomer;
                }
            }
        }
        customerArrayList.clear();
        customerArrayList.addAll(Arrays.asList(customerSortingArray));
    }

    /**
     * Sorts the suppliers in
     * ascending (alphabetical) order.
     * */
    public void sortSuppliersByName()
    {
        Supplier temporarySupplier;
        addSupplierDataToSortingArray();
        for (int i = 0; i < supplierSortingArray.length - 1; i++)
        {
            for (int j = i + 1; j < supplierSortingArray.length; j++)
            {
                if (supplierSortingArray[i].getName().
                    compareTo(supplierSortingArray[j].getName()) > 0)
                {
                    temporarySupplier = supplierSortingArray[i];
                    supplierSortingArray[i] = supplierSortingArray[j];
                    supplierSortingArray[j] = temporarySupplier;
                }
            }
        }
        supplierArrayList.clear();
        supplierArrayList.addAll(Arrays.asList(supplierSortingArray));
    }

    public int calculateCustomerAge(Customer customer)
    {
        LocalDate dateOfBirth = LocalDate.parse(customer.getDateOfBirth());
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfBirth, currentDate).getYears();
    }

    public String formatDateOfBirth(Customer person)
    {
        LocalDate dateOfBirthToFormat = LocalDate.parse(person.getDateOfBirth());
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return dateOfBirthToFormat.format(dateFormat);
    }

    private void initSortingArrays()
    {
        this.customerSortingArray = new Customer[customerArrayList.size()];
        this.supplierSortingArray = new Supplier[supplierArrayList.size()];
    }

    private void addCustomerDataToSortingArray()
    {
        for(int i = 0; i < customerArrayList.size(); ++i)
        {
            customerSortingArray[i] = customerArrayList.get(i);
        }
    }

    private void addSupplierDataToSortingArray()
    {
        for(int i = 0; i < supplierArrayList.size(); ++i)
        {
            supplierSortingArray[i] = supplierArrayList.get(i);
        }
    }

    public boolean isCustomer(Object object)
    {
        return (object.getClass().equals(Customer.class));
    }

    public boolean isSupplier(Object object)
    {
        return (object.getClass().equals(Supplier.class));
    }

    private void addToCustomerList(Customer object)
    {
        customerArrayList.add(object);
    }

    private void addToSupplierList(Supplier object)
    {
        supplierArrayList.add(object);
    }

}

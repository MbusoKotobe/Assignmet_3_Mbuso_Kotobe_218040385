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

import java.io.*;

/**
 *
 * @author Mbuso Kotobe 218040385
 */
public class Writer {
    PrintWriter objectOutputStream;
    private Reader reader;
    private int rentalsNo;
    private int illegibleRentersNo;

    public Writer()
    {
        reader = new Reader();
        reader.openFile();
        reader.readFile();
        reader.sortCustomersById();
        reader.sortSuppliersByName();
    }

    public PrintWriter getObjectOutputStream()
    {
        return objectOutputStream;
    }

    public void createFile(String fileName)
    {
        try{
            objectOutputStream = new PrintWriter(
                                 new FileWriter(fileName));
        }catch(IOException ioException)
        {
            System.out.println("Failed to create file " + fileName +
                               "Exception Message " + ioException.getMessage());
        }
    }

    public void writeToCustomerFile()
    {
        try{
            writeCustomerHeading();
            writeCustomers();
        }catch (IOException ioException)
        {
            System.out.println("Failed to write to file: " + ioException.getMessage());
        }
    }

    public void writeToSuppliersFile()
    {
        try{
            writeSupplierHeading();
            writeSuppliers();
        }catch (IOException ioException)
        {
            System.out.println("Failed to write to file: " + ioException.getMessage());
        }
    }

    public void closeFile()
    {
        this.objectOutputStream.close();

    }

    private void writeCustomerHeading() throws IOException
    {
        objectOutputStream.write(
                "===================== CUSTOMERS =======================\n" +
                        "ID   \tName      \tSurname   \tDate of birth  \tAge  \n" +
                   "=======================================================\n"
        );
    }

    private void writeSupplierHeading() throws IOException
    {
        objectOutputStream.write(
                "=========================== SUPPLIERS ===========================\n" +
                        "ID   \tName                \tProd Type\tDescription    \n" +
                        "=================================================================\n"
        );
    }

    private void writeCustomers() throws IOException
    {
        for(Customer person : reader.getCustomerArrayList())
        {
                objectOutputStream.write(String.format("%-5s\t%-10s\t%-10s\t%-15s\t%-10s\n",
                person.getStHolderId() ,
                person.getFirstName() ,
                person.getSurName() ,
                reader.formatDateOfBirth(person) ,
                reader.calculateCustomerAge(person)
            ));
            countRenters(person);
        }
        objectOutputStream.write("\n\nNumber of customers who can rent: \t\t" + rentalsNo);
        objectOutputStream.write("\nNumber of customers who cannot rent: \t" + illegibleRentersNo);
    }

    private void countRenters(Customer person)
    {
        if (isEligibleForRenting(person)) ++rentalsNo;
        else countIllegibleRenters();
    }

    private void countIllegibleRenters()
    {
        ++illegibleRentersNo;
    }

    private boolean isEligibleForRenting(Customer person)
    {
        return person.getCanRent();
    }

    private void writeSuppliers() throws IOException
    {
        for(Supplier supplier : reader.getSupplierArrayList())
        {
            objectOutputStream.write(String.valueOf(supplier) + '\n');
        }
    }
}

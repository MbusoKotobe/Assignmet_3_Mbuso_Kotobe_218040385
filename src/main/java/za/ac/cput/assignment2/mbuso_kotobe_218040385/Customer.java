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

/**
 *
 * @author Mbuso Kotobe 218040385
 */
public class Customer extends Stakeholder{
    private String firstName;
    private String surName;
    private String address;
    private String dateOfBirth;
    private double credit;
    private boolean canRent; //true - can rent a car; false - not allowed to rent because they have not returned a previously rented car

    public Customer()
    {
        super();
    }

    public Customer(String stHolderId, String firstName, String surName, String addr, String dob, double cred, boolean canRent)
    {
        super(stHolderId);
        setFirstName(firstName);
        setSurName(surName);
        setAddress(addr);
        setDateOfBirth(dob);
        setCredit(cred);
        setCanRent(canRent);
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setSurName(String surName)
    {
        this.surName = surName;
    }

    public void setAddress(String addr)
    {
        this.address = addr;
    }

    public void setDateOfBirth(String dob)
    {this.dateOfBirth = dob; }

    public void setCredit(double cred)
    {
        this.credit = cred;
    }

    public void setCanRent(boolean canRent)
    {
        this.canRent = canRent;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getSurName()
    {
        return surName;
    }

    public String getAddress()
    {
        return address;
    }

    public String getDateOfBirth()
    {return dateOfBirth; }

    public double getCredit()
    {
        return credit;
    }

    public boolean getCanRent()
    {
        return canRent;
    }

    @Override
    public String toString()
    {
        return String.format("%-10s\t%-10s\t%-10s\t%-15s\t%-10s\tR%-10.2f\t%-10s", super.toString(), getFirstName(), getSurName(),
                getAddress(), getDateOfBirth(), getCredit(), getCanRent());
    }

}

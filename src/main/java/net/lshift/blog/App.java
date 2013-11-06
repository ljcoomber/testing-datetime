package net.lshift.blog;

import java.util.Date;

public class App 
{
    public static void main(String[] args)
    {
	System.out.println("The date is " + getDate());
    }

    public static Date getDate() {
        return new Date();
    }
}

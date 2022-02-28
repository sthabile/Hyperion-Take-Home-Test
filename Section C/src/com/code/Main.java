package com.code;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        while(true)
        {
            boolean isValid = false;
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter an ISBN : ");
            String isbn = sc.nextLine();

            if(isbn == null)
            {
                System.out.println("Enter an ISBN");
                continue;
            }
            else
            {
                int isbnLength = isbn.length();
                System.out.println(isbnLength);

                if(isbnLength == 10)
                {
                   isValid = verifyIsbn10(isbn);
                   if(isValid)
                   {
                       isbn = convertToIsbn13(isbn);
                       System.out.println(isbn);
                   }
                }
                else if(isbnLength == 13)
                {
                   isValid = verifyIsbn13(isbn);
                   if(isValid)
                   {
                       System.out.println("Valid");
                   }
                }
            }
            if (!isValid)
            {
                System.out.println("Invalid");
            }
        }

    }

    private static String convertToIsbn13(String isbn10)
    {
        return "978" + isbn10;
    }

    private static boolean verifyIsbn10(String isbn)
    {
        int sum = 0;
        int index =0;

        for(int i=10; i>=1; i--)
        {
            int multi = 0;

            //If the 10th digit is an X replace it with a 10
            if(index == 9 && isbn.toLowerCase().charAt(9)== 'x')
            {
                sum += 10; //10 * i , in this case i will always be 1, so it makes sense to just add 10 to the sum
            }
            else
            {
                multi = (int) isbn.charAt(index) * i;
            }

            sum +=  multi;

            index++;
        }
        return sum%11 == 0;
    }

    private static boolean verifyIsbn13(String isbn)
    {
        String[] toVerifyWith = "1313131313131".split("");
        String[] isbnSplit = isbn.split("");
        int sum = 0;
        for(int i=0; i<13 ; i++)
        {
            int multi = Integer.parseInt(isbnSplit[i]) * Integer.parseInt(toVerifyWith[i]);
            sum += multi;
        }
        return sum%10 == 0;
    }
}



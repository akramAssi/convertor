package com.example.convertor;

import android.widget.Toast;

public class systemConvert {

    public systemConvert()
    { /*...*/}

    public static String[] convert(String type ,String value)
    {
        String []temp={"","","",""};
        switch (type)
        {
            case "Binary":
            {

                return Convert(Long.parseLong(value, 2));
            }
            case "Decimal":
            {
                return Convert(Long.parseLong(value));
            }
            case "Octal":
            {
                return Convert(Long.parseLong(value,8));
            }
            case "Hexdecimal":
            {
                return Convert(Long.parseLong(value,16));
            }
        }
        return temp;
    }

    private static String [] Convert(long decimalValue)
    {
        String  []temp =new String[4];
//        int decimalValue =Integer.parseInt(value, 2);
        temp[0]=Long.toBinaryString(decimalValue);
        temp[1]=""+decimalValue;
        temp[2]=Long.toOctalString(decimalValue);
        temp[3]=Long.toHexString(decimalValue);
        return temp;
    }

}

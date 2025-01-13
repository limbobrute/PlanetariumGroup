package com.revature.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class FileEncoder
{
    public static String encoder(String filePath)
    {
        String base64file = "";
        File file = new File(filePath);
        try(FileInputStream imageInFile = new FileInputStream(file))
        {
            byte[] fileData = new byte[(int) file.length()];
            imageInFile.read(fileData);
            base64file = Base64.getEncoder().encodeToString(fileData);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found " + e);
        }
        catch(IOException ioe)
        {
            System.out.println("Exception while reading file " + ioe);
        }
        return base64file;
    }
}

package com.amit.al.utilities;

import java.io.*;

public class CustomFileReader {

    /**
     * Reads the file from resources folder and returns a buffered stream.
     *
     * @param filePath filepath of file
     * @return buffered reader
     * @throws IOException
     */
    public static BufferedReader readFileFromResourcesAsBR(String filePath) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filePath);
        BufferedReader br
                = new BufferedReader(new InputStreamReader(inputStream));
        return br;

    }

    public static BufferedReader readFileFromLocation(String filePath) throws FileNotFoundException {
        return new BufferedReader(new FileReader(filePath));
    }

}

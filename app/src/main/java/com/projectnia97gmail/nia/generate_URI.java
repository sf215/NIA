package com.projectnia97gmail.nia;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class generate_URI {
final static String  Basic_Url="https://en.wikipedia.org/w/api.php";

   // https://en.wikipedia.org/w/api.php?action=query&titles=black%20hole&prop=extracts&exintro&explaintext&format=json
    static String search2;

    public static URL buildUrl(String SearchQuery) {
         search2=SearchQuery;



        Uri builtUri = Uri.parse(Basic_Url).buildUpon()
                .appendQueryParameter("action","query")
                .appendQueryParameter("titles", SearchQuery)
                .appendQueryParameter("prop","extracts")     //revisions
                .appendQueryParameter("exintro","")
                .appendQueryParameter("explaintext","")
               // .appendQueryParameter("rvprop","content")
               // .appendQueryParameter("rvsection","0")
                .appendQueryParameter("format","json")
                //.appendQueryParameter("formatversion","2")

                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
    public static String title(){
        return search2;
    }
}

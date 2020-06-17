package com.projectnia97gmail.nia;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

public class Wiki {
    String utext ;

    public Wiki(String search) {
        this.utext = search;
        search=search.trim();
        if(search.contains(" ")){

            search=search.replace(" ","%20");

        }

        URL SearchUrl = generate_URI.buildUrl(search);
        new QueryTask().execute(SearchUrl);
    }

    // public int hashCode()
    //{
      //  utext = Basic.getUsertext();

        //getName();
        //return 0;
    //}


    public class QueryTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
           // URL SearchUrl = generate_URI.buildUrl(utext);
            URL searchUrl = urls[0];
            String SearchResults = null;

            try {
                SearchResults = generate_URI.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return SearchResults;

        }

        @Override
        protected void onPostExecute(String SearchResults) {
            if (SearchResults != null && !SearchResults.equals("")) {
                SearchResults=SearchResults.substring(0,410);

                String search2=generate_URI.title();

                SearchResults=SearchResults.toLowerCase();
                SearchResults = SearchResults.replaceAll("\"", "");
                SearchResults = SearchResults.replaceAll(search2, "");
                SearchResults = SearchResults.replaceAll("\n", "");
                SearchResults = SearchResults.replaceAll("[0-9]","");
                SearchResults=SearchResults.substring(83);
                SearchResults = SearchResults.replaceAll("listen", "");
                SearchResults = SearchResults.replaceAll("[\\-\\+\\{\\}\\%\\=\\<\\>\\*\\[\\]\\^:,]","");
                String[] sr=SearchResults.split("\\.");
                int count=0;
                for (String a : sr) {
                    count++;
                    if(count <2)
                       // System.out.print(a);
                    {
                        Basic.print(search2+a);
                    }
                    else
                        break;
                }

            } else {
                Basic.print("error");
            }
        }
    }
}

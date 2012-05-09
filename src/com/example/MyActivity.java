package com.example;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyActivity extends ListActivity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ArrayList<Item> a = new ArrayList<Item>();
        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(MyActivity.this, R.layout.list_item, R.id.title, a);
        setListAdapter(adapter);

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item)parent.getItemAtPosition(position);
                // When clicked, show a toast with the TextView text
                Toast.makeText(getApplicationContext(), "Opening browser",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink())));



            }
        });

        /*
        ArrayList data = new ArrayList<Map<String,String>>();
        String[] from = {"title", "link"};
        int[] to = {R.id.title, R.id.link};
        SimpleAdapter adapter = new SimpleAdapter(MyActivity.this, (List<? extends Map<String, ?>>) data, R.layout.feed_list, from, to);

        // specify the list adaptor
        setListAdapter(adapter);
        */
        new RequestTask().execute("http://news.google.com/news?pz=1&cf=all&ned=us&hl=en&topic=tc&output=rss");
    }

    private class RequestTask extends AsyncTask<String, String, String> {

        protected String doInBackground(String... uri) {
            String feedString = null;
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(uri[0]);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                StatusLine statusLine = httpResponse.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    httpResponse.getEntity().writeTo(out);
                    out.close();
                    feedString = out.toString();
                } else{
                    //Closes the connection.
                    httpResponse.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }
            return feedString;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //ArrayList data = new ArrayList<Map<String,String>>();

            XMLParser parser = new XMLParser();
            Feed feed = parser.getFeed(result);
            ArrayList<Item> data = feed.getItems();

            // setup the data adaptor
            ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(MyActivity.this, R.layout.list_item, R.id.title, data);
            // specify the list adaptor
            setListAdapter(adapter);
            /*
            for(Item item : feed.getItems()){
                Map m = new HashMap();
                m.put(item.getTitle(), item.getLink());
                data.add(m);
            }
            String[] from = {"title", "link"};
            int[] to = {R.id.title, R.id.link};

            SimpleAdapter adapter = new SimpleAdapter(MyActivity.this, (List<? extends Map<String, ?>>) data, R.layout.feed_list, from, to);

            // specify the list adaptor
            setListAdapter(adapter);
//            setListAdapter(new ArrayAdapter<Item>(MyActivity.this, android.R.layout.simple_list_item_1, feed.getItems()));
*/
        }

    }
}

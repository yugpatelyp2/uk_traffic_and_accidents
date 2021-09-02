package com.app.spotaccident;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotaccident.adapter.MainListAdapter;
import com.app.spotaccident.model.DataPojo;
import com.app.spotaccident.model.TypePojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainListAdapter adapter;
    private String TAG = MainActivity.class.getSimpleName();

    List<DataPojo> listValue = new ArrayList<DataPojo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        getData();
    }

    // getting data from the json file which is added in asset folder.
    private void getData() {
        //converting string to JSONObject and seperate it into the individiual
        // JSONArray pf each type.
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                setData(jsonObject);
                if(i == jsonArray.length()-1){
                    adapter = new MainListAdapter(this, listValue);
                    recyclerView.setAdapter(adapter);
                }
            }
            Log.i(TAG, "getData: " + listValue.size());
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }

    public void setData(JSONObject jsonObject) {
        try {
            DataPojo dataPojo = new DataPojo();
            dataPojo.setTitle(jsonObject.getString("title"));
            JSONArray typesArray = jsonObject.getJSONArray("types");
            List<TypePojo> typeList = new ArrayList<TypePojo>();
            for (int i = 0; i < typesArray.length(); i++) {
                JSONObject typesObj = typesArray.getJSONObject(i);
                TypePojo typePojo = new TypePojo();
                typePojo.setTitle(typesObj.getString("title"));
                typePojo.setPath(typesObj.getString("path"));
                typeList.add(typePojo);
            }
            dataPojo.setType(typeList);
            listValue.add(dataPojo);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }

    // fetching data from json file, provided in asset folder
    public String loadJSONFromAsset() {
        String json = null;
        String fileName = "accident_data.json";
        try {
            InputStream inputStream = getBaseContext().getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
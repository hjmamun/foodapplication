package com.example.android.navdrawerexperiment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class SidesFragment extends Fragment {

    private RecyclerView recyclerView;
    private SidesAdapter sidesAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sides, container, false);



        recyclerView = view.findViewById(R.id.sides_item_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<SideItem> sideItemList = parseJson();
        sidesAdapter = new SidesAdapter(sideItemList);
        recyclerView.setAdapter(sidesAdapter);


        return  view;
    }

    private List<SideItem> parseJson() {
        List<SideItem> sideItems = new ArrayList<>();

        try {
            InputStream inputStream = getContext().getResources().openRawResource(R.raw.sides);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("sides");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                String name = item.getString("name");
                String imageUrl = item.getString("imageUrl");
                String description = item.getString("description");

                SideItem sideItem = new SideItem(name, imageUrl, description);
                sideItems.add(sideItem);
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return sideItems;
    }

}
package com.example.android.navdrawerexperiment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class DrinksFragment extends Fragment {
    private RecyclerView recyclerView;
    private DrinkAdapter drinkAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_drinks, container, false);


        recyclerView = view.findViewById(R.id.drink_item_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<DrinkItem> drinkItemList1 = parseJson();
        drinkAdapter = new DrinkAdapter(drinkItemList1);
        recyclerView.setAdapter(drinkAdapter);



       return  view;
    }
    private List<DrinkItem> parseJson() {
        List<DrinkItem> drinkList = new ArrayList<>();

        try {
            InputStream inputStream = getContext().getResources().openRawResource(R.raw.drinks);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("drink_items");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                String name = item.getString("name");
                String imageUrl = item.getString("imageUrl");
                String description = item.getString("description");

                DrinkItem drinkItem = new DrinkItem(name, imageUrl, description);
                drinkList.add(drinkItem);
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return drinkList;
    }

}
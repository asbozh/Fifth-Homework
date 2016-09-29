package com.asbozh.fifthhomework;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButtonAddItems, mButtonRefresh;
    private RecyclerView mRecyclerView;

    private ArrayList<Item> mItemList = new ArrayList<>();
    private RVAdapter mAdapter;

    SQLHandler mHandler = new SQLHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        setRecyclerView();
        mButtonRefresh.performClick();
    }

    private void setRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rvList);
        mAdapter = new RVAdapter(mItemList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setViews() {
        mButtonAddItems = (Button) findViewById(R.id.btnAdd);
        mButtonRefresh = (Button) findViewById(R.id.btnRefresh);
        mButtonAddItems.setOnClickListener(this);
        mButtonRefresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mButtonAddItems) {
            new AddRecordsToDB().execute("");
        }
        if (v == mButtonRefresh) {
            new ShowAllRecordsFromDB().execute("");
        }
    }

    private void addNewItems() {
        Item newItem = new Item("Bread", "800g", "bread.jpg", "0.80");
        mItemList.add(newItem);

        newItem = new Item("Cheese", "200g", "cheese.jpg", "2.50");
        mItemList.add(newItem);

        newItem = new Item("Milk", "1.5l", "milk.jpg", "1.20");
        mItemList.add(newItem);

        newItem = new Item("Ham", "300g", "ham.png", "3.00");
        mItemList.add(newItem);

        newItem = new Item("Rice", "1kg", "rice.jpg", "2.10");
        mItemList.add(newItem);

        newItem = new Item("Tomato", "500g", "tomato.jpg", "2.00");
        mItemList.add(newItem);

        newItem = new Item("Water Melon", "4kg", "water_melon.jpg", "3.45");
        mItemList.add(newItem);

        newItem = new Item("Egg", "20g", "egg.jpg", "0.20");
        mItemList.add(newItem);

        newItem = new Item("Carrot", "50g", "carrot.jpg", "0.50");
        mItemList.add(newItem);

        newItem = new Item("Chocolate", "100g", "chocolate.jpg", "2.20");
        mItemList.add(newItem);
    }

    private class AddRecordsToDB extends AsyncTask<String, Void, Boolean> {


        @Override
        protected Boolean doInBackground(String... params) {

            addNewItems();
            mHandler.open();
            for (Item itemToAdd : mItemList) {
                mHandler.createEntry(SQLContract.ItemEntry.TABLE_NAME, itemToAdd.getTitle(), itemToAdd.getDescription(), itemToAdd.getIcon(), itemToAdd.getPrice());
            }
            mHandler.close();

            return true;
        }
    }

    private class ShowAllRecordsFromDB extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {

            mHandler.open();
            mItemList.clear();
            mItemList.addAll(mHandler.getAllEntries(SQLContract.ItemEntry.TABLE_NAME));
            mHandler.close();

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            mAdapter.notifyDataSetChanged();
        }
    }
}

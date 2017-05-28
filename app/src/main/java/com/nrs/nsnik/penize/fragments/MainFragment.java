package com.nrs.nsnik.penize.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.nrs.nsnik.penize.R;
import com.nrs.nsnik.penize.adapters.MainListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainFragment extends Fragment {

    @BindView(R.id.mainList) RecyclerView mMainList;
    @BindView(R.id.mainFab) FloatingActionMenu mActionMenu;
    private static final String TAG = MainFragment.class.getSimpleName();
    private Unbinder mUnbinder;
    private List<String> mDateList;
    private MainListAdapter mAdapter;

    public MainFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mUnbinder = ButterKnife.bind(this, v);
        initialize();
        listeners();
        setupFab();
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_frag_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuFragSearch:
                showSearchList();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSearchList(){
        AlertDialog.Builder searchDialog= new AlertDialog.Builder(getActivity());
        List<String> searchList = Arrays.asList(getResources().getStringArray(R.array.searchItems));
        ArrayAdapter<String> searchAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,searchList);
        searchDialog.setAdapter(searchAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        Toast.makeText(getActivity(),"one",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(),"two",Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(),"three",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        searchDialog.create().show();
    }

    private void setupFab() {
        FloatingActionButton addIncome = new com.github.clans.fab.FloatingActionButton(getActivity());
        addIncome.setButtonSize(FloatingActionButton.SIZE_NORMAL);
        addIncome.setLabelText(getResources().getString(R.string.navItem4));
        addIncome.setImageResource(R.drawable.add_24);
        addIncome.setColorNormal(ContextCompat.getColor(getActivity(),R.color.colorAccent));
        addIncome.setColorPressed(ContextCompat.getColor(getActivity(),R.color.colorAccentLight));
        mActionMenu.addMenuButton(addIncome);
        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        FloatingActionButton addSavings = new com.github.clans.fab.FloatingActionButton(getActivity());
        addSavings.setButtonSize(FloatingActionButton.SIZE_NORMAL);
        addSavings.setLabelText(getResources().getString(R.string.navItem3));
        addSavings.setImageResource(R.drawable.add_24);
        addSavings.setColorNormal(ContextCompat.getColor(getActivity(),R.color.colorAccent));
        addSavings.setColorPressed(ContextCompat.getColor(getActivity(),R.color.colorAccentLight));
        mActionMenu.addMenuButton(addSavings);
        addSavings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        FloatingActionButton addExpenditure = new com.github.clans.fab.FloatingActionButton(getActivity());
        addExpenditure.setButtonSize(FloatingActionButton.SIZE_NORMAL);
        addExpenditure.setLabelText(getResources().getString(R.string.navItem2));
        addExpenditure.setImageResource(R.drawable.add_24);
        addExpenditure.setColorNormal(ContextCompat.getColor(getActivity(),R.color.colorAccent));
        addExpenditure.setColorPressed(ContextCompat.getColor(getActivity(),R.color.colorAccentLight));
        mActionMenu.addMenuButton(addExpenditure);
        addExpenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initialize() {
        mDateList = new ArrayList<>();
        mMainList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MainListAdapter(getActivity(), mDateList);
        mMainList.setAdapter(mAdapter);
        makeList();
    }

    private void makeList() {
        Calendar now = Calendar.getInstance();
        long second = TimeUnit.MILLISECONDS.toDays(now.getTimeInMillis());
        mDateList.add("Monthly Expenditure");
        for (int i = 0; i <7; i++) {
            mDateList.add(formatDate(second,(-i)));
        }
        mAdapter.modifyAllValues(mDateList);
    }


    private void listeners() {

    }

    private String formatDate(long today,int diff) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, diff);
        long thisDay = TimeUnit.MILLISECONDS.toDays(calendar.getTimeInMillis());
        if(thisDay-today==0){
            return "Today";
        }else if(thisDay-today==-1){
            return "Yesterday";
        }else {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d", Locale.ENGLISH);
            return sdf.format(calendar.getTimeInMillis());
        }
    }

    private void cleanUp() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        cleanUp();
        super.onDestroy();
    }
}

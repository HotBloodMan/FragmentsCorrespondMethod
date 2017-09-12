package com.ljt.fragmentscorrespondmethod;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NameFragment extends Fragment {

    private List<String> names;
    private ListView lv;
    private ShowName mCallback;
    private ArrayAdapter<String> adapter;
    public NameFragment() {
    }

    public interface  ShowName{
        public void showProByName(String name);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        names = new ArrayList<String>();
        names.add("AA");
        names.add("BB");
        names.add("CC");
        names.add("DD");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_name, container, false);
        initLV(view);
        return view;
    }

    private void initLV(View view) {
        lv = (ListView) view.findViewById(R.id.name_lv);
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, names);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView nameTV = (TextView) view;
                String name = nameTV.getText().toString();

                if ("AA".equals(name)) {
                    mCallback.showProByName(name);
                } else if ("BB".equals(name)) {
                    ContentFragment cf = (ContentFragment) getActivity()
                            .getFragmentManager().findFragmentById(
                                    R.id.content_fg);
                    cf.showPro(name);
                } else if ("CD".equals(name) || "CC".equals(name)) {
                    //发广播
                    Intent intent = new Intent("showPro");
                    intent.putExtra("name", name);
                    LocalBroadcastManager.getInstance(getActivity())
                            .sendBroadcast(intent);
                } else if ("DD".equals(name)) {
                    ((MainActivity) getActivity()).showProByName(name);
                }
                Log.d("TAG","name=="+name.toString());
            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity!=null){
            mCallback= (ShowName) activity;
        }
    }
    public void clearData() {
        names = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, names);
        lv.setAdapter(adapter);
    }
}

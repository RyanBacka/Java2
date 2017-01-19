// Ryan Backa
// JAV2 - 1612
// DetailsFragment

package com.ce07.java2.backaryan.backaryan_ce07.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ce07.java2.backaryan.backaryan_ce07.R;
import com.ce07.java2.backaryan.backaryan_ce07.ReadData;
import com.ce07.java2.backaryan.backaryan_ce07.SaveData;
import com.ce07.java2.backaryan.backaryan_ce07.objects.Person;

import java.util.ArrayList;

public class DetailsFragment extends Fragment {

    public static final String PERSON = "Person";

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(Person person){
        DetailsFragment frag = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(PERSON, person);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Person person = (Person) getArguments().getSerializable(PERSON);
        ((TextView)getActivity().findViewById(R.id.details_name)).setText(person.getName());
        ((TextView)getActivity().findViewById(R.id.details_dob)).setText(person.getBirthday());
        ((TextView)getActivity().findViewById(R.id.details_age)).setText(person.getAge()+"");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Person person = (Person) getArguments().getSerializable(PERSON);
        switch (item.getItemId()){

            case R.id.deleteItem:
                ReadData readData = new ReadData();
                ArrayList<Person> people = readData.readData(getActivity().getApplicationContext());
                for(int i = 0; i<people.size(); i++){
                    String name = people.get(i).getName();
                    if(person!=null) {
                        if (person.getName().equals(name)) {
                            people.remove(i);
                        }
                    }
                }
                SaveData saveData = new SaveData();
                saveData.saveData(people,getActivity().getApplicationContext());
                getActivity().finish();
                break;
            case R.id.shareItem:
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                if(person!=null) {
                    sendIntent.putExtra(Intent.EXTRA_TEXT, person.getName()+"\n"+person.getBirthday()+"\n"+person.getAge());
                    sendIntent.setType("text/plain");
                    startActivity(Intent.createChooser(sendIntent, "Share data with..."));
                }
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

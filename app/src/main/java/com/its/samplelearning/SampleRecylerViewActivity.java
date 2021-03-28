package com.its.samplelearning;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.its.samplelearning.databinding.ActivitySampleRecyBinding;

import java.util.ArrayList;

public class SampleRecylerViewActivity extends AppCompatActivity {

    private ActivitySampleRecyBinding objUIBinding;

    private RcySampleAdapter rcySampleAdapter;
    private ArrayList<String> sampleData;
    private ArrayList<StudentModel> sampleData1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        objUIBinding = DataBindingUtil.setContentView(this, R.layout.activity_sample_recy);
        Utility.setToolbar(this, "Sample RCY VIEW");

        initUI();
    }

    private void initUI() {

        sampleData = new ArrayList<>();
        sampleData.add("Liam");
        sampleData.add("Noah");
        sampleData.add("Oliver");
        sampleData.add("William");
        sampleData.add("Elijah");
        sampleData.add("James");
        sampleData.add("Benjamin");
        sampleData.add("Lucas");
        sampleData.add("Mason");
        sampleData.add("Ethan");
        sampleData.add("Liam");
        sampleData.add("Noah");
        sampleData.add("Oliver");
        sampleData.add("William");
        sampleData.add("Elijah");
        sampleData.add("James");
        sampleData.add("Benjamin");
        sampleData.add("Lucas");
        sampleData.add("Mason");
        sampleData.add("Ethan");
        sampleData.add("Liam");
        sampleData.add("Noah");
        sampleData.add("Oliver");
        sampleData.add("William");
        sampleData.add("Elijah");
        sampleData.add("James");
        sampleData.add("Benjamin");
        sampleData.add("Lucas");
        sampleData.add("Mason");
        sampleData.add("Ethan");
        sampleData.add("Liam");
        sampleData.add("Noah");
        sampleData.add("Oliver");
        sampleData.add("William");
        sampleData.add("Elijah");
        sampleData.add("James");
        sampleData.add("Benjamin");
        sampleData.add("Lucas");
        sampleData.add("Mason");
        sampleData.add("Ethan");


        sampleData1 = new ArrayList<>();
        StudentModel studentModel = new StudentModel("01","Liam","20");
        sampleData1.add(studentModel);

        StudentModel studentModel1 = new StudentModel("02","Noah","21");
        sampleData1.add(studentModel1);

        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));
        sampleData1.add(new StudentModel("03","Oliver","22"));

        setAdapter();
    }

    private void setAdapter() {
        rcySampleAdapter = new RcySampleAdapter(sampleData1);

        objUIBinding.rcyView.setLayoutManager(new LinearLayoutManager(this));
        objUIBinding.rcyView.setHasFixedSize(true);
        objUIBinding.rcyView.setAdapter(rcySampleAdapter);
    }
}

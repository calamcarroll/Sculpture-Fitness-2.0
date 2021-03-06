package ie.app.sculpturefitness20;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditWorkouts extends AppCompatActivity {
    DB_Handler myDB;
    ListAdapter myAdapter;
    ListView myList;
    ArrayList<String> ProgramName;
    ArrayList<String> MuscleChoice;

    String PassName;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workouts);




        myDB = new DB_Handler(this);
        ProgramName = new ArrayList<>();
        MuscleChoice = new ArrayList<>();
        Cursor myCursor = myDB.getFirstListContents();


        if (myCursor.getCount() == 0) {
            Toast.makeText(EditWorkouts.this, "No programs to show", Toast.LENGTH_LONG).show();
        } else {
            while (myCursor.moveToNext()) {
                ProgramName.add(myCursor.getString(0));
                MuscleChoice.add(myCursor.getString(1));
                myAdapter = new CustomAdaptor(this, ProgramName, MuscleChoice);
                myList = (ListView) findViewById(R.id.editWorkoutList);

                myList.setAdapter(myAdapter);




            }
            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent openEditWorkout = new Intent(EditWorkouts.this, ShowEditWorkouts.class);

                    openEditWorkout.putExtra(PassName, String.valueOf(parent.getItemAtPosition(position)));
                    startActivity(openEditWorkout);
                }
            });

        }


    }
}

package sg.edu.rp.c346.id20039529.singaporesisland;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etTitle, etSingers, etYear;
    Button btnCancel, btnUpdate, btnDelete;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_third));

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etSingers = (EditText) findViewById(R.id.etSingers);
        etYear = (EditText) findViewById(R.id.etYear);
        ratingBar = findViewById(R.id.ratingbarStars);

        Intent i = getIntent();
        final Island currentSong = (Island) i.getSerializableExtra("song");

        etID.setText(currentSong.getId()+"");
        etTitle.setText(currentSong.getTitle());
        etSingers.setText(currentSong.getSingers());
        etYear.setText(currentSong.getYearReleased()+"");

        ratingBar.setRating(currentSong.getStars());
        /*switch (currentSong.getStars()){
            case 5: rb5.setChecked(true);
                    break;
            case 4: rb4.setChecked(true);
                    break;
            case 3: rb3.setChecked(true);
                    break;
            case 2: rb2.setChecked(true);
                    break;
            case 1: rb1.setChecked(true);
        }*/

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentSong.setTitle(etTitle.getText().toString().trim());
                currentSong.setSingers(etSingers.getText().toString().trim());
                int year = 0;
                try {
                    year = Integer.valueOf(etYear.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(ThirdActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentSong.setYearReleased(year);

                //int selectedRB = rg.getCheckedRadioButtonId();
                //RadioButton rb = (RadioButton) findViewById(selectedRB);
                //currentSong.setStars(Integer.parseInt(rb.getText().toString()));
                currentSong.setStars((int) ratingBar.getRating());
                int result = dbh.updateSong(currentSong);
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Song updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the island /n" + currentSong.getTitle());
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(ThirdActivity.this);
                         dbh.deleteSong(currentSong.getId());

                            Toast.makeText(ThirdActivity.this, "Song deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                });
                myBuilder.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard changes");
                myBuilder.setCancelable(false);
                myBuilder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                myBuilder.setPositiveButton("CANCEL", null);

            }
        });

    }


}
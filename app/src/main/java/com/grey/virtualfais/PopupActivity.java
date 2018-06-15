package com.grey.virtualfais;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.grey.virtualfais.daos.EmployeeDao;
import com.grey.virtualfais.daos.RoomDao;
import com.grey.virtualfais.models.Employee;
import com.grey.virtualfais.models.Room;
import com.grey.virtualfais.services.AppDatabase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PopupActivity extends Activity {

    static private RoomDao roomDao;
    static private EmployeeDao employeeDao;
    ImageButton closeBtn;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup);

        // setting popup window size according to device screen size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .75), (int) (height * .45));

        // close button handler
        closeBtn = findViewById(R.id.ib_close);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupActivity.this.finish();
            }
        });

        String room_id = getIntent().getStringExtra("room_id");
        AppDatabase appDatabase = AppDatabase.getInstance(this);

        roomDao = appDatabase.roomDao();
        Room room = roomDao.getByRoomId(room_id);

        TextView roomNumberTextField = findViewById(R.id.room_popup);
        TextView phoneTextField = findViewById(R.id.phone_num_popup);
        TextView employeesTextField = findViewById(R.id.employees_popup);

        roomNumberTextField.setText(room.getId());


        employeeDao = appDatabase.employeeDao();
        List<Employee> employeesList = employeeDao.getEmployeesByRoomId(room_id);

        if (employeesList != null && employeesList.size() > 0) {
            Set<String> fullNamesSet = new HashSet<>();
            Set<String> telephonesSet = new HashSet<>();
            for (Employee e : employeesList) {
                fullNamesSet.add(e.getName().getFirstName() + " " + e.getName().getLastName());
                telephonesSet.add(e.getTelephone());
            }
            employeesTextField.setText(String.join(", ", fullNamesSet));
            phoneTextField.setText(String.join(", ", telephonesSet));

        } else {
            findViewById(R.id.employees_label).setVisibility(View.GONE);
            employeesTextField.setVisibility(View.GONE);
            findViewById(R.id.phone_label).setVisibility(View.GONE);
            phoneTextField.setVisibility(View.GONE);
        }
    }
}

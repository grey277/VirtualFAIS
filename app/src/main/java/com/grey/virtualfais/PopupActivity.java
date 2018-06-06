package com.grey.virtualfais;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.grey.virtualfais.daos.EmployeeDao;
import com.grey.virtualfais.daos.RoomDao;
import com.grey.virtualfais.models.Employee;
import com.grey.virtualfais.models.Room;
import com.grey.virtualfais.services.AppDatabase;

import java.util.List;

public class PopupActivity extends Activity {

    static private RoomDao roomDao;
    static private EmployeeDao employeeDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup);

        // setting popup window size according to device screen size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .75), (int) (height * .70));

        String room_id = getIntent().getStringExtra("room_id");
        AppDatabase appDatabase = AppDatabase.getInstance(this);

        roomDao = appDatabase.roomDao();
        Room room = roomDao.getByRoomId(room_id);

        TextView roomNumberTextField = findViewById(R.id.room_popup);   // room number
        TextView phoneTextField = findViewById(R.id.phone_num_pp);      // phone number
        TextView employeesTextField = findViewById(R.id.employee_pp);   // employees names

        // show room number in popup
        roomNumberTextField.setText(room.getId());

        employeeDao = appDatabase.employeeDao();
        Employee employee = employeeDao.getEmployeesByRoomId(room_id);

        if (employee != null) {
            employeesTextField.setText(employee.getName().getFirstName() + " " + employee.getName().getLastName());
        } else {
            findViewById(R.id.employees_pp).setVisibility(View.GONE); // text field title
            employeesTextField.setVisibility(View.GONE);
        }

        String phoneNum = "";
        phoneNum = "126644511";

        if (phoneNum != null && phoneNum != "") {
            phoneTextField.setText(phoneNum);
        } else {
            findViewById(R.id.phone_pp).setVisibility(View.GONE); // text field title
            phoneTextField.setVisibility(View.GONE);
        }

    }
}

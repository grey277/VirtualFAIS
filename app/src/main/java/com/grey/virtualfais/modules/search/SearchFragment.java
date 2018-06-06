package com.grey.virtualfais.modules.search;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.grey.virtualfais.R;
import com.grey.virtualfais.base.BaseFragment;
import com.grey.virtualfais.daos.EmployeeDao;
import com.grey.virtualfais.daos.RoomDao;
import com.grey.virtualfais.models.Employee;
import com.grey.virtualfais.models.Room;
import com.grey.virtualfais.services.AppDatabase;

import java.util.ArrayList;


public class SearchFragment extends BaseFragment {

    public final static String TAG = "SearchFragment",
            TITLE = "SearchFragment.TITLE",
            SUBTITLE = "SearchFragment.SUBTITLE";
    private String title, subtitle;
    private AutoCompleteTextView autoCompleteTextView;
    private Button navigate;
    private AppDatabase appDatabase = AppDatabase.getInstance(getContext());
    private EmployeeDao employeeDao = appDatabase.employeeDao();
    private RoomDao roomDao = appDatabase.roomDao();

    private String selectedValue = "";


    SearchHandler searchHandler;
    private ArrayList<String> catchwordsList;

    public static SearchFragment newInstance(String title, String subtitle, SearchHandler searchHandler) {
        SearchFragment result = new SearchFragment();

        Bundle bundle = new Bundle(2);
        bundle.putString(TITLE, title);
        bundle.putString(SUBTITLE, subtitle);
        result.setArguments(bundle);

        result.searchHandler = searchHandler;

        return result;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            if (title == null)
                this.title = getArguments().getString(TITLE, null);
            if (subtitle == null)
                this.subtitle = getArguments().getString(SUBTITLE, null);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        bind(view);
        setupViews();

        return view;
    }

    private void bind(View view) {
        autoCompleteTextView = view.findViewById(R.id.auto_text_view);
        navigate = view.findViewById(R.id.confirm);
    }

    private void setupViews() {
        catchwordsList = new ArrayList<>();
        for (Room room : roomDao.getAll()) {
            catchwordsList.add(room.getId());
        }
        for (Employee employee : employeeDao.getAll()) {
            String employeeItem = employee.getName().getFirstName() + " " + employee.getName().getLastName() + " (" + employee.getRoomId() + ")";
            if (employee.getName().getTitle() != null && !employee.getName().getTitle().equals("")) {
                employeeItem = employee.getName().getTitle() + " " + employeeItem;
            }
            catchwordsList.add(employeeItem);
        }
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.select_dialog_item, catchwordsList);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> updateButtonState((String) parent.getItemAtPosition(position)));

        autoCompleteTextView.setOnDismissListener(this::updateButtonState);

        autoCompleteTextView.setOnKeyListener((v, keyCode, event) -> {
            updateButtonState();
            return false;
        });

        navigate.setOnClickListener(button -> {
            if (!button.isClickable()) {
                return;
            }

            hideKeyboard();
            if (selectedValue.indexOf('(') > -1) {
                searchHandler.accept(selectedValue.substring(selectedValue.indexOf('(') + 1, selectedValue.indexOf(')')));
            } else {
                searchHandler.accept(selectedValue);
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void updateButtonState() {
        updateButtonState(autoCompleteTextView.getText().toString());
    }

    private void updateButtonState(String selectedValue) {
        this.selectedValue = selectedValue;
        boolean buttonActive = catchwordsList.contains(selectedValue);
        setButtonActive(navigate, buttonActive);
    }

    private void setButtonActive(Button button, boolean active) {
        button.setClickable(active);
        if (active) {
            button.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        } else {
            button.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.dark_gray));
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubtitle() {
        return null;
    }

    @Override
    public void onBackOnScreen() {
        setToolbarTitle(getTitle(), getSubtitle());
        setArrow();
    }

    @Override
    public boolean onBackPressed() {
        return super.onBackPressed();
    }
}

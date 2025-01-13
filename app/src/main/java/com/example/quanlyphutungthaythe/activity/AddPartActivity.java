package com.example.quanlyphutungthaythe.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlyphutungthaythe.R;
import com.example.quanlyphutungthaythe.model.Part;
import com.example.quanlyphutungthaythe.sqlite.PartDAO;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddPartActivity extends AppCompatActivity {

    private Button btnAdd, btnCancel;
    private TextInputEditText txtName, txtCategory, txtDescription, txtPrice;
    private TextInputLayout txtNameLayout, txtCategoryLayout, txtDescriptionLayout, txtPriceLayout;
    private PartDAO _partDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_part);

        addView();
        addEvent();
    }

    private void addView(){
        _partDAO = new PartDAO(this);
        btnAdd =  findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

        txtName = findViewById(R.id.txtName);
        txtCategory = findViewById(R.id.txtCategory);
        txtDescription = findViewById(R.id.txtDescription);
        txtPrice = findViewById(R.id.txtPrice);

        txtNameLayout = findViewById(R.id.txtNameLayout);
        txtCategoryLayout = findViewById(R.id.txtCategoryLayout);
        txtDescriptionLayout = findViewById(R.id.txtDescriptionLayout);
        txtPriceLayout = findViewById(R.id.txtPriceLayout);
    }

    private void addEvent(){
        btnAdd.setOnClickListener(view -> {
            if (validate()) {
                var result = _partDAO.addPart(new Part(0, txtName.getText().toString(), txtCategory.getText().toString(),
                        txtDescription.getText().toString(), Float.parseFloat(txtPrice.getText().toString())));
                if (result){
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(view -> {
            finish();
        });
    }

    private boolean validate(){
        boolean result = true;

        if (txtName.getText().toString().isEmpty()){
            txtNameLayout.setError("Vui lòng nhập tên");
            result = false;
        }

        if (txtCategory.getText().toString().isEmpty()){
            txtCategoryLayout.setError("Vui lòng nhập danh mục");
            result = false;
        }

        if (txtDescription.getText().toString().isEmpty()){
            txtDescriptionLayout.setError("Vui lòng nhập mô tả");
            result = false;
        }

        if (txtPrice.getText().toString().isEmpty()){
            txtPriceLayout.setError("Vui lòng nhập giá");
            result = false;
        }

        return result;
    }

}
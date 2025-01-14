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

public class UpdateDeletePartActivity extends AppCompatActivity {

    private TextInputEditText txtName, txtCategory, txtDescription, txtPrice;
    private TextInputLayout txtNameLayout, txtCategoryLayout, txtDescriptionLayout, txtPriceLayout;
    private Button btnUpdate, btnDelete, btnCancel;
    private PartDAO _partDAO;
    private Part _part;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_delete_part);

        addView();
        addEvent();
        loadData();
    }

    private void addView(){
        _partDAO = new PartDAO(this);
        btnUpdate =  findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        txtName = findViewById(R.id.txtName);
        txtCategory = findViewById(R.id.txtCategory);
        txtDescription = findViewById(R.id.txtDescription);
        txtPrice = findViewById(R.id.txtPrice);

        txtNameLayout = findViewById(R.id.txtNameLayout);
        txtCategoryLayout = findViewById(R.id.txtCategoryLayout);
        txtDescriptionLayout = findViewById(R.id.txtDescriptionLayout);
        txtPriceLayout = findViewById(R.id.txtPriceLayout);

        _part = (Part) getIntent().getSerializableExtra("part");
        txtName.setText(_part.getName());
        txtCategory.setText(_part.getCategory());
        txtDescription.setText(_part.getDescription());
        txtPrice.setText(_part.getPrice()+"");
    }

    private void addEvent(){
        btnUpdate.setOnClickListener(view -> {
            if (validate()) {
                _partDAO.updatePart(new Part(_part.getId(), txtName.getText().toString(), txtCategory.getText().toString(),
                        txtDescription.getText().toString(), Float.parseFloat(txtPrice.getText().toString())));
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btnDelete.setOnClickListener(view -> {
            _partDAO.deletePart(_part.getId());
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
            finish();
        });
        btnCancel.setOnClickListener(view -> {
            finish();
        });
    }

    private void loadData(){

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
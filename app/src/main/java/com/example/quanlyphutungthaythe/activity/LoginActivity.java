package com.example.quanlyphutungthaythe.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlyphutungthaythe.R;
import com.example.quanlyphutungthaythe.sqlite.AccountDAO;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    //Bước 1: Khai báo các biến
    TextInputEditText editTextAccount, editTextPassWord;
    CheckBox checkBoxSave;
    Button buttonLogin;
    //Bước 4 Khai báo tên tập tin
    String perferName = "DangNhapData";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        //Bước 3: Gọi hàm
        AddView();

        AddEvents();
    }

    //Bước 2 Truy Xuất các view
    private void AddView() {
        editTextAccount = findViewById(R.id.editTextAccount);
        editTextPassWord = findViewById(R.id.editTextPassWord);
        checkBoxSave = findViewById(R.id.checkBoxSave);
        buttonLogin = findViewById(R.id.btnLogin);
    }

    //Bước 5 Viết hàm lưu trạng thái đăng nhập
    private void SavePreferences() {
        SharedPreferences preferences = getSharedPreferences(perferName, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if (checkBoxSave.isChecked()) {
            editor.putString("TaiKhoan", editTextAccount.getText().toString().trim());
            editor.putString("MatKhau", editTextPassWord.getText().toString().trim());
            editor.putBoolean("Luu", checkBoxSave.isChecked());
        } else {
            editor.clear();
        }
        editor.commit();
    }

    //Buoc71 6 Gọi hàm onPause
    @Override
    protected void onPause() {
        super.onPause();
        //Gọi hàm onPause
        SavePreferences();
    }

    //Bước 8 Gọi hàm chứa các sự kiện
    private void AddEvents() {
        //Bước 7.1 Gán sự kiện lên nút đăng nhập
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = editTextAccount.getText().toString().trim();
                String passWord = editTextPassWord.getText().toString().trim();
                if (account.equals("") && passWord.equals("")) {
                    Toast.makeText(LoginActivity.this, "Chưa nhập tài khoản hoặc mật khẩu", Toast.LENGTH_LONG).show();

                }
                else {
                    AccountDAO accountDAO = new AccountDAO(LoginActivity.this);
                    Boolean ketQua = accountDAO.isAccount_PassWord(account, passWord);
                    if (ketQua) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, ManagePartActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void RestorePreferences() {
        SharedPreferences preferences = getSharedPreferences(perferName, MODE_PRIVATE);

        boolean save = preferences.getBoolean("Lưu", false);
        if (save) {
            String account = preferences.getString("Tài khoản", "");
            String password = preferences.getString("Mật khẩu", "");
            editTextAccount.setText(account);
            editTextPassWord.setText(password);
        }
        checkBoxSave.setChecked(save);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RestorePreferences();
    }
}
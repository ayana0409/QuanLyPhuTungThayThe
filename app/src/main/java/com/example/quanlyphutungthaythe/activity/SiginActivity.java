package com.example.quanlyphutungthaythe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlyphutungthaythe.R;
import com.example.quanlyphutungthaythe.sqlite.AccountDAO;
import com.google.android.material.textfield.TextInputEditText;

public class SiginActivity extends AppCompatActivity {
    TextInputEditText edUsername, edPassword, edConfirm;
    Button btnAdd, btnLogin;
    AccountDAO accountDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sigin);

        edUsername = findViewById(R.id.edUsername_AddUser);
        edPassword= findViewById(R.id.edPassword_AddUser);
        edConfirm= findViewById(R.id.edConfirm_AddUser);
        btnAdd=findViewById(R.id.btnAdd_AddUser);
        btnLogin=findViewById(R.id.btnLogin_AddUser);
        accountDAO=new AccountDAO(this);

        // Gọi hàm
        addEvent();
    }
    public void addEvent(){
        btnAdd.setOnClickListener(v -> {
            String account = edUsername.getText().toString().trim();
            String passWord = edPassword.getText().toString().trim();
            String confirm = edConfirm.getText().toString().trim();
            if (account.equals("") && passWord.equals("")&& confirm.equals("")) {
                Toast.makeText(SiginActivity.this, "Chưa nhập tài khoản hoặc mật khẩu", Toast.LENGTH_LONG).show();

            }
            else {
                if (!passWord.equals(confirm)){
                    Toast.makeText(SiginActivity.this, "Mật khẩu xác nhận không trùng khớp", Toast.LENGTH_SHORT).show();
                    return;
                }
                Boolean ketQua = accountDAO.insert(account, passWord);
                if (ketQua) {
                    Toast.makeText(SiginActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SiginActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SiginActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(SiginActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
package com.example.computermanage.ui.hoadonxuat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.computermanage.dao.DAOHoaDon;
import com.example.computermanage.dao.DAOHoaDonCT;
import com.example.computermanage.dao.DAOKhachHang;
import com.example.computermanage.dao.DAONhanVien;
import com.example.computermanage.dao.DAOSanPham;
import com.example.computermanage.model.HoaDon;
import com.example.computermanage.model.HoaDonChiTiet;
import com.example.computermanage.model.KhachHang;
import com.example.computermanage.model.NhanVien;
import com.example.computermanage.model.SanPham;
import com.example.computermanage.R;

import java.text.DecimalFormat;

public class ActivityChiTietHoaDonXuat extends AppCompatActivity {
    TextView tv_khachhangCTHDXuat, tv_ngayCTHDXuat, tv_nguoitaoCTHDXuat, tv_trangthaiCTHDXuat,
            tv_tenSPCTHDXuat, tv_soluongCTHDXuat, tv_dongiaCTHDXuat, tv_baohanhCTHDXuat, tv_khuyenmaiCTHDXuat, tv_thanhtienCTHDXuat;
    DAOHoaDon daoHoaDon;
    DAOHoaDonCT daoHoaDonCT;
    DAOSanPham daoSanPham;
    DAONhanVien daoNhanVien;
    DAOKhachHang daoKhachHang;
    String mahdxuat;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don_xuat);


        tv_khachhangCTHDXuat = findViewById(R.id.tv_khachhangCTHDXuat);
        tv_ngayCTHDXuat = findViewById(R.id.tv_ngayCTHDXuat);
        tv_nguoitaoCTHDXuat = findViewById(R.id.tv_nguoitaoCTHDXuat);
        tv_trangthaiCTHDXuat = findViewById(R.id.tv_trangthaiCTHDXuat);
        tv_tenSPCTHDXuat = findViewById(R.id.tv_tenSPCTHDXuat);
        tv_soluongCTHDXuat = findViewById(R.id.tv_soluongCTHDXuat);
        tv_dongiaCTHDXuat = findViewById(R.id.tv_dongiaCTHDXuat);
        tv_baohanhCTHDXuat = findViewById(R.id.tv_baohanhCTHDXuat);
        tv_khuyenmaiCTHDXuat = findViewById(R.id.tv_khuyenmaiCTHDXuat);
        tv_thanhtienCTHDXuat = findViewById(R.id.tv_thanhtienCTHDXuat);
        toolbar = findViewById(R.id.tb_chitietHDXuat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Th??m h??ng");
        toolbar.setTitleTextColor(Color.WHITE);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        daoHoaDon = new DAOHoaDon(this);
        daoHoaDonCT = new DAOHoaDonCT(this);
        daoSanPham = new DAOSanPham(this);
        daoNhanVien = new DAONhanVien(this);
        daoKhachHang = new DAOKhachHang(this);
        getData();
    }
    private void getData(){
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        Intent intent = getIntent();
        mahdxuat = intent.getStringExtra("mahdxuat");
        getSupportActionBar().setTitle(mahdxuat);
        HoaDon hoaDon = daoHoaDon.getID(mahdxuat);
        HoaDonChiTiet hoaDonChiTiet = daoHoaDonCT.getID(mahdxuat);
        SanPham sanPham=daoSanPham.getID(hoaDonChiTiet.getMssp());
        NhanVien nhanVien = daoNhanVien.getID(hoaDon.getMsnv());
        tv_nguoitaoCTHDXuat.setText(nhanVien.getHoten());
        KhachHang khachHang = daoKhachHang.getID(hoaDon.getMskh());
        tv_ngayCTHDXuat.setText(hoaDon.getNgaymua());
        tv_khachhangCTHDXuat.setText(khachHang.getHoten());
        tv_tenSPCTHDXuat.setText(sanPham.getTensp());
        tv_soluongCTHDXuat.setText(hoaDonChiTiet.getSoluong()+"");
        tv_dongiaCTHDXuat.setText(decimalFormat.format(hoaDonChiTiet.getDongia())+" VN??");
        switch (hoaDon.getTrangthai()) {
            case 0:
                tv_trangthaiCTHDXuat.setText("Like new 99%");
                break;
            case 1:
                tv_trangthaiCTHDXuat.setText("M???i");
                break;
            case 3:
                tv_trangthaiCTHDXuat.setText("C??");
                break;
        }
        switch (hoaDonChiTiet.getBaohanh()) {
            case 0:
                tv_baohanhCTHDXuat.setText("Kh??ng b???o h??nh");
                break;
            case 1:
                tv_baohanhCTHDXuat.setText("6 th??ng BH");
                break;
            case 2:
                tv_baohanhCTHDXuat.setText("12 th??ng BH");
                break;
        }
        double tien=hoaDonChiTiet.getSoluong()*hoaDonChiTiet.getDongia();
        switch (hoaDonChiTiet.getGiamgia()) {
            case 0:
                tv_khuyenmaiCTHDXuat.setText("kh??ng khuy???n m??i");
                tv_thanhtienCTHDXuat.setText(decimalFormat.format(tien) + " VN??");
                break;
            case 1:
                tv_khuyenmaiCTHDXuat.setText("5%");
                tv_thanhtienCTHDXuat.setText(decimalFormat.format(tien - (tien * 0.05)) + " VN??");
                break;
            case 2:
                tv_khuyenmaiCTHDXuat.setText("10%");
                tv_thanhtienCTHDXuat.setText(decimalFormat.format(tien-(tien*0.1)) + " VN??");
                break;
            case 3:
                tv_khuyenmaiCTHDXuat.setText("15%");
                tv_thanhtienCTHDXuat.setText(decimalFormat.format(tien-(tien*0.15)) + " VN??");
                break;
            case 4:
                tv_khuyenmaiCTHDXuat.setText("20%");
                tv_thanhtienCTHDXuat.setText(decimalFormat.format(tien-(tien*0.2)) + " VN??");
                break;
            case 5:
                tv_khuyenmaiCTHDXuat.setText("25%");
                tv_thanhtienCTHDXuat.setText(decimalFormat.format(tien-(tien*0.25)) + " VN??");
                break;
            case 6:
                tv_khuyenmaiCTHDXuat.setText("30%");
                tv_thanhtienCTHDXuat.setText(decimalFormat.format(tien-(tien*0.3)) + " VN??");
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    protected void onResume() {
        getData();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.menu_edit:
                Intent intent = new Intent(ActivityChiTietHoaDonXuat.this, ActivityEditHoaDonXuat.class);
                intent.putExtra("mahoaddonxuat", mahdxuat);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
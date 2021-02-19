package com.app.pengingatutang.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.app.pengingatutang.DetailActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.app.pengingatutang.R;
import com.app.pengingatutang.entities.Peminjaman;

public class PeminjamanListAdapter extends RecyclerView.Adapter<PeminjamanListAdapter.ViewHolder> {



    Context mContext;
    ArrayList<Peminjaman> listPeminjaman;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    public PeminjamanListAdapter(Context mContext, ArrayList<Peminjaman> listPeminjaman) {
        this.mContext = mContext;
        this.listPeminjaman = listPeminjaman;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_view_peminjaman_layout, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        final Peminjaman peminjaman = listPeminjaman.get(i);

        viewHolder.text_view_nama_peminjam.setText("Nama : "+peminjaman.getName());
        viewHolder.text_view__nomor.setText("No Telp : "+peminjaman.getTelphon());
        viewHolder.text_view__hutang.setText("Jumlah Pinjaman : "+formatRupiah.format((double)(peminjaman.getAmount())));
        viewHolder.text_view__keterangan.setText("Tujuan : "+peminjaman.getDescription());
        viewHolder.text_view__tanggal_pinjam.setText("Tanggal Peminjaman  : "+peminjaman.getDateOfLoan());
        viewHolder.text_view__batas_tanggal.setText("Tanggal Jatuh Tempo : "+peminjaman.getDateDue());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetailAmount = new Intent(mContext,DetailActivity.class);
                intentDetailAmount.putExtra("peminjaman",peminjaman);
                mContext.startActivity(intentDetailAmount);
            }
        });
    }
    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
    @Override
    public int getItemCount() {
        return listPeminjaman.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_view_nama_peminjam, text_view__nomor, text_view__keterangan, text_view__hutang, text_view__tanggal_pinjam, text_view__batas_tanggal, idPeminjaman;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_view_nama_peminjam = itemView.findViewById(R.id.text_view_nama_peminjam);
            text_view__nomor = itemView.findViewById(R.id.text_view_nomor);
            text_view__keterangan = itemView.findViewById(R.id.text_view_keterangan);
            text_view__hutang = itemView.findViewById(R.id.text_view_hutang);
            text_view__tanggal_pinjam = itemView.findViewById(R.id.text_view_tanggal_pinjam);
            text_view__batas_tanggal = itemView.findViewById(R.id.text_view_batas_tanggal);
        }

    }

}

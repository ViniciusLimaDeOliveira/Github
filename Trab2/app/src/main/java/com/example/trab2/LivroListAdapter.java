package com.example.trab2;

import android.content.Context;
import android.nfc.Tag;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LivroListAdapter extends ArrayAdapter<Livro> {
    private static final String TAG = "LivroListAdapter";
    private Context mContext;
    private int mResource;
    public LivroListAdapter(Context context, int resource,List<Livro> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource= resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int isbn = getItem(position).getIsbn();
        String titulo = getItem(position).getTitulo();
        String editora = getItem(position).getEditora();
        Livro l = new Livro(isbn,titulo,editora);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);
        TextView tvIsbn =(TextView) convertView.findViewById(R.id.isbn);
        TextView tvTitulo =(TextView) convertView.findViewById(R.id.titulo);
        TextView tvEditora =(TextView) convertView.findViewById(R.id.editora);
        tvIsbn.setText(Integer.toString(isbn));
        tvTitulo.setText(titulo);
        tvEditora.setText(editora);
        return  convertView;
    }
}

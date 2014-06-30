package com.tet.rcbmanutencao.adapter;

import java.util.List;

import com.tet.rcbmanutencao.R;
import com.tet.rcbmanutencao.model.bean.Manutencao;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListaManutencaoAdapter extends BaseAdapter{

	private final List<Manutencao> listaManutencoes;
	private final Activity activity;
	
	public ListaManutencaoAdapter(Activity activity, List<Manutencao> listaManutencoes){
		this.listaManutencoes = listaManutencoes;
		this.activity = activity;
	}
	
	@Override
	public int getCount() {
		return listaManutencoes.size();
	}

	@Override
	public Object getItem(int position) {
		return listaManutencoes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listaManutencoes.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = activity.getLayoutInflater().inflate(R.layout.activity_item, null);
		Manutencao manutencao = listaManutencoes.get(position);
		
		if (position % 2 == 0){
			view.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
		} else {
			view.setBackgroundColor(activity.getResources().getColor(R.color.linha_impar));
		}
		
		TextView nome = (TextView) view.findViewById(R.id.tvNome);
		nome.setText(manutencao.getNome());
		
		Bitmap bmp;
		if (manutencao.getFoto_plataforma() != null){
			bmp = BitmapFactory.decodeFile(manutencao.getFoto_plataforma());
		} else {
			bmp = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_launcher);
		}
		
		bmp = Bitmap.createScaledBitmap(bmp, 100, 100, true);
		ImageView foto_plataforma = (ImageView) view.findViewById(R.id.ivPlataforma);
		foto_plataforma.setImageBitmap(bmp);
			
		return view;
	}

}

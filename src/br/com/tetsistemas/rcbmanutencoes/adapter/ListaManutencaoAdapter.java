package br.com.tetsistemas.rcbmanutencoes.adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.tetsistemas.rcbmanutencoes.gen.R;
import br.com.tetsistemas.rcbmanutencoes.modelo.bean.Manutencao;

public class ListaManutencaoAdapter extends BaseAdapter {

	private final List<Manutencao> listaManutencoes;
	private final Activity activity;

	public ListaManutencaoAdapter(Activity activity, List<Manutencao> listaManutencoes) {
		this.listaManutencoes = listaManutencoes;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return listaManutencoes.size();
	}

	@Override
	public long getItemId(int posicao) {
		return listaManutencoes.get(posicao).getId();
	}

	@Override
	public Object getItem(int posicao) {
		return listaManutencoes.get(posicao);
	}

	@Override
	public View getView(int posicao, View convertView, ViewGroup parent) {
	
		// Infla o layout na view
		View view = activity.getLayoutInflater().inflate(R.layout.activity_item, null);

		Manutencao manutencao = listaManutencoes.get(posicao);

		// Definicao de cor de fundo de linhas pares ou impares
		if (posicao % 2 == 0) {
			view.setBackgroundColor(activity.getResources().getColor(
					R.color.linha_par));
		} else {
			view.setBackgroundColor(activity.getResources().getColor(
					R.color.linha_impar));
		}

		// Configuracao do nome
		TextView nome = (TextView) view.findViewById(R.id.itemNome);
		nome.setText(manutencao.getNome());

		// Configuracao da foto
		Bitmap bmp;
		if (manutencao.getFoto_plataforma() != null) {
			bmp = BitmapFactory.decodeFile(manutencao.getFoto_plataforma());
		} else {
			bmp = BitmapFactory.decodeResource(activity.getResources(),
					R.drawable.ic_no_image);
		}
		bmp = Bitmap.createScaledBitmap(bmp, 100, 100, true);
		ImageView foto_plataforma = (ImageView) view.findViewById(R.id.foto_plataforma);
		foto_plataforma.setImageBitmap(bmp);
		
		//Carregar o telefone
		TextView descricao = (TextView)view.findViewById(R.id.edPlataformaDescricao);
		if(descricao!=null){
			descricao.setText(manutencao.getDescricao());
		}
		
		//Carregar o site
		TextView numero = (TextView)view.findViewById(R.id.edPlataformaNumero);
		if(numero!=null){
			numero.setText(manutencao.getNumero());
		}

		return view;
	}

}

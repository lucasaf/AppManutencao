package com.tet.rcbmanutencao.helper;

import com.tet.rcbmanutencao.ManutencaoActivity;
import com.tet.rcbmanutencao.R;
import com.tet.rcbmanutencao.model.bean.Manutencao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;

public class ManutencaoHelper {

	private EditText nome;
	private EditText descricao;
	private EditText numero;
	private EditText af;
	private EditText horimetro;
	private ImageView foto_plataforma;
	private EditText descricao_problema;
	private ImageView foto_problema;
	private EditText causa_problema;
	private ImageView foto_causa;
	
	private Manutencao manutencao;
	
	public ManutencaoHelper(ManutencaoActivity activity) {
		
		nome = (EditText) activity.findViewById(R.id.edPlataformaNome);
		descricao = (EditText) activity.findViewById(R.id.edPlataformaDescricao);
		numero = (EditText) activity.findViewById(R.id.edPlataformaNumero);
		af = (EditText) activity.findViewById(R.id.edPlataformaAF);
		horimetro = (EditText) activity.findViewById(R.id.edPlataformaHorimetro);
		foto_plataforma = (ImageView) activity.findViewById(R.id.ivPlataforma);
		descricao_problema = (EditText) activity.findViewById(R.id.edPlataformaDescricaoProblema);
		foto_problema = (ImageView) activity.findViewById(R.id.ivProblema);
		causa_problema = (EditText) activity.findViewById(R.id.edPlataformaCausaProblema);
		foto_causa = (ImageView) activity.findViewById(R.id.ivCausa);
		
		manutencao = new Manutencao();
	}
	
	public Manutencao getManutencao() {
		
		manutencao.setNome(nome.getText().toString());
		manutencao.setDescricao(descricao.getText().toString());
		manutencao.setNumero(numero.getText().toString());
		manutencao.setAf(af.getText().toString());
		manutencao.setHorimetro(horimetro.getText().toString());
		manutencao.setDescricao_problema(descricao_problema.getText().toString());
		manutencao.setCausa_problema(causa_problema.getText().toString());
		
		return manutencao;
	}
	
	public void carregaFoto(String localFoto){
		
		Bitmap imagemFoto = BitmapFactory.decodeFile(localFoto);
		Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, 100, 100, true);
		manutencao.setFoto_plataforma(localFoto);
		manutencao.setFoto_problema(localFoto);
		manutencao.setFoto_causa(localFoto);
		
		foto_plataforma.setImageBitmap(imagemFotoReduzida);
		foto_problema.setImageBitmap(imagemFotoReduzida);
		foto_causa.setImageBitmap(imagemFotoReduzida);
		
	}
	
	public void setManutencao(Manutencao manutencao) {
		
		nome.setText(manutencao.getNome());
		descricao.setText(manutencao.getDescricao());
		numero.setText(manutencao.getNumero());
		af.setText(manutencao.getAf());
		horimetro.setText(manutencao.getHorimetro());
		descricao_problema.setText(manutencao.getDescricao_problema());
		causa_problema.setText(manutencao.getCausa_problema());
		this.manutencao = manutencao;
		
		if (manutencao.getFoto_plataforma() != null){
			carregaFoto(manutencao.getFoto_plataforma());
		}
		if (manutencao.getFoto_problema() != null){
			carregaFoto(manutencao.getFoto_problema());
		}
		if (manutencao.getFoto_causa() != null){
			carregaFoto(manutencao.getFoto_causa());
		}
	}
	
	public ImageView getFoto_plataforma(){
		return foto_plataforma;
	}
	
	public ImageView getFoto_problema(){
		return foto_problema;
	}
	
	public ImageView getFoto_causa(){
		return foto_causa;
	}
}

package com.tet.rcbmanutencao;

import java.io.File;

import com.tet.rcbmanutencao.helper.ManutencaoHelper;
import com.tet.rcbmanutencao.model.bean.Manutencao;
import com.tet.rcbmanutencao.model.dao.ManutencaoDAO;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ManutencaoActivity extends Activity{

	private Button btADD;
	private ManutencaoHelper helper;
	private Manutencao manutencaoParaSerAlterada = null;
	
	private String localArquivo;
	private static final int FAZER_FOTO = 123;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manutencao);
		
		helper = new ManutencaoHelper(this);
		btADD = (Button) findViewById(R.id.bPlataformaADD);
		
		if (savedInstanceState != null) {
			localArquivo = (String) savedInstanceState
					.getSerializable("localArquivo");
		}
		if (localArquivo != null) {
			helper.carregaFoto(localArquivo);
		}
		
		helper.getFoto_plataforma().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				localArquivo = Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + ".jpg";
				
				File arquivo = new File(localArquivo);
				Uri localFoto = Uri.fromFile(arquivo);
				
				Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);
				startActivityForResult(irParaCamera, FAZER_FOTO);
			}
		});
		manutencaoParaSerAlterada = (Manutencao) getIntent().getSerializableExtra("MANUTENCAO_SELECIONADA");
				
		if (manutencaoParaSerAlterada!=null){
			
			helper.setManutencao(manutencaoParaSerAlterada);
		}
		
		btADD.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				
				Manutencao manutencao = helper.getManutencao();
				
				ManutencaoDAO dao = new ManutencaoDAO(ManutencaoActivity.this);
				if(manutencao.getId()==null){
					dao.cadastrar(manutencao);
				}else{
					dao.alterar(manutencao);
				}
				
				dao.close();
				finish();
			}
		});
	}
	
	@Override	
	protected void onActivityResult(int requestCode, int resultCode, 
			Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		//Verificacao do resultado da nossa requisicao
		if (requestCode == FAZER_FOTO) {
			if (resultCode == Activity.RESULT_OK) {
				helper.carregaFoto(this.localArquivo);
			} else {
				localArquivo = null;
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable("localArquivo", localArquivo);
	}
}

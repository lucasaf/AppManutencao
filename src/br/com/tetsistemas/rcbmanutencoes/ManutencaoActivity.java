package br.com.tetsistemas.rcbmanutencoes;

import java.io.File;

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
import br.com.cursoandroid.cadastroaluno.R;
import br.com.tetsistemas.rcbmanutencoes.helper.FormularioHelper;
import br.com.tetsistemas.rcbmanutencoes.modelo.bean.Manutencao;
import br.com.tetsistemas.rcbmanutencoes.modelo.dao.ManutencaoDAO;

public class ManutencaoActivity extends Activity {
	
	private Button button;
	private FormularioHelper helper;

	private String localArquivo;
	private static final int FAZER_FOTO = 123;

	private Manutencao manutencaoParaSerAlterada = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manutencao);
		// Criacao do objeto Helper
		helper = new FormularioHelper(this);
		button = (Button) findViewById(R.id.sbSalvar);

		if (savedInstanceState != null) {
			localArquivo = (String) savedInstanceState
					.getSerializable("localArquivo");
		}
		if (localArquivo != null) {
			helper.carregarFoto(localArquivo);
		}

		helper.getFoto().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				localArquivo = Environment.getExternalStorageDirectory() 				
						+ "/" + System.currentTimeMillis() + ".jpg";
				
				File arquivo = new File(localArquivo);
				//URI que informa onde o arquivo resultado deve ser salvo
				Uri localFoto = Uri.fromFile(arquivo);

				Intent irParaCamera = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);

				irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);
				
				startActivityForResult(irParaCamera, FAZER_FOTO);

			}
		});

		// Busca a manutencao a ser alterado
		manutencaoParaSerAlterada = (Manutencao) getIntent().getSerializableExtra(
				"MANUTENCAO_SELECIONADA");

		if (manutencaoParaSerAlterada != null) {
			// Atualiza a tela com dados da manutencao
			helper.setManutencao(manutencaoParaSerAlterada);
		}

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// Utilizacao do Helper para recuperar dados da Manutencao
				Manutencao manutencao = helper.getManutencao();
				// Criacao do objeto DAO - inicio da conexao com o BD
				ManutencaoDAO dao = new ManutencaoDAO(ManutencaoActivity.this);

				// Verificacao para salvar ou cadastrar a manutencao
				if (manutencao.getId() == null) {
					dao.cadastrar(manutencao);
				} else {
					dao.alterar(manutencao);
				}

				// Encerramento da conexao com o Banco de Dados
				dao.close();
				// Encerrando a Activity
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
				helper.carregarFoto(this.localArquivo);
			} else {
				localArquivo = null;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.formulario, menu);
		return true;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable("localArquivo", localArquivo);
	}

}

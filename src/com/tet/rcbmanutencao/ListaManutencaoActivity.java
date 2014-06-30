package com.tet.rcbmanutencao;


import java.util.List;


import com.tet.rcbmanutencao.model.bean.Manutencao;
import com.tet.rcbmanutencao.model.dao.ManutencaoDAO;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.MenuInflater;



public class ListaManutencaoActivity extends Activity {

	//Constantes
	private final String TAG = "MANUTENCAO";
		
	//Atributos da activity 
	private ListView lvListagem;
	
	private List<Manutencao> listaManutencoes;
	
	private ArrayAdapter<Manutencao> adapter; //Converte listas e vetores em views.
	private int adapterLayout = android.R.layout.simple_list_item_1; //define o tipo de layout a ser listado.
	
	private Manutencao manutencaoSelecionada = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_manutencao);
		
		lvListagem = (ListView) findViewById(R.id.lvListagem);
		registerForContextMenu(lvListagem);
		
		lvListagem.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long id) {
				
				manutencaoSelecionada = (Manutencao) adapter.getItemAtPosition(position);
				Log.i(TAG, "Manutenção selecionada ListView.longClick()" + manutencaoSelecionada.getNome());
				return false;
			}
		});
		
		lvListagem.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent form = new Intent(ListaManutencaoActivity.this, ManutencaoActivity.class);
				manutencaoSelecionada = (Manutencao)lvListagem.getItemAtPosition(position);
				form.putExtra("MANUTENCAO_SELECIONADA", manutencaoSelecionada);
				startActivity(form);
			}
		});		
	}
	
	/**
	 * Metodo que carrega a lista de alunos com dados vindos do BD
	 */
	private void carregarLista() {
		// Criacao do objeto DAO - inicio da conexao com BD
		ManutencaoDAO dao = new ManutencaoDAO(this);
		// chamada ao metodo listar
		this.listaManutencoes = dao.listar();
		// Fim da conexao com BD
		dao.close();

		// O objeto ArraAdapter sabe converter listas de alunos em View
		this.adapter = new ArrayAdapter<Manutencao>(this, adapterLayout, listaManutencoes);
		this.lvListagem.setAdapter(adapter);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		this.carregarLista();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Definicao do objeto Inflater
		MenuInflater inflater = this.getMenuInflater();

		// Inflar um XML em um Menu vazio
		inflater.inflate(R.menu.menu_principal, menu);

		// Exibir o menu
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = null;
		// Verifica o item do menu selecionado
		switch (item.getItemId()) {
		// Verifica se foi selecionado o item NOVO
		case R.id.menu_nova_manut:
			intent = new Intent(ListaManutencaoActivity.this,
					ManutencaoActivity.class);
			startActivity(intent);
			return false;
			// Verifica se foi selecionado o item ENVIAR ALUNOS
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, view, menuInfo);

		getMenuInflater().inflate(R.menu.menu_contexto, menu);
	}	

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		//Intent intent;
		switch (item.getItemId()) {
		case R.id.menu_deletar:
			excluirManutencao();
			break;
		/*case R.id.menuLigar:
			intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" + alunoSelecionado.getTelefone()));
			startActivity(intent);
			break;
		case R.id.menuEnviarSMS:
			intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("sms:" + alunoSelecionado.getTelefone()));
			intent.putExtra("sms_body", "Mensagem de boas vindas :-)");
			startActivity(intent);
			break;*/
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	private void excluirManutencao() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Confirmar a exclusão de: "
				+ manutencaoSelecionada.getNome());

		builder.setPositiveButton("Sim", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int witch) {
				ManutencaoDAO dao = new ManutencaoDAO(ListaManutencaoActivity.this);
				dao.deletar(manutencaoSelecionada);
				dao.close();
				carregarLista();
				manutencaoSelecionada = null;
			}
		});

		builder.setNegativeButton("Não", null);
		AlertDialog dialog = builder.create();
		dialog.setTitle("Confirmar operação");
		dialog.show();
	}
}


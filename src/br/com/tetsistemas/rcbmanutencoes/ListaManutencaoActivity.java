package br.com.tetsistemas.rcbmanutencoes;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import br.com.tetsistemas.rcbmanutencoes.adapter.ListaManutencaoAdapter;
import br.com.tetsistemas.rcbmanutencoes.gen.R;
import br.com.tetsistemas.rcbmanutencoes.modelo.bean.Manutencao;
import br.com.tetsistemas.rcbmanutencoes.modelo.dao.ManutencaoDAO;
import br.com.tetsistemas.rcbmanutencoes.task.EnviaAlunosTask;

public class ListaManutencaoActivity extends Activity {

	// Definicao de constantes
	private final String TAG = "CADASTRO_MANUTENCAO";
	// private final String ALUNOS_KEY = "LISTA";

	// Atributos de tela
	// private EditText edNome;
	// private Button botao;
	private ListView lvListagem;

	// Colecao de Alunos a ser exibida na tela
	private List<Manutencao> listaManutencoes;

	// Adapter customizado
	private ListaManutencaoAdapter adapter;

	// Aluno selecionado no click longo da ListView
	private Manutencao manutencaoSelecionada = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Ligacao da Tela ao seu Controlador
		setContentView(R.layout.activity_listamanutencoes);
		// Ligacao dos componentes de TELA aos atributos da Activity
		lvListagem = (ListView) findViewById(R.id.lvListagem);
		// informa que a ListView tem Menu de Contexto
		registerForContextMenu(lvListagem);
		// Metodo que "escuta" o evento de Click LONGO
		lvListagem.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				// Marca o aluno selecionado na ListView
				manutencaoSelecionada = (Manutencao) adapter.getItemAtPosition(posicao);
				Log.i(TAG, "Manutencoção selecionada ListView.longClick()"
						+ manutencaoSelecionada.getNome());
				return false;
			}
		}); // Outras linhas do metodo, aqui...

		// Metodo que "escuta" o evento de Click SIMPLES
		lvListagem.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int posicao, long id) {

				Intent form = new Intent(ListaManutencaoActivity.this,
						ManutencaoActivity.class);

				manutencaoSelecionada = (Manutencao) lvListagem
						.getItemAtPosition(posicao);

				form.putExtra("MANUTENCAO_SELECIONADA", manutencaoSelecionada);

				startActivity(form);
			}
		});

		Log.i(TAG, "Execucao do metodo onCreate()");
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

		// O objeto ListaAlunoAdapter sabe converter listas de alunos em View
		this.adapter = new ListaManutencaoAdapter(this, listaManutencoes);

		// Associacao do Adapter aa ListView
		this.lvListagem.setAdapter(adapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Carga da coleÃ§Ã£o de Alunos
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
		case R.id.menu_novo:
			intent = new Intent(ListaManutencaoActivity.this,
					ManutencaoActivity.class);
			startActivity(intent);
			return false;
			// Verifica se foi selecionado o item ENVIAR ALUNOS
		case R.id.menu_enviar_alunos:

			// Execucao de tarefa assincrona
			new EnviaAlunosTask(this).execute();

			return false;

		case R.id.menu_receber_provas:
			intent = new Intent(this, ProvasActivity.class);
			startActivity(intent);
			return false;
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

		switch (item.getItemId()) {
		case R.id.menuDeletar:
			excluirManutencao();
			break;
		/*case R.id.menuLigar:
			intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" + manutencaoSelecionada.getTelefone()));
			startActivity(intent);
			break;
		case R.id.menuEnviarSMS:
			intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("sms:" + manutencaoSelecionada.getTelefone()));
			intent.putExtra("sms_body", "Mensagem de boas vindas :-)");
			startActivity(intent);
			break;
		// Outras opcoes aqui...
		case R.id.menuAcharNoMapa:
			intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("geo:0,0?z=14&q="
					+ manutencaoSelecionada.getEndereco()));
			intent.putExtra("sms_body", "Mensagem de boas vindas :-)");
			startActivity(intent);
			break;
		case R.id.menuEnviarEmail:
			intent = new Intent(Intent.ACTION_SEND);
			intent.setType("message/rfc822");
			intent.putExtra(Intent.EXTRA_EMAIL,
					new String[] { manutencaoSelecionada.getEmail() });
			intent.putExtra(Intent.EXTRA_SUBJECT, "Falando sobre o curso");
			intent.putExtra(Intent.EXTRA_TEXT, "O curso foi muito legal");
			startActivity(intent);
			break;*/
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	private void excluirManutencao() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Confirma a exclusão de: "
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
		dialog.setTitle("Confirmação de operação");
		dialog.show();
	}
}

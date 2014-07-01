package br.com.tetsistemas.rcbmanutencoes.task;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import br.com.tetsistemas.rcbmanutencoes.converter.ManutencaoConverter;
import br.com.tetsistemas.rcbmanutencoes.modelo.bean.Manutencao;
import br.com.tetsistemas.rcbmanutencoes.modelo.dao.ManutencaoDAO;
import br.com.tetsistemas.rcbmanutencoes.suport.WebClient;

public class EnviaAlunosTask extends AsyncTask<Object, Object, String> {

	//Servidor para teste JSON: http://www.jsontest.com/
	//private final String url = "http://ip.jsontest.com/";
	private final String url = "http://192.168.43.215:8080/AlunoWeb/receber-json";

	// Contexto (tela) para exibicao de mensagens
	private Context context;

	// Barra de progresso
	private ProgressDialog progress;
	
	//Construtor que recebe o contexto da App
	public EnviaAlunosTask(Context context) {
		this.context = context;
	}

	protected void onPreExecute() {
		//Executando a barra de progresso
		progress = ProgressDialog.show(context, "Aguarde...",
				"Enviando dados para o servidor web", true, true);
	}
	protected String doInBackground(Object... params) {
		//Lista de alunos
		ManutencaoDAO dao = new ManutencaoDAO(context);
		List<Manutencao> lista = dao.listar();
		dao.close();
		//Conversao da lista para JSON
		String json = new ManutencaoConverter().toJSON(lista);
		//Envio de dados para o servidor remoto
		String jsonResposta = new WebClient(url).post(json);
		//Devolvendo a resposta do servidor
		return jsonResposta;
	}
	protected void onPostExecute(String result) {
		//Encerra a exibicao da barra de progresso
		progress.dismiss();
		//Exibindo a resposta do servidor
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}

}

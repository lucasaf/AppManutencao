package br.com.tetsistemas.rcbmanutencoes.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.com.tetsistemas.rcbmanutencoes.modelo.bean.Manutencao;

/**
 * Classe para persistência de Dados da Manutencao
 * 
 * @author marciopalheta
 */
public class ManutencaoDAO extends SQLiteOpenHelper {

	// Constantes para auxilio no controle de versoes
	private static final int VERSAO = 1;
	private static final String TABELA = "Manutencao";
	private static final String DATABASE = "RCBManutencoes";

	// Constante para log no Logcat
	private static final String TAG = "CADASTRO_MANUTENCAO";

	public ManutencaoDAO(Context context) {

		// Chamada o construtor que sabe acessar o BD
		super(context, DATABASE, null, VERSAO);
	}

	/**
	 * Metodo responsavel pela criacao da estrutura do BD
	 * */
	public void onCreate(SQLiteDatabase database) {

		// Definicao do comando DDL a ser executado
		String ddl = "CREATE TABLE " + TABELA + "( " 
				+ "id INTEGER PRIMARY KEY, " 
				+ "nome TEXT, descricao TEXT, numero TEXT, af TEXT, horimetro TEXT, foto_plataforma TEXT, descricao_problema TEXT, "
				+ "foto_problema TEXT, causa_problema TEXT, foto_causa TEXT)";
		database.execSQL(ddl);
		Log.i(TAG, "Tabela criada: " + TABELA);
	}

	/**
	 * Metodo responsavel pela atualizacao das estrutura das tableas
	 * */
	public void onUpgrade(SQLiteDatabase database, int versaoAntiga,
			int versaoNova) {

		// Definicao do comando para destruir a tabela Manutencao
		String sql = "DROP TABLE IF EXISTS " + TABELA;

		// Execucao do comando de destruicao
		database.execSQL(sql);
		Log.i(TAG, "Tabela excluida: " + TABELA);

		// Chamada ao metodo de contrucao da base de dados
		onCreate(database);
	}

	@Override
	public void onDowngrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABELA;
		database.execSQL(sql);
		onCreate(database);
	}

	public void cadastrar(Manutencao manutencao) {

		// Objeto para armazenar os valores dos campos
		ContentValues values = new ContentValues();

		// Definicao de valores dos campos da tabela
		values.put("nome", manutencao.getNome());
		values.put("descricao", manutencao.getDescricao());
		values.put("numero", manutencao.getNumero());
		values.put("af", manutencao.getAf());
		values.put("horimetro", manutencao.getHorimetro());
		values.put("foto_plataforma", manutencao.getFoto_plataforma());
		values.put("descricao_problema", manutencao.getDescricao_problema());
		values.put("foto_problema", manutencao.getFoto_problema());
		values.put("causa_problema", manutencao.getCausa_problema());
		values.put("foto_causa", manutencao.getFoto_causa());

		// Inserir dados do Manutencao no BD
		getWritableDatabase().insert(TABELA, null, values);
		Log.i(TAG, "Manutencao cadastrada." + manutencao.getNome());
	}

	public void alterar(Manutencao manutencao) {
		
		ContentValues values = new ContentValues();
		values.put("nome", manutencao.getNome());
		values.put("descricao", manutencao.getDescricao());
		values.put("numero", manutencao.getNumero());
		values.put("af", manutencao.getAf());
		values.put("horimetro", manutencao.getHorimetro());
		values.put("foto_plataforma", manutencao.getFoto_plataforma());
		values.put("descricao_problema", manutencao.getDescricao_problema());
		values.put("foto_problema", manutencao.getFoto_problema());
		values.put("causa_problema", manutencao.getCausa_problema());
		values.put("foto_causa", manutencao.getFoto_causa());

		// Colecao de valores de parametros do SQL
		String[] args = { manutencao.getId().toString() };

		// Altera dados do Manutencao no BD
		getWritableDatabase().update(TABELA, values, "id=?", args);
		Log.i(TAG, "Manutencao alterada." + manutencao.getNome());
	}

	public List<Manutencao> listar() {
		// Definicao da colecao de Manutencoes
		List<Manutencao> lista = new ArrayList<Manutencao>();

		// Definicao da instrucao SQL
		String sql = "Select * from Manutencao order by nome";

		// Objeto que recebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);

		try {
			while (cursor.moveToNext()) {
				// Criacao de nova referencia para Manutencao
				Manutencao manutencao = new Manutencao();
				// Carregar os atributos de Manutencao com dados do BD
				manutencao.setId(cursor.getLong(0));
				manutencao.setNome(cursor.getString(1));
				manutencao.setDescricao(cursor.getString(2));
				manutencao.setNumero(cursor.getString(3));
				manutencao.setAf(cursor.getString(4));
				manutencao.setHorimetro(cursor.getString(5));
				manutencao.setFoto_plataforma(cursor.getString(6));
				manutencao.setDescricao_problema(cursor.getString(7));
				manutencao.setFoto_problema(cursor.getString(8));
				manutencao.setCausa_problema(cursor.getString(9));
				manutencao.setFoto_causa(cursor.getString(10));
				// Adicionar nova Manutencao a lista
				lista.add(manutencao);
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		} finally {
			cursor.close();
		}
		return lista;
	}

	/**
	 * Metodo responsavel pela exclusao de uma Manutencao do BD
	 * 
	 * @param Manutencao
	 *            a ser excluido
	 */
	public void deletar(Manutencao manutencao) {
		// Definicao de array de parametros
		String[] args = { manutencao.getId().toString() };

		// Exclusao da Manutencao
		getWritableDatabase().delete(TABELA, "id=?", args);

		Log.i(TAG, "Manutencao deletada: " + manutencao.getNome());
	}

	/**
	 * Metodo que verifica se um numero de telefone pertence a uma Manutencao
	 * @param telefone
	 * @return true, se o telefone pertence a uma Manutencao
	 
	public boolean isAluno(String telefone) {
		String sql = "select * from " + TABELA + " where telefone = ?";
		String[] valores = { telefone };
		Cursor cursor = null;
		try {
			//Abertura da conexao com BD e execucao da consulta
			cursor = getReadableDatabase().rawQuery(sql, valores);
			//Retorna true, se for devolvida alguma linha
			return cursor.getCount() > 0;
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			return false;
		} finally {
			cursor.close();
		}
	}*/

}

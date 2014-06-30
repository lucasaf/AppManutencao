package com.tet.rcbmanutencao.model.dao;

import java.util.ArrayList;
import java.util.List;

import com.tet.rcbmanutencao.model.bean.Manutencao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ManutencaoDAO extends SQLiteOpenHelper{

	private static final int VERSAO = 1;
	private static final String TABELA = "Manutencao";
	private static final String DATABASE = "DBRCBManut";
	
	private static final String TAG = "CADASTRO_MANUTENCAO";
	
	public ManutencaoDAO(Context context) {
		super(context, DATABASE, null, VERSAO);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String ddl = "CREATE TABLE " + TABELA + "( " 
				+ "id INTEGER PRIMARY KEY, " 
				+ "nome TEXT, descricao TEXT, numero TEXT, af TEXT, horimetro TEXT, foto_plataforma TEXT, descricao_problema TEXT, "
				+ "foto_problema TEXT, causa_problema TEXT, foto_causa TEXT)";
		db.execSQL(ddl);
		Log.i(TAG, "Tabela criada: " + TABELA);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
		String sql = "DROP TABLE IF EXISTS " + TABELA;
		db.execSQL(sql);
		Log.i(TAG, "Tabela excluida: " + TABELA);
		onCreate(db);
	}
	
	@Override
	public void onDowngrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABELA;
		database.execSQL(sql);
		onCreate(database);
	}
	
	public void cadastrar(Manutencao manutencao){
		
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

		getWritableDatabase().insert(TABELA, null, values);
		Log.i(TAG, "Manutencao cadastrada." + manutencao.getNome());
	}
	
	public void alterar(Manutencao manutencao){
	
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

		String[] args = { manutencao.getId().toString() };
		getWritableDatabase().update(TABELA, values, "id=?", args);
		Log.i(TAG, "Manutencao alterada." + manutencao.getNome());
	}
	
	public List<Manutencao> listar(){
		
		List<Manutencao> lista = new ArrayList<Manutencao>();
		String sql = "Select * from Manutencao order by nome";
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		
		try{
			while (cursor.moveToNext()){
				Manutencao manutencao = new Manutencao();
				manutencao.setId(cursor.getLong(0));
				manutencao.setDescricao(cursor.getString(1));
				manutencao.setNumero(cursor.getString(2));
				manutencao.setAf(cursor.getString(3));
				manutencao.setHorimetro(cursor.getString(4));
				manutencao.setFoto_plataforma(cursor.getString(5));
				manutencao.setDescricao_problema(cursor.getString(6));
				manutencao.setFoto_problema(cursor.getString(7));
				manutencao.setCausa_problema(cursor.getString(8));
				manutencao.setFoto_causa(cursor.getString(9));
				lista.add(manutencao);
			}
		}catch (SQLException e){
			Log.e(TAG, e.getMessage());
		}finally {
			cursor.close();
		}
		return lista;
	}
	
	public void deletar(Manutencao manutencao){
		
		String[] args = {manutencao.getId().toString()};
		getWritableDatabase().delete(TABELA, "id=?", args);
		Log.i(TAG, "Aluno deletado: "+manutencao.getNome());
	}

}

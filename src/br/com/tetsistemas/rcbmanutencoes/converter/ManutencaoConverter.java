package br.com.tetsistemas.rcbmanutencoes.converter;

import java.util.List;

import org.json.JSONException;
import org.json.JSONStringer;

import android.util.Log;
import br.com.tetsistemas.rcbmanutencoes.modelo.bean.Manutencao;

public class ManutencaoConverter {
	public String toJSON(List<Manutencao> listaManutencoes) {
		try {
			JSONStringer jsonStringer = new JSONStringer();
			// Definicao da colecao de alunos
			jsonStringer.object().key("list").array().
				object().key("manutencao").array();
			for (Manutencao manutencao : listaManutencoes) {
				//Carregar dados do aluno no JSON
				jsonStringer.object()
					.key("id").value(manutencao.getId())
					.key("nome").value(manutencao.getNome())
					.key("descricao").value(manutencao.getDescricao())
					.key("numero").value(manutencao.getNumero())
					.key("af").value(manutencao.getAf())
					.key("horimetro").value(manutencao.getHorimetro())
					.key("descricao_problema").value(manutencao.getDescricao_problema())
					.key("causa_problema").value(manutencao.getCausa_problema())
				.endObject();
			}
			jsonStringer.endArray().endObject()
				.endArray().endObject();
			return jsonStringer.toString();
		} catch (JSONException e) {
			Log.i("CADASTRO_MANUTENCAO", e.getMessage());
			return null;
		}
	}
}

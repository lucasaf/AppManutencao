package br.com.tetsistemas.rcbmanutencoes.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import br.com.tetsistemas.rcbmanutencoes.ManutencaoActivity;
import br.com.tetsistemas.rcbmanutencoes.gen.R;
import br.com.tetsistemas.rcbmanutencoes.modelo.bean.Manutencao;

/**
 * @author Lucas Alves Ferreira
 * 
 */
public class FormularioHelper {

	private EditText nome;
	private EditText descricao;
	private EditText numero;
	private EditText af;
	private EditText horimetro;
	private ImageView foto_plataforma;
	private EditText descricao_problema;
	//private ImageView foto_problema;
	private EditText causa_problema;
	//private ImageView foto_causa;
	
	private Manutencao manutencao;

	// Continua...
	/**
	 * Metodo construtor que associa seus atributos a campos da tela
	 * 
	 * @param activity
	 *            referencia para a tela a ser controlada
	 */

	public FormularioHelper(ManutencaoActivity activity) {

		// Associacao de campos da tela a atributos de controle
		nome = (EditText) activity.findViewById(R.id.edPlataformaNome);
		descricao = (EditText) activity.findViewById(R.id.edPlataformaDescricao);
		numero = (EditText) activity.findViewById(R.id.edPlataformaNumero);
		af = (EditText) activity.findViewById(R.id.edPlataformaAF);
		horimetro = (EditText) activity.findViewById(R.id.edPlataformaHorimetro);
		foto_plataforma = (ImageView) activity.findViewById(R.id.foto_plataforma);
		descricao_problema = (EditText) activity.findViewById(R.id.edPlataformaDescricaoProblema);
		//foto_problema = (ImageView) activity.findViewById(R.id.foto_problema);
		causa_problema = (EditText) activity.findViewById(R.id.edPlataformaCausaProblema);
		//foto_causa = (ImageView) activity.findViewById(R.id.foto_causa);

		// Criacao do objeto Aluno
		manutencao = new Manutencao();
	}

	/**
	 * Metodo que carrega uma foto, a partir de um arquivo armazenado no device
	 * 
	 * @param localFoto
	 */

	public void carregarFoto(String localFoto) {
		
		// Carregar arquivo de imagem
		Bitmap imagemFoto = BitmapFactory.decodeFile(localFoto);
		
		// Gerar imagem reduzida
		Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, 100, 100, true);
		
		// Guarda o caminho da foto do aluno
		manutencao.setFoto_plataforma(localFoto);
		
		// Atualiza a imagem exibida na tela de formulário
		foto_plataforma.setImageBitmap(imagemFotoReduzida);
	}

	/**
	 * Metodo que retorna uma referencia para Aluno
	 * 
	 * @return referencia para aluno com dados da tela
	 */
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

	/**
	 * Metodo que carrega os dados do aluno na tela
	 * 
	 * @param aluno
	 */
public void setManutencao(Manutencao manutencao) {
		
		nome.setText(manutencao.getNome());
		descricao.setText(manutencao.getDescricao());
		numero.setText(manutencao.getNumero());
		af.setText(manutencao.getAf());
		horimetro.setText(manutencao.getHorimetro());
		descricao_problema.setText(manutencao.getDescricao_problema());
		causa_problema.setText(manutencao.getCausa_problema());
		this.manutencao = manutencao;
		// Carregar foto da manutencao
		if (manutencao.getFoto_plataforma() != null) {
			carregarFoto(manutencao.getFoto_plataforma());
		}
	}

	public ImageView getFoto() {
		return foto_plataforma;
	}
}
package br.com.tetsistemas.rcbmanutencoes;

import br.com.tetsistemas.rcbmanutencoes.gen.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{

	private EditText usuario;
	private EditText senha;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		usuario = (EditText) findViewById(R.id.usuario);
		senha = (EditText) findViewById(R.id.senha);
	}
	
public void entrarOnClick(View v) { 
		
		String usuarioInformado = usuario.getText().toString();
		String senhaInformada = senha.getText().toString();
			
			if("rcb".equals(usuarioInformado) &&
					"123".equals(senhaInformada)) {
					startActivity(new Intent(this,ListaManutencaoActivity.class));
			} else{
				String mensagemErro = getString(R.string.erro_autenticacao);
				Toast toast = Toast.makeText(this, mensagemErro,
				Toast.LENGTH_SHORT);
				toast.show();
			}
	}
}

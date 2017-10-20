package br.univille.dsi2017android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import br.univille.dsi2017android.model.Cliente;

public class FrmCliente extends AppCompatActivity {

    private Cliente model;
    private  EditText txtnome;
    private CheckBox cbSexoMasc, cbSexoFem;

    /*
    * para testar
    * https://developer.android.com/topic/libraries/data-binding/index.html?hl=pt-br
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_cliente);
            model = (Cliente)  getIntent().getExtras().getSerializable("model");

            txtnome = (EditText) findViewById(R.id.txtnome);
            cbSexoMasc = (CheckBox) findViewById(R.id.cbSexoMasc);
            cbSexoFem = (CheckBox) findViewById(R.id.cbSexoFem);

            if (model != null) {
                txtnome.setText(model.getNome());
                cbSexoMasc.setChecked(model.getSexo() == 1);
                cbSexoFem.setChecked(model.getSexo() == 0);
            }

    }

    public void onClickConfirmar(View view) {
        model.setNome(txtnome.getText().toString());
        model.setSexo((cbSexoMasc.isChecked())?1:0);
        Intent intReturn = new Intent();
        intReturn.putExtra("model",model);
        setResult(RESULT_OK,intReturn);
        finish();
    }


}

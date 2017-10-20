package br.univille.dsi2017android;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import br.univille.dsi2017android.model.Cliente;
import br.univille.dsi2017android.server.ServerConnection;
import br.univille.dsi2017android.server.ServerInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ServerInterface servidor;
    private ListView listClientes;
    private ArrayAdapter<Cliente> listClienteAdapter;
    private SwipeRefreshLayout mySwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listClienteAdapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1,new ArrayList<Cliente>());
        listClientes = (ListView) findViewById(R.id.listClientes);
        listClientes.setAdapter(listClienteAdapter);

        mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);


        servidor = ServerConnection.getInstance().getServidor();

        updateList();

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mySwipeRefreshLayout.setRefreshing(true);
                        updateList();
                        mySwipeRefreshLayout.setRefreshing(false);

                    }
                }
        );

    }
    public void updateList(){
        Call<List<Cliente>> retorno = servidor.getAllClientes();

        Log.i("DSI2017","Chamando servidor");

        retorno.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                Log.i("DSI2017",response.message());
                List<Cliente> listData = response.body();
                if(listData != null) {
                    listClienteAdapter.clear();
                    listClienteAdapter.addAll(listData);
                }

            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Não deu...",Toast.LENGTH_LONG);
            }
        });
    }


    public void onClickInserir(View view) {
        Intent i = new Intent(this,FrmCliente.class);
        i.putExtra("model",new Cliente());
        startActivityForResult(i,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            Cliente model = (Cliente)data.getExtras().getSerializable("model");
            Call<String> retorno = servidor.insertClientes(model);
            retorno.enqueue(new Callback<String>() {

                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(MainActivity.this,"Inserido com sucesso",Toast.LENGTH_SHORT);
                    updateList();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(MainActivity.this,"Bugou...",Toast.LENGTH_SHORT);
                }
            });
        }
    }
}

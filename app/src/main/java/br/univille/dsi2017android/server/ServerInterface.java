package br.univille.dsi2017android.server;

import java.util.List;

import br.univille.dsi2017android.model.Cliente;
import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by walte on 18/10/2017.
 */

public interface ServerInterface {

    @GET("cliente/")
    public Call<List<Cliente>> getAllClientes();


    @POST("cliente/")
    public Call<String> insertClientes(@Body Cliente c);


}

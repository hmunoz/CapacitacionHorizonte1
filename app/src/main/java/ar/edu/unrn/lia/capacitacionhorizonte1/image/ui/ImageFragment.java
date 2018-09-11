package ar.edu.unrn.lia.capacitacionhorizonte1.image.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ar.edu.unrn.lia.capacitacionhorizonte1.R;
import ar.edu.unrn.lia.capacitacionhorizonte1.api.ImageApiEndpointInterface;
import ar.edu.unrn.lia.capacitacionhorizonte1.api.ImageClient;
import ar.edu.unrn.lia.capacitacionhorizonte1.api.VolleySingleton;
import ar.edu.unrn.lia.capacitacionhorizonte1.dao.AppDatabase;
import ar.edu.unrn.lia.capacitacionhorizonte1.dao.ImageDao;
import ar.edu.unrn.lia.capacitacionhorizonte1.entities.ImageEntity;
import ar.edu.unrn.lia.capacitacionhorizonte1.image.adapter.ImagesAdapter;
import ar.edu.unrn.lia.capacitacionhorizonte1.image.entity.Image;
import ar.edu.unrn.lia.capacitacionhorizonte1.lib.GlideImageLoader;
import ar.edu.unrn.lia.capacitacionhorizonte1.lib.ImageLoader;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment implements OnItemClickListener, Callback<JsonObject> {


    ChangeListener listener;


    public interface ChangeListener {
        public void onChange();
    }

    public void setChangeListener(ChangeListener listener) {
        this.listener = listener;
    }

    static final String TAG = "ImageFragment";

    static final String URL_GET = "https://sizzling-heat-8971.firebaseio.com/images.json";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    ImagesAdapter adapter;
    ImageLoader imageLoader;

    ImageApiEndpointInterface imageApiEndpointInterface;
    ImageDao imageDao;

    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image, container, false);
        ButterKnife.bind(this, view);

        //ROOM DAO
        AppDatabase database = AppDatabase.getDatabase(getContext());
        imageDao = database.imageDao();

        imageApiEndpointInterface = new ImageClient().getImageService();

        imageLoader = new GlideImageLoader(this);


        adapter = new ImagesAdapter(this, imageLoader);
        recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        // LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));

        //Parte 1 - Lista Estatica
        //adapter.setItems(initImageStatic());

        //Parte 2 - Lista desde Servicio Rest  AsynTask Http
        //new HttpAsyncTask().execute(URL_GET);

        //Parte 3 - Utilizando Volley
        //volleyInitListImage();


        //Parte 4 - Retrofit 2.0
        retrofiInitListImage();


        // Iniciar la tarea asíncrona al revelar el indicador
        swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        retrofiInitListImage();
                    }
                }
        );

        return view;
    }


    @Override
    public void onItemClick(Image image) {
        String textInfo = String.format(getString(R.string.info_navegate_url), image.getSourceURL());
        Snackbar.make(container, textInfo, Snackbar.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(image.getSourceURL()));
        startActivity(intent);

        //Select where- Consulta para ubicar al elemento seleccionado en la DB
        List<ImageEntity> lista = imageDao.getImageEntityByText(image.getText());
        //Si el elemento no esta en la base lo persisto
        if (lista.size()==0){
            //Persistir
            ImageEntity imageEntity = new ImageEntity(image.getText(),
                    image.getImageURL(),
                    image.getSourceURL());


            imageDao.insertAll(imageEntity);

            //Notifica al Frgment Dos
            listener.onChange();
        }

    }


    @NonNull
    private List<Image> initImageStatic() {

        List<Image> images = new ArrayList<Image>(0);
        images.add(new Image("https://firebasestorage.googleapis.com/v0/b/sizzling-heat-8971.appspot.com/o/2%2Fdorso%2F2f75293a-459e-4730-bdee-80a1eec41e00.jpg?alt=media&token=ac25426e-eab8-475d-b89d-090547d0052b",
                "Test 1",
                "http://www.noticias3d.com/"));
        images.add(new Image("https://firebasestorage.googleapis.com/v0/b/sizzling-heat-8971.appspot.com/o/2%2Fdorso%2F2f75293a-459e-4730-bdee-80a1eec41e00.jpg?alt=media&token=ac25426e-eab8-475d-b89d-090547d0052b",
                "Test 2",
                "http://www.noticias3d.com/"));
        images.add(new Image("https://firebasestorage.googleapis.com/v0/b/sizzling-heat-8971.appspot.com/o/2%2Fdorso%2F2f75293a-459e-4730-bdee-80a1eec41e00.jpg?alt=media&token=ac25426e-eab8-475d-b89d-090547d0052b",
                "Test 3",
                "http://www.noticias3d.com/"));
        images.add(new Image("https://firebasestorage.googleapis.com/v0/b/sizzling-heat-8971.appspot.com/o/2%2Fdorso%2F2f75293a-459e-4730-bdee-80a1eec41e00.jpg?alt=media&token=ac25426e-eab8-475d-b89d-090547d0052b",
                "Test 4",
                "http://www.noticias3d.com/"));
        images.add(new Image("https://firebasestorage.googleapis.com/v0/b/sizzling-heat-8971.appspot.com/o/2%2Fdorso%2F2f75293a-459e-4730-bdee-80a1eec41e00.jpg?alt=media&token=ac25426e-eab8-475d-b89d-090547d0052b",
                "Test 5",
                "http://www.noticias3d.com/"));
        images.add(new Image("https://firebasestorage.googleapis.com/v0/b/sizzling-heat-8971.appspot.com/o/2%2Fdorso%2F2f75293a-459e-4730-bdee-80a1eec41e00.jpg?alt=media&token=ac25426e-eab8-475d-b89d-090547d0052b",
                "Test 6",
                "http://www.noticias3d.com/"));
        return images;
    }


    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }


    //Metodo Servicio Rest
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            showProgressBar();
            return GET(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            List<Image> images = new ArrayList<Image>(0);
            try {
                JSONObject json = new JSONObject(result);

                Iterator<String> iter = json.keys();
                while (iter.hasNext()) {
                    String key = iter.next();
                    try {
                        Image image = new Image();
                        JSONObject jsonObject = (JSONObject) json.get(key);
                        image.setText(jsonObject.get("text").toString());
                        image.setImageURL(jsonObject.get("imageURL").toString());
                        image.setSourceURL(jsonObject.get("sourceURL").toString());

                        images.add(image);


                    } catch (JSONException e) {
                        Log.d(TAG, e.getLocalizedMessage());
                    }
                }


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            adapter.setItems(images);
            hideProgressBar();
        }
    }

    private static String GET(String url) {
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }


    public void volleyInitListImage() {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, URL_GET, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject json) {
                        //mTxtDisplay.setText("Response: " + response.toString());
                        List<Image> images = new ArrayList<Image>(0);
                        Iterator<String> iter = json.keys();
                        while (iter.hasNext()) {
                            String key = iter.next();
                            try {
                                Image image = new Image();
                                JSONObject jsonObject = (JSONObject) json.get(key);
                                image.setText(jsonObject.get("text").toString());
                                image.setImageURL(jsonObject.get("imageURL").toString());
                                image.setSourceURL(jsonObject.get("sourceURL").toString());

                                images.add(image);


                            } catch (JSONException e) {
                                Log.d(TAG, e.getLocalizedMessage());
                            }
                        }
                        adapter.setItems(images);
                        hideProgressBar();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        showProgressBar();
        // Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(getContext()).addToRequestQueue(jsObjRequest);
    }


    private void retrofiInitListImage() {
        showProgressBar();
        Call<JsonObject> call = imageApiEndpointInterface.getImages();
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
        List<Image> images = new ArrayList<Image>(0);
        if (response.isSuccessful()) {
            JSONObject json = null;
            try {
                json = new JSONObject(response.body().toString());


                Iterator<String> iter = json.keys();
                while (iter.hasNext()) {
                    String key = iter.next();

                    Image image = new Image();
                    JSONObject jsonObject = (JSONObject) json.get(key);
                    image.setText(jsonObject.get("text").toString());
                    image.setImageURL(jsonObject.get("imageURL").toString());
                    image.setSourceURL(jsonObject.get("sourceURL").toString());

                    images.add(image);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            adapter.setItems(images);
            hideProgressBar();
            // Parar la animación del indicador
            swipeRefresh.setRefreshing(false);

        } else {
            Log.d(TAG, response.message());
        }
    }

    @Override
    public void onFailure(Call<JsonObject> call, Throwable t) {
        Log.d(TAG, t.getLocalizedMessage());
    }

}

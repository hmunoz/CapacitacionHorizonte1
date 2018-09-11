package ar.edu.unrn.lia.capacitacionhorizonte1.store;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.lia.capacitacionhorizonte1.R;
import ar.edu.unrn.lia.capacitacionhorizonte1.dao.AppDatabase;
import ar.edu.unrn.lia.capacitacionhorizonte1.dao.ImageDao;
import ar.edu.unrn.lia.capacitacionhorizonte1.entities.ImageEntity;
import ar.edu.unrn.lia.capacitacionhorizonte1.image.adapter.ImagesAdapter;
import ar.edu.unrn.lia.capacitacionhorizonte1.image.entity.Image;
import ar.edu.unrn.lia.capacitacionhorizonte1.image.ui.OnItemClickListener;
import ar.edu.unrn.lia.capacitacionhorizonte1.lib.GlideImageLoader;
import ar.edu.unrn.lia.capacitacionhorizonte1.lib.ImageLoader;
import butterknife.BindView;
import butterknife.ButterKnife;


public class StoreFragment extends Fragment implements OnItemClickListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.container)
    FrameLayout container;


    ImagesAdapter adapter;
    ImageLoader imageLoader;
    ImageDao imageDao;

    public StoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        ButterKnife.bind(this, view);


        //ROOM DAO
        AppDatabase database = AppDatabase.getDatabase(getContext());
        imageDao = database.imageDao();

        imageLoader = new GlideImageLoader(this);


        adapter = new ImagesAdapter(this,imageLoader);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));



        //Parte 5 - Load from Sqlite
        dbInitListImage();

        return view;
    }

    private void dbInitListImage() {
        List<ImageEntity> lista = imageDao.getAll();
        List<Image> resultado = new ArrayList<Image>(0);
        for (ImageEntity item:lista) {
            resultado.add(new Image(item.getImageURL(),item.getText(),item.getSourceURL()));
        }
        adapter.setItems(resultado);
    }

    @Override
    public void onItemClick(Image image) {

    }

    public void updateList() {
        dbInitListImage();
    }

}

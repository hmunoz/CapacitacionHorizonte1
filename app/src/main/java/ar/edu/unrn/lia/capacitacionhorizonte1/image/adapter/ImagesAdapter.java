package ar.edu.unrn.lia.capacitacionhorizonte1.image.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.lia.capacitacionhorizonte1.R;
import ar.edu.unrn.lia.capacitacionhorizonte1.image.entity.Image;
import ar.edu.unrn.lia.capacitacionhorizonte1.image.ui.OnItemClickListener;
import ar.edu.unrn.lia.capacitacionhorizonte1.lib.ImageLoader;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private List<Image> items = new ArrayList<Image>(0);
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public ImagesAdapter(OnItemClickListener clickListener, ImageLoader imageLoader) {
        this.items = items;
        this.clickListener = clickListener;
        this.imageLoader = imageLoader;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image imagen = items.get(position);
        holder.setClickListener(imagen, clickListener);
        holder.txtText.setText(imagen.getText());
        imageLoader.load(holder.imgMedia, imagen.getImageURL()); //Cargo la imagen
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Image> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtText) TextView txtText;
        @BindView(R.id.imgMedia) ImageView imgMedia;

        private View view;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }

        public void setClickListener(final Image image, final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(image);
                }
            });

        }
    }
}

package com.macronomos.dailymacro.ui.search;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.macronomos.dailymacro.R;
import com.macronomos.dailymacro.databinding.SearchNewsItemBinding;
import com.macronomos.dailymacro.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder> {

    private List<Article> articles = new ArrayList<>();

    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        notifyDataSetChanged();
    }

    public interface ItemCallback {
        void onOpenDetails(Article article);
    }

    private ItemCallback itemCallback;

    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }

    @NonNull
    @Override
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_news_item, parent, false);
        SearchNewsViewHolder viewHolder = new SearchNewsViewHolder(view);
        Log.d("test", "onCreate " + viewHolder.toString());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
        Log.d("test", "onCreate " + holder.toString());
        Article article = articles.get(position);
        holder.itemTitleTextView.setText(article.title);
        if (article.urlToImage != null) {
            Picasso.get().load(article.urlToImage).resize(200, 200).into(holder.itemImageView);
            holder.itemView.setOnClickListener(v -> itemCallback.onOpenDetails(article));
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageView;
        TextView itemTitleTextView;

        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchNewsItemBinding binding = SearchNewsItemBinding.bind(itemView);
            itemImageView = binding.searchItemImage;
            itemTitleTextView = binding.searchItemTitle;
        }
    }
}
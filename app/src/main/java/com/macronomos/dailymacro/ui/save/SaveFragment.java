package com.macronomos.dailymacro.ui.save;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macronomos.dailymacro.R;
import com.macronomos.dailymacro.databinding.FragmentSaveBinding;
import com.macronomos.dailymacro.model.Article;
import com.macronomos.dailymacro.repository.NewsRepository;
import com.macronomos.dailymacro.repository.NewsViewModelFactory;


public class SaveFragment extends Fragment {

    private FragmentSaveBinding binding;
    private SaveViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =  FragmentSaveBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SavedNewsAdapter savedNewsAdapter = new SavedNewsAdapter();
        binding.newsSavedRecyclerView.setAdapter(savedNewsAdapter);
        binding.newsSavedRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        savedNewsAdapter.setItemCallback(new SavedNewsAdapter.ItemCallback() {
            @Override
            public void onOpenDetails(Article article) {
                Log.d("onOpenDetails", article.toString());
                SaveFragmentDirections.ActionNavigationSaveToNavigationDetails direction = SaveFragmentDirections.actionNavigationSaveToNavigationDetails(article);
                NavHostFragment.findNavController(SaveFragment.this).navigate(direction);
            }

            @Override
            public void onRemoveFavorite(Article article) {
                viewModel.deleteSavedArticle(article);
            }
        });

        NewsRepository repository = new NewsRepository();
        viewModel = new ViewModelProvider(this, new NewsViewModelFactory(repository)).get(SaveViewModel.class);
        viewModel
                .getAllSavedArticles()
                .observe(
                        getViewLifecycleOwner(),
                        savedArticles -> {
                            if (savedArticles != null) {
                                Log.d("SaveFragment", savedArticles.toString());
                                savedNewsAdapter.setArticles(savedArticles);
                            }
                        });
    }

}
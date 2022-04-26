package com.pugerp.movieapp.adapter;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pugerp.movieapp.BuildConfig;
import com.pugerp.movieapp.data.Review;
import com.pugerp.movieapp.databinding.IReviewBinding;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {

    private List<Review> data;

    public ReviewAdapter(List<Review> data) {
        this.data = data;

    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @NonNull IReviewBinding binding = IReviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ReviewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ReviewHolder extends RecyclerView.ViewHolder {

        private IReviewBinding binding;

        public ReviewHolder(@NonNull IReviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Review review) {
            String author[] = review.getAuthor().split("");
            binding.tvInitial.setText(author[0].toUpperCase());
            Glide.with(binding.ivAuthor)
                    .load(BuildConfig.BASE_URL_IMAGE + review.getAuthorDetails().getAvatarPath())
                    .circleCrop()
                    .into(binding.ivAuthor);

            binding.tvAuthor.setText(review.getAuthor());
            binding.tvContent.setText(review.getContent());

            Layout layout = binding.tvContent.getLayout();
            if (layout != null) {
                int lines = layout.getLineCount();
                if (lines > 0) {
                    int ellipsisCount = layout.getEllipsisCount(lines - 1);
                    binding.tvMoreLess.setVisibility(ellipsisCount > 0 ? View.VISIBLE : View.GONE);
                }
            }

            binding.tvMoreLess.setOnClickListener(view -> {
                binding.tvContent.setMaxLines(Integer.MAX_VALUE);
                binding.tvMoreLess.setVisibility(View.GONE);
            });
        }

    }
}

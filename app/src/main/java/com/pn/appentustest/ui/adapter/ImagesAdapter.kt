package com.pn.appentustest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pn.appentustest.data.ImagesData
import com.pn.appentustest.databinding.ItemImagesBinding
import com.pn.appentustest.utils.getShimmerDrawable


class ImagesAdapter :
    PagingDataAdapter<ImagesData, ImagesAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            ItemImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class PhotoViewHolder(private val binding: ItemImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: ImagesData) {
            binding.apply {
                Glide.with(itemView)
                    .load(photo.downloadUrl)
                    .placeholder(getShimmerDrawable())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(imageView)
            }
        }
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<ImagesData>() {
            override fun areItemsTheSame(oldItem: ImagesData, newItem: ImagesData) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ImagesData, newItem: ImagesData) =
                oldItem == newItem
        }
    }
}
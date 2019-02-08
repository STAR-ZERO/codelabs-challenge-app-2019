package droidkaigi.github.io.challenge2019

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import droidkaigi.github.io.challenge2019.data.api.response.Item
import droidkaigi.github.io.challenge2019.databinding.ItemAdBinding
import droidkaigi.github.io.challenge2019.databinding.ItemCommentBinding


class CommentAdapter(
    var comments: List<Item?>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class CommentViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root)

    class AdViewHolder(binding: ItemAdBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return if (position < comments.size) {
            R.layout.item_comment
        } else {
            R.layout.item_ad
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_comment -> {
                val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CommentViewHolder(binding)
            }
            R.layout.item_ad -> {
                val binding = ItemAdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                AdViewHolder(binding)
            }
            else -> throw IllegalStateException("Unknown view type")
        }
    }

    override fun getItemCount(): Int = if (comments.isNotEmpty()) {
        comments.size + 1
    } else {
        0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CommentViewHolder -> {
                holder.binding.item = comments[position]
            }
        }
    }
}
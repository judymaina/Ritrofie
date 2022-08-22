package io.trell.mypost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.trell.mypost.databinding.ActivityCommentsBinding
import io.trell.mypost.databinding.CommentListItemBinding

class RvCommentAdapter(var commentList: List<Comment>):RecyclerView.Adapter<CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
      var binding=CommentListItemBinding
          .inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
       var currentComment=commentList.get(position)
        with(holder.binding){
            tvPost.text=currentComment.postId.toString()
            tvname.text=currentComment.name
            tvid.text=currentComment.id.toString()
            tvemail.text=currentComment.email
            tvComment.text=currentComment.body
        }
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

}
class CommentViewHolder (var binding: CommentListItemBinding):RecyclerView.ViewHolder(binding.root)
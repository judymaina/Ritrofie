package io.trell.mypost


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.trell.mypost.databinding.ApiListBinding

 class RvAdapter (var context: Context,var postList: List<Post>):
        RecyclerView.Adapter<PostsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
var binding=ApiListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        var postsViewHolder=PostsViewHolder(binding)
        return postsViewHolder
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        var recentpostion=postList.get(position)
        holder.binding.tvTitle.text=recentpostion.title.toString()
        holder.binding.tvBody.text=recentpostion.body.toString()
        holder.binding.tvTitle.text=recentpostion.title.toString()

        val context=holder.itemView.context
        holder.binding.cvApi.setOnClickListener {
            val intent = Intent(context,CommentsActivity::class.java)
            intent.putExtra("POST_ID",recentpostion.id)
            context.startActivity(intent)
        }



    }

    override fun getItemCount(): Int {
        return postList.size

    }
}

class PostsViewHolder(var binding: ApiListBinding):
        RecyclerView.ViewHolder(binding.root)

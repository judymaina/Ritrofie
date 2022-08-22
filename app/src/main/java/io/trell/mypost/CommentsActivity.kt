package io.trell.mypost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import io.trell.mypost.databinding.ActivityCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class CommentsActivity : AppCompatActivity() {
    var postId=0
    lateinit var binding:ActivityCommentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        obtainPostId()
        fetchPostById()
        SetupToolbar()
        obtainComment()
        fetchComment()

    }
    fun obtainPostId(){
        postId=intent.extras?.getInt("POST_ID")?:0
    }
    fun obtainComment(){
        var extras=intent
        postId=extras.getIntExtra("POST_ID",0)
    }

    fun fetchPostById(){
        val apiClient=APIClient.buildApiClient(ApiInterface::class.java)
        val request=apiClient.getPostById(postId)
        request.enqueue(object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
             if (response.isSuccessful){
                 var post =response.body()
                 binding.tvPostTitle.text=post?.title
                 binding.tvPostBody.text=post?.title
             }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
          Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG)
            }


        } )

}
    fun SetupToolbar(){
        setSupportActionBar(binding.toolbarcomment)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    fun fetchComment() {
        val apiClient = APIClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getComments(postId)
        request.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {

                if (response.isSuccessful) {
                    var comment = response.body() ?: emptyList()
                    displayComment(comment)

                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
        fun displayComment(comment: List<Comment>) {
            var adapter = RvCommentAdapter(comment)
            binding.rvComments.layoutManager=LinearLayoutManager(this)
            binding.rvComments.adapter=adapter


        }

    }






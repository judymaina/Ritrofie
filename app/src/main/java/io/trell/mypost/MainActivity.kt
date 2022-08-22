package io.trell.mypost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import io.trell.mypost.databinding.ActivityMainBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchPosts()
//        bringPost()
    }
    fun fetchPosts(){
        val apiClient = APIClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.getPosts()
        request.enqueue(object: Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
             if (response.isSuccessful){
                 var posts=response.body()
                 Toast.makeText(baseContext,"fetched ${posts!!.size}posts",Toast.LENGTH_LONG).show()
                var adapter=RvAdapter(baseContext,posts)
                 Log.d("Tag",posts.toString())
                 binding.rvApi.adapter=adapter
                 binding.rvApi.layoutManager=LinearLayoutManager(baseContext)
             }

            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }
        })

        }



    }

//    fun bringPost(){
//        var post1=Post(42,213,"Black panther","it is a real movie")
//        var post2=Post(23,204,"All the boys I have ever loved","a great show to watch")
//        var post3=Post(24,117,"Never have I ever","a bollywood movie which excites me")
//        var post4=Post(25,342,"Big brother","a reality show in Nigeria")
//        var post5=Post(26,111,"Atomic habits","it is a real book")
//        var post6=Post(27,112,"Hoodies","I like water bottles")
//        var post7=Post(28,113,"bottles","I like hoodies")
//        var posts= listOf(post1,post2,post3,post4,post5,post6,post7)
//        var postingAdapter=RvAdapter(posts)
//        binding.rvApi.layoutManager=LinearLayoutManager(this)
//        binding.rvApi.adapter=postingAdapter
//    }
//}
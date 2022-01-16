package com.chndr.githubtestapp.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.chndr.githubtestapp.adapter.ReposAdapter
import com.chndr.githubtestapp.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    private lateinit var adapter: ReposAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ReposAdapter()
        adapter.notifyDataSetChanged()

        val username = intent.getStringExtra(EXTRA_USERNAME)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)

        if (username != null) {
            viewModel.setUserDetail(username)
        }
        viewModel.getUserDetail().observe(this, {
            if(it != null){
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = "@${it.login}"
                    tvBio.text = it.bio

                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .apply(RequestOptions.circleCropTransform())
                        .into(ivProfile)

                    rvRepos.layoutManager = LinearLayoutManager(this@DetailUserActivity)
                    rvRepos.setHasFixedSize(true)
                    rvRepos.adapter = adapter

                }

            }
        })
        showLoading(true)
        if (username != null) {
            viewModel.setListRepos(username)
        }
        viewModel.getListRepos().observe(this, {
            if(it != null){
                adapter.setList(it)
                showLoading(false)
            }
        })
    }
    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }


}
package com.chndr.githubtestapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chndr.githubtestapp.data.model.ReposUserResponse
import com.chndr.githubtestapp.databinding.ItemReposBinding
import kotlin.collections.ArrayList
import java.text.SimpleDateFormat
import java.util.*
import org.ocpsoft.prettytime.PrettyTime





class ReposAdapter : RecyclerView.Adapter<ReposAdapter.ReposViewHolder>() {

    private val list = ArrayList<ReposUserResponse>()



    @SuppressLint("NotifyDataSetChanged")
    fun setList(repos: ArrayList<ReposUserResponse>){
        list.clear()
        list.addAll(repos)
        notifyDataSetChanged()
    }

    inner class ReposViewHolder(private val binding: ItemReposBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bind(repos: ReposUserResponse){
            //2021-06-17T15:46:48Z


            binding.apply {
                tvReposname.text = repos.name
                tvReposdesc.text = repos.description
                tvStar.text = repos.stargazers_count.toString()
                val timeupdate = repos.updated_at
                val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                sdf.timeZone = TimeZone.getTimeZone("GMT")
                val time = sdf.parse(timeupdate).time
                val prettyTime = PrettyTime(Locale.getDefault())
                val ago: String = prettyTime.format(Date(time))
                tvUpdate.text = "Updated $ago"

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val view = ItemReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReposViewHolder((view))
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size



}
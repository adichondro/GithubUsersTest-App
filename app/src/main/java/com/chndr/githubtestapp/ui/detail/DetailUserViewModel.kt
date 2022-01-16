package com.chndr.githubtestapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chndr.githubtestapp.api.RetrofitClient
import com.chndr.githubtestapp.data.model.DetailUserResponse
import com.chndr.githubtestapp.data.model.ReposUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel: ViewModel() {
    val user = MutableLiveData<DetailUserResponse>()
    val listRepos = MutableLiveData<ArrayList<ReposUserResponse>>()

    fun setUserDetail(username: String){
        RetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<DetailUserResponse>{
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if(response.isSuccessful){
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getUserDetail(): LiveData<DetailUserResponse> {
        return user
    }

    fun setListRepos(username: String){
        RetrofitClient.apiInstance
            .getRepos(username)
            .enqueue(object : Callback<ArrayList<ReposUserResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<ReposUserResponse>>,
                    response: Response<ArrayList<ReposUserResponse>>
                ) {
                    if(response.isSuccessful){
                        listRepos.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<ReposUserResponse>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getListRepos() : LiveData<ArrayList<ReposUserResponse>> {
        return listRepos
    }


}
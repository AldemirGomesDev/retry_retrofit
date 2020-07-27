package com.aldemir.retryretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aldemir.retryretrofit.retrofit.NetworkConfig
import com.aldemir.retryretrofit.retrofit.StatesResponse
import com.aldemir.retryretrofit.retrofit.StatesServiceAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), StatesAdapter.ClickListener {
    private lateinit var mtextMessage: TextView
    private lateinit var adapter: StatesAdapter
    private lateinit var recyclerViewStates: RecyclerView
    private var listStates: ArrayList<States> = ArrayList()
    private var mState: States = States()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mtextMessage = text_message
        recyclerViewStates = recyclerView_states
        loading.visibility = View.VISIBLE

        val apiService = NetworkConfig.provideApi(StatesServiceAPI::class.java)

        apiService.getStates().enqueue(object : Callback<ArrayList<StatesResponse>>{
            override fun onResponse(
                call: Call<ArrayList<StatesResponse>>,
                response: Response<ArrayList<StatesResponse>>
            ) {
                if (response.isSuccessful) {
                    listStates = arrayListOf()
                    for (state in response.body()!!) {
                        mState = States()
                        mState.id = state.id
                        mState.nome = state.nome
                        mState.regiao = state.regiao!!.nome
                        mState.sigla = state.sigla

                        listStates.add(mState)
                    }
                    loading.visibility = View.GONE
                    listStates.sortBy { it.nome }
                    setupRecyclerView(listStates)

                    Toast.makeText(this@MainActivity, "Status: ${response.code()}", Toast.LENGTH_SHORT).show()

                } else {
                    loading.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()

                }
            }
            override fun onFailure(call: Call<ArrayList<StatesResponse>>, t: Throwable) {
                loading.visibility = View.GONE
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setupRecyclerView(listStates: ArrayList<States>) {
        adapter = StatesAdapter(listStates)
        recyclerViewStates.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        recyclerViewStates.layoutManager = layoutManager

        adapter.setOnItemClickListener(this)
    }

    override fun onClick(position: Int, aView: View) {
        Toast.makeText(this@MainActivity, "Item: $position", Toast.LENGTH_SHORT).show()
    }
}
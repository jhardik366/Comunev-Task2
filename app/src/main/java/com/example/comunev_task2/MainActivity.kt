package com.example.comunev_task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.comunev_task2.MySingleton
import com.example.comunev_task2.R
import com.example.comunev_task2.RandomUserAdapter
import com.example.comunev_task2.User
import org.json.JSONObject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: RandomUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        progressBar.visibility = View.VISIBLE
        mAdapter = RandomUserAdapter()
        recyclerView.adapter = mAdapter
    }

    private fun fetchData() {

        val url = "https://randomuser.me/api/?results=100&inc=name"
        val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                {
                    progressBar.visibility = View.GONE
                    val usersJsonArray = it.getJSONArray("results")
                    val usersArray = ArrayList<User>()
                    for (i in 0 until usersJsonArray.length()) {
                        val usersJsonObject = usersJsonArray.getJSONObject(i)
                        val name = usersJsonObject.getJSONObject("name")
                        val users = User(
                                name.getString("title"),
                                name.getString("first"),
                                name.getString("last")
                        )
                        usersArray.add(users)
                    }
                    mAdapter.updateUsers(usersArray)
                },
                {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
                }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}
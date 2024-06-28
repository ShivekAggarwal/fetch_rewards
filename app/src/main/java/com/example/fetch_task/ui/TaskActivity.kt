package com.example.fetch_task.ui


import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch_task.adapter.GroupedItemAdapter
import com.example.fetch_task.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TaskActivity : AppCompatActivity() {

    private val itemViewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val errorTextView = findViewById<TextView>(R.id.errorTextView)
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floatingRefreshButton)
        toolbar.setOnClickListener {
           onButtonPressed()
        }
      floatingActionButton.setOnClickListener{
            restart()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)

        itemViewModel.groupedItems.observe(this, Observer { groupedItems ->

            if (!groupedItems.isNullOrEmpty()) {
                errorTextView.visibility = View.GONE
                recyclerView.visibility= View.VISIBLE
                progressBar.visibility = View.GONE
                recyclerView.adapter = GroupedItemAdapter(groupedItems)
            }else {
                errorTextView.visibility = View.VISIBLE
                recyclerView.visibility = View.INVISIBLE
            }

        })
        itemViewModel.isLoading.observe(this, Observer { isLoading ->

            progressBar.visibility =  View.VISIBLE
            if (!isLoading) {
                progressBar.visibility =  View.GONE
                itemViewModel.error.observe(this, Observer { error ->
                    if (error.isNullOrBlank()) {
                        errorTextView.visibility = View.GONE
                    } else {
                        errorTextView.visibility = View.VISIBLE
                        recyclerView.visibility = View.INVISIBLE
                        errorTextView.text = error
                    }
                })
            }
        })

    }

    private fun onButtonPressed() {
        val intent = Intent(this , HomeActivity::class.java )
        startActivity(intent , ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        finish()
    }

    override fun onPause() {
        restart()
        super.onPause()
    }

    override fun onStop() {
        restart()
        super.onStop()
    }

    private fun restart() {
        itemViewModel.fetchItems()

    }
}



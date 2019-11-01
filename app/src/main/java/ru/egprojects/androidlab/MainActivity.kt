package ru.egprojects.androidlab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: AnimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = AnimeAdapter(Api.loadAnimes()) {
            AnimeActivity.start(this, it)
        }
        rv_main_datalist.layoutManager = LinearLayoutManager(baseContext)
        rv_main_datalist.adapter = adapter
    }

}

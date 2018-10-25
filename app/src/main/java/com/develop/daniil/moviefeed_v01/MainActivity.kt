package com.develop.daniil.moviefeed_v01

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        fakeData()
        setUpRecyclerView()
    }

    private fun setUpView() { //тулбар
        val toolbar = findViewById<View>(R.id.toolbar) as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayShowTitleEnabled(false) //делитим загаловок по умолчанию
        //toolbar.setNavigationIcon(R.drawable.sharp_menu_black_36) //бургер
    }

    private fun fakeData() { //заполним листвью
        for (i in 0..20)
            mList.add("Will Smith в очередной раз получил Оскара в Каннах")
    }

    private fun setUpRecyclerView() { //прикручиваем массив mList адптером к ресайклер вью
        val mRecyclerView = findViewById<RecyclerView>(R.id.main_recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        mRecyclerView.adapter = ListAdapter(mList)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //menuInflater.inflate(R.menu.toolbar_menu, menu) //верхнее меню три точки
        return true
    }
}


internal class ListAdapter(list: List<String>?) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private val mList = ArrayList<String>()

    init {
        mList.addAll(list!!) //лист не будет Null, проверка на null
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var textView: TextView = v.findViewById(R.id.NewsName) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_model, parent, false) //надуваем row_model данными
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) { //классная фича для ресайклер вью,
        holder.textView.text = mList[position] //забиваем держатель textView текстом. Обеспечивает плавность прокрутки
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}

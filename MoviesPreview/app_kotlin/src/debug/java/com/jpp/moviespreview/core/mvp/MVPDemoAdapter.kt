package com.jpp.moviespreview.core.mvp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jpp.moviespreview.extentions.ctx
import org.jetbrains.anko.find

/**
 * RecyclerView.Adapter for the MVPDemoActivity
 *
 * Created by jpp on 6/20/17.
 */
class MVPDemoAdapter(val stringList: List<String>) : RecyclerView.Adapter<MVPDemoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view, view.find(android.R.id.text1))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(stringList[position])
    }

    override fun getItemCount() = stringList.size


    class ViewHolder(view: View, var textView: TextView) : RecyclerView.ViewHolder(view) {

        fun bind(value: String) {
            textView.text = value
        }

    }
}
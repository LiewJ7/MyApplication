package com.example.myapplication

import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MomentListAdapter internal constructor(context: Context):
        RecyclerView.Adapter<MomentListAdapter.MomentViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var moments = emptyList<Moment>() // Cached copy of user

    inner class MomentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val topicTitleTextView: TextView = itemView.findViewById(R.id.buttonTitle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentViewHolder {
        val itemView = inflater.inflate(R.layout.moment_record, parent, false)
        return MomentViewHolder(itemView)
    }

    override fun getItemCount()=moments.size;

    override fun onBindViewHolder(holder: MomentViewHolder, position: Int) {
        val current = moments[position]
        holder.topicTitleTextView.text = current.topicTitle

    }

    internal fun setMoments(moments: List<Moment>) {
        this.moments = moments
        notifyDataSetChanged()
    }

}

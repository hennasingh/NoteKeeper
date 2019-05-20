package com.geek.notekeeper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListSelectionRecyclerViewAdapter(val lists: ArrayList<TaskList>) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_selection_item, parent,false)
        return ListSelectionViewHolder(view)

    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.listPosition.text = (position + 1).toString()
        holder.listTitle.text = lists.get(position).name


    }

    fun addList(list: TaskList) {
        lists.add(list)
        notifyDataSetChanged()
    }

}

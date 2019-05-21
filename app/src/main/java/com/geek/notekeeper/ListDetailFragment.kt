package com.geek.notekeeper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListDetailFragment : Fragment() {


    lateinit var list: TaskList
    lateinit var listItemsRV: RecyclerView

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = arguments!!.getParcelable(MainActivity.INTENT_LIST_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_detail, container, false)
        view?.let {
            listItemsRV = it.findViewById(R.id.list_items_rv)
            listItemsRV.adapter = ListItemsRecyclerViewAdapter(list)
            listItemsRV.layoutManager = LinearLayoutManager(activity)

        }
        return view
    }

    fun addTask(item: String) {
        list.tasks.add(item)
        val listRecycleAdapter = listItemsRV.adapter as ListItemsRecyclerViewAdapter
        listRecycleAdapter.list = list
        listRecycleAdapter.notifyDataSetChanged()

    }


    companion object {

        @JvmStatic
        fun newInstance(list: TaskList) =
            ListDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MainActivity.INTENT_LIST_KEY, list)
                }
            }
    }
}

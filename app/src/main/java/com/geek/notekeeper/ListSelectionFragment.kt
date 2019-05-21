package com.geek.notekeeper

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListSelectionFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListSelectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ListSelectionFragment : Fragment(), ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {


    private var listener: OnFragmentInteractionListener? = null

    lateinit var listRecyclerView: RecyclerView
    lateinit var listDataManager: ListDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_selection, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
            listDataManager = ListDataManager(context)
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {

        fun onListItemClicked(list: TaskList)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String): ListSelectionFragment {
            val fragment = ListSelectionFragment()
            return fragment
        }
    }

    fun addList(list: TaskList) {
        listDataManager.saveList(list)
        val recyclerAdapter = listRecyclerView.adapter as ListSelectionRecyclerViewAdapter
        recyclerAdapter.addList(list)
    }

    fun saveList(list: TaskList) {
        listDataManager.saveList(list)
        updateLists()
    }

    private fun updateLists() {
        val lists = listDataManager.readList()
        listRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists, this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val lists = listDataManager.readList()
        view?.let {
            listRecyclerView = it.findViewById<RecyclerView>(R.id.rv_list)
            listRecyclerView.layoutManager = LinearLayoutManager(activity)
            listRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists, this)
        }
    }

    override fun listItemClicked(list: TaskList) {
        listener?.onListItemClicked(list)
    }
}

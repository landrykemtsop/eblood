package com.fomus.eblood.ui.postbloodRequest

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.fomus.eblood.R
import kotlinx.android.synthetic.main.post_bllood_t_request_fragment.*

class PostBlloodTRequestFragment : Fragment(), AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() = PostBlloodTRequestFragment()
    }

    var groups = arrayListOf<String>()
    var towns = arrayListOf<String>()
    private lateinit var viewModel: PostBlloodTRequestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_bllood_t_request_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostBlloodTRequestViewModel::class.java)
        initGroups()
        initTownss()
// Create an ArrayAdapter using the string array and a default spinner layout


        var adapterTowns= ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item,towns)
        var adapterGroups= ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item,groups)
        towns_spinner.adapter=adapterTowns
        towns_spinner.onItemSelectedListener=this
        group_spinner.adapter=adapterGroups
        group_spinner.onItemSelectedListener=this
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p0!!.id == R.id.group_spinner) {
            // first spinner selected

            selected_group.setText(groups[p2])
        } else if (p0!!.id == R.id.towns_spinner) {
            // second spinner selected
            selected_town.setText(towns[p2])
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    private fun initGroups() {
        groups.add(("A+"))
        groups.add(("A-"))
        groups.add(("B+"))
        groups.add(("B-"))
        groups.add(("AB+"))
        groups.add(("AB-"))
        groups.add(("0+"))
        groups.add(("0-"))
    }

    private fun initTownss() {
        towns.add(("Yaounde"))
        towns.add(("Douala"))
        towns.add(("Bertoua"))
        towns.add(("Ebolwa"))
        towns.add(("Bafoussam"))
        towns.add(("Ngaoundere"))
        towns.add(("Maroua"))
        towns.add(("Garoua"))
        towns.add(("Buea"))
        towns.add(("Bamenda"))
    }

}
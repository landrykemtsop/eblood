package com.fomus.eblood.ui.postdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fomus.eblood.R

class BloodPostDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = BloodPostDetailsFragment()
    }

    private lateinit var viewModel: BloodPostDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.blood_post_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BloodPostDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
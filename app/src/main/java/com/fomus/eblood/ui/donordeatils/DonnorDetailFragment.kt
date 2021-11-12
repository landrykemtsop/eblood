package com.fomus.eblood.ui.donordeatils

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fomus.eblood.R

class DonnorDetailFragment : Fragment() {

    companion object {
        fun newInstance() = DonnorDetailFragment()
    }

    private lateinit var viewModel: DonnorDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.donnor_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DonnorDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
package com.fomus.eblood.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fomus.eblood.ui.UpdateBankStockFragment
import com.fomus.eblood.ui.banks.BloodBankFragment
import com.fomus.eblood.ui.donors.BloodDonorsFragment
import com.fomus.eblood.ui.postbloodRequest.PostBlloodTRequestFragment
import com.fomus.eblood.ui.posts.BloodPostFragment

class CreationAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment = BloodPostFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            val ARG_OBJECT="agument"
            putInt(ARG_OBJECT, position + 1)
        }
        return when(position){
            0-> PostBlloodTRequestFragment()
            1-> UpdateBankStockFragment()
            else -> PostBlloodTRequestFragment()
        }

    }
}
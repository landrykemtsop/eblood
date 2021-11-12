package com.fomus.eblood.ui.notifications

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.fomus.eblood.MainActivity
import com.fomus.eblood.MainActivity.Companion.sharedPreferences
import com.fomus.eblood.R
import com.fomus.eblood.data.User
import com.fomus.eblood.databinding.FragmentNotificationsBinding
import com.fomus.eblood.ui.editeProfile.EditeProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_notifications.*
import java.util.*

class NotificationsFragment : Fragment() {


    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val model: EditeProfileViewModel by activityViewModels<EditeProfileViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // See: https://developer.android.com/training/basics/intents/result
        // See: https://developer.android.com/training/basics/intents/result
        onClickListeners()

        if (sharedPreferences.userEmail == "" || sharedPreferences.userEmail == null) {

// Choose authentication providers
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.PhoneBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build()
            )

// Create and launch sign-in intent
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            signInLauncher.launch(signInIntent)
        } else {
            Toast.makeText(requireContext(), "${sharedPreferences.userId}", Toast.LENGTH_LONG)
                .show()
            model.checkExistingUser(sharedPreferences.userId!!, {
                if (it == User()) {
                    findNavController().navigate(R.id.action_navigation_notifications_to_editeProfileFragment)
                } else {
                    email_adress.text= it.emailAdress
                    user_weight.text=it.userWeight.toString()+"KG"
                    user_quarter.text=it.userQuarter?:"not provided"
                    last_donation_date.text= "${dateDiffDays(Date(),it.userLastDonnationDate!!)} Ago"?:"not provided"
                    user_age.text= "${dateDiffDays(Date(),it.userLastDonnationDate!!)/365} ans"?:"not provided"
                    user_main_phone_number.text=it.userMainPhoneNumber?:"not provided"
                    user_alt_phone_number.text=it.userAltPhoneNumber?:"not provided"
                    user_town.text=it.userTown?:"not provided"
                    user_group.text=it.bloodGroup?:"not provided"
                    user_quarter.text= it.userQuarter

                }


            }, {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()

            })
        }
    }

    public  fun dateDiffDays(date1:Date,date2:Date): Long {
        val diff: Long = date1.getTime() - date2.time
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        return days
    }


    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == Activity.RESULT_OK) {
            val user = FirebaseAuth.getInstance().currentUser
            Log.e("user", "user ${user?.email}")
            sharedPreferences.userEmail = (user?.email!!)
            sharedPreferences.userId = (user?.uid!!)
            // ...
        } else {

        }
    }

    private fun onClickListeners() {
        edit_profile.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_notifications_to_editeProfileFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
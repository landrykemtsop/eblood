package com.fomus.eblood.ui.editeProfile

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.fomus.eblood.R
import com.fomus.eblood.utils.GeneralUtils.dateDiffDays
import kotlinx.android.synthetic.main.edite_profile_fragment.*
import kotlinx.android.synthetic.main.edite_profile_fragment.email_adress
import java.util.*

class EditeProfileFragment : Fragment() {

    companion object {
        fun newInstance() = EditeProfileFragment()
    }

    val model: EditeProfileViewModel by activityViewModels<EditeProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edite_profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ccp_main.registerCarrierNumberEditText(main_phone_number)
        ccp_main.formattedFullNumber
        ccp.registerCarrierNumberEditText(alt_phone_number)
        ccp_main.formattedFullNumber
        lastDonationDate.setOnClickListener {
            displayCallendar(1,lastDonationDate)
        }
        birth_date.setOnClickListener {
            displayCallendar(0,birth_date)
        }

        initFields()

        save.setOnClickListener {

            if(validatFields()){
                progress_bar.visibility=View.VISIBLE
                model.creatreANewUser(model.user.value!!,{
                    progress_bar.visibility=View.GONE
                    findNavController().navigateUp()
                },{
                    progress_bar.visibility=View.GONE
                    Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()

                })
            }
        }

    }

    private lateinit var birthDay : Date
    private lateinit var lastDonationDateVar : Date

    private fun initFields(){
        model.user.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            Log.e("user",it.toString())
            email_adress.setText(it.emailAdress)
            user_weight_edition.setText(it.userWeight.toString()+"KG")
            quarter.setText(it.userQuarter?:"not provided")
            lastDonationDate.setText("${dateDiffDays(Date(),it.userLastDonnationDate!!)} Ago")
            user_age_edition.setText( "${dateDiffDays(Date(),it.birthDate!!)/365} ans"?:"not provided")
            main_phone_number.setText(it.userMainPhoneNumber?:"not provided")
            alt_phone_number.setText(it.userAltPhoneNumber?:"not provided")
            town_leaving.setText(it.userTown?:"not provided")
            user_group_edition.setText(it.bloodGroup?:"not provided")
            quarter.setText(it.userQuarter)
        })
    }

    private fun displayCallendar(value:Int,field:TextView) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            requireActivity(),
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in textbox
                field.setText("" + dayOfMonth + "/" + monthOfYear + "/" + year)
                if (value==0){
                    birthDay=GregorianCalendar(year, month - 1, day).time
                }else{
                    lastDonationDateVar=GregorianCalendar(year, month - 1, day).time
                }
            },
            year,
            month,
            day
        )

        dpd.show()
    }
    private fun init(){

    }
    fun validatFields():Boolean{
        var result = true
        if(names.text.toString().isEmpty() or names.text.toString().isBlank()){
            names.error="Provide your full name"
            return  false
        }else{
            model.userToCreate.value!!.names=names.text.toString()
        }
        if(town_leaving.text.isEmpty() or town_leaving.text.isBlank()){
            town_leaving.error="required"
            return  false
        }else{
            model.userToCreate.value!!.userTown=town_leaving.text.toString()
        }
        if(!(this::birthDay.isInitialized)){
            birth_date.error= " choose a birthday"
        }else{
            model.userToCreate.value!!.birthDate=birthDay
        }
        if(!(this::lastDonationDateVar.isInitialized)){
            lastDonationDate.error= " choose a birthday"
        }else{
            model.userToCreate.value!!.userLastDonnationDate=lastDonationDateVar
        }
        if(main_phone_number.text.isEmpty() or main_phone_number.text.isBlank()){
            main_phone_number.error="required"
            return  false
        }else{
            model.userToCreate.value!!.userMainPhoneNumber=main_phone_number.text.toString()
        }
        if(!(ccp_main!!.isValidFullNumber)){
            main_phone_number.error="required"
            return  false
        }else{
            model.userToCreate.value!!.userMainPhoneNumber=ccp_main!!.fullNumberWithPlus
        }
        if(!(ccp!!.isValidFullNumber)){
        }else{
            model.userToCreate.value!!.userMainPhoneNumber=ccp_main!!.fullNumberWithPlus
        }
        if(user_weight_edition.text.isEmpty() or user_weight_edition.text.isBlank()){
            user_weight_edition.error="we need to know your weight"
            return  false
        }else{
            model.userToCreate.value!!.userWeight=user_weight_edition.text.toString().toDouble()
        }
        if(user_age_edition.text.isEmpty() or user_age_edition.text.isBlank()){
            user_age_edition.error="Type your age"
            return  false
        }else{
            model.userToCreate.value!!.UserAge=user_age_edition.text.toString().toInt()
        }
        if(user_group_edition.text.isEmpty() or user_group_edition.text.isBlank()){


        }else{
            model.userToCreate.value!!.bloodGroup=user_group_edition.text.toString()
        }
        if(email_adress.text.isEmpty() or email_adress.text.isBlank()){

        }else{
            model.userToCreate.value!!.emailAdress=email_adress.text.toString()
        }

        return result
    }
}
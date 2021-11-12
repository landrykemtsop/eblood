package com.fomus.eblood.data

import java.util.*

data class User(
    var names: String,
    var birthDate: Date?,
    var bloodGroup: String,
    var userTown: String,
    var userQuarter: String?,
    var userLastDonnationDate: Date?,
    var userMainPhoneNumber: String?,
    var userAltPhoneNumber: String?,
    var emailAdress:String,
    var authId:String,
    var userWeight:Double,
    var UserAge:Int,

){
    constructor():this("",null,BlooodGroup.O.bloodGroup,"","",null,"","","","",0.0,0)
}

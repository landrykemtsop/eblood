package com.fomus.eblood.dao.userDao

import android.util.Log
import com.fomus.eblood.MainActivity
import com.fomus.eblood.data.User
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject

class UserDao {
    val db = MainActivity.db
    fun createUser(agent: User, onSuccess: () -> Unit, onFailure: (message: String) -> Unit) {
        Log.e("dao","creation called")
        db.collection("users").document(MainActivity.sharedPreferences.userId!!).set(agent,
            SetOptions.merge()).addOnSuccessListener {
           onSuccess()
        }.addOnFailureListener {
            if (it.message == null) {
                onFailure("failed to save the agent try again later")
            } else {
                onFailure("failed to save the agent: ${it.message}")

            }

        }

    }

    fun getUser(userId: String, onSuccess: (user:User?) -> Unit, onFailure: (message: String) -> Unit) {
        db.collection("users").document(userId).get().addOnSuccessListener {
            if(it.data.isNullOrEmpty()){
                onSuccess(User())
            }else{
                onSuccess(it.toObject(User::class.java))
            }

        }.addOnFailureListener {
            if (it.message == null) {
                onFailure("failed to get the user from the database try again later")
            } else {
                onFailure("failed to save the agent: ${it.message}")

            }

        }

    }


}
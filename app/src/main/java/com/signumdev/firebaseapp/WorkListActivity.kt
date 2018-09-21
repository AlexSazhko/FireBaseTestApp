package com.signumdev.firebaseapp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class WorkListActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null
    val database = FirebaseDatabase.getInstance()
    var myRef: DatabaseReference = database.getReference("Users")
    var query: Query = myRef

    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent {
            val intent = Intent(context, WorkListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_list)

        var user = auth?.currentUser
        //addUser()
        loadUsers()
    }

    fun addUser(){
        val user = User("1x", "Vasia")
        myRef.push().setValue(user)
    }

    fun loadUsers(){
        query.addChildEventListener(object : ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(dataSnapShot: DataSnapshot, p1: String?) {
                for (userSnapShot in dataSnapShot.children){
                    val user = userSnapShot.getValue(User::class.java)
                    Log.d("TAG", "size ${user?.name}")
                }


            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }

        } )
    }


}

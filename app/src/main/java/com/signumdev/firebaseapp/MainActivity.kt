package com.signumdev.firebaseapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.widget.Toast
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "user"
    }

    private var mAuth: FirebaseAuth? = null

    private var email: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance();

        initControls()
        setTestCredential()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth?.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {

    }

    fun setTestCredential(){
        emailEditText.setText("magd@gmail.com")
        passwordEditText.setText("123555")
    }

    fun initControls(){
        signInButton.setOnClickListener{ attemptSignIn() }
        signUpButton.setOnClickListener { attemptSignUp() }
    }

    fun attemptSignIn(){
        email = emailEditText.text.toString()
        password = passwordEditText.text.toString()

        mAuth?.apply {
            signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    showMessage("Auth is comlete")
                    startActivity(WorkListActivity.newInstance(this@MainActivity))
                } else showMessage("Auth is failed")
            }
        }
    }

    fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    fun attemptSignUp(){
        email = emailEditText.text.toString()
        password = passwordEditText.text.toString()

        mAuth?.apply {
            createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) showMessage("Registration is comlete") else showMessage("Registration is failed")
            }
        }
    }

}

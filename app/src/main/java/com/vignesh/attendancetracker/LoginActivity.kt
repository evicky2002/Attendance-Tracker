package com.vignesh.attendancetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"
    private val RC_SIGN_IN = 1
    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var mSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mFirebaseAuth = FirebaseAuth.getInstance();
        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mSignInClient = GoogleSignIn.getClient(this,gso)
        val btnSignIn = findViewById<SignInButton>(R.id.btnSignIn)
        btnSignIn.setOnClickListener{
            signIn()
        }

    }

    private fun signIn(){
        val signInIntent = mSignInClient.signInIntent
        startActivityForResult(signInIntent,RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.getIdToken(), null)
        mFirebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener(this) { authResult: AuthResult? ->
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener(
                this
            ) { e: Exception? ->
                Toast.makeText(
                    this, "Authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = mFirebaseAuth.currentUser
        if(user == null){
            return
        }else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}
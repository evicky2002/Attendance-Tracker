package com.vignesh.attendancetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.HapticFeedbackConstants
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"
    private val RC_SIGN_IN = 1
    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var mSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        hideSystemBars()


        mFirebaseAuth = FirebaseAuth.getInstance()
        db = Firebase.firestore
        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mSignInClient = GoogleSignIn.getClient(this,gso)
        val btnSignIn = findViewById<MaterialCardView>(R.id.btnSignIn)
        btnSignIn.setOnClickListener{
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            signIn()
        }

    }
    private fun hideSystemBars() {
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return
        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
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
                startActivity(Intent(this, GetPreferencesActivity::class.java))
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
            Log.d(TAG, "new user")
            return
        }else{
            Log.d(TAG, "old user")
            Log.d(TAG, user.email.toString())
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}
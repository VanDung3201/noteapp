package com.example.notesapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentSignlnBinding
import com.example.notesapp.databinding.FragmentSignupBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class SignlnFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var navControl: NavController
    private lateinit var mBinding: FragmentSignlnBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSignlnBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerEvents()
    }


    private fun init(view: View) {
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }

    private fun registerEvents() {

        mBinding.authTextView.setOnClickListener {
            navControl.navigate(R.id.action_signlnFragment_to_signupFragment)
        }

        mBinding.nextBtn.setOnClickListener {
            val email = mBinding.emailEt.text.toString().trim()
            val pass = mBinding.passEt.text.toString().trim()


            if (email.isNotEmpty() && pass.isNotEmpty() ) {

                mBinding.progressBar.visibility= View.VISIBLE

                    auth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(OnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Login Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navControl.navigate(R.id.action_signlnFragment_to_homeFragment)

                            } else {
                                Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                            mBinding.progressBar.visibility= View.GONE

                        })

            }else{
                Toast.makeText(context, "Empty fields not allowed ", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
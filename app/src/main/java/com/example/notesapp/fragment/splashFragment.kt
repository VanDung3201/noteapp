package com.example.notesapp.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth


class splashFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController
    private  lateinit var  mBinding : FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSplashBinding.inflate(inflater, container, false)
        return  mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            if (auth.currentUser != null) {
                navController.navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                navController.navigate(R.id.action_splashFragment_to_signlnFragment)
            }
        }, 2000)
    }
}
package com.example.navegacionii

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navegacionii.databinding.FragmentBlueBinding

class BlueFragment : Fragment() {

    private var _binding: FragmentBlueBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentBlueBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        enableOnBackPressed(false)
        super.onViewCreated(view, savedInstanceState)



        val text = binding.tvLogin.text
        val ss = SpannableString(text)
        binding.tvLogin.movementMethod = LinkMovementMethod.getInstance()
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                activity?.let {
                    val fragment = RedFragment()
                    val transaction = it.supportFragmentManager.beginTransaction()
                    transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    transaction.replace(R.id.mainContainer, fragment).addToBackStack("BlueFragment").commit()}
            }
        }

        val start = text.indexOf("Login")
        val end = start + "Login".length
        ss.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        /*CustomUnderlineSpan customUnderlineSpan = new CustomUnderlineSpan();
        ss.setSpan(customUnderlineSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);*/

        binding.tvLogin.text = ss



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
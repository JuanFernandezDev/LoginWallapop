package com.example.navegacionii

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.addTextChangedListener
import com.example.navegacionii.databinding.FragmentRedBinding


class RedFragment : Fragment() {
    private var _binding: FragmentRedBinding? = null
    private val binding get() = _binding!!
    private val passwordPattern = "(?=.*[A-Z])(?=.*\\d).{6,}".toRegex()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        enableOnBackPressed(true)
        super.onViewCreated(view, savedInstanceState)

        binding.root.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            false
        }


        //Si el email o el formato de la pwd esta bien se quita el error
        binding.email.editText?.addTextChangedListener {
            if (isEmailValid(binding.email.editText?.text.toString())) {
                binding.email.error = null
            }
        }

        binding.contra.editText?.addTextChangedListener {
            if(binding.contra.editText?.text != null){
                if (binding.contra.editText?.text!!.matches(passwordPattern)) {
                    binding.contra.error = null
                }
            }

        }

        val text = getString(R.string.terminos)

        val ss = SpannableString(text)
        binding.conditions.movementMethod = LinkMovementMethod.getInstance()
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                activity?.let {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
                    startActivity(intent)
                }
            }
        }

        var start = text.indexOf("Terms")
        var end = start + "Terms and\n Conditions".length
        ss.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.conditions.text = ss

        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                activity?.let {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitch.tv"))
                    startActivity(intent)
                }
            }
        }

        val start2 = text.indexOf("Privacy")
        val end2 = start2 + "Privacy Policy".length
        ss.setSpan(clickableSpan2, start2, end2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.conditions.text = ss

         binding.btnW.setOnClickListener {
             activity?.let {
                 val fragment = MostrarFragment()
                 if (comprobarCrendenciales()){
                     fragment.arguments = Bundle().apply {putString("email", binding.email.editText?.text.toString())}
                     val transaction = it.supportFragmentManager.beginTransaction()
                     transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                     transaction.replace(R.id.mainContainer, fragment).addToBackStack("RedFragment@").commit()
                 }
             }


         }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun comprobarCrendenciales(): Boolean {

        var email = false
        var contra = false


        if (isEmailValid(binding.email.editText?.text.toString())){
           email = true
       }else{
           binding.email.error = "Email incorrecto."
       }


        if(binding.contra.editText?.text != null){
            if (binding.contra.editText?.text!!.matches(passwordPattern)) {
                contra = true
            }else{
                binding.contra.error = "La contraseña debe tener al menos 6 caracteres, una mayúscula y un número."
            }
        }

        return email && contra
    }


    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
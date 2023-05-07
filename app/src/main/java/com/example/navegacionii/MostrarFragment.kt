package com.example.navegacionii

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.navegacionii.databinding.FragmentMostrarBinding

class MostrarFragment : Fragment() {

    private var _binding: FragmentMostrarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMostrarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        enableOnBackPressed(true)
        super.onViewCreated(view, savedInstanceState)

        binding.email.text = arguments?.getString("email")
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
}
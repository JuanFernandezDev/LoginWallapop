package com.example.navegacionii

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.enableOnBackPressed(enable: Boolean){
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(enable)
    (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(enable)

    setHasOptionsMenu(enable)
}

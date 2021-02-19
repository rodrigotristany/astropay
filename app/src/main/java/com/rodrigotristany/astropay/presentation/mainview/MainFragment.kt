package com.rodrigotristany.astropay.presentation.mainview

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.rodrigotristany.astropay.R
import com.rodrigotristany.astropay.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        const val LOCATION_PERMISSION_REQUEST = 1000
    }

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        setButtonsListeners()
    }

    private fun setButtonsListeners() {
        binding.btnBuenosAires.setOnClickListener {
            val bundle = bundleOf("city_name" to binding.btnBuenosAires.text)
            Navigation.createNavigateOnClickListener(R.id.next_action, bundle).onClick(it)
        }

        binding.btnLondon.setOnClickListener {
            val bundle = bundleOf("city_name" to binding.btnLondon.text)
            Navigation.createNavigateOnClickListener(R.id.next_action, bundle).onClick(it)
        }

        binding.btnMontevideo.setOnClickListener {
            val bundle = bundleOf("city_name" to binding.btnMontevideo.text)
            Navigation.createNavigateOnClickListener(R.id.next_action, bundle).onClick(it)
        }

        binding.btnMunich.setOnClickListener {
            val bundle = bundleOf("city_name" to binding.btnMunich.text)
            Navigation.createNavigateOnClickListener(R.id.next_action, bundle).onClick(it)
        }

        binding.btnSanPablo.setOnClickListener {
            val bundle = bundleOf("city_name" to binding.btnSanPablo.text)
            Navigation.createNavigateOnClickListener(R.id.next_action, bundle).onClick(it)
        }

        binding.btnYourLocation.setOnClickListener {
            val bundle = bundleOf("should_request_location" to true)
            Navigation.createNavigateOnClickListener(R.id.next_action, bundle).onClick(it)
        }
    }

    private fun checkLocationPermission() : Boolean {
        var permission = context?.let {
            ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (permission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST)
            permission = context?.let {
                ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION)
            }
            return permission == PackageManager.PERMISSION_GRANTED
        }
        return true;
    }
}
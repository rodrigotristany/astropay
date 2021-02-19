package com.rodrigotristany.astropay.presentation.detailview

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rodrigotristany.astropay.R
import com.rodrigotristany.astropay.core.extensions.showSnackbar
import com.rodrigotristany.astropay.databinding.FragmentDetailBinding
import com.rodrigotristany.astropay.data.models.Coord
import com.rodrigotristany.astropay.presentation.mainview.MainActivity
import javax.inject.Inject

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
        const val REQUEST_CODE = 1900
    }

    @Inject
    lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding
    private var city: String? = ""
    private var shouldRequestLocation: Boolean? = false
    private lateinit var coord: Coord
    private val adapter : DetailAdapter by lazy { DetailAdapter(arrayListOf()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        setRecyclerView()
        with(viewModel) {
            weatherModelList.observe(viewLifecycleOwner, { onWeatherListObserve(it) })
            error.observe(viewLifecycleOwner, { binding.message.text = "${it?.message}" })
        }
        city = arguments?.getString("city_name")
        city?.let { viewModel.weatherInfoByCity(it) }
        shouldRequestLocation = arguments?.getBoolean("should_request_location")
        if(shouldRequestLocation == true) requestLocationPermission()
    }

    private fun onWeatherListObserve(list: MutableList<DetailModel>?) {
        binding.message.text = ""
        fillView(list)
    }

    private fun fillView(list: MutableList<DetailModel>?) {
        list?.let {
            adapter.refreshData(list)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == REQUEST_CODE) {
            if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                binding.message.text = getString(R.string.loading_data)
                viewModel.weatherInfoByPosition()
            } else {
                binding.message.text = getString(R.string.location_not_available)
            }
            return
        }
    }

    private fun requestLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.weatherInfoByPosition()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) -> {
                requireView().showSnackbar(
                        getString(R.string.location_denied_message),
                        Snackbar.LENGTH_INDEFINITE,
                        getString(R.string.ok)) {
                    requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_CODE)
                }
            }
            else -> {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_CODE)
            }
        }
    }

    private fun setRecyclerView() {
        binding.rvWeatherDetail?.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                context, RecyclerView.VERTICAL, false)
        binding.rvWeatherDetail?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        binding.rvWeatherDetail?.adapter = adapter
    }
}
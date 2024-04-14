package com.example.android_task.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_task.databinding.FragmentFilterBinding
import com.example.android_task.localdata.FilterFlow
import com.example.android_task.model.simple.Filter
import com.example.android_task.ui.rv.filter.FilterAdapter
import com.example.android_task.ui.vm.FilmsViewModel
import com.example.android_task.utils.Global
import com.example.android_task.utils.parseAgeRating
import com.example.android_task.utils.parseYears
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [FilterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null

    private val viewModel: FilmsViewModel by activityViewModels()
    val countiresAdapter = FilterAdapter()
    val genresAdapter = FilterAdapter()
    private val binding get() = _binding!!
    val countriesList = mutableListOf<String>()
    val genresList = mutableListOf<String>()
    val TAG = "FilterFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val managerCountries = LinearLayoutManager(requireActivity())
        val managerGenres = LinearLayoutManager(requireActivity())

        binding.recyclerViewFilter.layoutManager = managerCountries
        binding.recyclerViewFilter.adapter = countiresAdapter
        countiresAdapter.data = Global.COUNTRIES//вызвать метод из апишки ту не забыть

        binding.recyclerViewFilterGenres.layoutManager = managerGenres
        binding.recyclerViewFilterGenres.adapter = genresAdapter
        genresAdapter.data = Global.GENRES

        countiresAdapter.onFilterClickListener = {
            if (!countriesList.contains(it)) {
                countriesList += it
            } else {
                countriesList.remove(it)
            }
        }

        genresAdapter.onFilterClickListener = {
            if (!genresList.contains(it)) {
                genresList += it
            } else {
                genresList.remove(it)
            }
        }

        binding.btFilters.setOnClickListener() {
            val ageRate = parseAgeRating(binding.etAgerating.text.toString())
            val years = parseYears(binding.etYear.text.toString())
            if ((ageRate != null) &&
                (years != null)
            ) {
                FilterFlow.sendData(
                    Filter(
                        years,
                        ageRate,
                        countriesList,
                        seekBarPosition(),
                        genresList
                    )
                )

                findNavController().navigate(
                    FilterFragmentDirections.actionFilterFragmentToFragmentAllFilms(
                    )
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    "Вы не соблюли форматирование(",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun seekBarPosition(): Boolean? {
        when (binding.seekBar.progress) {
            0 -> {
                return true
            }

            1 -> {
                return null
            }

            2 -> {
                return false
            }
        }

        return null
    }


}
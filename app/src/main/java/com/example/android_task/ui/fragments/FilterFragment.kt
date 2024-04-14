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
import com.example.android_task.model.simple.Filter
import com.example.android_task.localdata.FilterFlow
import com.example.android_task.ui.rv.FilterAdapter
import com.example.android_task.ui.vm.FilmsViewModel
import com.example.android_task.utils.Global
import com.example.android_task.utils.parseAgeRating
import com.example.android_task.utils.parseCountries
import com.example.android_task.utils.parseYears
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder

/**
 * A simple [Fragment] subclass.
 * Use the [FilterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null

    private val viewModel: FilmsViewModel by activityViewModels()
    private val binding get() = _binding!!
    val list = mutableListOf<String>()
    val TAG = "FilterFragment"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("lifecycle", "onViewCreated: debounce1 doneАШДЕУК")
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager = LinearLayoutManager(requireActivity()) // LayoutManager
        val adapter = FilterAdapter()  // LayoutManager
        // Создание объекта
        Log.d("lifecycle", "onViewCreated: debounce2 doneАШДЕУК")

        binding.recyclerViewFilter.layoutManager =
            manager
        binding.recyclerViewFilter.adapter = adapter
        adapter.data = Global.COUNTRIES//вызвать метод из апишки ту не забудь инде

        val str = StringBuilder()

        adapter.onFilterClickListener = {
            if (!list.contains(it)) {
                list += it
                if (str.isEmpty()) {
                    str.append(it)
                } else{
                    str.append(", $it")
                }
                binding.tvCountries.text = str
            }
        }

        binding.btFilters.setOnClickListener() {
            val ageRate = parseAgeRating(binding.etAgerating.text.toString())
            val years = parseYears(binding.etYear.text.toString())
            Log.d(
                TAG,
                "age: ${parseAgeRating(binding.etAgerating.text.toString())}   $years  ${ parseCountries(binding.tvCountries.text.toString())}"
            )

            if ((ageRate != null) &&
                (years != null)
            ) {

                FilterFlow.sendData(
                    Filter(
                        years,
                        ageRate,
                        parseCountries(binding.tvCountries.text.toString())
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


}
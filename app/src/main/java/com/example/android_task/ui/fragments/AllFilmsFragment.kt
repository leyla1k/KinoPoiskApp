package com.example.android_task.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.android_task.databinding.FragmentAllFilmsBinding
import com.example.android_task.localdata.FilterFlow
import com.example.android_task.localdata.HistoryManager
import com.example.android_task.textwatcher.DebouncedTextWatcher
import com.example.android_task.ui.rv.films.FilmAdapter
import com.example.android_task.ui.vm.FilmsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class AllFilmsFragment : Fragment() {

    val TAG = "AllFilmsFragment"

    private var _binding: FragmentAllFilmsBinding? = null
    private val binding get() = _binding!!
    val filmAdapter = FilmAdapter()
    private val viewModel: FilmsViewModel by activityViewModels()

    @Inject
    lateinit var histManager: HistoryManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filmAdapter.addLoadStateListener { loadState ->
            when (loadState.append) {
                is LoadState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is LoadState.Error -> {
                    // Показать сообщение об ошибке
                }

                is LoadState.NotLoading -> {
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        }

        binding.recyclerView.apply {
            adapter = filmAdapter
            setHasFixedSize(true)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.filmList.flowWithLifecycle(
                viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED
            ).collect { list ->
                filmAdapter.submitData(list)

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val a = FilterFlow.filterFlow.value
            if (a != null) {
                viewModel.getNewListWithFilters(a)
            }
        }

        val debouncedTextWatcher = DebouncedTextWatcher(1000) {
            if (binding.etSearch.text.toString() != "") {
                viewModel.getFilmsByName(binding.etSearch.text.toString())
                histManager.addToHistory(binding.etSearch.text.toString())
            }
        }

        binding.etSearch.addTextChangedListener(debouncedTextWatcher)
        registerForContextMenu(binding.etSearch)

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getFilms()
            binding.swipeRefresh.isRefreshing = false
        }

        binding.ibDeleteHist.setOnClickListener {
            histManager.clearHistory()
        }
        binding.imageButton.setOnClickListener {
            findNavController().navigate(
                AllFilmsFragmentDirections.actionFragmentAllFilmsToFilterFragment(
                )
            )
        }
        filmAdapter.onFilmClickListener = {
            findNavController().navigate(
                AllFilmsFragmentDirections.actionAllFilmsFragmentToFilmFragment(
                    it.id
                )
            )
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle("История поиска")
        val items = histManager.getHistory()
        for (item in items) {
            menu.add(item)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        binding.etSearch.setText(item.title)
        binding.etSearch.setSelection(binding.etSearch.text.length)
        return super.onContextItemSelected(item)
    }
}
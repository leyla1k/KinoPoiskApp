package com.example.android_task.ui.fragments

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.android_task.databinding.FragmentFilmBinding
import com.example.android_task.model.simple.Film
import com.example.android_task.model.simple.Poster
import com.example.android_task.ui.rv.carousel.CarouselRVAdapter
import com.example.android_task.ui.rv.review.ReviewAdapter
import com.example.android_task.ui.vm.FilmViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.abs

@AndroidEntryPoint
class FilmFragment : Fragment() {
    private var _binding: FragmentFilmBinding? = null

    private val binding get() = _binding!!
    private val filmIid by lazy { navArgs<FilmFragmentArgs>().value.filmIid }
    lateinit var thisFilm: Film
    private val viewModel: FilmViewModel by viewModels()
    val reviewAdapter = ReviewAdapter()
    var listOfPosters = mutableListOf<Poster>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        runBlocking {//флоу во вьюмодели и коллектить не успевала
            thisFilm = viewModel.getFilmById(id = filmIid)

        }
        runBlocking {
            listOfPosters = viewModel.getPosters().toMutableList()

        }
        _binding = FragmentFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            name.text = thisFilm.name
            description.text = thisFilm.description
            rate.text = thisFilm.rating.toString()
            recyclerView.apply {
                adapter = reviewAdapter
                setHasFixedSize(true)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.reviewList.flowWithLifecycle(
                viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED
            ).collect { list ->
                reviewAdapter.submitData(list)

            }
        }
        reviewAdapter.onReviewClickListener = {
            val review = it.review.toString()
            findNavController().navigate(
                FilmFragmentDirections.actionFilmFragmentToReviewDialogFragment(
                    review
                )
            )

        }

        binding.viewPager.apply {
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 3
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER
        }

        binding.viewPager.adapter = CarouselRVAdapter(
            listOfPosters.toMutableList()
        )

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        binding.viewPager.setPageTransformer(compositePageTransformer)
    }

}
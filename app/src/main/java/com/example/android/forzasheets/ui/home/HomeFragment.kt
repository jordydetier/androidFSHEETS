package com.example.android.forzasheets.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.android.forzasheets.MainActivity
import com.example.android.forzasheets.R
import com.example.android.forzasheets.databinding.FragmentHomeBinding

/**
 * Fragment which allows the user to choose between different leagues to look at
 *
 * This fragment allows the user select a league to view the standings of that league
 */
class HomeFragment : Fragment() {


    /**
     * Inflates the fragment's view, overridden from Fragment.java
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (this.activity as MainActivity).supportActionBar!!.title = "Home"
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        binding.premierLeague.setOnClickListener {
            this.findNavController().navigate(R.id.standingsFragmentPremierLeague)
        }
        binding.bundesliga.setOnClickListener {
            this.findNavController().navigate(R.id.standingsFragmentBundesliga)
        }
        binding.laLiga.setOnClickListener {
            this.findNavController().navigate(R.id.standingsFragmentLaLiga)
        }
        binding.serieA.setOnClickListener {
            this.findNavController().navigate(R.id.standingsFragmentSerieA)
        }
        binding.ligue1.setOnClickListener {
            this.findNavController().navigate(R.id.standingsFragmentLigue1)
        }
        binding.eredivisie.setOnClickListener {
            this.findNavController().navigate(R.id.standingsFragmentEredivisie)
        }


        return binding.root
    }

}
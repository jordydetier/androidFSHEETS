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


class HomeFragment : Fragment() {


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
package com.example.android.forzasheets.ui.standings


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.forzasheets.MainActivity
import com.example.android.forzasheets.R
import com.example.android.forzasheets.databinding.FragmentStandingsBinding
import com.example.android.forzasheets.models.Standings.Standings


class StandingsFragment : Fragment() {

    private lateinit var viewModel: StandingsViewModel
    private lateinit var adapter: StandingsAdapter
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var binding: FragmentStandingsBinding
    private var flag: Boolean = false
    private lateinit var leagueId: String
    private lateinit var leagueName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (this.activity as MainActivity).supportActionBar!!.title = "Standings"

        flag = false
        leagueId = StandingsFragmentArgs.fromBundle(requireArguments()).leagueId
        leagueName = StandingsFragmentArgs.fromBundle(requireArguments()).leagueName

        val viewModelFactory =
            StandingsViewModelFactory(requireNotNull(this.activity).application, leagueId)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(StandingsViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_standings, container, false
        )

        layoutManager = LinearLayoutManager(
            this.context,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.standingsList.layoutManager = layoutManager
        adapter = StandingsAdapter(mutableListOf(), StandingsAdapter.OnClickListener {
            if (null != it) {
                this.findNavController()
                    .navigate(
                        StandingsFragmentDirections.actionStandingsFragmentToTeamDetailFragment(
                            it
                        )
                    )
            }
        })

        binding.standingsList.adapter = adapter
        binding.leagueName.text = leagueName
        binding.progressBar.visibility = View.VISIBLE

        getStandings()

        return binding.root
    }

    private fun getStandings() {
        viewModel.standings.observe(this.viewLifecycleOwner, object : Observer<List<Standings>> {
            override fun onChanged(@Nullable standings: List<Standings>) {
                adapter.appendStandings(standings)
                flag = true
                binding.progressBar.visibility = View.GONE
            }
        })
    }

}
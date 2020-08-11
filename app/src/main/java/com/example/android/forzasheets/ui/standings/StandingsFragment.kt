package com.example.android.forzasheets.ui.standings


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.forzasheets.MainActivity
import com.example.android.forzasheets.R
import com.example.android.forzasheets.databinding.FragmentStandingsBinding

class StandingsFragment : Fragment() {

    /**
     * ViewModel which contains the business-logic
     */
    private lateinit var viewModel: StandingsViewModel

    /**
     * Holds a reference to the StandingsAdapter
     */
    private lateinit var adapter: StandingsAdapter

    /**
     * Holds a reference to the LinearLayoutManager
     */
    private lateinit var layoutManager: LinearLayoutManager

    /**
     * Binding object to access the fragment's layout
     */
    lateinit var binding: FragmentStandingsBinding

    /**
     * Boolean to check if a loading spinner should be shown or not
     */
    private var flag: Boolean = false

    /**
     * Id of the league to get the correct data from the API
     */
    private lateinit var leagueId: String

    /**
     * name of the league to display the correct title
     */
    private lateinit var leagueName: String

    /**
     * Gets called when the fragment gets created. If there is a [savedInstanceState], it will be restored
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
        (this.activity as MainActivity).supportActionBar!!.title = "Standings"

        flag = false
        leagueId = StandingsFragmentArgs.fromBundle(requireArguments()).leagueId
        leagueName = StandingsFragmentArgs.fromBundle(requireArguments()).leagueName

        val viewModelFactory =
            StandingsViewModelFactory(requireNotNull(this.activity).application, leagueId)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(StandingsViewModel::class.java)

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
            if (it != null) {
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
        binding.progressBarStandings.visibility = View.VISIBLE

        getStandings()

        return binding.root
    }

    /**
     * Retrieves a list of popular movies and appends those to the adapter
     *
     */
    private fun getStandings() {
        viewModel.standings.observe(this.viewLifecycleOwner,
            Observer { standings ->
                adapter.appendStandings(standings)
                flag = true
                binding.progressBarStandings.visibility = View.GONE
            })
    }

}
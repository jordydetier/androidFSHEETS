package com.example.android.forzasheets.ui.standings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.android.forzasheets.R
import com.example.android.forzasheets.models.standing.Standings


class StandingsAdapter(
    private var standings: MutableList<Standings>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder>() {

    override fun getItemCount() = standings.size

    override fun onBindViewHolder(holder: StandingsViewHolder, position: Int) {
        val item = standings[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item.id.toString())
        }
        holder.bind(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingsViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.standings_item, parent, false)
        return StandingsViewHolder(view)
    }

    fun appendStandings(standings: List<Standings>) {
        if (this.standings != standings) {
            this.standings.addAll(standings)
            notifyItemRangeInserted(
                this.standings.size,
                standings.size.minus(1)
            )
        }
    }

    inner class StandingsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val teamPosition: TextView = itemView.findViewById(R.id.team_position)
        private val teamCrest: ImageView = itemView.findViewById(R.id.team_crest)
        private val teamName: TextView = itemView.findViewById(R.id.team_name)
        private val teamPoints: TextView = itemView.findViewById(R.id.team_points)
        private val matchesLost: TextView = itemView.findViewById(R.id.matches_lost)
        private val matchesDrawn: TextView = itemView.findViewById(R.id.matches_drawn)
        private val matchesWon: TextView = itemView.findViewById(R.id.matches_won)
        private val matchesPlayed: TextView = itemView.findViewById(R.id.matches_played)
        private val goalsDiff: TextView = itemView.findViewById(R.id.goals_diff)
        fun bind(item: Standings) {
            Glide.with(itemView)
                .load(item.logo)
                .transform(CenterCrop())
                .into(teamCrest)
            teamPosition.text = item.rank.toString()
            teamName.text = item.name
            teamPoints.text = item.points.toString()
            matchesLost.text = item.matchesInfo.matchesLost.toString()
            matchesDrawn.text = item.matchesInfo.matchesDrawn.toString()
            matchesWon.text = item.matchesInfo.matchesWon.toString()
            matchesPlayed.text = item.matchesInfo.matchesPlayed.toString()
            goalsDiff.text = item.goalsDiff.toString()
        }
    }

    class OnClickListener(val clickListener: (teamId: String) -> Unit) {
        fun onClick(teamId: String) = clickListener(teamId)
    }
}
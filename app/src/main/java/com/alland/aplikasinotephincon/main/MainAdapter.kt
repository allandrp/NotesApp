package com.alland.aplikasinotephincon.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alland.aplikasinotephincon.Util
import com.alland.aplikasinotephincon.databinding.NoteItemBinding
import com.alland.aplikasinotephincon.room.NoteEntity

class MainAdapter(val onClick: (NoteEntity) -> (Unit)
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var listNote = ArrayList<NoteEntity>()

    fun setList(list: ArrayList<NoteEntity>) {
        listNote = list
        notifyDataSetChanged()
    }

    class MainViewHolder(
        private val binding: NoteItemBinding
    ) : ViewHolder(binding.root) {
        fun bind(note: NoteEntity) {
            binding.tvTitleNoteItem.text = note.title
            binding.tvContentNoteItem.text = note.content
            var formatTime = "dd MMM yyyy"
            if (Util.isSameYear(note.lastUpdated)) {
                formatTime = "dd MMM"
            }
            binding.tvDateNoteItem.text =
                Util.convertMillisToReadableTimeWithFormat(note.lastUpdated, formatTime)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listNote[position])
        holder.itemView.setOnClickListener{
            onClick(listNote[position])
        }
    }
}
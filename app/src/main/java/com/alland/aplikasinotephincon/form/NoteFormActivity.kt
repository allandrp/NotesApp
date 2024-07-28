package com.alland.aplikasinotephincon.form

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alland.aplikasinotephincon.R
import com.alland.aplikasinotephincon.Util
import com.alland.aplikasinotephincon.ViewModelFactory
import com.alland.aplikasinotephincon.databinding.ActivityNoteFormBinding
import com.alland.aplikasinotephincon.main.MainActivity
import com.alland.aplikasinotephincon.room.NoteEntity
import com.google.android.material.snackbar.Snackbar

class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private var note: NoteEntity? = null
    private val viewModel: NoteFormViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val type = intent.getStringExtra(MainActivity.ACTION_TYPE)

        val noteId = intent.getLongExtra(MainActivity.NOTE_ID, -1)

        if (noteId != -1L) {
            viewModel.getNoteById(noteId).observe(this) {
                if (it != null) {
                    binding.etTitleForm.setText(it.title)
                    binding.etContentForm.setText(it.content)
                    binding.tvDateForm.text =
                        Util.convertMillisToReadableTimeWithFormat(it.lastUpdated)
                    note = it
                }
            }
            binding.tvDateForm.visibility = View.VISIBLE
        } else {
            binding.btnDeleteForm.visibility = View.GONE
            binding.tvDateForm.visibility = View.GONE
        }

        binding.btnSaveForm.setOnClickListener {
            when (type) {
                MainActivity.NOTE_ADD -> {
                    val title = binding.etTitleForm.text.toString()
                    val content = binding.etContentForm.text.toString()
                    val createdDate = System.currentTimeMillis()

                    val newNote = NoteEntity(
                        title = title,
                        createdDate = createdDate,
                        lastUpdated = createdDate,
                        content = content
                    )

                    viewModel.insertNote(newNote)
                    Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show()
                    finish()
                }

                MainActivity.NOTE_EDIT -> {
                    val title = binding.etTitleForm.text.toString()
                    val content = binding.etContentForm.text.toString()
                    val currentDate = System.currentTimeMillis()
                    val note = intent.getParcelableExtra<NoteEntity>(MainActivity.NOTE_ITEM)

                    note?.title = title
                    note?.content = content
                    note?.lastUpdated = currentDate

                    if (note != null) {
                        viewModel.updateNote(note)
                    }

                    Toast.makeText(this, "Update success", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnDeleteForm.setOnClickListener {
            showDeleteDialogConfirmation()
        }

        toolbarSetup()
    }

    private fun showDeleteDialogConfirmation() {
        val dialog = AlertDialog.Builder(this).setTitle("Confirmation")
            .setMessage("Do you want to delete this note ?")
            .setPositiveButton("Delete") { dialog, which ->
                if (note != null) {
                    viewModel.deleteNote(note!!)
                    finish()
                }
            }.setNegativeButton("Close") { dialog, _ ->
                dialog.dismiss()
            }.create()

        dialog.show()
        dialog.getButton(DialogInterface.BUTTON_POSITIVE)
            .setTextColor(ContextCompat.getColor(this, R.color.colorError))

    }

    private fun toolbarSetup() {
        setSupportActionBar(binding.toolbarForm)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        supportActionBar?.setDisplayShowTitleEnabled(false);
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
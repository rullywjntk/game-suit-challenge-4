package com.rully.gamesuitchallenge.view.game

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.rully.gamesuitchallenge.R
import com.rully.gamesuitchallenge.databinding.ActivityPlayerBinding
import com.rully.gamesuitchallenge.databinding.DialogLayoutBinding
import com.rully.gamesuitchallenge.view.main.MainActivity

class PlayerActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPlayerBinding
    private val TAG = "tag"
    private var userSelection = Selection.ROCK
    private var player2Selection = Selection.ROCK
    private var userWinner = true
    private var draw = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvPlayer.text = intent.getStringExtra(ComActivity.EXTRA_NAME)
            Glide.with(this@PlayerActivity)
                .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
                .into(ivTitle)
        }
        binding.imgBtnPaper1.setOnClickListener(this)
        binding.imgBtnRock1.setOnClickListener(this)
        binding.imgBtnScissor1.setOnClickListener(this)
        binding.imgBtnRetry.setOnClickListener {
            bgTransparent()
            retry()
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.imgBtnPaper1 -> {
                player2Selection = Selection.PAPER
            }
            R.id.imgBtnRock1 -> {
                player2Selection = Selection.ROCK
            }
            R.id.imgBtnScissor1 -> {
                player2Selection = Selection.SCISSOR
            }
        }
        proceed()
    }

    private fun proceed() {
        binding.imgBtnPaper.setOnClickListener {
            userSelection = Selection.PAPER
        }
        binding.imgBtnRock.setOnClickListener {
            userSelection = Selection.ROCK
        }
        binding.imgBtnScissor.setOnClickListener {
            userSelection = Selection.SCISSOR
        }
        checkWinner()
        showWinner()
        bgSelector()
    }

    private fun checkWinner() {
        if (userSelection == player2Selection) {
            draw = true
        }
        if (userSelection == Selection.ROCK) {
            if (player2Selection == Selection.PAPER) {
                userWinner = false
            } else if (player2Selection == Selection.SCISSOR) {
                userWinner = true
            }
        }

        if (userSelection == Selection.PAPER) {
            if (player2Selection == Selection.SCISSOR) {
                userWinner = false
            } else if (player2Selection == Selection.ROCK) {
                userWinner = true
            }
        }

        if (userSelection == Selection.SCISSOR) {
            if (player2Selection == Selection.ROCK) {
                userWinner = false
            } else if (player2Selection == Selection.PAPER) {
                userWinner = true
            }
        }
        Log.d(TAG, "User select: $userSelection")
        Log.d(TAG, "Player 2 select: $player2Selection")
    }

    private fun showWinner() {
        val name = intent.getStringExtra(ComActivity.EXTRA_NAME)
        val dialogLayoutBinding = DialogLayoutBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .apply {
                setView(dialogLayoutBinding.root)
            }
            .create()
        if (draw) {
            dialogLayoutBinding.tvResult.text = getString(R.string.draw)
            Toast.makeText(this, "Player 2 select: $player2Selection", Toast.LENGTH_SHORT).show()
        } else {
            if (userWinner) {
                dialogLayoutBinding.tvName.text = name
                dialogLayoutBinding.tvResult.text = getString(R.string.win)
                Toast.makeText(this, "Player 2 select: $player2Selection", Toast.LENGTH_SHORT).show()
            } else {
                dialogLayoutBinding.tvResult.text = getString(R.string.player2_win)
                Toast.makeText(this, "Player 2 select: $player2Selection", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        dialogLayoutBinding.btnRetry.setOnClickListener {
            dialog.dismiss()
            bgTransparent()
            retry()
        }
        dialogLayoutBinding.btnMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(MainActivity.EXTRA_NAME, name)
            startActivity(intent)
        }
        dialog.show()
    }

    private fun retry() {
        userWinner = false
        draw = false
    }

    private fun bgSelector() {
        when (userSelection) {
            Selection.ROCK -> binding.imgBtnRock.setBackgroundResource(R.drawable.shape)
            Selection.SCISSOR -> binding.imgBtnScissor.setBackgroundResource(R.drawable.shape)
            Selection.PAPER -> binding.imgBtnPaper.setBackgroundResource(R.drawable.shape)
        }
        when (player2Selection) {
            Selection.ROCK -> binding.imgBtnRock1.setBackgroundResource(R.drawable.shape)
            Selection.SCISSOR -> binding.imgBtnScissor1.setBackgroundResource(R.drawable.shape)
            Selection.PAPER -> binding.imgBtnPaper1.setBackgroundResource(R.drawable.shape)

        }
    }


    private fun bgTransparent() {
        binding.imgBtnPaper.setBackgroundColor(Color.TRANSPARENT)
        binding.imgBtnRock.setBackgroundColor(Color.TRANSPARENT)
        binding.imgBtnScissor.setBackgroundColor(Color.TRANSPARENT)
        binding.imgBtnRock1.setBackgroundColor(Color.TRANSPARENT)
        binding.imgBtnScissor1.setBackgroundColor(Color.TRANSPARENT)
        binding.imgBtnPaper1.setBackgroundColor(Color.TRANSPARENT)
    }

    enum class Selection {
        ROCK, PAPER, SCISSOR
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

}


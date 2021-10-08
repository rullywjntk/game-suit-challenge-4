package com.rully.gamesuitchallenge.view.game

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rully.gamesuitchallenge.R
import com.rully.gamesuitchallenge.databinding.ActivityComBinding
import com.rully.gamesuitchallenge.databinding.DialogLayoutBinding
import com.rully.gamesuitchallenge.view.main.MainActivity
import kotlin.random.Random

class ComActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityComBinding
    private val TAG = "tag"
    private var userSelection = Selection.SCISSOR
    private var comSelection = Selection.ROCK
    private var userWinner = true
    private var draw = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            tvPlayer.text = intent.getStringExtra(EXTRA_NAME)
            Glide.with(this@ComActivity)
                .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
                .into(ivTitle)
        }

        binding.imgBtnPaper.setOnClickListener(this)
        binding.imgBtnRock.setOnClickListener(this)
        binding.imgBtnScissor.setOnClickListener(this)
        binding.imgBtnRetry.setOnClickListener {
            bgTransparent()
            retry()
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.imgBtnRock -> {
                p0.setBackgroundResource(R.drawable.shape)
                userSelection = Selection.ROCK
            }
            R.id.imgBtnPaper -> {
                p0.setBackgroundResource(R.drawable.shape)
                userSelection = Selection.PAPER
            }
            R.id.imgBtnScissor -> {
                p0.setBackgroundResource(R.drawable.shape)
                userSelection = Selection.SCISSOR
            }
        }
        proceed()
    }

    private fun retry() {
        userSelection = Selection.ROCK
        comSelection = Selection.ROCK
        binding.tvVs.text = getString(R.string.versus)

        userWinner = false
        draw = false
    }

    private fun proceed() {
        comSelection = getRandom()
        checkWinner()
        showWinner()
    }

    private fun checkWinner() {
        if (userSelection == comSelection) {
            draw = true
        }

        if (userSelection == Selection.ROCK) {
            if (comSelection == Selection.PAPER) {
                userWinner = false
            } else if (comSelection == Selection.SCISSOR) {
                userWinner = true
            }
        }

        if (userSelection == Selection.PAPER) {
            if (comSelection == Selection.SCISSOR) {
                userWinner = false
            } else if (comSelection == Selection.ROCK) {
                userWinner = true
            }
        }

        if (userSelection == Selection.SCISSOR) {
            if (comSelection == Selection.ROCK) {
                userWinner = false
            } else if (comSelection == Selection.PAPER) {
                userWinner = true
            }
        }
        Log.d(TAG, "User select: $userSelection")
        Log.d(TAG, "Computer select: $comSelection")
    }

    private fun showWinner() {
        val name = intent.getStringExtra(EXTRA_NAME)
        val dialogLayoutBinding = DialogLayoutBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .apply {
                setView(dialogLayoutBinding.root)
            }
            .create()
        if (draw) {
            dialogLayoutBinding.tvResult.text = getString(R.string.draw)
            Toast.makeText(this, getString(R.string.draw), Toast.LENGTH_SHORT).show()
        } else {
            if (userWinner) {
                dialogLayoutBinding.tvName.text = name
                dialogLayoutBinding.tvResult.text = getString(R.string.win)
                Toast.makeText(this, "$name Win", Toast.LENGTH_SHORT).show()
            } else {
                dialogLayoutBinding.tvResult.text = getString(R.string.com_win)
                Toast.makeText(this, getString(R.string.com_win), Toast.LENGTH_SHORT).show()
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

    private fun getRandom(): Selection {
        when (Random.nextInt(3)) {
            0 -> {
                binding.ivRock.setBackgroundResource(R.drawable.shape)
                return Selection.ROCK
            }
            1 -> {
                binding.ivPaper.setBackgroundResource(R.drawable.shape)
                return Selection.PAPER
            }
            2 -> {
                binding.ivScissor.setBackgroundResource(R.drawable.shape)
                return Selection.SCISSOR
            }
        }
        return Selection.ROCK
    }

    private fun bgTransparent() {
        binding.imgBtnPaper.setBackgroundColor(Color.TRANSPARENT)
        binding.imgBtnRock.setBackgroundColor(Color.TRANSPARENT)
        binding.imgBtnScissor.setBackgroundColor(Color.TRANSPARENT)
        binding.ivRock.setBackgroundColor(Color.TRANSPARENT)
        binding.ivScissor.setBackgroundColor(Color.TRANSPARENT)
        binding.ivPaper.setBackgroundColor(Color.TRANSPARENT)
    }

    enum class Selection {
        ROCK, PAPER, SCISSOR
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
    }
}

package com.rully.gamesuitchallenge

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.rully.gamesuitchallenge.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val TAG = "tag"
    private var userSelection = Selection.SCISSOR
    private var comSelection = Selection.ROCK
    private var userWinner = true
    private var draw = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val paper = binding.imgBtnPaper
        val rock = binding.imgBtnRock
        val scissor = binding.imgBtnScissor
        val retry = binding.imgBtnRetry
        val rockCom = binding.ivRock
        val paperCom = binding.ivPaper
        val scissorCom = binding.ivScissor

        paper.setOnClickListener(this)
        rock.setOnClickListener(this)
        scissor.setOnClickListener(this)
        retry.setOnClickListener {
            paper.setBackgroundColor(Color.TRANSPARENT)
            rock.setBackgroundColor(Color.TRANSPARENT)
            scissor.setBackgroundColor(Color.TRANSPARENT)
            rockCom.setBackgroundColor(Color.TRANSPARENT)
            scissorCom.setBackgroundColor(Color.TRANSPARENT)
            paperCom.setBackgroundColor(Color.TRANSPARENT)
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
        if (draw) {
            binding.tvVs.text = getString(R.string.draw)
        } else {
            if (userWinner) {
                binding.tvVs.text = getString(R.string.win)
            } else {
                binding.tvVs.text = getString(R.string.lose)
            }
        }
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

    enum class Selection {
        ROCK, PAPER, SCISSOR
    }
}
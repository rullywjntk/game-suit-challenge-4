package com.rully.gamesuitchallenge.view.landingpage

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroCustomLayoutFragment.Companion.newInstance
import com.rully.gamesuitchallenge.R

class LandingActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(newInstance(R.layout.intro_layout_1))
        addSlide(newInstance(R.layout.intro_layout_2))
        addSlide(newInstance(R.layout.intro_layout_3))
        setProgressIndicator()
        setNavBarColorRes(R.color.black)

//        addSlide(
//            AppIntroFragment.newInstance(
//            title = "Welcome...",
//            description = "This is the first Slide of the example",
//            imageDrawable = R.drawable.landing_page1,
//            backgroundColor = Color.WHITE,
//            titleColor = Color.BLACK,
//            descriptionColor = Color.BLACK
//        ))
//        addSlide(AppIntroFragment.newInstance(
//            "Welcome...",
//            "This is the last Slide of the example",
//            R.drawable.landing_page2,
//            Color.WHITE
//        ))
//
//        setIndicatorColor(Color.RED, Color.BLACK)
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        finish()
    }
}
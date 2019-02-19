package com.bh.memedb

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.text.Html
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class landing : AppCompatActivity() {

    private lateinit var mSlideViewPager: ViewPager
    private lateinit var mDotLayout: LinearLayout

    private lateinit var nextButton: Button
    private lateinit var prevButton: Button

    private var mDots = ArrayList<TextView>()

    private var curPage: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)


        mSlideViewPager = findViewById(R.id.slideViewPager)
        mDotLayout = findViewById(R.id.dotsLayout)
        nextButton = findViewById(R.id.nextButton)
        prevButton = findViewById(R.id.prevButton)

        mSlideViewPager.adapter = SliderAdapter(this)

        addDotsIndicator()
        changeDotsIndicator(0)

        mSlideViewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                changeDotsIndicator(position)
            }
        })

        nextButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View) {
                if(curPage==mDots.size-1)
                {
                    finish()
                }
                else
                {
                    mSlideViewPager.setCurrentItem(curPage+1)
                }
            }
        })

        prevButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View) {
                mSlideViewPager.setCurrentItem(curPage-1)
            }
        })
    }


    fun addDotsIndicator() {
        for(i in 0..2)
        {
            val textView = TextView(this)
            textView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            textView.gravity = Gravity.CENTER
            textView.text = Html.fromHtml("&#8226;")
            textView.textSize = 35f
            textView.setTextColor(resources.getColor(R.color.colorTransparentWhite))
            mDots.add(textView)
            mDotLayout.addView(textView)
        }
    }

    fun changeDotsIndicator(position: Int)
    {
        for(i in 0..2)
        {
            mDots[i].setTextColor(resources.getColor(R.color.colorTransparentWhite))
        }
        mDots[position].setTextColor(resources.getColor(R.color.colorWhite))
        curPage = position

        if(curPage==0)
        {
            nextButton.isEnabled = true
            prevButton.isEnabled = false
            prevButton.visibility = View.INVISIBLE
        }
        else if(curPage == mDots.size-1)
        {
            nextButton.setText(resources.getString(R.string.fin))
        }
        else
        {
            prevButton.isEnabled = true
            nextButton.isEnabled = true
            prevButton.visibility = View.VISIBLE
            nextButton.visibility = View.VISIBLE
            nextButton.setText(resources.getString(R.string.next))
        }
    }

}

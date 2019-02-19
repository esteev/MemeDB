package com.bh.memedb

import android.content.Context
import android.content.res.Resources
import android.graphics.ColorSpace
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class SliderAdapter(private val context: Context) : PagerAdapter() {

    public var slide_images = intArrayOf(R.drawable.eat_icon, R.drawable.sleep_icon, R.drawable.code_icon)
    public var slide_headings = arrayOf("1","2","3")
    public var slide_descs = arrayOf("12","23","31")

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.slide_layout, container, false) as ViewGroup
        container.addView(layout)

        slide_headings = context.resources.getStringArray(R.array.headings)
        slide_descs = context.resources.getStringArray(R.array.descriptions)

        val slideImageView: ImageView = layout.findViewById(R.id.slide_image)
        val slideHeading: TextView = layout.findViewById(R.id.slide_heading)
        val slideDescription: TextView = layout.findViewById(R.id.slide_desc)

        slideImageView.setImageResource(slide_images[position])
        slideHeading.setText(slide_headings[position])
        slideDescription.setText(slide_descs[position])

        return layout
    }
    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }
    override fun getCount(): Int {
       // Toast.makeText(context, "henlo", Toast.LENGTH_SHORT).show()
        return slide_headings.size
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
   /*
    override fun getPageTitle(position: Int): CharSequence {
        val customPagerEnum = ColorSpace.Model.values()[position]
        return context.getString(position)
    }
    */
}
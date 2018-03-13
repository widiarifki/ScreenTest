package com.widiarifki.screentest.presentation.mapview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.widiarifki.screentest.R

/**
 * Created by widiarifki on 26/02/2018.
 */

class ImageSlideFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val name = arguments.getString("NAME")
        val image = arguments.getInt("IMAGE")

        val view = inflater!!.inflate(R.layout.fragment_imageslide, container, false)

        val textView = view.findViewById(R.id.txtName) as TextView
        textView.text = name

        val imageView = view.findViewById(R.id.image) as ImageView
        imageView.setImageResource(image)

        return view
    }

    companion object {

        fun newInstance(name: String?, image: Int): ImageSlideFragment {
            val imageSlideFragment = ImageSlideFragment()

            val args = Bundle(1)
            args.putString("NAME", name)
            args.putInt("IMAGE", image)
            imageSlideFragment.arguments = args

            return imageSlideFragment
        }
    }

}

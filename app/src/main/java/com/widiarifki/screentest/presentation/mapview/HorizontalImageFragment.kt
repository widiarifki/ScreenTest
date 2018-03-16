package com.widiarifki.screentest.presentation.mapview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.widiarifki.screentest.R
import kotlinx.android.synthetic.main.fragment_imageslide.*

/**
 * Created by widiarifki on 26/02/2018.
 */

class HorizontalImageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_imageslide, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val name = arguments.getString("NAME")
        val image = arguments.getInt("IMAGE")

        txtName.text = name
        imgEvent.setImageResource(image)
    }

    companion object {
        fun newInstance(name: String?, image: Int): HorizontalImageFragment {
            val imageSlideFragment = HorizontalImageFragment()

            val args = Bundle(1)
            args.putString("NAME", name)
            args.putInt("IMAGE", image)
            imageSlideFragment.arguments = args

            return imageSlideFragment
        }
    }

}

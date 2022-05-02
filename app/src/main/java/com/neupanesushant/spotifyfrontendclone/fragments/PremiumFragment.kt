package com.neupanesushant.spotifyfrontendclone.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.bold
import androidx.core.text.set
import androidx.recyclerview.widget.LinearLayoutManager
import com.neupanesushant.spotifyfrontendclone.R
import com.neupanesushant.spotifyfrontendclone.adapters.WhyPremiumAdapter
import com.neupanesushant.spotifyfrontendclone.databinding.FragmentPremiumBinding
import com.neupanesushant.spotifyfrontendclone.databinding.WhyPremiumRvCardBinding
import java.net.URI


class PremiumFragment : Fragment() {

    private var _binding: FragmentPremiumBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPremiumBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvPremiumImageFreeContentString = "Get 3 months of Premium for free"
        binding.tvPremiumImageFreeContent.text = tvPremiumImageFreeContentString


        setupBtnGetFree(binding.btnGetFree)
        bindingTermsAndConditionToTextView(binding.tvTerms, termsAndConditionDescription())

        binding.rvWhyPremiumList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val whyPremiumAdapter =
            WhyPremiumAdapter(requireContext(), whyPremiumDescriptionArrayList())
        binding.rvWhyPremiumList.adapter = whyPremiumAdapter

        setupBtnGetFree(binding.btnGetFree2)
        bindingTermsAndConditionToTextView(binding.tvTerms2, termsAndConditionDescription())


    }

    fun setupBtnGetFree(button: Button) {
        val btnGetFreeText = "Get 3 months free"
        button.apply {
            text = btnGetFreeText
            isAllCaps = true
            setOnClickListener {
                val intent: Intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://www.spotify.com/"))
                startActivity(intent)
            }
        }

    }

    fun termsAndConditionDescription(): SpannableStringBuilder {
        val firstString = "Individual plan only. USD 2.99/month after. "
        val mainString = "Terms and conditions apply. "
        val secondString =
            "Open only to users who haven't already tried Premium. Offer ends 5/12/22."
        val str = SpannableStringBuilder()
            .append(firstString)
            .bold { append(mainString) }
            .append(secondString)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(context, "Redirecting...", Toast.LENGTH_SHORT).show()
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.spotify.com/us/legal/end-user-agreement/")
                )
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.setColor(Color.WHITE)
                ds.isUnderlineText = false
            }
        }
        str.setSpan(clickableSpan, 43, 71, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return str
    }

    fun bindingTermsAndConditionToTextView(textview: TextView, str: SpannableStringBuilder) {
        textview.movementMethod = LinkMovementMethod()
        textview.text = str
    }

    fun whyPremiumDescriptionArrayList(): ArrayList<String> {
        val arrayList = arrayListOf<String>()
        arrayList.apply {
            add("Download to listen offline without wifi")
            add("Music without ad interruptions")
            add("2x higher sound quiality than our free plan")
            add("Play songs in any order, with unlimited skips")
            add("Cancel monthly plans online anytime")
        }
        return arrayList
    }

    fun setCurrentPlan(str: String) {
        binding.tvCurrentPlan.text = str
    }


}

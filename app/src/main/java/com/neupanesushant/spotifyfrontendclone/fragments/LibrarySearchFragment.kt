package com.neupanesushant.spotifyfrontendclone.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.neupanesushant.spotifyfrontendclone.MainActivity
import com.neupanesushant.spotifyfrontendclone.R
import com.neupanesushant.spotifyfrontendclone.adapters.LibraryContentAdapter
import com.neupanesushant.spotifyfrontendclone.data.DataLibraryContent
import com.neupanesushant.spotifyfrontendclone.dataLibraryContentList
import com.neupanesushant.spotifyfrontendclone.databinding.FragmentLibrarySearchFragmentBinding


class LibrarySearchFragment : Fragment() {

    private var _binding: FragmentLibrarySearchFragmentBinding? = null
    private val binding get() = _binding!!
    val foundSearchItemslist = ArrayList<DataLibraryContent>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLibrarySearchFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackImageClickListener()
        setupSearchInputStart()
        searchInputTextListner()
    }

    fun setBackImageClickListener() {
        binding.ivBackButton.setOnClickListener {
            val fragmentTransaction = parentFragmentManager
            fragmentTransaction.popBackStack()

        }
    }

    fun searchInputTextListner() {
        var infoText: String
        var invalidTextInput: String
        var descText: String
        var isRecyclerAnimationOnce = false
        binding.etSearchInput.addTextChangedListener {
            binding.apply {
                tvTextInfo.visibility = View.VISIBLE
                tvInputTextInvalid.visibility = View.VISIBLE
                tvSearchInputDescription.visibility = View.VISIBLE
                rvFoundLibraryContents.visibility = View.GONE
            }
            foundSearchItemslist.clear()

            if (it == null || it.length == 0) {
                infoText = "Find your favorites"
                binding.tvTextInfo.text = infoText
                binding.tvInputTextInvalid.visibility = View.GONE
                descText = "Search everything you've liked, followed, or created."
                binding.tvSearchInputDescription.text = descText
                isRecyclerAnimationOnce = false
            } else {
                dataLibraryContentList.forEach {

                    val listOfTitleWords = it.title.split(" ")
                    val listOfCreatorWords = it.artistName.get(0).split(" ")
                    if (isStringInLibrary(
                            listOfTitleWords,
                            target = binding.etSearchInput.text.toString()
                        )
                        || isStringInLibrary(
                            listOfCreatorWords,
                            binding.etSearchInput.text.toString()
                        )
                        || isStringInLibrary(
                            listOf(it.title),
                            target = binding.etSearchInput.text.toString()
                        )
                        || isStringInLibrary(
                            it.artistName.toList(),
                            binding.etSearchInput.text.toString()
                        )
                    ) {
                        foundSearchItemslist.add(it)
                    }
                }

                if (foundSearchItemslist.size == 0) {
                    infoText = "Couldn't find"
                    binding.tvTextInfo.text = infoText
                    binding.tvInputTextInvalid.visibility = View.VISIBLE
                    invalidTextInput = " \"${binding.etSearchInput.text}\" "
                    binding.tvInputTextInvalid.text = invalidTextInput
                    descText = "Try searching again using a different spelling or keyword."
                    binding.tvSearchInputDescription.text = descText
                    isRecyclerAnimationOnce = false
                }else{
                    binding.apply {
                        tvTextInfo.visibility = View.GONE
                        tvInputTextInvalid.visibility = View.GONE
                        tvSearchInputDescription.visibility = View.GONE
                    }
                    if(!isRecyclerAnimationOnce){
                        binding.rvFoundLibraryContents.startAnimation(AnimationUtils.loadAnimation(requireContext(), androidx.transition.R.anim.abc_grow_fade_in_from_bottom))
                        isRecyclerAnimationOnce = true
                    }
                    binding.rvFoundLibraryContents.visibility = View.VISIBLE
                    val adapter = LibraryContentAdapter(true, foundSearchItemslist)
                    binding.rvFoundLibraryContents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    binding.rvFoundLibraryContents.adapter = adapter

                }
            }
        }
    }

    fun isStringInLibrary(list: List<String>, target: String): Boolean {
        val lengthOfTarget = target.length
        for (i in list) {
            try {
                if (i.substring(0, lengthOfTarget).equals(target, true)) {
                    return true
                }
            } catch (e: StringIndexOutOfBoundsException) {
                continue
            }

        }
        return false
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    fun setupSearchInputStart() {
        binding.etSearchInput.requestFocus()
        val imm: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.etSearchInput, 0)
        binding.etSearchInput.setTextCursorDrawable(0)
    }
}
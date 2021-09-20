package com.userposts.testapplication.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.userposts.testapplication.R
import com.userposts.testapplication.databinding.LayoutErrorBottomsheetBinding

class ErrorBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: LayoutErrorBottomsheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.layout_error_bottomsheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = LayoutErrorBottomsheetBinding.bind(view)

        binding.bottomSheetRetryButton.setOnClickListener {
            childFragmentManager.setFragmentResult(BOTTOM_SHEET_KEY, bundleOf())
            dismiss()
        }

        binding.bottomSheetCancelButton.setOnClickListener { dismiss() }
    }

    fun show(fragmentManager: FragmentManager) {
        fragmentManager.findFragmentByTag(BOTTOM_SHEET_TAG)?.let {
            fragmentManager
                .beginTransaction()
                .remove(it)
                .commit()
        }
        show(fragmentManager, BOTTOM_SHEET_TAG)
    }

    companion object {
        private const val BOTTOM_SHEET_TAG = "BOTTOM_SHEET"
        const val BOTTOM_SHEET_KEY = "BOTTOM_SHEET_KEY"
    }
}
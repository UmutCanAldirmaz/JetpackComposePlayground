/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetpackcomposeplayground.samples.juicetracker.views_with_compose

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.R.layout
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.jetpackcomposeplayground.databinding.FragmentEntryDialogBinding
import com.example.jetpackcomposeplayground.samples.juicetracker.data.JuiceColor
import com.example.jetpackcomposeplayground.samples.juicetracker.ui.AppViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class EntryDialogFragment : BottomSheetDialogFragment() {

    private val entryViewModel by viewModels<EntryViewModel> { AppViewModelProvider.Factory }
    private var selectedColor: JuiceColor = JuiceColor.Red

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEntryDialogBinding.inflate(inflater, container, false).root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val colorLabelMap = JuiceColor.entries.associateBy { getString(it.label) }
        val binding = FragmentEntryDialogBinding.bind(view)
        val juiceId = arguments?.getLong("itemId") ?: 0L

        if (juiceId > 0) {
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    entryViewModel.getJuiceStream(juiceId).filterNotNull().collect { item ->
                        with(binding) {
                            name.setText(item.name)
                            description.setText(item.description)
                            ratingBar.rating = item.rating.toFloat()
                            colorSpinner.setSelection(findColorIndex(item.color))
                        }
                    }
                }
            }
        }

        binding.name.doOnTextChanged { text, _, _, _ ->
            binding.saveButton.isEnabled = !text.isNullOrBlank()
        }

        binding.colorSpinner.adapter = ArrayAdapter(
            requireContext(),
            layout.support_simple_spinner_dropdown_item,
            colorLabelMap.keys.toList()
        )

        binding.colorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                val selected = parent.getItemAtPosition(pos).toString()
                selectedColor = colorLabelMap[selected] ?: selectedColor
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedColor = JuiceColor.Red
            }
        }

        binding.saveButton.setOnClickListener {
            entryViewModel.saveJuice(
                juiceId,
                binding.name.text.toString(),
                binding.description.text.toString(),
                selectedColor.name,
                binding.ratingBar.rating.toInt()
            )
            dismiss()
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }
    }

    private fun findColorIndex(color: String): Int {
        val juiceColor = JuiceColor.valueOf(color)
        return JuiceColor.entries.indexOf(juiceColor)
    }
}

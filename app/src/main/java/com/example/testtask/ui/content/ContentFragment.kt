package com.example.testtask.ui.content

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtask.R
import com.example.testtask.data.response.Project
import com.example.testtask.ui.base.BaseFragment
import com.example.testtask.ui.login.ProgectListAdapter
import kotlinx.android.synthetic.main.layout_content_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContentFragment : BaseFragment<ContentViewModel>() {

    override val layoutId: Int = R.layout.layout_content_fragment
    override val mViewModel: ContentViewModel by viewModel()
    private val args by lazy(mode = LazyThreadSafetyMode.NONE) { navArgs<ContentFragmentArgs>().value.appInit }

    override fun initialization(view: View, savedInstanceState: Bundle?) {
        initProjectsList()
    }

    private fun initProjectsList() {
        with(project_list_rv) {
            val lm = LinearLayoutManager(context)
            layoutManager = lm
            addItemDecoration(DividerItemDecoration(context, lm.orientation).apply {
                ResourcesCompat.getDrawable(resources, R.drawable.decorator, resources.newTheme())?.let {
                    setDrawable(it)
                }
            })
            adapter = ProgectListAdapter(arrayListOf()) {
                showEditNameDialog(it)
            }

            mViewModel.projects.observe(viewLifecycleOwner) {
                (adapter as? ProgectListAdapter)?.update(it)
            }
        }
    }

    private fun showEditNameDialog(project: Project) {
        val dialogInput = EditText(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            setText(project.name)
        }

        AlertDialog.Builder(requireActivity())
            .setMessage(R.string.change_proj_name)
            .setView(dialogInput)
            .setPositiveButton(R.string.save) { dialog, _ ->
                val txt = dialogInput.text.toString()
                if (txt.isNotBlank()) {
                    mViewModel.updateName(project.id, txt, doOnError = { Unit }, doOnSuccess = {
                        dialog.dismiss()
                    })
                } else {
                    Toast.makeText(context, R.string.not_empty, Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel") { _, _ -> }
            .create()
            .show()

    }
}
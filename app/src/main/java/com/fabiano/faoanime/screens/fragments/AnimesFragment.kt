package com.fabiano.faoanime.screens.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.fabiano.faoanime.R
import com.fabiano.faoanime.databinding.FragmentAnimesBinding
import com.fabiano.faoanime.utils.ViewAnimation
import com.fabiano.faoanime.utils.extensions.onTextChanged
import com.fabiano.faoanime.viewModels.AnimesViewModel
import kotlinx.android.synthetic.main.fragment_animes.*

class AnimesFragment : BaseDrawerFragment(), Toolbar.OnMenuItemClickListener {

    lateinit var animesViewModel: AnimesViewModel
    lateinit var fragmentHomeBinding: FragmentAnimesBinding
    val animation = ViewAnimation()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_animes, container, false)
        initViewModel()
        initToolbar()
        initNavigation()

        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInputText()
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        animesViewModel.toolbarTitle = getString(R.string.animes)
        fragmentHomeBinding.toolbarInclude.viewModel = animesViewModel
        fragmentHomeBinding.toolbarInclude.toolbar.inflateMenu(R.menu.toolbar_menu)
        fragmentHomeBinding.toolbarInclude.toolbar.setOnMenuItemClickListener(this)
    }

    private fun initInputText() {
        editTextSearch.onTextChanged(animesViewModel.textchanger)
    }

    private fun initNavigation() {
        navController = view?.findNavController()
    }

    private fun initViewModel() {
        animesViewModel = ViewModelProvider(this).get(AnimesViewModel::class.java)
        animesViewModel.drawerInterface = this
        fragmentHomeBinding.viewModel = animesViewModel
        fragmentHomeBinding.navigationInclude.viewModel = animesViewModel
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.action_search) {
            setSearch(item)
            return true
        }
        return false
    }

    private fun setSearch(item: MenuItem) {
        if (animation.inAnimation) return
        val isGone = constraintSearch.isGone
        item.icon = getIcon(isGone)
        animateSearch(isGone)
    }

    private fun getIcon(isGone: Boolean): Drawable? =
        if (isGone) activity?.getDrawable(R.drawable.ic_close) else activity?.getDrawable(R.drawable.ic_search)

    private fun animateSearch(isGone: Boolean) =
        if (isGone) animation.fadeIn(constraintSearch) else animation.fadeOut(constraintSearch)
}

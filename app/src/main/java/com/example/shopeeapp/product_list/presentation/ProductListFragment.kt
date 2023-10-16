package com.example.shopeeapp.product_list.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ProductListFragment : Fragment() {
    private val adapter = ProductCardListAdapter(
        ::onItemClicked
    )

    private fun onItemClicked(viewState: ProductCardViewState) {
        findNavController().navigate(ProductListFragmentDirections.ac)
    }
}
package com.example.shopeeapp.ui.base

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.shopeeapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.lang.ref.WeakReference
import kotlin.coroutines.CoroutineContext

abstract class SimpleAdapter<T, VH : SimpleAdapter.ViewHolder, VB : ViewDataBinding>(protected val context: Context) :
    RecyclerView.Adapter<VH>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    lateinit var dataBinding: VB

    private var data = ArrayList<T>()

    private var originData = ArrayList<T>()

    private lateinit var onItemClicked: (position: Int) -> Unit

    private lateinit var onItemClick: (item: T) -> Unit

    private var weakRecyclerView: WeakReference<RecyclerView>? = null

    private val recyclerView get() = weakRecyclerView?.get()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        dataBinding = createViewBinding(parent)
        return getViewHolder(viewType, dataBinding)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        weakRecyclerView = WeakReference(recyclerView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (getRegisterOnItemClickListener()) {
            registerOnItemClickListener(holder)
        }
        holder.bindViews(position)
    }

    open fun getRegisterOnItemClickListener() = true

    private fun registerOnItemClickListener(holder: ViewHolder) {
        holder.itemView.setOnClickListener {
            if (::onItemClicked.isInitialized) {
                onItemClicked.invoke(holder.adapterPosition)
            }
        }
    }

    fun setOnItemClickListener(onItemClicked: (position: Int) -> Unit) {
        this.onItemClicked = onItemClicked
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<T>, updateData: Boolean) {
        if (updateData) {
            originData.clear()
            originData.addAll(list)
        }
        data.clear()
        data.addAll(list)
        runLayoutAnimation(recyclerView)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitListWithoutAnim(list: List<T>, updateData: Boolean) {
        if (updateData) {
            originData.clear()
            originData.addAll(list)
        }
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun invokeOnItemClicked(position: Int) {
        if (::onItemClicked.isInitialized) {
            onItemClick.invoke(getPositionItem(position))
        }
    }

    private fun getPositionItem(position: Int) = data[position]

    private fun runLayoutAnimation(recyclerView: RecyclerView?) {
        if (recyclerView != null) {
            val controller = AnimationUtils.loadLayoutAnimation(
                recyclerView.context,
                R.anim.layout_animation_fall_down
            )
            recyclerView.layoutAnimation = controller
            recyclerView.scheduleLayoutAnimation()
        }
    }

    abstract fun getViewHolder(viewType: Int, dataBinding: VB): VH

    abstract fun createViewBinding(parent: ViewGroup): VB

    abstract class ViewHolder(itemView: ViewDataBinding) : RecyclerView.ViewHolder(itemView.root) {
        abstract fun bindViews(position: Int)
    }
}
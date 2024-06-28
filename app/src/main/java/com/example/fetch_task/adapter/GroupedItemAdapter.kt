package com.example.fetch_task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch_task.R
import com.example.fetch_task.model.Item

class GroupedItemAdapter(private val groupedItems: Map<Int, List<Item>>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_HEADER = 0
    private val VIEW_TYPE_ITEM = 1

    private val dataList: MutableList<Pair<Int?, Item?>> = mutableListOf()
    private val expandedGroup: MutableSet<Int> = mutableSetOf()


    init {
        createDataList()
    }

    private fun createDataList() {
        dataList.clear()
        for ((listId, items) in groupedItems) {
            dataList.add(Pair(listId, null)) // Header
            if (expandedGroup.contains( listId)) {
                for (item in items) {
                    dataList.add(Pair(null, item)) // Item
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position].second == null) VIEW_TYPE_HEADER else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
            ItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.bind(dataList[position].first!!)
        } else if (holder is ItemViewHolder) {
            holder.bind(dataList[position].second!!)
        }
    }

    override fun getItemCount() = dataList.size

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val headerTextView: TextView = itemView.findViewById(R.id.headerTextView)
        private val expandIcon: ImageView = itemView.findViewById(R.id.expandIcon)

        private var listId: Int = 0

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(listId: Int) {
            this.listId = listId
            headerTextView.text = "List ID: $listId"
            expandIcon.setImageResource(if (expandedGroup.contains(listId)) R.drawable.ic_expand_more else R.drawable.ic_expand_less)

        }

        override fun onClick(v: View?) {
            val imageView: ImageView = itemView.findViewById(R.id.expandIcon)
            if (expandedGroup.contains(listId)) {
                expandedGroup.remove(listId)

            } else {
                expandedGroup.add(listId)
            }
            createDataList()
            notifyDataSetChanged()
              createDataList()
            notifyDataSetChanged()
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemTextView: TextView = itemView.findViewById(R.id.itemTextView)
        private val itemTextView2: TextView = itemView.findViewById(R.id.itemTextView2)


        fun bind(item: Item?) {
            val id = item?.id
            val name = item?.name
            itemTextView.text = "Id: $id"
            itemTextView2.text = "Name: $name"

        }
    }
}
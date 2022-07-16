package com.example.affirmations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.R
import com.example.affirmations.model.Affirmation

// will adapt a data objet to be displayed to the layout by the means of a View item (of type ViewHolder)
// <GOOGLE DOC> Adapter for the [RecyclerView] in [MainActivity]. Displays [Affirmation] data object.
class ItemAdapter(
    private val context: Context,
    private val dataset: List<Affirmation>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    // nested class that will only be used here as the View item (of type ViewHolder)
    // <GOOGLE DOC> Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
    }

    // check the parent layout to be able to attach the item to it as a child
    // <GOOGLE DOC> Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    // bind the view item to its parent layout, to replace the contents of a list item view
    // <GOOGLE DOC> Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        // we need to update the views referenced by this ViewHolder to reflect the correct data for this item we just targeted
        holder.textView.text = context.resources.getString(item.stringResourceId)
    }

    // <GOOGLE DOC> Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int = dataset.size
}
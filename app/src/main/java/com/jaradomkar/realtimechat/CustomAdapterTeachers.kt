import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jaradomkar.realtimechat.R
import com.jaradomkar.realtimechat.model.students

class CustomAdapterTeachers(context: Context, private val items: ArrayList<students>) : RecyclerView.Adapter<CustomAdapterTeachers.ViewHolder>() {

    private lateinit var mListener : onItemClickListener
    //creating an interface for onClickListener


    interface onItemClickListener{

        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design_student, parent, false)

        return ViewHolder(view,mListener)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position)

        holder.rollNumber.text = item.rollNumber
        holder.name.text = item.name

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View,listener: onItemClickListener) : RecyclerView.ViewHolder(ItemView) {
        val rollNumber: TextView = itemView.findViewById(R.id.student_rollNumber)
        val name: TextView = itemView.findViewById(R.id.student_name)


        init {
            itemView.setOnClickListener{

                listener.onItemClick(adapterPosition)
            }
        }


    }


}

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jaradomkar.realtimechat.R
import com.jaradomkar.realtimechat.model.getTeachers

class CustomAdapter(val context:Context, val items: ArrayList<getTeachers>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position)

        holder._id.text = item._id
        holder.name.text = item.name
        holder.email.text = item.email
        holder.phone.text = item.phone
        holder.department.text = item.department

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val _id: TextView = itemView.findViewById(R.id.teacher_id)
        val name: TextView = itemView.findViewById(R.id.teacher_name)
        val email: TextView = itemView.findViewById(R.id.teacher_email)
        val phone: TextView = itemView.findViewById(R.id.teacher_phone)
        val department: TextView = itemView.findViewById(R.id.teacher_department)

    }
}

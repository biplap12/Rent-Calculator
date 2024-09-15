package www.biplapneupane.com.np.rentcalculator.SavedDataActivity;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import www.biplapneupane.com.np.rentcalculator.R;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.RentViewHolder> {

    private List<RentRecord> rentRecordList;

    public DataAdapter(List<RentRecord> rentRecordList) {
        this.rentRecordList = rentRecordList;
    }

    @NonNull
    @Override
    public RentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rent_record, parent, false);
        return new RentViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RentViewHolder holder, int position) {
        RentRecord record = rentRecordList.get(position);
        if (record!=null){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = dateFormat.format(new Date(record.getPaymentDate()));
        holder.textViewDate.setText(formattedDate);
        holder.textViewElectricity.setText(record.getElectricityStart() + "---" + record.getElectricityEnd());
        int unitsConsumed = (int) (record.getElectricityEnd() - record.getElectricityStart());
        holder.textViewUnit.setText(unitsConsumed + " units");        holder.textViewWaterBill.setText("Rs. " + record.getWaterBill());
        holder.textViewDustCharge.setText("Rs. " + record.getDustCharge());
        holder.textViewRoomRent.setText("Rs. " + record.getRoomRent());
    }else {
            holder.textViewRoomRent.setText("No Data Found");
        }    }

    @Override
    public int getItemCount() {
        return rentRecordList != null ? rentRecordList.size() : 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<RentRecord> newRecords) {
        this.rentRecordList = newRecords;
        notifyDataSetChanged();
    }

    public static class RentViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDate, textViewElectricity, textViewUnit, textViewWaterBill, textViewDustCharge, textViewRoomRent;

        public RentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewElectricity = itemView.findViewById(R.id.textViewElectricity);
            textViewUnit = itemView.findViewById(R.id.textViewUnit);
            textViewWaterBill = itemView.findViewById(R.id.textViewWaterBill);
            textViewDustCharge = itemView.findViewById(R.id.textViewDustCharge);
            textViewRoomRent = itemView.findViewById(R.id.textViewRoomRent);
        }
    }
}

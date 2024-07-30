package com.example.recyclerviewexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//ViewHolder 클래스 정의
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private ArrayList<MainData> arrayList;
    // 생성자에서 데이터 리스트를 받아옴
    //Alt+insert
    public MainAdapter(ArrayList<MainData> arrayList) {
        this.arrayList = arrayList;
    }

    // ViewHolder 를 생성하고 레이아웃을 설정
    //처음 생성될때의 생명주기를 뜻한다
    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    // 데이터 리스트의 각 항목을 ViewHolder에 바인딩
    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, int position) { // 실제로 item이 화면에 추가될 때의 동작
        holder.iv_profile.setImageResource(arrayList.get(position).getIv_profile());
        holder.tv_name.setText(arrayList.get(position).getTv_name());
        holder.tv_content.setText(arrayList.get(position).getTv_content());
        // 태그 지정
        holder.itemView.setTag(position);
        // 아이쳄 View 가 짧게 클릭 됐을 때 아이템명을 띄움
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String curName = holder.tv_name.getText().toString(); // 클릭한 item 의 이름을 가져옴
                Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show(); // activity 가 아니라서 view 로부터 가져옴
            }
        });

        //아이템 View가 롱클릭 됐을 때 item 제거
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                remove(holder.getAbsoluteAdapterPosition());

                return true;
            }
        });
    }
    // 전체 데이터 갯수 리턴
    @Override
    public int getItemCount() {
        // activity_main 화면에서 추가 버튼을 누르면
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position) {
        try {
            arrayList.remove(position);  // 삭제
            notifyItemRemoved(position); // 새로고침

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_profile;
        protected TextView tv_name;
        protected TextView tv_content;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = (ImageView) itemView.findViewById(R.id.iv_profile);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}

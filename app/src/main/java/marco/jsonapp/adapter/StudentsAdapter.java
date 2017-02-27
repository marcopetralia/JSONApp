package marco.jsonapp.adapter;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import marco.jsonapp.R;
import marco.jsonapp.model.Student;

/**
 * Created by marco on 27/02/2017.
 */

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>{

    private ArrayList<Student> dataSet=new ArrayList<>();

    @Override
    public StudentsAdapter.StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        StudentViewHolder holder = new StudentViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(StudentsAdapter.StudentViewHolder holder, int position) {
        Student currentStudent = dataSet.get(position);
        holder.studentNameTv.setText(currentStudent.getName());
        holder.studentEmail.setText(currentStudent.getEmail());
        //holder.studentID.setText(String.valueOf(currentStudent.getCorso().getID())); setText vuole una stringa ma il valore è un int, allora faccio la conversione
        holder.studentCourse.setText(currentStudent.getCorso().getNome());


    }
    public void setDataSet(ArrayList<Student> students) {
        dataSet = students;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView studentNameTv;
        public TextView studentEmail;
        public TextView studentCourse;
        public ImageButton studenGithub;

        public StudentViewHolder(final View v) {
            super(v);
            studentNameTv= (TextView) v.findViewById(R.id.student_name);
            studentEmail = (TextView) v.findViewById(R.id.student_email);
            studentCourse = (TextView) v.findViewById(R.id.student_name_course);
            studenGithub = (ImageButton) v.findViewById(R.id.student_github);

            studenGithub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //inseriamo un listener all'icona che ci collega col sito

                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(dataSet.get(getAdapterPosition()).getGithub()));//ritorna @marcopetralia
                    itemView.getContext().startActivity(i);


                }
            });
        }
    }
}

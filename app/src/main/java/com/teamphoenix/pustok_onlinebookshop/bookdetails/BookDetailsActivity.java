package com.teamphoenix.pustok_onlinebookshop.bookdetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.teamphoenix.pustok_onlinebookshop.R;

public class BookDetailsActivity extends AppCompatActivity {
    RecyclerView relatedBookRecycle;
    String[] bookNameList={"স্বর্ণভূমি","রাজতন্ত্র","আলাদিন জিন্দাবাদ","মকবরা","হিজলতলি","মধ্যবিত্ত"};
    int[] bookImgList ={R.drawable.book,R.drawable.book1,R.drawable.book2,R.drawable.book3,R.drawable.book4,R.drawable.book5};
    int[] bookReviewList={10,4,31,34,23,48};
    int[] bookRatingList={3,2,4,3,2,5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        relatedBookRecycle=findViewById(R.id.relatedBookRecycle);

        BookDetailAdapter adapter = new BookDetailAdapter(this,bookNameList,bookImgList,bookReviewList,bookRatingList);
        relatedBookRecycle.setAdapter(adapter);
        relatedBookRecycle.setLayoutManager(new GridLayoutManager(this,3));
    }
}
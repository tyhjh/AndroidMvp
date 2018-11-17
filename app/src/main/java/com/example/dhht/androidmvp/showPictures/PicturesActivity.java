package com.example.dhht.androidmvp.showPictures;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dhht.androidmvp.R;
import com.example.dhht.androidmvp.data.Picture;
import com.example.recyclelibrary.CommonAdapter;
import com.example.recyclelibrary.CommonViewHolder;

import java.util.ArrayList;
import java.util.List;

import permison.PermissonUtil;
import toast.ToastUtil;

public class PicturesActivity extends AppCompatActivity implements PicturesContract.View {

    RecyclerView rvPictures;
    CommonAdapter<Picture> mPictureAdapter;
    PicturesContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new PicturePresenter(this);
        mPresenter.getLocalPic();
        PermissonUtil.checkPermission(PicturesActivity.this, null, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});
        initRcycleView();
        findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addPic();
            }
        });
    }

    /**
     * 初始化recyclerView
     */
    private void initRcycleView() {
        rvPictures = findViewById(R.id.rv_pictures);
        mPictureAdapter = new CommonAdapter<Picture>(this, new ArrayList<Picture>(0), R.layout.item_picture) {
            @Override
            public void onBindView(final CommonViewHolder commonViewHolder, final Picture picture) {
                ImageView imageView = commonViewHolder.getView(R.id.iv_pic);
                Glide.with(PicturesActivity.this).load(picture.getLocalPath()).into(imageView);
                imageView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        mPresenter.deletePic(picture);
                        deletePic(commonViewHolder.getAdapterPosition());
                        return true;
                    }
                });
            }
        };
        rvPictures.setLayoutManager(new GridLayoutManager(PicturesActivity.this, 4));
        rvPictures.setAdapter(mPictureAdapter);
    }

    //展示，刷新图片
    @Override
    public void showPic(List<Picture> pictureList) {
        mPictureAdapter.replaceData(pictureList);
    }

    //删除图片
    @Override
    public void deletePic(int position) {
        mPictureAdapter.removeData(position);
    }

    //添加图片
    @Override
    public void addPic(Picture picture) {
        mPictureAdapter.insertData(0, picture);
    }

}

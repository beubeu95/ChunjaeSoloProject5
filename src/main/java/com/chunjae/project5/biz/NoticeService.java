package com.chunjae.project5.biz;

import com.chunjae.project5.entity.Notice;
import com.chunjae.project5.entity.User;
import com.chunjae.project5.persis.NoticeMapper;
import com.chunjae.project5.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public List<Notice> getList(Page page) { return noticeMapper.getList(page); }
    public Notice getNotice(int no) { return noticeMapper.getNotice(no); }
    public void noticeInsert(Notice param) { noticeMapper.noticeInsert(param); }
    public void noticeUpdate(Notice param) { noticeMapper.noticeUpdate(param); }
    public void noticeDelete(int no) { noticeMapper.noticeDelete(no); }
    public int getCount(Page page) { return noticeMapper.getCount(page); }
}

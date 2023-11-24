package com.chunjae.project5.biz;

import com.chunjae.project5.entity.Faq;
import com.chunjae.project5.persis.FaqMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqService {

    @Autowired
    private FaqMapper faqMapper;

    public List<Faq> getList () { return faqMapper.getList(); }
}

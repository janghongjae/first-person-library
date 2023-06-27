package com.reading.lib.domain.bestbook.service;

import com.reading.lib.domain.bestbook.dto.BestBookDto;
import com.reading.lib.domain.bestbook.mapper.BestBookMapper;
import com.reading.lib.domain.comment.dto.CommentCountDto;
import com.reading.lib.domain.comment.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


@Service
public class BestBookServiceImpl implements BestBookService{

    private final CommentMapper commentMapper;
    private final BestBookMapper bestBookMapper;

    public BestBookServiceImpl(CommentMapper commentMapper, BestBookMapper bestBookMapper) {
        this.commentMapper = commentMapper;
        this.bestBookMapper = bestBookMapper;
    }

    @Override
    public void updateMonthlyBestBooks() {
        bestBookMapper.deleteBestBooks();

        List<CommentCountDto> commentCounts = commentMapper.calculateTotalCommentCount();
        commentCounts.sort(Comparator.comparingInt(CommentCountDto::getCommentCount).reversed());

        int ranking = 1;
        for (CommentCountDto dto : commentCounts) {
            if (ranking > 10) {
                break;
            }

            bestBookMapper.insertBestBook(ranking, dto.getBookId(), dto.getCommentCount());
            ranking++;
        }
    }

    @Override
    public List<BestBookDto> getBestBooks() {

        return bestBookMapper.getBestBooks();
    }
}

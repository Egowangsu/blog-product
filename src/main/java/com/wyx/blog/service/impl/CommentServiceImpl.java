package com.wyx.blog.service.impl;

import com.wyx.blog.dao.CommentDao;
import com.wyx.blog.domain.Comment;
import com.wyx.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;
    @Override
    public List<Comment> listCommentsByBlogId(Integer blogId) {
        List<Comment> commentsList=commentDao.listCommentsByBlogId(blogId);  //拿到所有顶级节点，就是没有父节点的
       return eachComment(commentsList);
    }

    @Override
    public int saveComment(Comment comment) {
        Integer parentCommentId=comment.getParentCommentId();
        if(parentCommentId!=-1){     //得到父类的id不是-1，说明他有父类
        comment.setParentComment(commentDao.getCommentById(parentCommentId)); //查询到父类对象然后赋值给自己的父类
        }else{
           comment.setParentComment(null);  //说明没有对象
        }
        return commentDao.saveComment(comment);
    }

    /**
     * 循环每个顶级的评论节点
     * @param comments
     * @return
     */
    //存放迭代找出的所有子代的集合
    private List<Comment> sonCommend= new ArrayList<>();
    private List<Comment> topCommend = new ArrayList<>();

    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            List<Comment> list=c.getReplyComment();
            for(Comment c2:list){
                if(c2.getId()==null){
                  c.setReplyComment(new ArrayList());
                }
            }
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中

        return combineChildren(commentsView);
    }

        public List<Comment> combineChildren(List<Comment> commentsView){ //获得第一层的子节点
            topCommend=new ArrayList<>();
            for (Comment c:commentsView){
                List<Comment> list=c.getReplyComment();  //判断他是否有子节点，有就把子节点的集合传到下一层循环中
                if(list.size()>0){
                    List<Comment> list2=combineChildren2(list);
                    c.setReplyComment(list2);
                }
                topCommend.add(c);
                sonCommend=new ArrayList<Comment>(); //将上个存的数据清空
            }
            Collections.reverse(topCommend);
            return topCommend;
        }



        public List<Comment> combineChildren2(List<Comment> list){
            for(Comment c:list){   //判断二级节点以及子节点有有没有子类
                //到这里，把对象装进集合里
                sonCommend.add(c);
                List<Comment> sonList=commentDao.getSonCommentByParentId(c.getId());
                if(sonList.size()>0){
                    combineChildren2(sonList);  //只要有子集，就继续找，找到底
                }
            }
            //Collections.reverse(sonCommend);  //将集合倒叙
            return sonCommend;
        }



    @Override
    public Comment getCommentById(Integer parentCommentId) {
        return commentDao.getCommentById(parentCommentId);
    }

    @Override
    public Comment getParentComment(Integer parentCommentId) {
        return commentDao.getParentComment(parentCommentId) ;
    }
}

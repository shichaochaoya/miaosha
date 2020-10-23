package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.SequenceDo;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public interface SequenceDoMapper {
    int deleteByPrimaryKey(String name);

    int insert(SequenceDo record);

    int insertSelective(SequenceDo record);

    SequenceDo selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(SequenceDo record);

    int updateByPrimaryKey(SequenceDo record);

    SequenceDo getSequenceByName(String name);
}
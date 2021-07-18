package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.dto.toy.CreateOrUpdateToyDTO;
import io.github.talelin.latticy.model.ToyDO;

public interface ToyService {
    IPage<ToyDO> selectPageByKeyWord(Integer page, Integer count, String keyWord);

    ToyDO selectById(Integer id);

    boolean createToy(CreateOrUpdateToyDTO validator);

    boolean updateToy(ToyDO toy, CreateOrUpdateToyDTO validator);

    boolean deleteById(Integer id);

}

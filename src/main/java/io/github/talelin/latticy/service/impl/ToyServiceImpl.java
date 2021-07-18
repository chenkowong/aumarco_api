package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.dto.toy.CreateOrUpdateToyDTO;
import io.github.talelin.latticy.mapper.ToyMapper;
import io.github.talelin.latticy.model.ToyDO;
import io.github.talelin.latticy.model.ToySortDO;
import io.github.talelin.latticy.model.ToyUserDO;
import io.github.talelin.latticy.service.ToyService;
import io.github.talelin.latticy.service.ToySortService;
import io.github.talelin.latticy.service.ToyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ToyServiceImpl extends ServiceImpl<ToyMapper, ToyDO> implements ToyService {

    @Autowired
    private ToyMapper toyMapper;

    @Autowired
    private ToySortService toySortService;

    @Autowired
    private ToyUserService toyUserService;

    @Override
    public IPage<ToyDO> selectPageByKeyWord(Integer page, Integer count, String keyWord) {
        Page<ToyDO> paper = new Page<>(page, count);
        IPage<ToyDO> iPage = toyMapper.selectPageByKeyWord(paper, keyWord);
        return iPage;
    }

    @Override
    public ToyDO selectById(Integer id) {
        ToyDO toy = toyMapper.selectById(id);
        return toy;
    }

    @Override
    public boolean createToy(CreateOrUpdateToyDTO validator) {
        ToyDO toy = new ToyDO();
        toy.setToyNo('T' + validator.getUserId() + String.valueOf(new Date().getTime()));
        toy.setToyTitle(validator.getToyTitle());
        toy.setToyCover(validator.getToyCover());
        toy.setToyUrls(validator.getToyUrls());
        toy.setToyContent(validator.getToyContent());
        save(toy);
        // 关联user和sort
        ToySortDO toySort = new ToySortDO();
        toySort.setToyId(toy.getId());
        toySort.setSortId(validator.getSortId());
        toySortService.dispatchToySort(toySort);
        ToyUserDO toyUser = new ToyUserDO();
        toyUser.setToyId(toy.getId());
        toyUser.setUserId(validator.getUserId());
        toyUserService.dispatchToyUser(toyUser);
        return true;
    }

    @Override
    public boolean updateToy(ToyDO toy, CreateOrUpdateToyDTO validator) {
        toy.setToyCover(validator.getToyCover());
        toy.setToyUrls(validator.getToyUrls());
        toy.setToyContent(validator.getToyContent());
        return toyMapper.updateById(toy) > 0;
    }

    @Override
    public boolean deleteById(Integer id) { return toyMapper.deleteById(id) > 0; }
}

package personal.zhou.travelshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import personal.zhou.travelshare.dao.StorageMapper;
import personal.zhou.travelshare.domain.dto.StorageExample;
import personal.zhou.travelshare.domain.entity.StorageEntity;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StorageService {
    @Autowired
    private StorageMapper storageMapper;

    public void deleteByKey(String key) {
        StorageExample example = new StorageExample();
        example.or().andKeyEqualTo(key);
        storageMapper.logicalDeleteByExample(example);
    }

    public void add(StorageEntity storageInfo) {
        storageInfo.setAddTime(LocalDateTime.now());
        storageInfo.setUpdateTime(LocalDateTime.now());
        storageMapper.insertSelective(storageInfo);
    }

    public StorageEntity findByKey(String key) {
        StorageExample example = new StorageExample();
        example.or().andKeyEqualTo(key).andDeletedEqualTo(false);
        return storageMapper.selectOneByExample(example);
    }

    public int update(StorageEntity storageInfo) {
        storageInfo.setUpdateTime(LocalDateTime.now());
        return storageMapper.updateByPrimaryKeySelective(storageInfo);
    }

    public StorageEntity findById(Integer id) {
        return storageMapper.selectByPrimaryKey(id);
    }

    public List<StorageEntity> querySelective(String key, String name, Integer page, Integer limit, String sort, String order) {
        StorageExample example = new StorageExample();
        StorageExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(key)) {
            criteria.andKeyEqualTo(key);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        return storageMapper.selectByExample(example);
    }
}

package net.fnsco.bigdata.service.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.bigdata.service.sys.dao.SequenceDAO;
import net.fnsco.bigdata.service.sys.entity.SequenceDO;
import net.fnsco.core.base.BaseService;

@Service
public class SequenceService extends BaseService {

    private Logger      logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SequenceDAO sequenceDAO;

    // 获取订单后6位
    @Transactional
    public int getOrderSequence(String tableName) {
        int sequenceId = sequenceDAO.getOrderSequence();
        if (sequenceId > 999998) {
            SequenceDO sequence = sequenceDAO.getTradeOrder();
            sequence.setCurrentValue(100000);
            sequence.setNextValue(100001);
            sequenceDAO.update(sequence);
        }
        return sequenceId;
    }
}
package com.uetty.mulds.service;

import com.uetty.mulds.entity.FrontLog;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(rollbackFor = Throwable.class)
@Service
public class MuldbService {

    @Autowired
    SQLManager sqlManager;

    public List<FrontLog> getLatestFrontLog() {

        return sqlManager.lambdaQuery(FrontLog.class)
                .desc(FrontLog::getCreatedTime)
                .limit(1, 10)
                .select();
    }
}

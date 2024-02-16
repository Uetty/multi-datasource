package com.uetty.mulds.ctr;

import com.uetty.mulds.base.RespBody;
import com.uetty.mulds.db.DatasourceSwitcher;
import com.uetty.mulds.entity.FrontLog;
import com.uetty.mulds.service.MuldbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author vince
 */
@Slf4j
@RestController
@RequestMapping("/muldb")
public class MuldbController {

    @Autowired
    private MuldbService muldbService;

    @Autowired
    private DatasourceSwitcher datasourceSwitcher;

    @RequestMapping(value = "getLatestFrontLog")
    public RespBody<List<FrontLog>> getLatestFrontLog(@RequestParam(value = "source", required = false) String source) {

        if (source != null && source.trim().length() > 0) {
            log.info("switch datasource: " + source);
            datasourceSwitcher.switchDataSource(source);
        }
        List<FrontLog> frontLogs = muldbService.getLatestFrontLog();

        return RespBody.success(frontLogs);
    }

}

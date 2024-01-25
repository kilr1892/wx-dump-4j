package com.xcs.wx.service.impl;

import cn.hutool.core.lang.Opt;
import com.xcs.wx.domain.vo.SessionVO;
import com.xcs.wx.repository.SessionRepository;
import com.xcs.wx.service.SessionService;
import com.xcs.wx.util.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 会话服务实现类
 *
 * @author xcs
 * @date 2023年12月21日 17时17分
 **/
@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Override
    public List<SessionVO> querySession() {
        // 分页查询会话信息
        return Opt.ofNullable(sessionRepository.querySession())
                // 处理日期
                .ifPresent(sessionVos -> sessionVos.forEach(sessionVo -> sessionVo.setShortTime(DateFormatUtil.formatTimestamp(sessionVo.getTime()))))
                // 默认值
                .orElse(Collections.emptyList());
    }
}

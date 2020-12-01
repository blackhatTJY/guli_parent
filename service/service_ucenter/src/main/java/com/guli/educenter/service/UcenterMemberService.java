package com.guli.educenter.service;

import com.guli.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-18
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    void register(RegisterVo registerVo);

    String login(UcenterMember member);
}

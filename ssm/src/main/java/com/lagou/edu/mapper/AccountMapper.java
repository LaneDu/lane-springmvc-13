package com.lagou.edu.mapper;

import com.lagou.edu.pojo.Account;

import java.util.List;

/**
 * @author lane
 * @date 2021年04月07日 下午3:09
 */
public interface AccountMapper {
    /**
     * 查询所有账户信息
     * @author lane
     * @date 2021/4/7 下午3:11
     * @return java.util.List<com.lagou.edu.pojo.Account>
     */
    public List<Account> queryAccountList() throws Exception;

}

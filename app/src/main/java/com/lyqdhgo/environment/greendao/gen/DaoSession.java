package com.lyqdhgo.environment.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.lyqdhgo.environment.entity.EmimsMonitorResult;
import com.lyqdhgo.environment.entity.User;

import com.lyqdhgo.environment.greendao.gen.EmimsMonitorResultDao;
import com.lyqdhgo.environment.greendao.gen.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig emimsMonitorResultDaoConfig;
    private final DaoConfig userDaoConfig;

    private final EmimsMonitorResultDao emimsMonitorResultDao;
    private final UserDao userDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        emimsMonitorResultDaoConfig = daoConfigMap.get(EmimsMonitorResultDao.class).clone();
        emimsMonitorResultDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        emimsMonitorResultDao = new EmimsMonitorResultDao(emimsMonitorResultDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);

        registerDao(EmimsMonitorResult.class, emimsMonitorResultDao);
        registerDao(User.class, userDao);
    }
    
    public void clear() {
        emimsMonitorResultDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
    }

    public EmimsMonitorResultDao getEmimsMonitorResultDao() {
        return emimsMonitorResultDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

}
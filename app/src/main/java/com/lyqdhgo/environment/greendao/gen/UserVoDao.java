package com.lyqdhgo.environment.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lyqdhgo.environment.entity.UserVo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_VO".
*/
public class UserVoDao extends AbstractDao<UserVo, Void> {

    public static final String TABLENAME = "USER_VO";

    /**
     * Properties of entity UserVo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", false, "ID");
        public final static Property LoginName = new Property(1, String.class, "loginName", false, "LOGIN_NAME");
        public final static Property Password = new Property(2, String.class, "password", false, "PASSWORD");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
        public final static Property Email = new Property(4, String.class, "email", false, "EMAIL");
        public final static Property Phone = new Property(5, String.class, "phone", false, "PHONE");
        public final static Property Mobile = new Property(6, String.class, "mobile", false, "MOBILE");
    }


    public UserVoDao(DaoConfig config) {
        super(config);
    }
    
    public UserVoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_VO\" (" + //
                "\"ID\" TEXT," + // 0: id
                "\"LOGIN_NAME\" TEXT," + // 1: loginName
                "\"PASSWORD\" TEXT," + // 2: password
                "\"NAME\" TEXT," + // 3: name
                "\"EMAIL\" TEXT," + // 4: email
                "\"PHONE\" TEXT," + // 5: phone
                "\"MOBILE\" TEXT);"); // 6: mobile
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_VO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserVo entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String loginName = entity.getLoginName();
        if (loginName != null) {
            stmt.bindString(2, loginName);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(3, password);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(5, email);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(6, phone);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(7, mobile);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserVo entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String loginName = entity.getLoginName();
        if (loginName != null) {
            stmt.bindString(2, loginName);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(3, password);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(5, email);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(6, phone);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(7, mobile);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public UserVo readEntity(Cursor cursor, int offset) {
        UserVo entity = new UserVo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // loginName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // password
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // email
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // phone
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // mobile
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserVo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setLoginName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPassword(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEmail(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPhone(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setMobile(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(UserVo entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(UserVo entity) {
        return null;
    }

    @Override
    public boolean hasKey(UserVo entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

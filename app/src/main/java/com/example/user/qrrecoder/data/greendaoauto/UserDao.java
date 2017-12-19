package com.example.user.qrrecoder.data.greendaoauto;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.user.qrrecoder.data.greendao.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Userid = new Property(1, int.class, "userid", false, "USERID");
        public final static Property Acount = new Property(2, String.class, "acount", false, "ACOUNT");
        public final static Property Username = new Property(3, String.class, "username", false, "USERNAME");
        public final static Property Fname = new Property(4, String.class, "fname", false, "FNAME");
        public final static Property Userpwd = new Property(5, String.class, "userpwd", false, "USERPWD");
        public final static Property Email = new Property(6, String.class, "email", false, "EMAIL");
        public final static Property Sessionid = new Property(7, int.class, "sessionid", false, "SESSIONID");
    }


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"USERID\" INTEGER NOT NULL ," + // 1: userid
                "\"ACOUNT\" TEXT," + // 2: acount
                "\"USERNAME\" TEXT," + // 3: username
                "\"FNAME\" TEXT," + // 4: fname
                "\"USERPWD\" TEXT," + // 5: userpwd
                "\"EMAIL\" TEXT," + // 6: email
                "\"SESSIONID\" INTEGER NOT NULL );"); // 7: sessionid
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_USER_USERID ON \"USER\"" +
                " (\"USERID\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getUserid());
 
        String acount = entity.getAcount();
        if (acount != null) {
            stmt.bindString(3, acount);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(4, username);
        }
 
        String fname = entity.getFname();
        if (fname != null) {
            stmt.bindString(5, fname);
        }
 
        String userpwd = entity.getUserpwd();
        if (userpwd != null) {
            stmt.bindString(6, userpwd);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(7, email);
        }
        stmt.bindLong(8, entity.getSessionid());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getUserid());
 
        String acount = entity.getAcount();
        if (acount != null) {
            stmt.bindString(3, acount);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(4, username);
        }
 
        String fname = entity.getFname();
        if (fname != null) {
            stmt.bindString(5, fname);
        }
 
        String userpwd = entity.getUserpwd();
        if (userpwd != null) {
            stmt.bindString(6, userpwd);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(7, email);
        }
        stmt.bindLong(8, entity.getSessionid());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // userid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // acount
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // username
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // fname
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // userpwd
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // email
            cursor.getInt(offset + 7) // sessionid
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserid(cursor.getInt(offset + 1));
        entity.setAcount(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUsername(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setFname(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUserpwd(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setEmail(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setSessionid(cursor.getInt(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
